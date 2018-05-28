package Visitor;


import IR.IRInstruction.*;
import IR.IRInstruction.Operand.*;
import IR.IRNode.*;
import IR.IRInstruction.Operand.Variable;
import NasmTranslate.RegX86;
import NasmTranslate.StackAlloc;

import java.util.*;
import java.util.Vector;

public class Translator {
    private Vector<IRFunction> functions = new Vector<>();
    private IRScope global_scope;
    private List<IRInstruction> global_init;
    private List<String>code = new LinkedList<>();
    private Map<RegX86, Variable> regMap;
    private IRFunction main;
    private StackAlloc current_stackAlloc;

    private void setRegMap() {
        regMap = new HashMap<>();
        regMap.put(RegX86.rax, null);
        regMap.put(RegX86.rcx, null);
        regMap.put(RegX86.rdx, null);
        regMap.put(RegX86.rbx, null);
        regMap.put(RegX86.rsp, null);
        regMap.put(RegX86.rbp, null);
        regMap.put(RegX86.rsi, null);
        regMap.put(RegX86.rdi, null);
        regMap.put(RegX86.r8 , null);
        regMap.put(RegX86.r9 , null);
        regMap.put(RegX86.r10, null);
        regMap.put(RegX86.r11, null);
        regMap.put(RegX86.r12, null);
        regMap.put(RegX86.r13, null);
        regMap.put(RegX86.r14, null);
        regMap.put(RegX86.r15, null);
    }

    public Translator(IRBuilder IR_Builder) {
        Map<String, IRFunction> functions = IR_Builder.getFunctions();
        for (String func_name : functions.keySet()) {
            if (func_name.equals("print") || func_name.equals("println") || func_name.equals("getString")
                    || func_name.equals("getInt") || func_name.equals("toString") || func_name.equals("malloc"))
                continue;
            if (func_name.equals("main_func__"))
                main = functions.get(func_name);
            else
                this.functions.add(functions.get(func_name));
        }
        this.global_scope = IR_Builder.getGlobalScope();
        this.global_init = IR_Builder.getGlobalStatements();
        setRegMap();
    }

    private String address(Operand op) {
        if (op instanceof Variable){
            if (((Variable) op).isGlobal()) {
                return "qword [" + ((Variable) op).getName() + "]";
            } else {
                int offset = current_stackAlloc.getOffset(op);
                String offset_str = offset < 0 ? Integer.toString(offset) : "+" + Integer.toString(offset);
                return "qword [" + RegX86.rbp + offset_str + "]";
            }
        } else if (op instanceof Memory) {
            Operand base = ((Memory) op).getBase();
            Operand index = ((Memory) op).getIndex();
            String base_str = "0";
            String index_str = "0";
            int scale = ((Memory) op).getScale();
            int number = ((Memory) op).getNumber();
            String number_str = number < 0 ? Integer.toString(number) : "+" + Integer.toString(number);
            if (base != null) {
                load(RegX86.r14, base);
                base_str = "r14";
            }
            if (index != null) {
                load(RegX86.r15, index);
                index_str = "r15";
            }
            return "qword [" + base_str + "+" + index_str + "*" + scale + number_str + "]";
        }
        return "";
    }

    private void load(RegX86 reg, Operand var) {
        if (var instanceof Immediate)
            code.add("\tmove\t" + reg + ", " + var);
        else
            code.add("\tmove\t" + reg + ", " + address(var));
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
        code.add("\tmove\t" + address(dest) + ", rcx");
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
        code.add("\tmove\t" + address(dest) + ", rcx");
    }

    private void addIns(Call call) {
        IRFunction function = call.getFunction();
        Vector<Operand> parameters = call.getParameters().getParameters();
        int len = parameters.size() < 6 ? parameters.size() : 6;
        System.out.println(parameters);
        for (int i = 0; i < len; ++i)
            load(RegX86.getParameter(i), parameters.get(i));
        code.add("\tcall\t" + function.getBeginLabel().getName());
        if (call.getTmp_return() != null)
            code.add("\tmove\t" + address(call.getTmp_return()) + ", rax");
    }

