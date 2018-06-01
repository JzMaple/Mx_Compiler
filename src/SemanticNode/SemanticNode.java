package SemanticNode;

import Type.BaseType;

abstract public class SemanticNode {
    protected Boolean leaf_value;
    public abstract BaseType getType();
    public Boolean getLeafValue() {
        return leaf_value;
    }
}
