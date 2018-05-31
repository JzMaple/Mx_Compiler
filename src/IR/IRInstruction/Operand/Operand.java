package IR.IRInstruction.Operand;

import IR.IR;

import java.util.Set;

abstract public class Operand extends IR {
    Boolean isString;

    public Boolean getIsString() {
        return isString;
    }

    public void setIsString(Boolean isString) {
        this.isString = isString;
    }

    abstract public Set<Variable> getDef();

    abstract public Set<Variable> getUse();
}
