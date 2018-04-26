package Type;

import java.util.*;

import Exception.*;

abstract public class BaseType {
    protected MyException error = new MyException();
    Map<String, BaseType> class_member_variable = new HashMap<>();
    Map<String, FunctionType> class_member_function = new HashMap<>();

    public BaseType() {}

    public abstract String getClassName();

    public void insertClassFunction(String function_name, FunctionType function_type) {
    }

    public void insertClassVariable(String variable_name, BaseType member_type) {
    }

    private BaseType _variableQuery(String variableName) throws Exception {
        if (!class_member_variable.containsKey(variableName))
            throw new ClassException("Undefined class member variable\"" + variableName + "\"");
        else
            return class_member_variable.get(variableName);
    }

    private FunctionType _functionQuery(String functionName) throws Exception {
        if (!class_member_function.containsKey(functionName))
            throw new ClassException("Undefined class member function\"" + functionName + "\"");
        else
            return class_member_function.get(functionName);
    }

    public BaseType getVariableType(String variableName) {
        try {
            return _variableQuery(variableName);
        } catch (Exception e) {
            error.printException();
            System.exit(1);
            return null;
        }
    }

    public FunctionType getFunctionType(String functionName) {
        try {
            return _functionQuery(functionName);
        } catch (Exception e) {
            error.printException();
            System.exit(1);
            return null;
        }
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

    public boolean contain(String name) {
        return class_member_variable.containsKey(name) || class_member_function.containsKey(name);
    }
}
