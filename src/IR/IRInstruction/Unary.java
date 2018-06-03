package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;

abstract public class Unary extends IRInstruction {
    protected Operand expr;
    protected Variable dest;

    public Variable getDest() {
        return dest;
    }

    public Operand getExpr() {
        return expr;
    }
}
