package Type;

import Exception.*;

import java.util.Map;

public class UserType extends BaseType {
    private String class_name;

    public UserType(String _class_name) {
        class_name = _class_name;
    }

    @Override
    public String getClassName() {
        return class_name;
    }

}
