package IR.IRInstruction;

import IR.IRInstruction.Operand.Variable;
import IR.IRNode.IRFunction;
import IR.IRNode.IRParameter;

public class Call extends IRInstruction {
    IRFunction function;
    IRParameter parameters;
    Variable tmp_return;

    public Call(IRFunction function, IRParameter parameters) {
        this.function = function;
        this.parameters = parameters;
        this.tmp_return = new Variable("return", function.getFunctionType().getReturnType(), false);
    }

    public IRFunction getFunction() {
        return function;
    }

    public IRParameter getParameters() {
        return parameters;
    }

    public Variable getTmp_return() {
        return tmp_return;
    }
}
