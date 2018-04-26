package Visitor;

import java.util.*;

import Type.*;
import Exception.*;

public class ClassList {
    private MyException error = new MyException();
    private Map<String, BaseType> class_list = new HashMap<>();

    public ClassList() {
        class_list.put("int", new IntType());
        class_list.put("void", new VoidType());
        class_list.put("bool", new BoolType());
        class_list.put("string", new StringType());
        class_list.put("null", new NullType());
    }

    private void _insertClass(String class_name) throws Exception {
        if (class_list.containsKey(class_name))
            throw new ClassException("Already have a class named\"" + class_name + "\"");
        BaseType new_class = new UserType(class_name);
        class_list.put(class_name, new_class);
    }

    public void insertClass(String class_name) {
        try {
            _insertClass(class_name);
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    private BaseType _getClass(String class_name) throws Exception {
        if (class_list.containsKey(class_name))
            return class_list.get(class_name);
        else if (class_name.length() > 2 && class_name.substring(class_name.length() - 2).equals("[]")) {
            BaseType basic_array_type = _getClass(class_name.substring(0, class_name.length() - 2));
            BaseType new_type = new ArrayType(basic_array_type);
            class_list.put(class_name, new_type);
            return new_type;
        } else {
            throw new ClassException("Have no class named\"" + class_name + "\" and it is not an array either");
        }
    }

    public BaseType getClass(String class_name) {
        try {
            return _getClass(class_name);
        } catch (Exception e) {
            error.printException();
            System.exit(1);
            return null;
        }
    }
}
