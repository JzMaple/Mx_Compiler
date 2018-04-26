package Visitor;

import Parser.*;
import IRnode.*;
import Exception.*;

public class FirstVisitor extends MxBaseVisitor {
    private ClassList class_list;
    private MyException error = new MyException();

    public FirstVisitor(ClassList _class_list) {
        class_list = _class_list;
    }

    private void errorPrint() {
        error.printException();
        System.exit(1);
    }

    @Override
    public IRnode visitTypeDefine(MxParser.TypeDefineContext ctx) {
        class_list.insertClass(ctx.Identifier().getText());
        return null;
    }

    @Override
    public IRnode visitFunction(MxParser.FunctionContext ctx) {
        return null;
    }
}
