package IR.IRInstruction.Operand;

import Type.BaseType;

public class Memory extends Operand {
    private Operand base;
    private Operand index;
    private int scale;
    private int number;
    private BaseType type;
    private Variable base_var;
    private Variable index_var;

    public Memory(Operand base, Operand index, int scale, int number, BaseType type) {
        this.base = base;
        this.index = index;
        this.scale = scale;
        this.number = number;
        this.type = type;
        this.isString = false;
        this.base_var = new Variable("base", null, false);
        this.index_var = new Variable("index", null, false);
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

    public Variable getBase_var() {
        return base_var;
    }

    public Variable getIndex_var() {
        return index_var;
    }
}
