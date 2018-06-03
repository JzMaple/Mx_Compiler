package Nasm;

import IR.IRInstruction.*;
import IR.IRInstruction.Operand.Immediate;
import IR.IRInstruction.Operand.Memory;
import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;
import IR.IRNode.IRFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RegAllocator {
    private List<IRInstruction> inst;
    private StackAllocator stackAlloc;
    private Boolean[][] conflictGraph;
    private int[] color;
    private Boolean nodeCombineOptim = false;

    private void setUse(IRInstruction ins, Operand op) {
        if (op == null) return;
        ins.setUse(op);
    }

    private void setDef(IRInstruction ins, Operand op) {
        if (op == null) return;
        ins.setDef(op);
        if (op instanceof Memory)
            ins.setUse(op);
    }

    private void set(Call call, int index) {
        call.setDef(call.getTmp_return());
        List<Operand> parameters = call.getParameters().getParameters();
        for (int i = 0; i < parameters.size(); ++i) {
            setUse(call, parameters.get(i));
            if (i < 6) parameters.get(i).setParaOrd(i);
        }
        int cnt = index + 1;
        while (inst.get(cnt).getIsDead()) ++cnt;
        call.setSuccessor(inst.get(cnt));
    }

    private void set(Binary binary, int index) {
        setUse(binary, binary.getLhs());
        setUse(binary, binary.getRhs());
        setDef(binary, binary.getDest());
        int cnt = index + 1;
        while (inst.get(cnt).getIsDead()) ++cnt;
        binary.setSuccessor(inst.get(cnt));
    }

    private void set(Move move, int index) {
        setDef(move, move.getLhs());
        setUse(move, move.getRhs());
        int cnt = index + 1;
        while (inst.get(cnt).getIsDead()) ++cnt;
        move.setSuccessor(inst.get(cnt));
    }

    private void set(Unary unBin, int index) {
        setUse(unBin, unBin.getExpr());
        setDef(unBin, unBin.getDest());
        int cnt = index + 1;
        while (inst.get(cnt).getIsDead()) ++cnt;
        unBin.setSuccessor(inst.get(cnt));
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
        int cnt = index + 1;
        while (inst.get(cnt).getIsDead()) ++cnt;
        label.setSuccessor(inst.get(cnt));
    }

    private void set(Return ret, int index) {
        setUse(ret, ret.getRet());
        int cnt = index + 1;
        while (inst.get(cnt).getIsDead()) ++cnt;
        ret.setSuccessor(inst.get(cnt));
    }

    private void LivenessAnalysis() {
        int size = inst.size();
        for (int i = 0; i < size; ++i) {
            IRInstruction ins = inst.get(i);
            if (ins.getIsDead()) continue;
            ins.reset();
            if (ins instanceof Call) set((Call) ins, i);
            if (ins instanceof Binary) set((Binary) ins, i);
            if (ins instanceof Unary) set((Unary) ins, i);
            if (ins instanceof CJump) set((CJump) ins, i);
            if (ins instanceof Jump) set((Jump) ins, i);
            if (ins instanceof Label) set((Label) ins, i);
            if (ins instanceof Return) set((Return) ins, i);
            if (ins instanceof Move) set((Move) ins, i);
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
            for (Variable o : out) {
                for (Variable d : def) {
                    if (o != d) {
                        int index_o = stackAlloc.getIndex(o);
                        int index_d = stackAlloc.getIndex(d);
                        conflictGraph[index_o][index_d] = true;
                        conflictGraph[index_d][index_o] = true;
                    }
                }
            }
            for (Variable d : def) d.setBegin(i);
            for (Variable o : out) o.setEnd(i);
            for (Variable u : ins.getUse()) u.setUse();
        }
    }

    int[] father;
    Boolean[][] append;
    private int getFather(int x) {
        if (father[x] == x)
            return x;
        else
            return father[x] = getFather(father[x]);
    }

    private void combine(int index1, int index2) {
        int num = stackAlloc.getIndex();
        int x = getFather(index1);
        int y = getFather(index2);
        father[y] = x;
        for (int i = 0; i < num; ++i) {
            if (conflictGraph[y][i]) conflictGraph[x][i] = true;
            if (append[y][i]) append[x][i] = true;
        }
        int o1 = stackAlloc.getVar(x).getParaOrd();
        int o2 = stackAlloc.getVar(y).getParaOrd();
        stackAlloc.getVar(x).setParaOrd(o1 < o2 ? o1 : o2);
    }

    private void nodeCombine() {
        int size = inst.size();
        int num = stackAlloc.getIndex();
        int[] conflict = new int[num];
        append = new Boolean[num][num];
        father = new int[num];
        for (int i = 0; i < num; ++i) {
            father[i] = i;
            conflict[i] = 0;
            for (int j = 0; j < num; ++j) {
                if (conflictGraph[j][i]) conflict[i] += 1;
                append[i][j] = (i == j);
            }
        }
        for (int i = 0; i < size; ++i) {
            IRInstruction ins = inst.get(i);
            if (ins instanceof Binary && !(ins instanceof Cmp)) {
                Operand lhs = ((Binary) ins).getLhs();
                if (lhs instanceof Variable && !((Variable) lhs).isGlobal()) {
                    int index_lhs = stackAlloc.getIndex((Variable) lhs);
                    int index_dest = stackAlloc.getIndex(((Binary) ins).getDest());
                    if (!conflictGraph[index_dest][index_lhs] && (conflict[index_dest] + conflict[index_lhs]) < RegX86.allocNum)
                        combine(index_dest, index_lhs);
                }
            }
            if (ins instanceof Move) {
                Operand lhs = ((Move) ins).getLhs();
                if (lhs instanceof Variable && !((Variable) lhs).isGlobal()) {
                    Operand rhs = ((Move) ins).getRhs();
                    if (rhs instanceof Variable && !((Variable) rhs).isGlobal()) {
                        int index_lhs = stackAlloc.getIndex((Variable) lhs);
                        int index_rhs = stackAlloc.getIndex((Variable) rhs);
                        if (!conflictGraph[index_rhs][index_lhs] && (conflict[index_rhs] + conflict[index_lhs]) < RegX86.allocNum)
                            combine(index_lhs, index_rhs);
                    }
                }
            }
        }
    }

    private void RegisterAllocate() {
        int num = stackAlloc.getIndex();
        color = new int[num];
        for (int i = 1; i < num; ++i) color[i] = -1;
        int[] order = new int[num];
        int[] life = new int[num];
        int[] use = new int[num];
        for (int i = 1; i < num; ++i) {
            order[i] = i;
            life[i] = stackAlloc.getVar(i).getLife();
            use[i] = stackAlloc.getVar(i).getUsed();
//            System.out.println(stackAlloc.getVar(i).getName() + " " + life[i]);
        }

        if (nodeCombineOptim) {
            for (int i = 1; i < num; ++i) {
                life[i] = 0;
                use[i] = 0;
                for (int j = 1; j < num; ++j) {
                    if (append[i][j]) {
                        life[i] += life[j];
                        use[i] += use[j];
                    }
                }
            }
        }

        //sort
        for (int i = 1; i < num; ++ i) {
            for (int j = 1; j < num - 1; ++j)
                if (life[j] > life[j + 1]) {
                    int x = life[j];
                    int y = life[j + 1];
                    life[j + 1] = x;
                    life[j] = y;
                    x = order[j];
                    y = order[j + 1];
                    order[j + 1] = x;
                    order[j] = y;
                }
        }

        if (nodeCombineOptim) {
            Boolean[] conflict = new Boolean[num];
            for (int reg = 0; reg < RegX86.allocNum; ++reg) {
                for (int i = 1; i < num; ++i) {
                    int x = order[i];
                    if (father[x] != x) continue;
                    if (color[x] != -1) continue;
                    if (RegX86.getParaOrd(reg) < stackAlloc.getVar(x).getParaOrd()) continue;
                    Boolean flag = true;
                    int use_all = 0;
                    for (int j = 1; j < num; ++j) conflict[j] = false;
                    for (int j = 1; j < num; ++j) {
                        if (conflictGraph[x][j] && color[getFather(j)] == reg) {
                            flag = false;
                            if (!conflict[getFather(j)]) {
                                conflict[getFather(j)] = true;
                                use_all = use_all + use[getFather(j)];
                            }
                        }
                    }
                    if (flag) {
                        color[x] = reg;
                    } else if (use_all < use[x]) {
                        color[x] = reg;
                        for (int j = 1; j < num; ++j) {
                            if (conflict[j]) color[j] = -1;
                        }
                    }
                }
            }
            for (int i = 0; i < num; ++i) {
                if (father[i] != i) continue;
                for (int j = 0; j < num; ++j)
                    if (append[i][j]) color[j] = color[i];
            }
        } else {
            Boolean[] conflict = new Boolean[num];
            for (int reg = 0; reg < RegX86.allocNum; ++reg) {
                for (int i = 1; i < num; ++i) {
                    int x = order[i];
//                int x = i;
                    if (color[x] != -1) continue;
                    if (RegX86.getParaOrd(reg) < stackAlloc.getVar(x).getParaOrd()) continue;
                    Boolean flag = true;
                    int use_all = 0;
                    for (int j = 1; j < num; ++j) conflict[j] = false;
                    for (int j = 1; j < num; ++j) {
                        if (conflictGraph[x][j] && color[j] == reg) {
                            flag = false;
                            conflict[j] = true;
                            use_all = use_all + use[j];
                        }
                    }
                    if (flag) {
                        color[x] = reg;
                    } else if (use_all < use[x]) {
                        color[x] = reg;
                        for (int j = 1; j < num; ++j) {
                            if (conflict[j]) color[j] = -1;
                        }
                    }
                }
            }
        }

        for (int i = 1; i < num; ++i) {
            Variable var = stackAlloc.getVar(i);
            RegX86 regX86 = RegX86.allocReg(color[i]);
            var.setReg(regX86);
//            System.out.println(var.getName() + " " /*+ regX86 + " " + var.getLife() + " " + var.getUsed() + " "*/ + i + " " + father[i]);
        }
    }

    private void initInst(IRFunction function) {
        List<Variable> parameters = function.getParameters();
        inst = new ArrayList<>();
        int len = parameters.size();
        for (int i = 0; i < len; ++i)
            inst.add(new Move(parameters.get(i), new Immediate(0)));
        inst.addAll(function.getStatements());
        inst.add(new Jump(function.getEndLabel()));
    }

    private Boolean SetContain(Set<Variable> set1, Set<Variable> set2) {
        if (set1.size() < set2.size()) return false;
        for (Variable var : set2)
            if (!set1.contains(var)) return false;
        return true;
    }

    private Boolean ok(IRInstruction ins, Set<Variable> in) {
        if (ins instanceof Binary)
            return !in.contains(((Binary) ins).getDest());
        if (ins instanceof Unary)
            return !in.contains(((Unary) ins).getDest());
        if (ins instanceof Move)
            if (((Move) ins).getLhs() instanceof Memory) return false;
            else if (((Move) ins).getLhs() instanceof Variable) return !in.contains((Variable) ((Move) ins).getLhs());
        return !(ins instanceof Call || ins instanceof Return);
    }

    private Boolean DeadCodeElimination() {
        int size = inst.size();
        Boolean f = false;
        for (int i = 0; i < size; ++i) {
            int flag = 0;
            int end = i - 1;
            IRInstruction ins = inst.get(i);
            if (ins.getIsDead()) continue;
            if (ins instanceof Label) continue;
            if (ins instanceof Return) continue;
            if (ins instanceof Call) continue;
            if (ins instanceof Jump) {
                int x = inst.indexOf(((Jump) ins).getLabel());
                if (x < i) continue;
                else flag = flag < x ? x : flag;
            }
            if (ins instanceof CJump) {
                int x = inst.indexOf(((CJump) ins).getFalse_label());
                int y = inst.indexOf(((CJump) ins).getFalse_label());
                if (x < i) continue;
                else flag = flag < x ? x : flag;
                if (y < i) continue;
                else flag = flag < y ? y : flag;
            }
            Set<Variable> in = ins.getIn();
            for (int j = i; j < size; ++j) {
                IRInstruction ins_j = inst.get(j);
                if (!ok(ins_j, in)) break;
                else if (ins_j instanceof Jump) {
                    int x = inst.indexOf(((Jump) ins_j).getLabel());
                    if (x < i) break;
                    else flag = flag < x ? x : flag;
                } else if (ins_j instanceof CJump) {
                    int x = inst.indexOf(((CJump) ins_j).getFalse_label());
                    int y = inst.indexOf(((CJump) ins_j).getFalse_label());
                    if (x < i) break;
                    else flag = flag < x ? x : flag;
                    if (y < i) break;
                    else flag = flag < y ? y : flag;
                }
                Set<Variable> out = ins_j.getOut();
                if (SetContain(in, out))
                    end = j;
            }
            if (end >= flag && end >= i) {
                f = true;
                for (int j = i; j <= end; ++j) inst.get(j).setIsDead(true);
            }
        }
        return f;
    }

    public void allocate(IRFunction function) {
        initInst(function);
//        System.out.println(function.getFunction_name());
        stackAlloc = function.getStackAlloc();
        LivenessAnalysis();
        while (DeadCodeElimination()) {
            LivenessAnalysis();
        }
        BuildConflictGraph();
//        nodeCombine();
        RegisterAllocate();
    }
}
