package Type;

import Exception.*;

public class UserType extends BaseType {
    private String class_name;

    public UserType(String _class_name) {
        class_name = _class_name;
    }

    @Override
    public String getClassName() {
        return class_name;
    }

    private void _insertClassVariable(String variable_name, BaseType variable_type) throws Exception {
        if (class_member_variable.containsKey(variable_name))
            throw new ClassException("The class has already had a member variable named\"" + variable_name + "\"");
        else
            class_member_variable.put(variable_name, variable_type);
    }

    public void insertClassVariable(String variable_name, BaseType variable_type) {
        try {
            _insertClassVariable(variable_name, variable_type);
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    private void _insertClassFunction(String function_name, FunctionType function_type) throws Exception {
        if (class_member_function.containsKey(function_name))
            throw new ClassException("The class has already had a member function named\"" + function_name + "\"");
        else
            class_member_function.put(function_name, function_type);
    }

    public void insertClassFunction(String function_name, FunctionType function_type){
        try {
            _insertClassFunction(function_name, function_type);
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }
}
