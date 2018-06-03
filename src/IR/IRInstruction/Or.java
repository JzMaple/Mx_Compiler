package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;

public class Or extends Binary {
    public Or(Operand lhs, Operand rhs, Variable dest) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.dest = dest;
    }
}
