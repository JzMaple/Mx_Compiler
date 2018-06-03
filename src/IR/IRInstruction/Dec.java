package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;

public class Dec extends Unary {
    public Dec(Operand expr) {
        this.expr = expr;
    }
}
