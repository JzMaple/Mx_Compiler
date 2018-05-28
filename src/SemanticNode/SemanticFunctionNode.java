package SemanticNode;

import Type.FunctionType;

public class SemanticFunctionNode extends SemanticNode {
    private FunctionType function_type;

    public SemanticFunctionNode(FunctionType _function_type) {
        function_type = _function_type;
        leaf_value = false;
    }

    @Override
    public FunctionType getType() {
        return function_type;
    }
}
