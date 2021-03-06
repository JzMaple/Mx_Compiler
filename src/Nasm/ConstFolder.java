package Nasm;

import IR.IRInstruction.*;
import IR.IRInstruction.Operand.Immediate;
import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;
import IR.IRNode.IRFunction;

import java.util.*;

public class ConstFolder {
    Map<Variable, Integer> value = new HashMap<>();

    private Boolean ok(Operand op) {
        if (op instanceof Immediate) return true;
        else return (op instanceof Variable) && value.containsKey(op);
    }

    private int get(Operand op) {
        if (op instanceof Immediate) return ((Immediate) op).getValue();
        else if (op instanceof Variable) return value.get(op);
        else return -1;
    }

    public void fold(IRFunction function) {
        List<IRInstruction> inst = function.getStatements();
        int size = inst.size();
        for (int i = 0; i < size; ++i) {
            IRInstruction ins = inst.get(i);
            if (ins instanceof Label || ins instanceof Jump || ins instanceof CJump)
                value.clear();
            if (ins instanceof Binary) {
                Operand lhs = ((Binary) ins).getLhs();
                Operand rhs = ((Binary) ins).getRhs();
                Variable dest = ((Binary) ins).getDest();
                if (ok(lhs) && ok(rhs)) {
                    int l = get(lhs);
                    int r = get(rhs);
                    int d = -1;
                    if (ins instanceof Add) d = l + r;
                    if (ins instanceof And) d = l & r;
                    if (ins instanceof Cmp) {
                        String op = ((Cmp) ins).getOp();
                        if (op.equals("==")) d = l == r ? 1 : 0;
                        if (op.equals("!=")) d = l != r ? 1 : 0;
                        if (op.equals("<")) d = l < r ? 1 : 0;
                        if (op.equals("<=")) d = l <= r ? 1 : 0;
                        if (op.equals(">")) d = l > r ? 1 : 0;
                        if (op.equals(">=")) d = l >= r ? 1 : 0;
                    }
                    if (ins instanceof Div) {
                        if (r != 0) d = l / r;
                        else d = -1;
                    }
                    if (ins instanceof Mod) {
                        if (r != 0) d = l % r;
                        else d = -1;
                    }
                    if (ins instanceof Mul) d = l * r;
                    if (ins instanceof Or) d = l | r;
                    if (ins instanceof Sal) d = l << r;
                    if (ins instanceof Sar) d = l >> r;
                    if (ins instanceof Sub) d = l - r;
                    if (ins instanceof Xor) d = l ^ r;
                    value.put(dest, d);
                    inst.set(i, new Move(dest, new Immediate(d)));
                } else {
                    value.remove(dest);
                }
            } else if (ins instanceof Move) {
                Operand lhs = ((Move) ins).getLhs();
                Operand rhs = ((Move) ins).getRhs();
                if (lhs instanceof Variable && ok(rhs)) {
                    int r = get(rhs);
                    value.put((Variable) lhs, r);
                    inst.set(i, new Move(lhs, new Immediate(r)));
                } else if (lhs instanceof Variable) value.remove(lhs);
            } else if (ins instanceof Unary) {
                Operand expr = ((Unary) ins).getExpr();
                Variable dest = ((Unary) ins).getDest();
                if (ins instanceof Inc || ins instanceof Dec) {
                    if (expr instanceof Variable && ok(expr)){
                        int d = get(expr);
                        if (ins instanceof Inc) d = d + 1;
                        if (ins instanceof Dec) d = d - 1;
                        value.put((Variable) expr, d);
                        inst.set(i, new Move(expr, new Immediate(d)));
                    } else if (expr instanceof Variable) value.remove(expr);
                } else {
                    if (ok(expr)) {
                        int e = get(expr);
                        int d = -1;
                        if (ins instanceof Not) d = e == 1 ? 0 : 1;
                        if (ins instanceof Neg) d = ~e;
                        value.put(dest, d);
                        inst.set(i, new Move(dest, new Immediate(d)));
                    } else value.remove(dest);
                }
            }
        }
//        System.out.println(1);
    }
}
