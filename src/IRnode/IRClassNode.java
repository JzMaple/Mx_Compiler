package IRnode;

import Type.*;

public class IRClassNode extends IRnode{
    private BaseType class_type;

    public IRClassNode(BaseType _class_type) {
        class_type = _class_type;
        leaf_value = false;
    }

    @Override
    public BaseType getType(){
        return class_type;
    }
}
