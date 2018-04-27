package Visitor;

import java.util.*;

import Type.*;
import Parser.*;
import IRnode.*;
import Exception.*;

public class SecondVisitor extends MxBaseVisitor<IRnode> {
    private MyException error = new MyException();
    private ClassList class_list;
    private FunctionList global_function_list;
    private Stack<IRnode> class_stack = new Stack<>();

    public SecondVisitor(ClassList _class_list, FunctionList _global_function_list) {
        class_list = _class_list;
        global_function_list = _global_function_list;
    }

    private void error(String errorMessage) {
        System.err.println(errorMessage);
        System.exit(1);
    }

    private void checkConstructionFunction(String function_name) {
        try {
            if (class_stack.empty())
                throw new FunctionException("Have no return type declaration of the function named \"" + function_name + "\"");
            else {
                BaseType class_type = class_stack.peek().getType();
                String class_name = class_type.getClassName();
                if (!function_name.equals(class_name))
                    throw new FunctionException("Construction function's identifier is expected to be consistent with class's");
            }
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    private void checkVoidType(BaseType class_type) {
        try {
            if (class_type instanceof VoidType)
                throw new VariableException("cannot have a void type variable");
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    private void checkMainFunction() {
        try {
            FunctionType main_function_type = global_function_list.getFunctionType("main");
            if (main_function_type.getReturnType() != class_list.getClass("int"))
                throw new FunctionException("main function is expected to have an int return type");
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    @Override
    public IRnode visitTypeDefine(MxParser.TypeDefineContext ctx) {
        BaseType class_type = class_list.getClass(ctx.Identifier().getText());
        IRnode class_node = new IRClassNode(class_type);
        class_stack.push(class_node);
        if (ctx.program() != null) visit(ctx.program());
        class_stack.pop();
        return class_node;
    }

    @Override
    public IRnode visitFunction(MxParser.FunctionContext ctx) {
        String function_name = ctx.Identifier().getText();
        BaseType return_type;
        if (ctx.class_statement() == null) {
            checkConstructionFunction(function_name);
            return_type = class_list.getClass("void");
        } else {
            return_type = class_list.getClass(ctx.class_statement().getText());
        }
        Vector<BaseType> parameter_type_list;
        if (ctx.parameter() == null) {
            parameter_type_list = new Vector<>();
        } else {
            parameter_type_list = ((IRParameterNode) visit(ctx.parameter())).getParameterTypeList();
        }
        FunctionType function_type = new FunctionType(return_type, parameter_type_list);
        if (class_stack.empty()) {
            global_function_list.insertFunction(function_name, function_type);
        } else {
            BaseType class_type = class_stack.peek().getType();
            class_type.insertClassFunction(function_name, function_type);
        }
        return null;
    }

    @Override
    public IRnode visitParameter(MxParser.ParameterContext ctx) {
        Vector<BaseType> parameter_type_list = new Vector<>();
        String class_name = ctx.class_statement().getText();
        BaseType class_type = class_list.getClass(class_name);
        checkVoidType(class_type);
        parameter_type_list.add(class_type);
        if (ctx.parameter() != null)
            parameter_type_list.addAll(((IRParameterNode) visit(ctx.parameter())).getParameterTypeList());
        return new IRParameterNode(parameter_type_list);
    }

    @Override
    public IRnode visitNORMAL_INS(MxParser.NORMAL_INSContext ctx) {
        String variable_class_name = ctx.class_statement().getText();
        String variable_identifier_name = ctx.Identifier().getText();
        BaseType variable_type = class_list.getClass(variable_class_name);
        if (!class_stack.empty()) {
            checkVoidType(variable_type);
            BaseType class_type = class_stack.peek().getType();
            class_type.insertClassVariable(variable_identifier_name, variable_type);
        }
        return new IRExpressionNode(variable_type, false);
    }

    @Override
    public IRnode visitProgram(MxParser.ProgramContext ctx) {
        visitChildren(ctx);
        if (class_stack.empty()) checkMainFunction();
        return null;
    }
}
