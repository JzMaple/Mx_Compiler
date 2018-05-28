package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;

public class And extends Bin {
    public And(Operand lhs, Operand rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.dest = new Variable("tmp", null, false);
    }
}
