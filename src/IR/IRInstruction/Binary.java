package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;

abstract public class Binary extends IRInstruction {
    protected Operand lhs;
    protected Operand rhs;
    protected Variable dest;

    public Operand getLhs() {
        return lhs;
    }

    public Operand getRhs() {
        return rhs;
    }

    public Variable getDest() {
        return dest;
    }
}
