package Semantic.Visitor;

import Parser.MxBaseVisitor;
import Parser.MxParser;
import Semantic.SemanticNode.SemanticNode;
import Type.ClassList;

public class FirstVisitor extends MxBaseVisitor<SemanticNode> {
    private ClassList class_list;
    private String[] program;

    public FirstVisitor(ClassList _class_list, String[] _program) {
        class_list = _class_list;
        program = _program;
    }

    @Override
    public SemanticNode visitTypeDefine(MxParser.TypeDefineContext ctx) {
        String class_name = ctx.Identifier().getText();
        Boolean check = class_list.insertClass(class_name);
        if (!check) {
            System.err.println("[CLASS ERROR] Duplicated Class Name: The program has already had a class named \"" + class_name + "\".");
            System.err.print("Line " + ctx.getStart().getLine() + ":");
            System.err.println("             " + program[ctx.getStart().getLine()-1]);
            System.exit(1);
        }
        return null;
    }

    @Override
    public SemanticNode visitFunction(MxParser.FunctionContext ctx) {
        return null;
    }
}
