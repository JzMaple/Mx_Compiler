package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;

public class Return extends IRInstruction {
    Operand ret;
    public Return(Operand ret) {
        this.ret = ret;
    }

    public Operand getRet() {
        return ret;
    }
}
