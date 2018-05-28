package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;

public class Sar extends Bin {
    public Sar(Operand lhs, Operand rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.dest = new Variable("tmp", null, false);
    }
}
