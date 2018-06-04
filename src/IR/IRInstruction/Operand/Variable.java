package IR.IRInstruction.Operand;

import Nasm.RegX86;
import Nasm.StackAllocator;
import Type.BaseType;
import IR.IRBuilder;

import java.util.HashSet;
import java.util.Set;

public class Variable extends Operand {
    private String variable_name;
    private BaseType variable_type;
    private Boolean isGlobal;
    private Boolean isConstant;
    private RegX86 reg;
    private int begin = 0;
    private int end = 0;
    private int life = 0;
    private int use = 0;
    private int paraOrd = -1;
    private int value = -1;
    private Boolean gotValue = false;

    public Variable(String variable_name, BaseType variable_type, Boolean isGlobal, StackAllocator stackAllocator) {
        this.variable_name = variable_name;
        this.variable_type = variable_type;
        this.isGlobal = isGlobal;
        if (!isGlobal && stackAllocator != null)
            stackAllocator.insert(this);
        this.isConstant = false;
    }

    public String getName() {
        return variable_name;
    }

    public BaseType getType() {
        return variable_type;
    }

    public Boolean isGlobal() {
        return isGlobal;
    }

    public Boolean getIsConstant() {
        return isConstant;
    }

    public void setIsConstant(Boolean isConstant) {
        this.isConstant = isConstant;
    }

    public void setReg(RegX86 reg) {
        this.reg = reg;
    }

    public RegX86 getReg() {
        return reg;
    }

    @Override
    public Set<Variable> getDef() {
        Set<Variable> def = new HashSet<>();
        if (!isGlobal) def.add(this);
        return def;
    }

    @Override
    public Set<Variable> getUse() {
        Set<Variable> use = new HashSet<>();
        if (!isGlobal) use.add(this);
        return use;
    }

    public void setBegin(int begin) {
        this.life = this.life + this.end - this.begin;
        this.begin = begin;
        this.end = begin;
    }

    public void setEnd(int end) {
        if (end > this.end) this.end = end;
    }

    public int getLife() {
        if (begin != end)  {
            life = life + end - begin;
            begin = end;
        }
        return life;
    }

    public void setUse() {
        ++use;
    }

    public int getUsed() {
        return use;
    }

    @Override
    public void setParaOrd(int ord) {
        this.paraOrd = ord;
    }

    public int getParaOrd() {
        return this.paraOrd;
    }

    public void setValue(int value) {
        if (value != -1) {
            this.value = value;
            this.gotValue = true;
        }
    }

    public int getValue() {
        return value;
    }

    public Boolean gotValue() {
        return gotValue;
    }
}
