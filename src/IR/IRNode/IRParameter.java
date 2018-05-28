package IR.IRNode;

import IR.IRInstruction.Operand.Operand;

import java.util.Vector;

public class IRParameter extends IRNode {
    private Vector<Operand> parameters;
    public IRParameter(Vector<Operand> parameters) {
        this.parameters = parameters;
    }
    public Vector<Operand> getParameters() {
        return parameters;
    }
}
