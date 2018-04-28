package Type;

public class StringType extends BaseType {
    @Override
    public String getClassName(){
        return "string";
    }

    public StringType() {
        class_member_function.setStringInsideFunctionList(this);
    }
}
