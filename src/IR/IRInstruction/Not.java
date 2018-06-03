package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;

public class Not extends Unary {
    public Not(Operand expr, Variable dest) {
        this.expr = expr;
        this.dest = dest;
    }
}
