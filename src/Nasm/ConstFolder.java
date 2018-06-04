package Nasm;

import IR.IRInstruction.*;
import IR.IRInstruction.Operand.Immediate;
import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;
import IR.IRNode.IRFunction;

import java.util.*;

public class ConstFolder {
    private Boolean ok(Operand op) {
        if (op instanceof Immediate) return true;
        else return (op instanceof Variable) && ((Variable) op).gotValue();
    }

    private int get(Operand op) {
        if (op instanceof Immediate) return ((Immediate) op).getValue();
        else if (op instanceof Variable) return ((Variable) op).getValue();
        else return -1;
    }

    public void fold(IRFunction function) {
        List<IRInstruction> inst = function.getStatements();
        int size = inst.size();
        for (int i = 0; i < size; ++i) {
            IRInstruction ins = inst.get(i);
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
                    if (ins instanceof Div) d = l / r;
                    if (ins instanceof Mod) d = l % r;
                    if (ins instanceof Mul) d = l * r;
                    if (ins instanceof Or) d = l | r;
                    if (ins instanceof Sal) d = l << r;
                    if (ins instanceof Sar) d = l >> r;
                    if (ins instanceof Sub) d = l - r;
                    if (ins instanceof Xor) d = l ^ r;
                    dest.setValue(d);
                    inst.set(i, new Move(dest, new Immediate(d)));
                }
            } else if (ins instanceof Move) {
                Operand lhs = ((Move) ins).getLhs();
                Operand rhs = ((Move) ins).getRhs();
                if (lhs instanceof Variable && ok(rhs)) {
                    int r = get(rhs);
                    ((Variable) lhs).setValue(r);
                    inst.set(i, new Move(lhs, new Immediate(r)));
                }
            }
        }
    }
}
