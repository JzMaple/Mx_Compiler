package IRnode;

import Type.*;

public class IRExpressionNode extends IRnode {
    private BaseType expression_type;

    public IRExpressionNode(BaseType _expression_type, Boolean _left_value) {
        expression_type = _expression_type;
        leaf_value = _left_value;
    }

    public BaseType getType() {
        return expression_type;
    }
}
