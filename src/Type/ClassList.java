package Type;

import java.util.HashMap;
import java.util.Map;

public class ClassList {
    private Map<String, BaseType> class_list = new HashMap<>();

    public ClassList() {
    }

    public void setClassList() {
        class_list.put("int", new IntType());
        class_list.put("void", new VoidType());
        class_list.put("bool", new BoolType());
        class_list.put("string", new StringType());
        class_list.put("null", new NullType());
    }

    public Boolean insertClass(String class_name) {
        if (class_list.containsKey(class_name))
            return false;
        else {
            BaseType new_class = new UserType(class_name);
            class_list.put(class_name, new_class);
            return true;
        }
    }

    public BaseType getClassType(String class_name) {
        if (class_list.containsKey(class_name))
            return class_list.get(class_name);
        else if (class_name.length() > 2 && class_name.substring(class_name.length() - 2).equals("[]")) {
            BaseType basic_array_type = getClassType(class_name.substring(0, class_name.length() - 2));
            BaseType new_type = new ArrayType(basic_array_type);
            class_list.put(class_name, new_type);
            return new_type;
        } else {
            return null;
        }
    }

    public Map<String, BaseType> getClassList() {
        return class_list;
    }
}
