package SemanticNode;

import Type.*;

public class SemanticExpressionNode extends SemanticNode {
    private BaseType expression_type;

    public SemanticExpressionNode(BaseType _expression_type, Boolean _left_value) {
        expression_type = _expression_type;
        leaf_value = _left_value;
    }

    public BaseType getType() {
        return expression_type;
    }
}
