package Visitor;

import java.util.*;

import Type.*;
import Exception.*;

public class FunctionList {
    private MyException error = new MyException();
    private ClassList class_list;
    private Map<String, FunctionType> function_list = new HashMap<>();

    public FunctionList(ClassList _class_list) {
        class_list = _class_list;
    }

    private FunctionType _getInt() {
        return new FunctionType(class_list.getClass("int"), new Vector<>());
    }

    private FunctionType _getString() {
        return new FunctionType(class_list.getClass("string"), new Vector<>());
    }

    private FunctionType _print() {
        Vector<BaseType> parameters = new Vector<>();
        parameters.add(class_list.getClass("string"));
        return new FunctionType(class_list.getClass("void"), parameters);
    }

    private FunctionType _println() {
        Vector<BaseType> parameters = new Vector<>();
        parameters.add(class_list.getClass("string"));
        return new FunctionType(class_list.getClass("void"), parameters);
    }

    private FunctionType _toString() {
        Vector<BaseType> parameters = new Vector<>();
        parameters.add(class_list.getClass("int"));
        return new FunctionType(class_list.getClass("string"), parameters);
    }

    public void setFunction_list(ClassList _class_list) {
        class_list = _class_list;
        function_list.put("getInt", _getInt());
        function_list.put("getSting", _getString());
        function_list.put("print", _print());
        function_list.put("println", _println());
        function_list.put("toString", _toString());
    }

    private void error(String errorMessage) {
        System.err.println(errorMessage);
        System.exit(1);
    }

    private void _insertFunction(String function_name, FunctionType function_type) throws Exception {
        if (function_list.containsKey(function_name))
            throw new FunctionException("already have the function named \"" + function_name + "\"");
        else
            function_list.put(function_name, function_type);
    }

    public void insertFunction(String function_name, FunctionType function_type) {
        try {
            _insertFunction(function_name, function_type);
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    private FunctionType _getFunctionType(String function_name) throws Exception {
        if (!function_list.containsKey(function_name))
            throw new FunctionException("have no such function named \"" + function_name + "\"");
        else
            return function_list.get(function_name);
    }

    public FunctionType getFunctionType(String function_name) {
        try {
            return _getFunctionType(function_name);
        } catch (Exception e) {
            error.printException();
            System.exit(1);
            return null;
        }
    }
}
