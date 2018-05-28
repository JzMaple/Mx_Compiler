package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;

public class Inc extends UnBin {
    public Inc(Operand expr) {
        this.expr = expr;
    }
}
