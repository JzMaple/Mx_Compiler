package Nasm;


import IR.IR;
import IR.IRInstruction.*;
import IR.IRInstruction.Operand.Immediate;
import IR.IRInstruction.Operand.Memory;
import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;
import IR.IRNode.IRFunction;
import IR.IRNode.IRScope;
import IR.IRBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Translator {
    private List<IRFunction> functions = new ArrayList<>();
    private List<IRInstruction> instructions;
    private IRScope global_scope;
    private List<IRInstruction> global_init;
    private Map<Variable, String> global_string;
    private Set<String> inFunction = new HashSet<>();
    private List<String> code = new LinkedList<>();
    private IRFunction main;
    private StackAllocator current_stackAlloc;
    private RegAllocator regAllocator = new RegAllocator();
    private InlineFunction inlineFunction;
    private IRBuilder irBuilder;
    private ConstFolder folder = new ConstFolder();
    private Map<IRFunction, Variable> functionRemember;

    public Translator(IRBuilder IR_Builder) {
        this.irBuilder = IR_Builder;
        Map<String, IRFunction> functions = IR_Builder.getFunctions();
        for (String func_name : functions.keySet()) {
            if (func_name.equals("main"))
                main = functions.get(func_name);
            else
                this.functions.add(functions.get(func_name));
        }
        functions = IR_Builder.getInFunctions();
        for (String func_name : functions.keySet()) {
            this.functions.add(functions.get(func_name));
            this.inFunction.add(func_name);
        }
        this.global_scope = IR_Builder.getGlobalScope();
        this.global_init = IR_Builder.getGlobalStatements();
        this.global_string = IR_Builder.getConst_string();
        this.functionRemember = IR_Builder.getFunctionRemember();
    }

    private String address(Operand op) {
        if (op instanceof Variable){
            if (((Variable) op).isGlobal()) {
                if (((Variable) op).getIsConstant())
                    return ((Variable) op).getName();
                else
                    return "qword [" + ((Variable) op).getName() + "]";
            } else {
                if (((Variable) op).getReg() != null)
                    return ((Variable) op).getReg().toString();
                else {
                    int offset = current_stackAlloc.getOffset((Variable) op);
                    String offset_str = offset < 0 ? Integer.toString(offset) : "+" + Integer.toString(offset);
                    return "qword [" + RegX86.rbp + offset_str + "]";
                }
            }
        } else if (op instanceof Memory) {
            int scale = ((Memory) op).getScale();
            int number = ((Memory) op).getNumber();
            String number_str;
            if (number == 0)
                number_str = "";
            else
                number_str = number < 0 ? Integer.toString(number) : "+" + Integer.toString(number);

            Operand base = ((Memory) op).getBase();
            Operand index = ((Memory) op).getIndex();
            RegX86 reg_base = getReg(base, RegX86.rbx);
            RegX86 reg_index = getReg(index, RegX86.rdx);
            if (index == null) return "qword[" + reg_base + number_str + "]";
            else return "qword[" + reg_base + "+" + reg_index + "*" + scale + number_str + "]";

        } else if (op instanceof Immediate) {
            return op.toString();
        }
        return "";
    }

    private void load(RegX86 reg, Operand op) {
        if (!(op instanceof Variable)) {
            code.add("\tmov \t" + reg + ", " + address(op));
        } else {
            String str_reg = reg.toString();
            String str_op = address(op);
            if (!str_reg.equals(str_op))
                code.add("\tmov \t" + str_reg + ", " + str_op);
        }
    }

    private void store(Operand op, RegX86 reg) {
        if (op instanceof Memory)
            code.add("\tmov \t" + address(op) + ", " + reg);
        else if (op instanceof Variable) {
            String str_op = address(op);
            String str_reg = reg.toString();
            if (!str_reg.equals(str_op))
                code.add("\tmov \t" + str_op + ", " + str_reg);
        }
    }

    private RegX86 getReg(Operand op, RegX86 reg) {
        if (op == null) return null;
        if (op instanceof Variable && ((Variable) op).getReg() != null)
            return ((Variable) op).getReg();
        else {
            load(reg, op);
            return reg;
        }
    }

    private void callerSavePush() {
        code.add("\tpush\trsi");
        code.add("\tpush\trdi");
        code.add("\tpush\tr8");
        code.add("\tpush\tr9");
        code.add("\tpush\tr10");
        code.add("\tpush\tr11");
    }

    private void callerSavePop() {
        code.add("\tpop \tr11");
        code.add("\tpop \tr10");
        code.add("\tpop \tr9");
        code.add("\tpop \tr8");
        code.add("\tpop \trdi");
        code.add("\tpop \trsi");
    }

    private void addIns(Add add) {
        Operand lhs = add.getLhs();
        Operand rhs = add.getRhs();
        Variable dest = add.getDest();
        RegX86 reg_dest = dest.getReg();
        if (lhs instanceof Variable && ((Variable) lhs).getReg() == reg_dest && reg_dest != null) {
            code.add("\tadd \t" + reg_dest + ", " + address(rhs));
        } else {
            load(RegX86.rcx, lhs);
            code.add("\tadd \t" + RegX86.rcx + ", " + address(rhs));
            if (reg_dest != null) {
                if (reg_dest != RegX86.rcx)
                    code.add("\tmov \t" + reg_dest + ", " + RegX86.rcx);
            } else
                store(dest, RegX86.rcx);
        }
    }

    private void addIns(And and) {
        Operand lhs = and.getLhs();
        Operand rhs = and.getRhs();
        Variable dest = and.getDest();
        RegX86 reg_dest = dest.getReg();
        if (lhs instanceof Variable && ((Variable) lhs).getReg() == reg_dest && reg_dest != null) {
            code.add("\tand \t" + reg_dest + ", " + address(rhs));
        } else {
            load(RegX86.rcx, lhs);
            code.add("\tand \t" + RegX86.rcx + ", " + address(rhs));
            if (reg_dest != null) {
                if (reg_dest != RegX86.rcx)
                    code.add("\tmov \t" + reg_dest + ", " + RegX86.rcx);
            } else
                store(dest, RegX86.rcx);
        }
    }

    private void addIns(Call call) {
        callerSavePush();
        IRFunction function = call.getFunction();
        List<Operand> parameters = call.getParameters().getParameters();
        int len = parameters.size();
        if (len <= 6) {
            for (int i = 0; i < len; ++i)
                load(RegX86.getParameter(i), parameters.get(i));
        } else {
            for (int i = len-1; i >= 6; --i) {
                load(RegX86.rcx, parameters.get(i));
                code.add("\tpush\trcx");
            }
            for (int i = 0; i < 6; ++i)
                load(RegX86.getParameter(i), parameters.get(i));
        }
        if (inFunction.contains(function.getFunction_name()))
            code.add("\txor \trax, rax");
        code.add("\tcall\t" + function.getBeginLabel().getName());
        if (len > 6) {
            for (int i = len-1; i >= 6; --i) {
                code.add("\tpop \trcx");
            }
        }
        callerSavePop();
        if (call.getTmp_return() != null)
            store(call.getTmp_return(), RegX86.rax);
    }

    private void addIns(CJump cJump) {
        Cmp cmp = cJump.getCond();
        Operand cond = cJump.getCondition();
        Label true_label = cJump.getTrue_label();
        Label false_label = cJump.getFalse_label();
        if (cmp == null) {
            if (cond instanceof Variable && ((Variable) cond).getReg() != null)
                code.add("\tcmp \t" + ((Variable) cond).getReg() + ", 0");
            else {
                load(RegX86.rcx, cond);
                code.add("\tcmp \trcx, 0");
            }
            code.add("\tjz  \t" + false_label.getName());
            int index = instructions.indexOf(cJump);
            IRInstruction next = instructions.get(index + 1);
            if (next != true_label)
                code.add("\tjmp \t" + true_label.getName());
        } else {
            Operand lhs = cmp.getLhs();
            Operand rhs = cmp.getRhs();
            String op = cmp.getOp();
            if (lhs.getIsString() && rhs.getIsString()) {
                callerSavePush();
                load(RegX86.rdi, lhs);
                load(RegX86.rsi, rhs);
                code.add("\tcall\tstrcmp");
                callerSavePop();
                code.add("\tcmp\trax, 0");
                switch (op) {
                    case "==":
                        code.add("\tjne \t" + false_label.getName());
                        break;
                    case "!=":
                        code.add("\tje  \t" + false_label.getName());
                        break;
                    case "<":
                        code.add("\tjge \t" + false_label.getName());
                        break;
                    case ">=":
                        code.add("\tjl  \t" + false_label.getName());
                        break;
                    case ">":
                        code.add("\tjle \t" + false_label.getName());
                        break;
                    case "<=":
                        code.add("\tjg  \t" + false_label.getName());
                        break;
                }
                int index = instructions.indexOf(cJump);
                IRInstruction next = instructions.get(index + 1);
                if (next != true_label)
                    code.add("\tjmp \t" + true_label.getName());
            } else {
                RegX86 reg_lhs = getReg(lhs, RegX86.rcx);
                code.add("\tcmp \t" + reg_lhs + ", " + address(rhs));
                switch (op) {
                    case "==":
                        code.add("\tjne \t" + false_label.getName());
                        break;
                    case "!=":
                        code.add("\tje  \t" + false_label.getName());
                        break;
                    case "<":
                        code.add("\tjge \t" + false_label.getName());
                        break;
                    case ">=":
                        code.add("\tjl  \t" + false_label.getName());
                        break;
                    case ">":
                        code.add("\tjle \t" + false_label.getName());
                        break;
                    case "<=":
                        code.add("\tjg  \t" + false_label.getName());
                        break;
                }
                int index = instructions.indexOf(cJump);
                IRInstruction next = instructions.get(index + 1);
                if (next != true_label)
                    code.add("\tjmp \t" + true_label.getName());
            }
        }
    }

    private void addIns(Cmp cmp) {
        Operand lhs = cmp.getLhs();
        Operand rhs = cmp.getRhs();
        Variable dest = cmp.getDest();
        RegX86 reg_lhs = getReg(lhs, RegX86.rcx);
        code.add("\tcmp \t" + reg_lhs + ", " + address(rhs));
        switch (cmp.getOp()) {
            case "==":
                code.add("\tsete\tcl");
                break;
            case "!=":
                code.add("\tsetne\tcl");
                break;
            case "<":
                code.add("\tsetl\tcl");
                break;
            case ">=":
                code.add("\tsetge\tcl");
                break;
            case ">":
                code.add("\tsetg\tcl");
                break;
            case "<=":
                code.add("\tsetle\tcl");
                break;
        }
        RegX86 reg_dest = dest.getReg();
        if (reg_dest != null) {
            code.add("\tmovzx\t" + reg_dest + ", cl");
        } else {
            code.add("\tmovzx\trcx, cl");
            store(dest, RegX86.rcx);
        }
    }

    private void addIns(Dec dec){
        Operand expr = dec.getExpr();
        RegX86 reg_expr = getReg(expr, RegX86.rcx);
        code.add("\tsub \t" + reg_expr + ", 1");
        if (reg_expr == RegX86.rcx)
            code.add("\tmov \t" + address(expr) + ", " + reg_expr);
    }

    private void addIns(Div div) {
        Operand lhs = div.getLhs();
        Operand rhs = div.getRhs();
        Operand dest = div.getDest();
        load(RegX86.rax, lhs);
        load(RegX86.rcx, rhs);
        code.add("\txor \trdx, rdx");
        code.add("\tcqo");
        code.add("\tidiv\trcx\n");
        code.add("\tmov \t" + address(dest) + ", rax");
    }

    private void addIns(Inc inc) {
        Operand expr = inc.getExpr();
        RegX86 reg_expr = getReg(expr, RegX86.rcx);
        code.add("\tadd \t" + reg_expr + ", 1");
        if (reg_expr == RegX86.rcx)
            code.add("\tmov \t" + address(expr) + ", " + reg_expr);
    }

    private void addIns(Jump jump) {
        int index = instructions.indexOf(jump);
        IRInstruction next = instructions.get(index + 1);
        if (jump.getLabel() != next)
            code.add("\tjmp \t" + jump.getLabel().getName());
    }

    private void addIns(Label label) {
        code.add("");
        code.add(label.getName() + ":" );
    }

    private void addIns(Mod mod) {
        Operand lhs = mod.getLhs();
        Operand rhs = mod.getRhs();
        Operand dest = mod.getDest();
        load(RegX86.rax, lhs);
        load(RegX86.rcx, rhs);
        code.add("\txor \trdx, rdx");
        code.add("\tcqo");
        code.add("\tidiv\trcx\n");
        code.add("\tmov \t" + address(dest) + ", rdx");
    }

    private void addIns(Move move) {
        Operand lhs = move.getLhs();
        Operand rhs = move.getRhs();
        if (rhs instanceof Immediate)
            code.add("\tmov \t" + address(lhs) + ", " + rhs);
        else if (lhs instanceof Variable && ((Variable) lhs).getReg() != null) {
            String str_rhs = address(rhs);
            if (!str_rhs.equals(((Variable) lhs).getReg().toString()))
                code.add("\tmov \t" + ((Variable) lhs).getReg() + ", " + address(rhs));
        } else {
            RegX86 reg_rhs = getReg(rhs, RegX86.rcx);
            String str_lhs = address(lhs);
            if (!str_lhs.equals(reg_rhs.toString()))
                code.add("\tmov \t" + str_lhs + ", " + reg_rhs);
        }
    }

    private void addIns(Mul mul) {
        Operand lhs = mul.getLhs();
        Operand rhs = mul.getRhs();
        Variable dest = mul.getDest();
        RegX86 reg_dest = dest.getReg();
        if (lhs instanceof Variable && ((Variable) lhs).getReg() == reg_dest && reg_dest != null) {
            code.add("\timul\t" + reg_dest + ", " + address(rhs));
        } else {
            load(RegX86.rcx, lhs);
            code.add("\timul\t" + RegX86.rcx + ", " + address(rhs));
            if (reg_dest != null) {
                if (reg_dest != RegX86.rcx)
                    code.add("\tmov \t" + reg_dest + ", " + RegX86.rcx);
            } else
                store(dest, RegX86.rcx);
        }
    }

    private void addIns(Neg neg){
        Operand expr = neg.getExpr();
        Variable dest = neg.getDest();
        RegX86 reg_dest = dest.getReg();
        load(RegX86.rcx, expr);
        code.add("\tneg \t" + RegX86.rcx);
        if (reg_dest != null) {
            if (reg_dest != RegX86.rcx)
                code.add("\tmov \t" + reg_dest + ", " + RegX86.rcx);
        } else
            store(dest, RegX86.rcx);
    }

    private void addIns(Not not){
        Operand expr = not.getExpr();
        Variable dest = not.getDest();
        RegX86 reg_dest = dest.getReg();
        load(RegX86.rcx, expr);
        code.add("\tnot \t" + RegX86.rcx);
        if (reg_dest != null) {
            if (reg_dest != RegX86.rcx)
                code.add("\tmov \t" + reg_dest + ", " + RegX86.rcx);
        } else
            store(dest, RegX86.rcx);
    }

    private void addIns(Or or){
        Operand lhs = or.getLhs();
        Operand rhs = or.getRhs();
        Variable dest = or.getDest();
        RegX86 reg_dest = dest.getReg();
        if (lhs instanceof Variable && ((Variable) lhs).getReg() == reg_dest && reg_dest != null) {
            code.add("\tor  \t" + reg_dest + ", " + address(rhs));
        } else {
            load(RegX86.rcx, lhs);
            code.add("\tor  \t" + RegX86.rcx + ", " + address(rhs));
            if (reg_dest != null) {
                if (reg_dest != RegX86.rcx)
                    code.add("\tmov \t" + reg_dest + ", " + RegX86.rcx);
            } else
                store(dest, RegX86.rcx);
        }
    }

    private void addIns(Return ret){
        Operand expr = ret.getRet();
        load(RegX86.rax, expr);
    }

    private void addIns(Sal sal){
        Operand lhs = sal.getLhs();
        Operand rhs = sal.getRhs();
        Operand dest = sal.getDest();
        load(RegX86.rax, lhs);
        load(RegX86.rcx, rhs);
        code.add("\tsal \trax, cl");
        code.add("\tmov \t" + address(dest) + ", rax");
    }

    private void addIns(Sar sar){
        Operand lhs = sar.getLhs();
        Operand rhs = sar.getRhs();
        Operand dest = sar.getDest();
        load(RegX86.rax, lhs);
        load(RegX86.rcx, rhs);
        code.add("\tsar \trax, cl");
        code.add("\tmov \t" + address(dest) + ", rax");
    }

    private void addIns(Sub sub){
        Operand lhs = sub.getLhs();
        Operand rhs = sub.getRhs();
        Variable dest = sub.getDest();
        RegX86 reg_dest = dest.getReg();
        if (lhs instanceof Variable && ((Variable) lhs).getReg() == reg_dest && reg_dest != null) {
            code.add("\tsub \t" + reg_dest + ", " + address(rhs));
        } else {
            load(RegX86.rcx, lhs);
            code.add("\tsub \t" + RegX86.rcx + ", " + address(rhs));
            if (reg_dest != null) {
                if (reg_dest != RegX86.rcx)
                    code.add("\tmov \t" + reg_dest + ", " + RegX86.rcx);
            } else
                store(dest, RegX86.rcx);
        }
    }

    private void addIns(Xor xor){
        Operand lhs = xor.getLhs();
        Operand rhs = xor.getRhs();
        Variable dest = xor.getDest();
        RegX86 reg_dest = dest.getReg();
        if (lhs instanceof Variable && ((Variable) lhs).getReg() == reg_dest && reg_dest != null) {
            code.add("\txor \t" + reg_dest + ", " + address(rhs));
        } else {
            load(RegX86.rcx, lhs);
            code.add("\txor \t" + RegX86.rcx + ", " + address(rhs));
            if (reg_dest != null) {
                if (reg_dest != RegX86.rcx)
                    code.add("\tmov \t" + reg_dest + ", " + RegX86.rcx);
            } else
                store(dest, RegX86.rcx);
        }
    }

    private void initFunction(IRFunction function) {
        current_stackAlloc = function.getStackAlloc();
        code.add("\tpush\trbp");
        code.add("\tmov  \trbp, rsp");
        code.add("\tsub \trsp, " + Integer.toString(current_stackAlloc.size()));

        //callee-save
        code.add("\tpush\tr12");
        code.add("\tpush\tr13");
        code.add("\tpush\tr14");
        code.add("\tpush\tr15");

        List<Variable> parameters = function.getParameters();
        int len = parameters.size();
        int offset = 8;
        int x = len < 6 ? len - 1 : 5;
        for (int i = 0; i < len; ++i) {
            if (i < 6) {
                RegX86 regX86 = RegX86.getParameter(x - i);
                Variable var = parameters.get(x - i);
                code.add("\tmov \t" + address(var) + ", " + regX86);
            } else {
                offset = offset + 8;
                Variable var = parameters.get(i);
                code.add("\tmov \trcx, [rbp + " + offset +"]");
                code.add("\tmov \t" + address(var) + ", rcx");
            }
        }
        code.add("");
    }

    private void addFunction(IRFunction function) {
        addIns(function.getBeginLabel());
        initFunction(function);
        instructions = function.getStatements();
        instructions.add(function.getEndLabel());
        for (IRInstruction ins : instructions) {
//            code.add(ins.toString());
            if (ins.getIsDead() && !(ins instanceof Label)) continue;
            if (ins instanceof Add) addIns((Add) ins);
            if (ins instanceof And) addIns((And) ins);
            if (ins instanceof Call) addIns((Call) ins);
            if (ins instanceof CJump) addIns((CJump) ins);
            if (ins instanceof Cmp) addIns((Cmp) ins);
            if (ins instanceof Dec) addIns((Dec) ins);
            if (ins instanceof Div) addIns((Div) ins);
            if (ins instanceof Inc) addIns((Inc) ins);
            if (ins instanceof Jump) addIns((Jump) ins);
            if (ins instanceof Label) addIns((Label) ins);
            if (ins instanceof Mod) addIns((Mod) ins);
            if (ins instanceof Move) addIns((Move) ins);
            if (ins instanceof Mul) addIns((Mul) ins);
            if (ins instanceof Neg) addIns((Neg) ins);
            if (ins instanceof Not) addIns((Not) ins);
            if (ins instanceof Or) addIns((Or) ins);
            if (ins instanceof Return) addIns((Return) ins);
            if (ins instanceof Sal) addIns((Sal) ins);
            if (ins instanceof Sar) addIns((Sar) ins);
            if (ins instanceof Sub) addIns((Sub) ins);
            if (ins instanceof Xor) addIns((Xor) ins);
        }
        //callee-save
        code.add("\tpop \tr15");
        code.add("\tpop \tr14");
        code.add("\tpop \tr13");
        code.add("\tpop \tr12");

        code.add("\tmov \trsp, rbp");
        code.add("\tpop \trbp");
        code.add("\tret");
        code.add("");
    }

    private String dealStr(String str){
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int x = 0;
            if (str.charAt(i) == '\\') {
                i++;
                switch (str.charAt(i)) {
                    case 'n':
                        x = 10;
                        break;
                    case '\\':
                        x = 92;
                        break;
                    case '\"':
                        x = 34;
                        break;
                }
            }
            else {
                x = (int) str.charAt(i);
            }
            String tmp = Long.toHexString(x).toUpperCase();
            if (tmp.length() < 2)
                ans.append("0" + tmp + "H, ");
            else
                ans.append(tmp + "H, ");
        }
        ans.append("00H");
        return ans.toString();
    }

    private void loadAsm(String fileName) {
        File file = new File(fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                code.add(tempString);
            }
        } catch (IOException e) {
            System.out.println(fileName + "loading failed");
        }
        code.add("");
    }

    public List<String> translate() {
        code.add("\tglobal main");
        code.add("\textern malloc");
        code.add("\textern puts");
        code.add("\textern printf");
        code.add("\textern sprintf");
        code.add("\textern scanf");
        code.add("\textern strlen");
        code.add("\textern strcpy");
        code.add("\textern strncpy");
        code.add("\textern strcat");
        code.add("\textern getline");
        code.add("\textern stdin");
        code.add("\textern strcmp");
        code.add("\textern __stack_chk_fail");
        code.add("");
        code.add("\tsection .text");

        global_init.addAll(main.getStatements());
        main.setStatements(global_init);

        inlineFunction = new InlineFunction(irBuilder);
        inlineFunction.InlineOptim();

        folder.fold(main);
        regAllocator.allocate(main);
        addFunction(main);
        for (IRFunction function : functions) {
            String func_name = function.getFunction_name();
            if (inFunction.contains(func_name)) continue;
            if (function.getIsInline()) continue;
            folder.fold(function);
            regAllocator.allocate(function);
            addFunction(function);
        }

        loadAsm("inFunction.asm");
        code.add("");

        code.add("\tsection\t.data");
        for (Variable var : global_string.keySet()) {
            code.add(var.getName() + ":");
            code.add("\tdb\t" + dealStr(global_string.get(var)));
//            System.out.println(var.getName() + "," +global_string.get(var));
        }
        code.add("");

        code.add("\tsection\t.bss");
        Map<String, Variable> global_def = global_scope.getScope();
        for (String name : global_def.keySet()) {
            Variable var = global_def.get(name);
            code.add(var.getName() + ":\tresq\t1");
        }
        for (IRFunction function : functionRemember.keySet()) {
            Variable var = functionRemember.get(function);
            code.add(var.getName() + "\tresq\t1");
        }
        code.add("");

        code.add("\tsection\t.rodata");
        loadAsm("rodata.asm");

        return code;
    }
}
