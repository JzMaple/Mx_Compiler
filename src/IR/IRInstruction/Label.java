package IR.IRInstruction;

public class Label extends IRInstruction {
    String name;

    public Label(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
