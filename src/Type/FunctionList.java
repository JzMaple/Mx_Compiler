package Type;

import Main.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class FunctionList {
    private FunctionList outer;
    private ClassList class_list;
    private Map<String, FunctionType> function_list;

    public FunctionList(FunctionList other, FunctionList _outer) {
        class_list = Main.getClassList();
        if (other != null) function_list = other.getFunctionList();
        else function_list = new HashMap<>();
        outer = _outer;
    }

/******************************************************************************************************************

    Inside Function Set for Global, Array, String
    Global : int getInt(), string getString(), void print(string), void println(string), string toString(int)
    Array : int size()
    String : int length(), string substring(int, int), int parseInt(), int ord(int pos)

 *******************************************************************************************************************/
    // Global Inside Function Set
    private FunctionType _getInt() {
        Vector<BaseType> parameters = new Vector<>();
        return new FunctionType(class_list.getClassType("int"), parameters, new Vector<>());
    }

    private FunctionType _getString() {
        Vector<BaseType> parameters = new Vector<>();
        return new FunctionType(class_list.getClassType("string"), parameters, new Vector<>());
    }

    private FunctionType _print() {
        Vector<BaseType> parameters = new Vector<>();
        parameters.add(class_list.getClassType("string"));
        Vector<String> parameters_name = new Vector<>();
        parameters_name.add("str");
        return new FunctionType(class_list.getClassType("void"), parameters, parameters_name);
    }

    private FunctionType _println() {
        Vector<BaseType> parameters = new Vector<>();
        parameters.add(class_list.getClassType("string"));
        Vector<String> parameters_name = new Vector<>();
        parameters_name.add("str");
        return new FunctionType(class_list.getClassType("void"), parameters, parameters_name);
    }

    private FunctionType _toString() {
        Vector<BaseType> parameters = new Vector<>();
        parameters.add(class_list.getClassType("int"));
        Vector<String> parameters_name = new Vector<>();
        parameters_name.add("i");
        return new FunctionType(class_list.getClassType("string"), parameters, parameters_name);
    }

    public void setGlobalInsideFunctionList() {
        function_list.put("getInt", _getInt());
        function_list.put("getString", _getString());
        function_list.put("print", _print());
        function_list.put("println", _println());
        function_list.put("toString", _toString());
    }

    // Array Inside Function Set
    private FunctionType _size() {
        Vector<BaseType> parameters = new Vector<>();
        return new FunctionType(class_list.getClassType("int"), parameters, new Vector<>());
    }

    public void setArrayInsideFunctionList() {
        function_list.put("size", _size());
    }

    // String Inside Function Set
    private FunctionType _length() {
        Vector<BaseType> parameters = new Vector<>();
        return new FunctionType(class_list.getClassType("int"), parameters, new Vector<>());
    }

    private FunctionType _substring(StringType it) {
        Vector<BaseType> parameters = new Vector<>();
        parameters.add(class_list.getClassType("int"));
        parameters.add(class_list.getClassType("int"));
        Vector<String> parameters_name = new Vector<>();
        parameters_name.add("left");
        parameters_name.add("right");
        return new FunctionType(it, parameters, parameters_name);
    }

    private FunctionType _parseInt() {
        Vector<BaseType> parameters = new Vector<>();
        return new FunctionType(class_list.getClassType("int"), parameters, new Vector<>());
    }

    private FunctionType _ord() {
        Vector<BaseType> parameters = new Vector<>();
        parameters.add(class_list.getClassType("int"));
        Vector<String> parameters_name = new Vector<>();
        parameters_name.add("pos");
        return new FunctionType(class_list.getClassType("int"), parameters, parameters_name);
    }

    public void setStringInsideFunctionList(StringType it) {
        function_list.put("length", _length());
        function_list.put("substring", _substring(it));
        function_list.put("parseInt", _parseInt());
        function_list.put("ord", _ord());
    }

/****************************************************************************************************************

    FunctionList port:
        Boolean insertFunction(String function_name, FunctionType function_type)
        Boolean changeFunction(String function_name, FunctionType function_type)
        FunctionType getFunctionType(String function_name)
        Map<String, FunctionType> getFunctionList()

*****************************************************************************************************************/

    public Boolean insertFunction(String function_name, FunctionType function_type) {
        if (function_list.containsKey(function_name))
            return false;
        else {
            function_list.put(function_name, function_type);
            return true;
        }
    }

    public void changeFunction(String function_name, FunctionType function_type) {
        function_list.put(function_name, function_type);
    }

    public FunctionType getFunctionType(String function_name) {
        if (function_list.containsKey(function_name))
            return function_list.get(function_name);
        else if (outer != null)
            return outer.getFunctionType(function_name);
        else
            return null;
    }

    public Map<String, FunctionType> getFunctionList() {
        return function_list;
    }
}
