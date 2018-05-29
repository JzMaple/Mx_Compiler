package IR.IRInstruction.Operand;

import NasmTranslate.RegX86;
import NasmTranslate.StackAlloc;
import Type.BaseType;
import Visitor.IRBuilder;

public class Variable extends Operand {
    private String variable_name;
    private BaseType variable_type;
    private Boolean isGlobal;
    private Boolean isString = false;
    private StackAlloc current_stackAlloc = IRBuilder.getCurrent_stackAlloc();

    public Variable(String variable_name, BaseType variable_type, Boolean isGlobal) {
        this.variable_name = variable_name;
        this.variable_type = variable_type;
        this.isGlobal = isGlobal;
        if (!isGlobal)
            current_stackAlloc.insert(this);
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

    public Boolean getIsString() {
        return isString;
    }
}
