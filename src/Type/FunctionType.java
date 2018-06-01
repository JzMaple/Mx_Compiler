package Type;

import java.util.Vector;

public class FunctionType extends BaseType {
    private BaseType return_type;
    private Vector<BaseType> parameter_type_list;
    private Vector<String> parameter_name_list;

    @Override
    public String getClassName() {
        return "function";
    }

    public FunctionType(BaseType return_type, Vector<BaseType> parameter_type_list, Vector<String> parameter_name_list) {
        this.return_type = return_type;
        this.parameter_type_list = parameter_type_list;
        this.parameter_name_list = parameter_name_list;
    }

    public BaseType getReturnType() {
        return return_type;
    }

    public Vector<BaseType> getParameterTypeList() {
        return parameter_type_list;
    }

    public Vector<String> getParameterNameList() {
        return parameter_name_list;
    }
}
