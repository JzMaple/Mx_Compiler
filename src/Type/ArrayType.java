package Type;

public class ArrayType extends BaseType {
    private String class_name;
    private BaseType basic_array_type;

    @Override
    public String getClassName(){
        return class_name;
    }

    public ArrayType(BaseType _basic_array_type, String class_name) {
        if (_basic_array_type instanceof VoidType) {
            System.err.println("Cannot have a void type array");
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
