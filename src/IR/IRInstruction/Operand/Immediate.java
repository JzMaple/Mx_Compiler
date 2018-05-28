package IR.IRInstruction.Operand;

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
}
