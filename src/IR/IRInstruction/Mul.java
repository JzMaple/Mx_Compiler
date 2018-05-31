package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;

public class Mul extends Bin {
    public Mul(Operand lhs, Operand rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.dest = new Variable("mul_tmp", null, false);
    }
}
