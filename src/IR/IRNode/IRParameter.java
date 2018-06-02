package IR.IRNode;

import IR.IRInstruction.Operand.Operand;

import java.util.*;

public class IRParameter extends IRNode {
    private List<Operand> parameters;
    public IRParameter(List<Operand> parameters) {
        this.parameters = parameters;
    }
    public List<Operand> getParameters() {
        return parameters;
    }
}
