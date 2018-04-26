package IRnode;

import Type.*;
import java.util.*;

public class IRParameterNode extends IRnode {
    private Vector<BaseType> parameter_type_list;

    public IRParameterNode(Vector<BaseType> _parameter_type_list) {
        parameter_type_list = _parameter_type_list;
        leaf_value = false;
    }

    @Override
    public BaseType getType() {
        return null;
    }

    public Vector<BaseType> getParameterTypeList() {
        return parameter_type_list;
    }
}