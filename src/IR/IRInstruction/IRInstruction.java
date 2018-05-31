package IR.IRInstruction;

import IR.IR;
import IR.IRInstruction.Operand.Memory;
import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;
import Type.VoidType;

import java.util.HashSet;
import java.util.Set;

abstract public class IRInstruction extends IR {
    private Set<IRInstruction> successor = new HashSet<>();
    private Set<Variable> in = new HashSet<>();
    private Set<Variable> out = new HashSet<>();
    private Set<Variable> def = new HashSet<>();
    private Set<Variable> use = new HashSet<>();

    public void setSuccessor(IRInstruction successor) {
        this.successor.add(successor);
    }

    public Set<Variable> getIn() {
        return in;
    }

    public Set<Variable> getOut() {
        return out;
    }

    public void setDef(Operand def) {
        this.def.addAll(def.getDef());
    }

    public Set<Variable> getDef() {
        return def;
    }

    public Set<Variable> getUse() {
        return use;
    }

    public Set<IRInstruction> getSuccessor() {
        return successor;
    }

    public void setUse(Operand use) {
//        System.out.println(use);
        this.use.addAll(use.getUse());
    }

    public Boolean update() {
        Set<Variable> _in = new HashSet<>();
        _in.addAll(in);
        Set<Variable> _out = new HashSet<>();
        _out.addAll(out);
        out.clear();
        for (IRInstruction inst : successor) out.addAll(inst.getIn());
        in.clear();
        in.addAll(use);
        for (Variable var : out)
            if (!def.contains(var)) in.add(var);
        return equal(in, _in) || equal(out, _out);
    }

    private Boolean equal(Set<Variable> set1, Set<Variable> set2) {
        if (set1.size() != set2.size()) return false;
        for (Variable var : set1)
            if (!set2.contains(var)) return false;
        return true;
    }
}
