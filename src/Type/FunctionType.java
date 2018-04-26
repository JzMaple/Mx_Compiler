package Type;

import java.util.Vector;

public class FunctionType extends BaseType{
    private BaseType return_type;
    private Vector<BaseType> parameter_type_list;

    @Override
    public String getClassName() {
        return "function";
    }

    public FunctionType(BaseType _return_type, Vector<BaseType> _parameter_type_list) {
        return_type = _return_type;
        parameter_type_list = _parameter_type_list;
    }

    public BaseType getReturnType() {
        return return_type;
    }

    public Vector<BaseType> getParameterTypeList() {
        return parameter_type_list;
    }
}
