package Visitor;

import Parser.*;
import IRnode.*;
import Type.*;

import java.util.Vector;

public class FirstVisitor extends MxBaseVisitor {
    private ClassList class_list;
    private Vector<String> program;

    public FirstVisitor(ClassList _class_list, Vector<String> _program) {
        class_list = _class_list;
        program = _program;
    }

    @Override
    public IRnode visitTypeDefine(MxParser.TypeDefineContext ctx) {
        String class_name = ctx.Identifier().getText();
        Boolean check = class_list.insertClass(class_name);
        if (!check) {
            System.err.println("[CLASS ERROR] Duplicated Class Name: The program has already had a class named \"" + class_name + "\".");
            System.err.print("Line " + ctx.getStart().getLine() + ":");
            System.err.println("             " + program.get(ctx.getStart().getLine()-1));
            System.exit(1);
        }
        return null;
    }

    @Override
    public IRnode visitFunction(MxParser.FunctionContext ctx) {
        return null;
    }
}
