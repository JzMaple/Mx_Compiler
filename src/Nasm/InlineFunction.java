package Nasm;

import IR.IRBuilder;
import IR.IRInstruction.*;
import IR.IRInstruction.Operand.Immediate;
import IR.IRInstruction.Operand.Memory;
import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;
import IR.IRNode.IRFunction;
import IR.IRNode.IRParameter;

import java.util.*;

public class InlineFunction {
    private int[][] callMap;
    private Set<IRFunction> inlineFunctions = new HashSet<>();
    private Map<Variable, Variable> variableMap = new HashMap<>();
    private Map<Integer, IRFunction> numberMap = new HashMap<>();
    private Map<IRFunction, Integer> functionMap = new HashMap<>();
    private Queue<IRFunction> wait = new LinkedList<>();
    private int num;

    public InlineFunction(IRBuilder irBuilder) {
        Map<String, IRFunction> functions = irBuilder.getFunctions();
        Map<String, IRFunction> inFunctions = irBuilder.getInFunctions();
        num = functions.size() + inFunctions.size();
        callMap = new int[num][num];
        for (int i = 0; i < num; ++i)
            for (int j = 0; j < num; ++j) callMap[i][j] = 0;
        int cnt = 0;
        for (String func_name : functions.keySet()) {
            IRFunction function = functions.get(func_name);
            numberMap.put(cnt, function);
            functionMap.put(function, cnt);
            cnt = cnt + 1;
        }
        for (String func_name : inFunctions.keySet()) {
            IRFunction function = inFunctions.get(func_name);
            numberMap.put(cnt, function);
            functionMap.put(function, cnt);
            cnt = cnt + 1;
        }
        for (String func_name : functions.keySet()) {
            IRFunction function = functions.get(func_name);
            if (isInline(function)) inlineFunctions.add(function);
        }
    }

    private Boolean isInline(IRFunction function) {
        List<IRInstruction> inst = function.getStatements();
        int len = inst.size();
        Boolean isInline = true;
        Boolean flag = false;
        for (int i = 0; i < len - 1; ++i) {
            IRInstruction ins = inst.get(i);
            if (ins instanceof Call) {
//                System.out.println(function.getFunction_name());
//                System.out.println(((Call) ins).getFunction().getFunction_name());
                callMap[functionMap.get(function)][functionMap.get(((Call) ins).getFunction())] += 1;
            }
            if (ins instanceof Jump || ins instanceof CJump) isInline = false;
            if (ins instanceof Return) {
                if (flag) isInline = false;
                else flag = true;
            }
        }
//        System.out.println(function.getFunction_name() + " " + function.getIsInline());
        return isInline;
    }

    private Operand get(Operand op) {
        if (op instanceof Immediate)
            return op;
        else if (op instanceof Variable)
            return variableMap.get(op);
        else if (op instanceof Memory) {
            Memory mem = (Memory) op;
            return new Memory(get(mem.getBase()), get(mem.getIndex()), mem.getScale(), mem.getNumber(), mem.getType());
        } else return null;
    }

