package Type;

import Exception.*;

abstract public class BaseType {
    protected MyException error = new MyException();
    protected VariableList class_member_variable = new VariableList(null,null);
    protected FunctionList class_member_function = new FunctionList(null,null);

    public BaseType() {}

    public abstract String getClassName();

    public FunctionList getMemberFunctionList() {
        return class_member_function;
    }

    public VariableList getMemberVariableList() {
        return class_member_variable;
    }

    public Boolean insertMemberVariable(String variable_name, BaseType variable_type) {
        return class_member_variable.insertVariable(variable_name, variable_type);
    }

    public Boolean insertMemberFunction(String function_name, FunctionType function_type) {
        return class_member_function.insertFunction(function_name, function_type);
    }

    public Boolean changeMemberFunction(String function_name, FunctionType function_type) {
        return class_member_function.changeFunction(function_name, function_type);
    }

    public BaseType getMemberVariableType(String variable_name) {
        return class_member_variable.getVariableType(variable_name);
    }

    public FunctionType getMemberFunctionType(String function_name) {
        return class_member_function.getFunctionType(function_name);
    }

    public Boolean assignment_check(BaseType x) {
        //check assign statement's validation (this = x;)
        if (this == x) return true;
        if (x == null) return false;
        if (x instanceof NullType && (this instanceof ArrayType || this instanceof UserType)) return true;
        if (this instanceof UserType && x instanceof UserType) return this.getClassName().equals(x.getClassName());
        if (this instanceof ArrayType && x instanceof ArrayType )
            return ((ArrayType) this).getBasicArrayType().assignment_check(((ArrayType) x).getBasicArrayType());
        return this.getClass() == x.getClass();
    }
}
