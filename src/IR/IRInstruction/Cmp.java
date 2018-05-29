package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;

public class Cmp extends Bin {
    private String op;
    public Cmp(Operand lhs, Operand rhs, String op) {
        this.lhs = lhs; this.rhs = rhs; this.op = op;
        this.dest = new Variable("tmp", null, false);
    }

    public String getOp() {
        return op;
    }
}
