package Visitor;


import IR.IRInstruction.*;
import IR.IRInstruction.Operand.*;
import IR.IRNode.*;
import IR.IRInstruction.Operand.Variable;
import NasmTranslate.RegX86;
import NasmTranslate.StackAlloc;

import java.io.*;
import java.util.*;
import java.util.Vector;

public class Translator {
    private Vector<IRFunction> functions = new Vector<>();
    private IRScope global_scope;
    private List<IRInstruction> global_init;
    private Map<Variable, String> global_string;
    private Set<String> inFunction = new HashSet<>();
    private List<String>code = new LinkedList<>();
    private IRFunction main;
    private StackAlloc current_stackAlloc;

    public Translator(IRBuilder IR_Builder) {
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
    }

    private String address(Operand op) {
        if (op instanceof Variable){
            if (((Variable) op).isGlobal()) {
                if (((Variable) op).getIsConstant())
                    return ((Variable) op).getName();
                else
                    return "qword [" + ((Variable) op).getName() + "]";
            } else {
                int offset = current_stackAlloc.getOffset(op);
                String offset_str = offset < 0 ? Integer.toString(offset) : "+" + Integer.toString(offset);
                return "qword [" + RegX86.rbp + offset_str + "]";
            }
        } else if (op instanceof Memory) {
            Operand base = ((Memory) op).getBase();
            Operand index = ((Memory) op).getIndex();
            Variable base_var = ((Memory) op).getBase_var();
            Variable index_var = ((Memory) op).getIndex_var();
            String base_str = "0";
            String index_str = "0";
            int scale = ((Memory) op).getScale();
            int number = ((Memory) op).getNumber();
            String number_str = number < 0 ? Integer.toString(number) : "+" + Integer.toString(number);
            if (base != null) {
                load(RegX86.r14, base);
                code.add("\tmov \t" + address(base_var) + ", r14");
                base_str = "r14";
            }
            if (index != null) {
                load(RegX86.r15, index);
                code.add("\tmov \t" + address(index_var) + ", r15");
                index_str = "r15";
            }
            load(RegX86.r14, base_var);
            load(RegX86.r15, index_var);
            return "qword [" + base_str + "+" + index_str + "*" + scale + number_str + "]";
        }
        return "";
    }

    private void load(RegX86 reg, Operand var) {
        if (var instanceof Immediate)
            code.add("\tmov \t" + reg + ", " + var);
        else
            code.add("\tmov \t" + reg + ", " + address(var));
    }

    private void addIns(Add add) {
        Operand lhs = add.getLhs();
        Operand rhs = add.getRhs();
        Operand dest = add.getDest();
        if (!(rhs instanceof Immediate)) {
            load(RegX86.rcx, lhs);
            load(RegX86.r11, rhs);
            code.add("\tadd \trcx, r11");
        } else {
            load(RegX86.rcx, lhs);
            code.add("\tadd \trcx, " + rhs);
        }
        code.add("\tmov \t" + address(dest) + ", rcx");
    }

    private void addIns(And and) {
        Operand lhs = and.getLhs();
        Operand rhs = and.getRhs();
        Operand dest = and.getDest();
        if (!(rhs instanceof Immediate)) {
            load(RegX86.rcx, lhs);
            load(RegX86.r11, rhs);
            code.add("\tand \trcx, r11");
        } else {
            load(RegX86.rcx, lhs);
            code.add("\tand \trcx, " + rhs);
        }
        code.add("\tmov \t" + address(dest) + ", rcx");
    }

    private void addIns(Call call) {
        IRFunction function = call.getFunction();
        Vector<Operand> parameters = call.getParameters().getParameters();
        int len = parameters.size();
        if (len <= 6) {
            for (int i = 0; i < len; ++i)
                load(RegX86.getParameter(i), parameters.get(i));
        } else {
            for (int i = 0; i < 6; ++i)
                load(RegX86.getParameter(i), parameters.get(i));
            for (int i = len-1; i >= 6; --i) {
                load(RegX86.r10, parameters.get(i));
                code.add("\tpush\tr10");
            }
        }
        code.add("\tcall\t" + function.getBeginLabel().getName());
        if (call.getTmp_return() != null)
            code.add("\tmov \t" + address(call.getTmp_return()) + ", rax");
        if (len > 6) {
            for (int i = len-1; i >= 6; --i) {
                code.add("\tpop\tr10");
            }
        }
    }

    private void addIns(CJump cJump) {
        Cmp cmp = cJump.getCond();
        Operand cond = cJump.getCondition();
        Label true_label = cJump.getTrue_label();
        Label false_label = cJump.getFalse_label();
        if (cmp == null) {
            load(RegX86.rcx, cond);
            code.add("\tcmp \trcx, 0");
            code.add("\tjz  \t" + false_label.getName());
            code.add("\tjmp \t" + true_label.getName());
        } else {
            Operand lhs = cmp.getLhs();
            Operand rhs = cmp.getRhs();
            String op = cmp.getOp();
            if (lhs.getIsString() && rhs.getIsString()) {
                code.add("\tcall\tstrcmp");
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
                code.add("\tjmp \t" + true_label.getName());
            } else {
                load(RegX86.r10, lhs);
                load(RegX86.r11, rhs);
                code.add("\tcmp \tr10, r11");
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
                code.add("\tjmp \t" + true_label.getName());
            }
        }
    }

