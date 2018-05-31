package IR.IRInstruction.Operand;

import NasmTranslate.RegX86;
import NasmTranslate.StackAllocator;
import Type.BaseType;
import Visitor.IRBuilder;

import java.util.HashSet;
import java.util.Set;

public class Variable extends Operand {
    private String variable_name;
    private BaseType variable_type;
    private Boolean isGlobal;
    private Boolean isConstant;
    private StackAllocator current_stackAlloc = IRBuilder.getCurrent_stackAlloc();
    private RegX86 reg;

    public Variable(String variable_name, BaseType variable_type, Boolean isGlobal) {
        this.variable_name = variable_name;
        this.variable_type = variable_type;
        this.isGlobal = isGlobal;
        if (!isGlobal)
            current_stackAlloc.insert(this);
        this.isString = false;
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

    public void setIsString(Boolean isString) {
        this.isString = isString;
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
        def.add(this);
        return def;
    }

    @Override
    public Set<Variable> getUse() {
        Set<Variable> use = new HashSet<>();
        use.add(this);
        return use;
    }
}
