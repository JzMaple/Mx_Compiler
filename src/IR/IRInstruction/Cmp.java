package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;
import Nasm.StackAllocator;

public class Cmp extends Binary {
    private String op;
    public Cmp(Operand lhs, Operand rhs, String op, Variable dest) {
        this.lhs = lhs; this.rhs = rhs; this.op = op;
        this.dest = dest;
    }

    public String getOp() {
        return op;
    }
}
