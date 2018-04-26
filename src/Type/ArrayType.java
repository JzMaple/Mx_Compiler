package Type;

import java.util.*;
import Exception.*;

public class ArrayType extends BaseType {
    private BaseType basic_array_type;

    @Override
    public String getClassName(){
        return "array";
    }

    public ArrayType(BaseType _basic_array_type) {
        try {
            if (_basic_array_type instanceof VoidType)
                throw new VariableException("Cannot have a void type array");
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
        basic_array_type = _basic_array_type;
        class_member_function.put("size", new FunctionType(new IntType(), new Vector<>()));
    }

    public BaseType getBasicArrayType() {
        return basic_array_type;
    }
}