    private void addIns(CJump cJump) {
        Cmp cmp = cJump.getCond();
        Operand cond = cJump.getCondition();
//        Label true_label = cJump.getTrue_label();
        Label false_label = cJump.getFalse_label();
        if (cmp == null) {
            load(RegX86.rcx, cond);
            code.add("\tcmp \trcx, 0");
            code.add("\tjz  \t" + false_label.getName());
//            code.add("\tjmp \t" + true_label.getName());
        } else {
            Operand lhs = cmp.getLhs();
            Operand rhs = cmp.getRhs();
            String op = cmp.getOp();
            load(RegX86.r10, lhs);
            load(RegX86.r11, rhs);
            code.add("\tcmp \tr10, r11");
            switch (op) {
                case "==":
                    code.add("\tjz  \t" + false_label.getName());
//                    code.add("\tjmp \t" + true_label.getName());
                    break;
                case "!=":
                    code.add("\tjnz \t" + false_label.getName());
//                    code.add("\tjmp \t" + true_label.getName());
                    break;
                case "<":
                    code.add("\tjna \t" + false_label.getName());
//                    code.add("\tjmp \t" + true_label.getName());
                    break;
                case ">=":
                    code.add("\tja  \t" + false_label.getName());
//                    code.add("\tjmp \t" + true_label.getName());
                    break;
                case ">":
                    code.add("\tjnb \t" + false_label.getName());
//                    code.add("\tjmp \t" + true_label.getName());
                    break;
                case "<=":
                    code.add("\tjb  \t" + false_label.getName());
//                    code.add("\tjmp \t" + true_label.getName());
                    break;
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
                code.add("\tsete\trcx");
                break;
            case "!=":
                code.add("\tsetne\trcx");
                break;
            case "<":
                code.add("\tseta\trcx");
                break;
            case ">=":
                code.add("\tsetbe\trcx");
                break;
            case ">":
                code.add("\tsetb\trcx");
                break;
            case "<=":
                code.add("\tsetae\trcx");
                break;
        }
        code.add("\tmove\t" + address(dest) + ", rcx");
    }

    private void addIns(Dec dec){
        Operand expr = dec.getExpr();
        load(RegX86.rcx, expr);
        code.add("\tsub \trcx, 1");
        code.add("\tmove\t" + address(expr) + ", rcx");
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
        code.add("\tmove\t" + address(dest) + ", rax");
    }

    private void addIns(Inc inc) {
        Operand expr = inc.getExpr();
        load(RegX86.rcx, expr);
        code.add("\tadd \trcx, 1");
        code.add("\tmove\t" + address(expr) + ", rcx");
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
        code.add("\tmove\t" + address(dest) + ", rdx");
    }

    private void addIns(Move move) {
        Operand lhs = move.getLhs();
        Operand rhs = move.getRhs();
        if (rhs instanceof Immediate)
            code.add("\tmove\t" + address(lhs) + ", " + rhs);
        else {
            load(RegX86.rcx, rhs);
            code.add("\tmove\t" + address(lhs) + ", rcx");
        }
    }

    private void addIns(Mul mul) {
        Operand lhs = mul.getLhs();
        Operand rhs = mul.getRhs();
        Operand dest = mul.getDest();
        if (!(rhs instanceof Immediate)) {
            load(RegX86.rcx, lhs);
            load(RegX86.r11, rhs);
            code.add("\tmul \trcx, r11");
        } else {
            load(RegX86.rcx, lhs);
            code.add("\tmul \trcx, " + rhs);
        }
        code.add("\tmove\t" + address(dest) + ", rcx");
    }

    private void addIns(Neg neg){
        Operand expr = neg.getExpr();
        Operand dest = neg.getDest();
        load(RegX86.r10, expr);
        code.add("\tneg \tr10");
        code.add("\tmove" + address(dest) + ", r10");
    }

    private void addIns(Not not){
        Operand expr = not.getExpr();
        Operand dest = not.getDest();
        load(RegX86.r10, expr);
        code.add("\tnot \tr10");
        code.add("\tmove" + address(dest) + ", r10");
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
        code.add("\tmove\t" + address(dest) + ", rcx");
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
        code.add("\tmove\t" + address(dest) + ", rax");
    }

    private void addIns(Sar sar){
        Operand lhs = sar.getLhs();
        Operand rhs = sar.getRhs();
        Operand dest = sar.getDest();
        load(RegX86.rax, lhs);
        load(RegX86.rcx, rhs);
        code.add("\tsar \trax, cl");
        code.add("\tmove\t" + address(dest) + ", rax");
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
        code.add("\tmove\t" + address(dest) + ", rcx");
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
        code.add("\tmove\t" + address(dest) + ", rcx");
    }

    private void resetMain(IRFunction main) {
        Label begin = main.getBeginLabel();
        begin.setName("main");
        main.setFunction_AsmName("main");
    }

    private void initFunction(IRFunction function) {
        current_stackAlloc = function.getStackAlloc();
        code.add("\tpush\trbp");
        code.add("\tmov \trbp, rsp");
        code.add("\tsub \trsp, " + Integer.toString(current_stackAlloc.size()));
        List<Variable> parameters = function.getParameters();
        int len = parameters.size() < 6 ? parameters.size() : 6;
        for (int i = 0; i < len; ++i) {
            RegX86 regX86 = RegX86.getParameter(i);
            Variable var = parameters.get(i);
            code.add("\tmove\t" + address(var) + ", " + regX86);
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
        code.add("\tmov \trsp, rbp");
        code.add("\tpop \trbp");
        code.add("\tret");
        code.add("");
    }

    public List<String> translate() {
        code.add("\tglobal main");
        code.add("\textern malloc");
        code.add("");

        code.add("\tsection .text");
        resetMain(main);
        addFunction(main);
        for (IRFunction function : functions)
            addFunction(function);

        code.add("\tsection .bss");
        Map<String, Variable> global_def = global_scope.getScope();
        for (String name : global_def.keySet()) {
            Variable var = global_def.get(name);
            code.add(var.getName() + ":\tresq\t1");
        }

        return code;
    }
}
