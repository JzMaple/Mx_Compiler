package IR.IRInstruction.Operand;

import IR.IR;

abstract public class Operand extends IR {
    Boolean isString;

    public Boolean getIsString() {
        return isString;
    }

    public void setIsString(Boolean isString) {
        this.isString = isString;
    }
}
