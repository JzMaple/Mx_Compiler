package Type;

import java.util.*;
import Exception.*;

public class ArrayType extends BaseType {
    private MyException error = new MyException();
    private String class_name;
    private BaseType basic_array_type;

    @Override
    public String getClassName(){
        return class_name;
    }

    public ArrayType(BaseType _basic_array_type, String class_name) {
        try {
            if (_basic_array_type instanceof VoidType)
                throw new VariableException("Cannot have a void type array");
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
        basic_array_type = _basic_array_type;
        class_member_function.setArrayInsideFunctionList();
        this.class_name = class_name;
    }

    public BaseType getBasicArrayType() {
        return basic_array_type;
    }
}
