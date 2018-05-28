package IR.IRNode;

import IR.IRInstruction.Operand.Variable;

import java.util.HashMap;
import java.util.Map;

public class IRScope {
    private Map<String, Variable> scope;
    private IRScope outer;

    public IRScope(IRScope outer){
        this.outer = outer;
        this.scope = new HashMap<>();
    }

    public Variable get(String name){
        if (scope.containsKey(name))
            return scope.get(name);
        else
            return outer.get(name);
    }

    public void insert(String name, Variable node){
        scope.put(name, node);
    }

    public Map<String, Variable> getScope() {
        return scope;
    }
}
