package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;

public class Neg extends Unary {
    public Neg(Operand expr, Variable dest) {
        this.expr = expr;
        this.dest = dest;
    }
}
