package Nasm;

import IR.IRInstruction.Operand.Variable;

import java.util.HashMap;
import java.util.Map;

public class StackAllocator {
    private Map<Variable, Integer> location = new HashMap<>();
    private Map<Integer, Variable> variable = new HashMap<>();
    private int index = -1;

    public void insert(Variable op) {
        if (location.containsKey(op)) return;
        location.put(op, index);
        variable.put(index, op);
        index = index - 1;
    }

    public int size(){
        return -(index * 8);
    }

    public int getOffset(Variable op) {
        return location.get(op) * 8;
    }

    public int getIndex() {
        return -index;
    }

    public int getIndex(Variable op) {
        return -location.get(op);
    }

    public Variable getVar(int index) {
        return variable.get(-index);
    }

    public Map<Variable, Integer> getLocation() {
        return location;
    }
}
