package SemanticNode;

import Type.*;

public class SemanticClassNode extends SemanticNode {
    private BaseType class_type;

    public SemanticClassNode(BaseType _class_type) {
        class_type = _class_type;
        leaf_value = false;
    }

    @Override
    public BaseType getType(){
        return class_type;
    }
}
