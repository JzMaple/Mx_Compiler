package IR.IRNode;

import IR.IRInstruction.IRInstruction;
import IR.IRInstruction.Label;
import IR.IRInstruction.Operand.Variable;
import Nasm.StackAllocator;
import Type.FunctionType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class IRFunction extends IRNode {
    private String function_name;
    private FunctionType function_type;
    private Label begin_label;
    private Label end_label;
    private StackAllocator stackAlloc;
    private List<IRInstruction> statements;
    private List<Variable> parameters;
    private Boolean isInline = false;
    private Boolean isRemember = false;

    public IRFunction(String function_name, FunctionType function_type){
        this.function_name = function_name;
        this.function_type = function_type;
        this.statements = new LinkedList<>();
        this.begin_label = new Label(function_name);
        this.end_label = new Label(function_name + "_end");
        setParameters(null);
        stackAlloc = new StackAllocator();
        this.parameters = new Vector<>();
    }

    public FunctionType getFunctionType() {
        return function_type;
    }

    public Label getBeginLabel() {
        return begin_label;
    }

    public Label getEndLabel() {
        return end_label;
    }

    public void setStatements(List<IRInstruction> statements) {
        this.statements = statements;
    }

    public List<IRInstruction> getStatements() {
        return statements;
    }

    public void setParameters(List<Variable> parameters) {
        this.parameters = parameters;
    }

    public List<Variable> getParameters() {
        return parameters;
    }

    public Variable getParameter(int i) {
        return parameters.get(i);
    }

    public StackAllocator getStackAlloc() {
        return stackAlloc;
    }

    public String getFunction_name() {
        return function_name;
    }

    public Boolean getIsInline() {
        return isInline;
    }

    public void setIsInline(Boolean isInline) {
        this.isInline = isInline;
    }

    public void setIsRemember(Boolean isRemember) {
        this.isRemember = isRemember;
    }

    public Boolean getIsRemember() {
        return isRemember;
    }
}