    private List<IRInstruction> convert(IRFunction callee, IRFunction caller, Call instruction) {
        //addStack
        StackAllocator callee_stack = callee.getStackAlloc();
        StackAllocator caller_stack = caller.getStackAlloc();
        Map<Variable, Integer> location = callee_stack.getLocation();
        variableMap.clear();
        for (Variable var : location.keySet()) {
            Variable variable = new Variable(var.getName(), var.getType(), false, caller_stack);
            variableMap.put(var, variable);
        }

        List<IRInstruction> inst = callee.getStatements();
        List<IRInstruction> instInline = new ArrayList<>();

        List<Operand> params_in = instruction.getParameters().getParameters();
        List<Variable> params_func = callee.getParameters();
        for (int i = 0; i < params_in.size(); ++i) {
            instInline.add(new Move(get(params_func.get(i)), params_in.get(i)));
        }

        for (IRInstruction ins : inst) {
            if (ins instanceof Binary) {
                Binary bin = (Binary) ins;
                if (ins instanceof Add) instInline.add(new Add(get(bin.getLhs()), get(bin.getRhs()), (Variable) get(bin.getDest())));
                if (ins instanceof And) instInline.add(new And(get(bin.getLhs()), get(bin.getRhs()), (Variable) get(bin.getDest())));
                if (ins instanceof Cmp) instInline.add(new Cmp(get(bin.getLhs()), get(bin.getRhs()), ((Cmp) bin).getOp(), (Variable) get(bin.getDest())));
                if (ins instanceof Div) instInline.add(new Div(get(bin.getLhs()), get(bin.getRhs()), (Variable) get(bin.getDest())));
                if (ins instanceof Mod) instInline.add(new Mod(get(bin.getLhs()), get(bin.getRhs()), (Variable) get(bin.getDest())));
                if (ins instanceof Mul) instInline.add(new Mul(get(bin.getLhs()), get(bin.getRhs()), (Variable) get(bin.getDest())));
                if (ins instanceof Or) instInline.add(new Or(get(bin.getLhs()), get(bin.getRhs()), (Variable) get(bin.getDest())));
                if (ins instanceof Sal) instInline.add(new Sal(get(bin.getLhs()), get(bin.getRhs()), (Variable) get(bin.getDest())));
                if (ins instanceof Sar) instInline.add(new Sar(get(bin.getLhs()), get(bin.getRhs()), (Variable) get(bin.getDest())));
                if (ins instanceof Sub) instInline.add(new Sub(get(bin.getLhs()), get(bin.getRhs()), (Variable) get(bin.getDest())));
                if (ins instanceof Xor) instInline.add(new Xor(get(bin.getLhs()), get(bin.getRhs()), (Variable) get(bin.getDest())));
            }
            if (ins instanceof Unary) {
                Unary unary = (Unary) ins;
                if (ins instanceof Inc) instInline.add(new Inc(get(unary.getExpr())));
                if (ins instanceof Dec) instInline.add(new Dec(get(unary.getExpr())));
                if (ins instanceof Not) instInline.add(new Not(get(unary.getExpr()), (Variable) get(unary.getDest())));
                if (ins instanceof Neg) instInline.add(new Neg(get(unary.getExpr()), (Variable) get(unary.getDest())));
            }
            if (ins instanceof Move) {
                instInline.add(new Move(get(((Move) ins).getLhs()), get(((Move) ins).getRhs())));
            }
            if (ins instanceof Return) {
                instInline.add(new Move(instruction.getTmp_return(), get(((Return) ins).getRet())));
            }
            if (ins instanceof Call) {
                Call call = (Call) ins;
                List<Operand> params = ((Call) ins).getParameters().getParameters();
                List<Operand> parameters = new ArrayList<>();
                for (Operand op : params)
                    parameters.add(get(op));
                instInline.add(new Call(call.getFunction(), new IRParameter(parameters), (Variable) get(call.getTmp_return())));
            }
        }
        return instInline;
    }

    private void addInline(IRFunction callee, IRFunction caller) {
        List<IRInstruction> inst = caller.getStatements();
        List<IRInstruction> instInline = new ArrayList<>();
        for (IRInstruction ins : inst) {
            if (ins instanceof Call && ((Call) ins).getFunction() == callee) {
                List<IRInstruction> inst_callee = convert(callee, caller, (Call) ins);
                instInline.addAll(inst_callee);
            } else {
                instInline.add(ins);
            }
        }
        caller.setStatements(instInline);
    }


    public void InlineOptim() {
        int[] pre = new int[num];
        for (int i = 0; i < num; ++i) {
            pre[i] = 0;
            for (int j = 0; j < num; ++j) pre[i] += callMap[i][j] > 0 ? 1 : 0;
        }

        for (IRFunction function : inlineFunctions)
            if (pre[functionMap.get(function)] == 0)
                wait.offer(function);

        while (!wait.isEmpty()) {
            IRFunction function = wait.poll();
            int j = functionMap.get(function);
            if (function.getStatements().size() > 50) continue;
            function.setIsInline(true);
            for (int i = 0; i < num; ++i)
                if (callMap[i][j] > 0) {
                    addInline(function, numberMap.get(i));
                    callMap[i][j] = 0;
                    pre[j] = pre[j] - 1;
                }
            for (IRFunction inlineFunction : inlineFunctions)
                if (pre[functionMap.get(inlineFunction)] == 0) wait.offer(inlineFunction);
        }
    }
}
