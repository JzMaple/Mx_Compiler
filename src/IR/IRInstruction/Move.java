package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;

public class Move extends Bin {
    public Move(Operand lhs, Operand rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
