package Visitor;

import java.util.*;

import Type.*;
import Parser.*;
import IRnode.*;
import org.antlr.v4.runtime.ParserRuleContext;

public class SecondVisitor extends MxBaseVisitor<IRnode> {
    private ClassList class_list;
    private FunctionList global_function_list;
    private Stack<IRnode> class_stack = new Stack<>();
    private String[] program;

    public SecondVisitor(ClassList _class_list, FunctionList _global_function_list, String[] _program) {
        class_list = _class_list;
        global_function_list = _global_function_list;
        program = _program;
    }

    private void errorReport(String message, ParserRuleContext ctx) {
        System.err.println(message);
        System.err.print("Line" + ctx.getStart().getLine() + ":");
        System.err.println("             " + program[ctx.getStart().getLine()-1]);
        System.exit(1);
    }

    @Override
    public IRnode visitTypeDefine(MxParser.TypeDefineContext ctx) {
        String class_name = ctx.Identifier().getText();
        BaseType class_type = class_list.getClassType(class_name);
        IRnode class_node = new IRClassNode(class_type);
        class_stack.push(class_node);
        global_function_list.insertFunction(class_name, new FunctionType(class_type, new Vector<>()));
        if (ctx.program() != null) visit(ctx.program());
        class_stack.pop();
        return class_node;
    }

    @Override
    public IRnode visitFunction(MxParser.FunctionContext ctx) {
        Boolean construction_function = false;
        String function_name = ctx.Identifier().getText();
        BaseType return_type;
        if (ctx.class_statement() == null) {
            if (class_stack.empty())
                errorReport("[FUNCTION ERROR] Incomplete Function Define: Lacking of return type definition.", ctx);
            else {
                String class_name = class_stack.peek().getType().getClassName();
                if (!class_name.equals(function_name))
                    errorReport("[FUNCTION ERROR] Wrong Function Name: The construction function's name should be consistent with class name.",ctx);
            }
            construction_function = true;
            return_type = class_stack.peek().getType();
        } else {
            String class_name = ctx.class_statement().getText();
            return_type = class_list.getClassType(class_name);
            if (return_type == null)
                errorReport("[FUNCTION ERROR] Wrong Function Return Type: Have no such class type as \"" + class_name + "\".", ctx);
        }
        Vector<BaseType> parameter_type_list;
        if (ctx.parameter() == null) {
            parameter_type_list = new Vector<>();
        } else {
            parameter_type_list = ((IRParameterNode) visit(ctx.parameter())).getParameterTypeList();
        }
        FunctionType function_type = new FunctionType(return_type, parameter_type_list);
        if (class_stack.empty()) {
            if (!global_function_list.insertFunction(function_name, function_type))
                errorReport("[FUNCTION ERROR] Duplicated Function Name: The program already has a global function named \"" + function_name +"\".", ctx);
        } else {
            BaseType class_type = class_stack.peek().getType();
            if (! construction_function) {
                if (!class_type.insertMemberFunction(function_name, function_type)) {
                    String class_name = class_type.getClassName();
                    errorReport("[FUNCTION ERROR] Duplicated Function Name: The class " + class_name + "has already had a member function named \"" + function_name + "\".", ctx);
                }
            } else {
                global_function_list.changeFunction(function_name, function_type);
            }
        }
        return null;
    }

    @Override
    public IRnode visitParameter(MxParser.ParameterContext ctx) {
        Vector<BaseType> parameter_type_list = new Vector<>();
        String class_name = ctx.class_statement().getText();
        BaseType class_type = class_list.getClassType(class_name);
        if (class_type == null)
            errorReport("[FUNCTION ERROR] Invalidation Parameter List: Have no such class type as\"" + class_name + "\".", ctx);
        if (class_type instanceof VoidType)
            errorReport("[FUNCTION ERROR] Invalidation Parameter List: Cannot have a void type parameter.", ctx);
        parameter_type_list.add(class_type);
        if (ctx.parameter() != null)
            parameter_type_list.addAll(((IRParameterNode) visit(ctx.parameter())).getParameterTypeList());
        return new IRParameterNode(parameter_type_list);
    }

    @Override
    public IRnode visitNORMAL_INS(MxParser.NORMAL_INSContext ctx) {
        String class_name = ctx.class_statement().getText();
        String variable_name = ctx.Identifier().getText();
        BaseType variable_type = class_list.getClassType(class_name);
        if (variable_type == null)
            errorReport("[STATEMENT ERROR] Invalidation Instantiation Statement: Have no such class type as\"" + class_name + "\".", ctx);
        if (variable_type instanceof VoidType)
            errorReport("[STATEMENT ERROR] Invalidation Instantiation Statement: Cannot have a void type variable.", ctx);
        if (!class_stack.empty()) {
            BaseType _class_type = class_stack.peek().getType();
            String _class_name = _class_type.getClassName();
            if (!_class_type.insertMemberVariable(variable_name, variable_type))
                errorReport("[CLASS ERROR] Duplicated Variable Name: The class "+ _class_name + "has already had a member variable named \"" + variable_name + "\".", ctx);
        }
        return new IRExpressionNode(variable_type, false);
    }

    @Override
    public IRnode visitProgram(MxParser.ProgramContext ctx) {
        visitChildren(ctx);
        if (class_stack.empty()) {
            if (global_function_list.getFunctionType("main") == null) {
                System.err.println("[FUNCTION ERROR] Lacking of Main Function: The main function cannot be found");
                System.exit(1);
            } else {
                FunctionType main_type = global_function_list.getFunctionType("main");
                Vector<BaseType> parameters = main_type.getParameterTypeList();
                String return_type_name = main_type.getReturnType().getClassName();
                if (!(main_type.getReturnType() instanceof IntType)) {
                    System.err.println("[FUNCTION ERROR] Wrong Main Function Return Type: The main function return type is expected to be int, but only get" + return_type_name + ".");
                    System.exit(1);
                }
                if (parameters.size() != 0) {
                    System.err.println("[FUNCTION ERROR] Wrong Main Function Parameters: The main function should not have parameters.");
                    System.exit(1);
                }
            }
        }
        return null;
    }
}
