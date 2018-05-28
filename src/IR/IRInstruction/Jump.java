package IR.IRInstruction;

public class Jump extends IRInstruction {
    Label label;

    public Jump(Label label) {
        this.label = label;
    }

    public Label getLabel() {
        return label;
    }
}
