package IRnode;

import Type.*;

public class IRVariableNode extends IRnode {
    private BaseType variable_type;

    public IRVariableNode(BaseType _variable_type, Boolean _left_value) {
        variable_type = _variable_type;
        leaf_value = _left_value;
    }

    @Override
    public BaseType getType() {
        return variable_type;
    }
}
