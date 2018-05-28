package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;

public class Mod extends Bin{
    public Mod(Operand lhs, Operand rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.dest = new Variable("tmp", null, false);
    }
}
