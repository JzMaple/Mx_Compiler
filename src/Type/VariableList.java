package Type;

import java.util.HashMap;
import java.util.Map;

public class VariableList {
    private VariableList outer;
    private Map<String, BaseType> variable_list;

    public VariableList(VariableList _variable_list, VariableList _outer) {
        outer = _outer;
        if (_variable_list == null)
            variable_list = new HashMap<>();
        else
            variable_list = _variable_list.getVariableList();
    }

    public Boolean insertVariable(String variable_name, BaseType variable_type) {
        if (variable_list.containsKey(variable_name))
            return false;
        else {
            variable_list.put(variable_name, variable_type);
            return true;
        }
    }

    public BaseType getVariableType(String variable_name) {
        if (variable_list.containsKey(variable_name))
            return variable_list.get(variable_name);
        else if (outer != null)
            return outer.getVariableType(variable_name);
        else
            return null;
    }

    public Map<String, BaseType> getVariableList(){
        return variable_list;
    }
}
