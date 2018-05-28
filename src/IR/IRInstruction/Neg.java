package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;

public class Neg extends UnBin {
    public Neg(Operand expr) {
        this.expr = expr;
        this.dest = new Variable("tmp", null, false);
    }
}
