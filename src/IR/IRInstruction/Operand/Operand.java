package IR.IRInstruction.Operand;

import IR.IR;

import java.util.Set;

abstract public class Operand extends IR {
    private Boolean isString = false;

    public Boolean getIsString() {
        return isString;
    }

    public void setIsString(Boolean isString) {
        this.isString = isString;
    }

    abstract public Set<Variable> getDef();

    abstract public Set<Variable> getUse();

    abstract public void setParaOrd(int ord);
}
