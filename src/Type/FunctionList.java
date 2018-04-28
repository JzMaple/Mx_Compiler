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
        return new FunctionType(class_list.getClassType("int"), parameters);
    }

    private FunctionType _getString() {
        Vector<BaseType> parameters = new Vector<>();
        return new FunctionType(class_list.getClassType("string"), parameters);
    }

    private FunctionType _print() {
        Vector<BaseType> parameters = new Vector<>();
        parameters.add(class_list.getClassType("string"));
        return new FunctionType(class_list.getClassType("void"), parameters);
    }

    private FunctionType _println() {
        Vector<BaseType> parameters = new Vector<>();
        parameters.add(class_list.getClassType("string"));
        return new FunctionType(class_list.getClassType("void"), parameters);
    }

    private FunctionType _toString() {
        Vector<BaseType> parameters = new Vector<>();
        parameters.add(class_list.getClassType("int"));
        return new FunctionType(class_list.getClassType("string"), parameters);
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
        return new FunctionType(class_list.getClassType("int"), parameters);
    }

    public void setArrayInsideFunctionList() {
        function_list.put("size", _size());
    }

    // String Inside Function Set
    private FunctionType _length() {
        Vector<BaseType> parameters = new Vector<>();
        return new FunctionType(class_list.getClassType("int"), parameters);
    }

    private FunctionType _substring(StringType it) {
        Vector<BaseType> parameters = new Vector<>();
        parameters.add(class_list.getClassType("int"));
        parameters.add(class_list.getClassType("int"));
        return new FunctionType(it, parameters);
    }

    private FunctionType _parseInt() {
        Vector<BaseType> parameters = new Vector<>();
        return new FunctionType(class_list.getClassType("int"), parameters);
    }

    private FunctionType _ord() {
        Vector<BaseType> parameters = new Vector<>();
        parameters.add(class_list.getClassType("int"));
        return new FunctionType(class_list.getClassType("int"), parameters);
    }

    public void setStringInsideFunctionList(StringType it) {
        function_list.put("length", _length());
        function_list.put("substring", _substring(it));
        function_list.put("parseInt", _parseInt());
        function_list.put("ord", _ord());
    }

/****************************************************************************************************************

    FunctionList port:
        void insertFunction(String function_name, FunctionType type)
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
