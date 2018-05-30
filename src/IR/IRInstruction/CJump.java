package IR.IRInstruction;

import IR.IRInstruction.Operand.Operand;

public class CJump extends IRInstruction {
    private Operand condition;
    private Cmp cond;
    private Label true_label;
    private Label false_label;

    public CJump(Operand condition, Label true_label, Label false_label) {
        this.condition = condition;
        this.true_label = true_label;
        this.false_label = false_label;
    }

    public CJump(Cmp cond, Label true_label, Label false_label) {
        this.cond = cond;
        this.true_label = true_label;
        this.false_label = false_label;
    }

    public Operand getCondition() {
        return condition;
    }

    public Cmp getCond() {
        return cond;
    }

    public Label getTrue_label() {
        return true_label;
    }

    public Label getFalse_label() {
        return false_label;
    }
}
