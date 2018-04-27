package Visitor;

import java.util.*;

import Type.*;

import Exception.*;

public class VariableList {
    private MyException error = new MyException();
    private VariableList outer;
    private Map<String, BaseType> variable_list;

    public VariableList(VariableList _outer) {
        outer = _outer;
        variable_list = new HashMap<>();
    }

    private void _insertVariable(String variable_name, BaseType variable_type) throws Exception {
        if (variable_list.containsKey(variable_name))
            throw new VariableException("already have a variable named \"" + variable_name + "\"");
        else
            variable_list.put(variable_name, variable_type);
    }

    public void insertVariable(String variable_name, BaseType variable_type) {
        try {
            _insertVariable(variable_name, variable_type);
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }
    private BaseType _getVariableType(String variable_name) throws Exception {
        if (variable_list.containsKey(variable_name))
            return variable_list.get(variable_name);
        else if (outer != null)
            return outer.getVariableType(variable_name);
        else
            throw new VariableException("have no variable named\"" + variable_name + "\"");
    }

    public BaseType getVariableType(String variable_name) {
        try {
            return _getVariableType(variable_name);
        } catch (Exception e) {
            error.printException();
            System.exit(1);
            return null;
        }
    }
}
