package Type;

import java.util.*;

public class StringType extends BaseType {
    @Override
    public String getClassName(){
        return "string";
    }

    private FunctionType substringFunctionType() {
        Vector<BaseType> parameter = new Vector<>();
        parameter.add(new IntType());
        parameter.add(new IntType());
        return new FunctionType(this, parameter);
    }

    private FunctionType parseIntFunctionType() {
        return new FunctionType(new IntType(), new Vector<>());
    }

    private FunctionType ordFunctionType() {
        Vector<BaseType> parameter = new Vector<>();
        parameter.add(new IntType());
        return new FunctionType(new IntType(), parameter);
    }

    private FunctionType lengthFunctionType() {
        return new FunctionType(new IntType(), new Vector<>());
    }

    public StringType() {
        class_member_function.put("substring", substringFunctionType());
        class_member_function.put("parseInt", parseIntFunctionType());
        class_member_function.put("ord", ordFunctionType());
        class_member_function.put("length", lengthFunctionType());
    }
}
