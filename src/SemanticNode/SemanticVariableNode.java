package SemanticNode;

import Type.BaseType;

public class SemanticVariableNode extends SemanticNode {
    private BaseType variable_type;

    public SemanticVariableNode(BaseType _variable_type, Boolean _left_value) {
        variable_type = _variable_type;
        leaf_value = _left_value;
    }

    @Override
    public BaseType getType() {
        return variable_type;
    }
}
