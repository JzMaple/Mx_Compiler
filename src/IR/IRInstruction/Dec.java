package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;

public class Dec extends UnBin {
    public Dec(Operand expr) {
        this.expr = expr;
    }
}
