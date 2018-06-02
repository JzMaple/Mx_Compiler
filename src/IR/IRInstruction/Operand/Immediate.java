package IR.IRInstruction.Operand;

import java.util.HashSet;
import java.util.Set;

public class Immediate extends Operand {
    private int value;

    public Immediate(int int_value) {
        this.value = int_value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public Set<Variable> getDef() {
        return new HashSet<>();
    }

    @Override
    public Set<Variable> getUse() {
        return new HashSet<>();
    }

    @Override
    public void setParaOrd(int ord) {

    }
}
