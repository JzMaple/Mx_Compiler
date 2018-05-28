package NasmTranslate;

import IR.IRInstruction.Operand.Operand;

import java.util.HashMap;
import java.util.Map;

public class StackAlloc {
    private Map<Operand, Integer> location = new HashMap<>();
    private int index = -8;

    public void insert(Operand op) {
        if (location.containsKey(op)) return;
        location.put(op, index);
        index -= 8;
    }

    public Integer size(){
        return -index;
    }

    public Integer getOffset(Operand op) {
        return location.get(op);
    }
}
