package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;

public class Move extends IRInstruction {
    private Operand lhs;
    private Operand rhs;

    public Move(Operand lhs, Operand rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Operand getLhs() {
        return lhs;
    }

    public Operand getRhs() {
        return rhs;
    }
}
