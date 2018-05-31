package NasmTranslate;

import IR.IRInstruction.*;
import IR.IRInstruction.IRInstruction;
import IR.IRInstruction.Operand.Memory;
import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;
import IR.IRNode.IRFunction;

import java.util.*;

public class RegAllocator {
    private List<IRInstruction> inst;
    private StackAllocator stackAlloc;
    private Boolean[][] conflictGraph;
    private int[] color;

    private void set(Call call, int index) {
        call.setDef(call.getTmp_return());
        call.setSuccessor(inst.get(index + 1));
    }

    private void setUse(IRInstruction ins, Operand op) {
        if (op == null) return;
        if (op instanceof Variable) {
            ins.setUse(op);
        } else if (op instanceof Memory) {
            ins.setUse(op);
            ins.setDef(op);
        }
    }

    private void setDef(IRInstruction ins, Operand op) {
        if (op == null) return;
        if (op instanceof Variable) {
            ins.setDef(op);
        } else if (op instanceof Memory) {
            ins.setUse(op);
            ins.setDef(op);
        }
    }

    private void set(Bin bin, int index) {
        if (bin instanceof Move) {
            setDef(bin, bin.getLhs());
            setUse(bin, bin.getRhs());
        } else {
            setUse(bin, bin.getLhs());
            setUse(bin, bin.getRhs());
            setDef(bin, bin.getRhs());
        }
        bin.setSuccessor(inst.get(index + 1));
    }

    private void set(UnBin unBin, int index) {
        setUse(unBin, unBin.getExpr());
        setDef(unBin, unBin.getDest());
        unBin.setSuccessor(inst.get(index + 1));
    }

    private void set(CJump cJump, int index) {
        if (cJump.getCond() == null) {
            setUse(cJump, cJump.getCondition());
        } else {
            Cmp cmp = cJump.getCond();
            setUse(cJump, cmp.getLhs());
            setUse(cJump, cmp.getRhs());
        }
        cJump.setSuccessor(cJump.getTrue_label());
        cJump.setSuccessor(cJump.getFalse_label());
    }

    private void set(Jump jump, int index) {
        jump.setSuccessor(jump.getLabel());
    }

    private void set(Label label, int index) {
        label.setSuccessor(inst.get(index + 1));
    }

    private void set(Return ret, int index) {
        setUse(ret, ret.getRet());
        ret.setSuccessor(inst.get(index + 1));
    }

    private void LivenessAnalysis() {
        int size = inst.size();
        for (int i = 0; i < size; ++i) {
            IRInstruction ins = inst.get(i);
            if (ins instanceof Call) set((Call) ins, i);
            if (ins instanceof Bin) set((Bin) ins, i);
            if (ins instanceof UnBin) set((UnBin) ins, i);
            if (ins instanceof CJump) set((CJump) ins, i);
            if (ins instanceof Jump) set((Jump) ins, i);
            if (ins instanceof Label) set((Label) ins, i);
            if (ins instanceof Return) set((Return) ins, i);
        }
        Boolean flag = false;
        while (!flag) {
            flag = true;
            for (int i = size -1; i >= 0; --i)
                flag = flag && inst.get(i).update();
//            System.out.println("yes");
        }
//        for (int i = 0; i < size; ++i) {
//            IRInstruction ins = inst.get(i);
//            System.out.println(ins);
//            System.out.print("def : ");
//            for (Variable var : ins.getDef())
//                System.out.print(var.getName() + " ");
//            System.out.print("\n");
//            System.out.print("use : ");
//            for (Variable var : ins.getUse())
//                System.out.print(var.getName() + " ");
//            System.out.print("\n");
//            System.out.print("in : ");
//            for (Variable var : ins.getIn())
//                System.out.print(var.getName() + " ");
//            System.out.print("\n");
//            System.out.print("out : ");
//            for (Variable var : ins.getOut())
//                System.out.print(var.getName() + " ");
//            System.out.print("\n");
////            System.out.println("succ");
////            for (IRInstruction inss : ins.getSuccessor())
////                System.out.print(inss);
//        }
    }

    private void BuildConflictGraph() {
        int size = inst.size();
        int num = stackAlloc.getIndex();
        conflictGraph = new Boolean[num][num];
        for (int i = 0; i < num; ++i)
            for (int j = 0; j < num; ++j) conflictGraph[i][j] = false;
        for (int i = 0; i < size; ++i) {
            IRInstruction ins = inst.get(i);
            Set<Variable> out = ins.getOut();
            Set<Variable> def = ins.getDef();
            for (Variable o : out)
                for (Variable d : def)
                    if (o != d) {
                        int index_o = stackAlloc.getIndex(o);
                        int index_d = stackAlloc.getIndex(d);
                        conflictGraph[index_o][index_d] = true;
                        conflictGraph[index_d][index_o] = true;
                    }
        }
//        for (int i = 1; i < num; ++i)
//            System.out.print(stackAlloc.getVar(i).getName() + ":" + conflictGraph[1][i] + " ");
    }

    private void RegisterAllocate() {
        int num = stackAlloc.getIndex();
        color = new int[num];
        for (int i = 0; i < num; ++i) color[i] = -1;
        for (int reg = 0; reg < RegX86.allocNum; ++reg) {
            for (int i = 1; i < num; ++i) {
                if (color[i] != -1) continue;
                Boolean flag = true;
                for (int j = 1; j < num; ++j)
                    if (conflictGraph[i][j] && color[j] == reg) {
                        flag = false; break;
                    }
                if (flag) {
                    color[i] = reg;
                }
             }
        }
        for (int i = 1; i < num; ++i) {
            Variable var = stackAlloc.getVar(i);
            RegX86 regX86 = RegX86.allocReg(color[i]);
            var.setReg(regX86);
        }
    }

    public void allocate(IRFunction function) {
        inst = function.getStatements();
        stackAlloc = function.getStackAlloc();
        LivenessAnalysis();
        BuildConflictGraph();
        RegisterAllocate();
    }
}
