package IR.IRInstruction.Operand;

import Type.BaseType;

import java.util.HashSet;
import java.util.Set;

public class Memory extends Operand {
    private Operand base;
    private Operand index;
    private int scale;
    private int number;
    private BaseType type;

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

    @Override
    public Set<Variable> getDef() {
        Set<Variable> def = new HashSet<>();
        return def;
    }

    @Override
    public Set<Variable> getUse() {
        Set<Variable> use = new HashSet<>();
        if (base instanceof Variable && !((Variable) base).isGlobal())
            use.add((Variable) base);
        if (index instanceof Variable && !((Variable) index).isGlobal())
            use.add((Variable) index);
        return use;
    }

    @Override
    public void setParaOrd(int ord) {
        if (base != null) base.setParaOrd(ord);
        if (index != null) index.setParaOrd(ord);
    }
}
