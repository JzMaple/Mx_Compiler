package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;
import Nasm.StackAllocator;

public class Add extends Binary {
    public Add(Operand lhs, Operand rhs, Variable dest) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.dest = dest;
    }
}
