package IRnode;

import Type.BaseType;
import Type.FunctionType;

public class IRFunctionNode extends IRnode {
    private FunctionType function_type;

    public IRFunctionNode(FunctionType _function_type) {
        function_type = _function_type;
        leaf_value = false;
    }

    @Override
    public FunctionType getType() {
        return function_type;
    }
}