    private void addIns(Cmp cmp) {
        Operand lhs = cmp.getLhs();
        Operand rhs = cmp.getRhs();
        Operand dest = cmp.getDest();
        load(RegX86.rcx, lhs);
        load(RegX86.r11, rhs);
        code.add("\tcmp \trcx, r11");
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
        code.add("\tmovzx\trcx, cl");
        code.add("\tmov \t" + address(dest) + ", rcx");
    }

    private void addIns(Dec dec){
        Operand expr = dec.getExpr();
        load(RegX86.rcx, expr);
        code.add("\tsub \trcx, 1");
        code.add("\tmov \t" + address(expr) + ", rcx");
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
        load(RegX86.rcx, expr);
        code.add("\tadd \trcx, 1");
        code.add("\tmov \t" + address(expr) + ", rcx");
    }

    private void addIns(Jump jump) {
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
        else {
            load(RegX86.rcx, rhs);
            code.add("\tmov \t" + address(lhs) + ", rcx");
        }
    }

    private void addIns(Mul mul) {
        Operand lhs = mul.getLhs();
        Operand rhs = mul.getRhs();
        Operand dest = mul.getDest();
        if (!(rhs instanceof Immediate)) {
            load(RegX86.rcx, lhs);
            load(RegX86.r11, rhs);
            code.add("\timul\trcx, r11");
        } else {
            load(RegX86.rcx, lhs);
            code.add("\timul\trcx, " + rhs);
        }
        code.add("\tmov \t" + address(dest) + ", rcx");
    }

    private void addIns(Neg neg){
        Operand expr = neg.getExpr();
        Operand dest = neg.getDest();
        load(RegX86.r10, expr);
        code.add("\tneg \tr10");
        code.add("\tmov " + address(dest) + ", r10");
    }

    private void addIns(Not not){
        Operand expr = not.getExpr();
        Operand dest = not.getDest();
        load(RegX86.r10, expr);
        code.add("\tnot \tr10");
        code.add("\tmov \t" + address(dest) + ", r10");
    }

    private void addIns(Or or){
        Operand lhs = or.getLhs();
        Operand rhs = or.getRhs();
        Operand dest = or.getDest();
        if (!(rhs instanceof Immediate)) {
            load(RegX86.rcx, lhs);
            load(RegX86.r11, rhs);
            code.add("\tor  \trcx, r11");
        } else {
            load(RegX86.rcx, lhs);
            code.add("\tor  \trcx, " + rhs);
        }
        code.add("\tmov \t" + address(dest) + ", rcx");
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
        Operand dest = sub.getDest();
        if (!(rhs instanceof Immediate)) {
            load(RegX86.rcx, lhs);
            load(RegX86.r11, rhs);
            code.add("\tsub \trcx, r11");
        } else {
            load(RegX86.rcx, lhs);
            code.add("\tsub \trcx, " + rhs);
        }
        code.add("\tmov \t" + address(dest) + ", rcx");
    }

    private void addIns(Xor xor){
        Operand lhs = xor.getLhs();
        Operand rhs = xor.getRhs();
        Operand dest = xor.getDest();
        if (!(rhs instanceof Immediate)) {
            load(RegX86.rcx, lhs);
            load(RegX86.r11, rhs);
            code.add("\txor \trcx, r11");
        } else {
            load(RegX86.rcx, lhs);
            code.add("\txor \trcx, " + rhs);
        }
        code.add("\tmov \t" + address(dest) + ", rcx");
    }

    private void initFunction(IRFunction function) {
        current_stackAlloc = function.getStackAlloc();
        code.add("\tpush\trbp");
        code.add("\tmov  \trbp, rsp");
        code.add("\tsub \trsp, " + Integer.toString(current_stackAlloc.size()));
        List<Variable> parameters = function.getParameters();
        int len = parameters.size();
        int offset = 8;
        for (int i = 0; i < len; ++i) {
            if (i < 6) {
                RegX86 regX86 = RegX86.getParameter(i);
                Variable var = parameters.get(i);
                code.add("\tmov \t" + address(var) + ", " + regX86);
            } else {
                offset = offset + 8;
                Variable var = parameters.get(i);
                code.add("\tmov \tr10, [rbp + " + offset +"]");
                code.add("\tmov \t" + address(var) + ", r10");
            }
        }
        code.add("");
    }

    private void addFunction(IRFunction function) {
        addIns(function.getBeginLabel());
        initFunction(function);
        if (function.getFunction_name().equals("main")){
            for (IRInstruction ins : global_init) {
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
        }
        List<IRInstruction> instructions = function.getStatements();
        for (IRInstruction ins : instructions) {
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
        addIns(function.getEndLabel());
        code.add("\tmov  \trsp, rbp");
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
        addFunction(main);
        for (IRFunction function : functions) {
            String func_name = function.getFunction_name();
            if (inFunction.contains(func_name)) continue;
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
        code.add("");

        code.add("\tsection\t.rodata");
        loadAsm("rodata.asm");

        return code;
    }
}
