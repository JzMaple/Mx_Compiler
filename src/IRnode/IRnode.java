package IRnode;

import java.util.*;

import Type.*;

abstract public class IRnode {
    protected Boolean leaf_value;

    public abstract BaseType getType();
    public Boolean getLeafValue() {
        return leaf_value;
    }
}
