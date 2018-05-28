package IR.IRInstruction.Operand;

import Type.BaseType;

public class Memory extends Operand {
    Operand base;
    Operand index;
    int scale;
    int number;
    BaseType type;

    public Memory(Operand base, Operand index, int scale, int number, BaseType type) {
        this.base = base;
        this.index = index;
        this.scale = scale;
        this.number = number;
        this.type = type;
    }

    public Operand getBase() {
        return base;
    }

    public Operand getIndex() {
        return index;
    }

    public int getScale() {
        return scale;
    }

    public int getNumber() {
        return number;
    }

    public BaseType getType() {
        return type;
    }
}
