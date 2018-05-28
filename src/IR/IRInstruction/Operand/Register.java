package IR.IRInstruction.Operand;

import NasmTranslate.RegX86;

public class Register extends Operand {
    RegX86 reg = null;
    Boolean isStore = false;
    int offset;

    public void setReg(RegX86 reg) {
        this.reg = reg;
    }

    public RegX86 getReg() {
        return reg;
    }

    public void setIsStore(Boolean b) {
        this.isStore = b;
    }

    public Boolean getIsStore() {
        return isStore;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }
}
