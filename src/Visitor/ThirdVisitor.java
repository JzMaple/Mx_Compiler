package Visitor;

import java.util.*;

import Parser.*;
import SemanticNode.*;
import Type.*;
import org.antlr.v4.runtime.ParserRuleContext;

public class ThirdVisitor extends MxBaseVisitor<SemanticNode> {
    private ClassList class_list;
    private Stack<SemanticNode> class_stack;
    private Stack<SemanticNode> function_stack;
    private Stack<SemanticNode> loop_stack;
    private Stack<FunctionList> function_scope_stack;
    private Stack<VariableList> variable_scope_stack;
    private String[] program;

    public ThirdVisitor(ClassList _class_list, FunctionList global_function_list, String[] _program) {
        class_list = _class_list;
        class_stack = new Stack<SemanticNode>();
        function_stack = new Stack<SemanticNode>();
        loop_stack = new Stack<SemanticNode>();
        variable_scope_stack = new Stack<>();
        variable_scope_stack.push(new VariableList(null, null));
        function_scope_stack = new Stack<>();
        function_scope_stack.push(global_function_list);
        program = _program;
    }

    private void errorReport(String message, ParserRuleContext ctx) {
        System.err.println(message);
        System.err.print("Line" + ctx.getStart().getLine() + ":");
        System.err.println("             " + program[ctx.getStart().getLine()-1]);
        System.exit(1);
    }

    @Override
    public SemanticNode visitTypeDefine(MxParser.TypeDefineContext ctx) {
        BaseType class_type = class_list.getClassType(ctx.Identifier().getText());
        SemanticNode class_node = new SemanticClassNode(class_type);
        class_stack.push(class_node);
        variable_scope_stack.push(new VariableList(class_type.getMemberVariableList(), variable_scope_stack.peek()));
        function_scope_stack.push(new FunctionList(class_type.getMemberFunctionList(), function_scope_stack.peek()));
        if (ctx.program() != null) visit(ctx.program());
        class_stack.pop();
        variable_scope_stack.pop();
        function_scope_stack.pop();
        return class_node;
    }

    @Override
    public SemanticNode visitFunction(MxParser.FunctionContext ctx) {
        String function_name = ctx.Identifier().getText();
        FunctionType function_type = function_scope_stack.peek().getFunctionType(function_name);
        SemanticNode function_node = new SemanticFunctionNode(function_type);
        function_stack.push(function_node);
        variable_scope_stack.push(new VariableList(null, variable_scope_stack.peek()));
        variable_scope_stack.peek().insertVariable(function_name, function_type);
        if (ctx.parameter() != null) visit(ctx.parameter());
        visit(ctx.noScope_block());
        function_stack.pop();
        variable_scope_stack.pop();
        return function_node;
    }

    @Override
    public SemanticNode visitScope_block(MxParser.Scope_blockContext ctx) {
        variable_scope_stack.push(new VariableList(null, variable_scope_stack.peek()));
        visitChildren(ctx);
        variable_scope_stack.pop();
        return null;
    }

    @Override
    public SemanticNode visitParameter(MxParser.ParameterContext ctx) {
        BaseType variable_type = class_list.getClassType(ctx.class_statement().getText());
        String variable_name = ctx.Identifier().getText();
        if (!variable_scope_stack.peek().insertVariable(variable_name, variable_type))
            errorReport("[FUNCTION ERROR] Duplicated Parameter Name: The parameter list has already had a parameter named \"" + variable_name + "\".", ctx);
        if (ctx.parameter() != null) visit(ctx.parameter());
        return null;
    }

    @Override
    public SemanticNode visitNORMAL_INS(MxParser.NORMAL_INSContext ctx) {
        if (!class_stack.empty() && function_stack.empty()) {
            return null;
        } else {
            String class_name = ctx.class_statement().getText();
            BaseType variable_type = class_list.getClassType(class_name);
            String variable_name = ctx.Identifier().getText();
            if (variable_type == null)
                errorReport("[STATEMENT ERROR] Invalidate Instantiation Statement: Have no such class type as\"" + class_name + "\".", ctx);
            if (variable_type instanceof VoidType)
                errorReport("[STATEMENT ERROR] Invalidate Instantiation Statement: Cannot have a void type variable.", ctx);
            if (!variable_scope_stack.peek().insertVariable(variable_name, variable_type))
                errorReport("[STATEMENT ERROR] Duplicated Variable Name: The scope has already had a variable named \"" + variable_name + "\".", ctx);
            return new SemanticVariableNode(variable_type, false);
        }
    }

    @Override
    public SemanticNode visitASSIGN_INS(MxParser.ASSIGN_INSContext ctx) {
        if (!class_stack.empty() && function_stack.empty()) {
            return null;
        } else {String class_name = ctx.class_statement().getText();
            BaseType variable_type = class_list.getClassType(class_name);
            String variable_name = ctx.Identifier().getText();
            if (variable_type == null)
                errorReport("[STATEMENT ERROR] Invalidate Instantiation Statement: Have no such class type as\"" + class_name + "\".", ctx);
            if (variable_type instanceof VoidType)
                errorReport("[STATEMENT ERROR] Invalidate Instantiation Statement: Cannot have a void type variable.", ctx);
            BaseType expression_type = visit(ctx.expression()).getType();
            if (!variable_type.assignment_check(expression_type))
                errorReport("[STATEMENT ERROR] Invalidate Instantiation Statement: The types on both sides of assign expression should be consistent with each other.", ctx);
            if (!variable_scope_stack.peek().insertVariable(variable_name, variable_type))
                errorReport("[STATEMENT ERROR] Duplicated Variable Name: The scope has already had a variable named \"" + variable_name + "\".", ctx);
            return new SemanticVariableNode(variable_type, false);
        }
    }

    @Override
    public SemanticNode visitIF_STATE(MxParser.IF_STATEContext ctx) {
        BaseType expression_type = visit(ctx.expression()).getType();
        if (!(expression_type instanceof BoolType)) {
            String expression_type_name = expression_type.getClassName();
            errorReport("[STATEMENT ERROR] Wrong Condition Expression: A bool expression is expected at condition area, but got a \"" + expression_type_name + "\".", ctx);
        }
        variable_scope_stack.push(new VariableList(null, variable_scope_stack.peek()));
        visit(ctx.noScope_block());
        variable_scope_stack.pop();
        return null;
    }

    @Override
    public SemanticNode visitIFELSE_STATE(MxParser.IFELSE_STATEContext ctx) {
        BaseType expression_type = visit(ctx.expression()).getType();
        if (!(expression_type instanceof BoolType)) {
            String expression_type_name = expression_type.getClassName();
            errorReport("[STATEMENT ERROR] Wrong Condition Expression: A bool expression is expected at condition area, but got a \"" + expression_type_name + "\".", ctx);
        }
        variable_scope_stack.push(new VariableList(null, variable_scope_stack.peek()));
        visit(ctx.noScope_block(0));
        variable_scope_stack.pop();
        variable_scope_stack.push(new VariableList(null, variable_scope_stack.peek()));
        visit(ctx.noScope_block(1));
        variable_scope_stack.pop();
        return null;
    }

    @Override
    public SemanticNode visitFOR_STATE(MxParser.FOR_STATEContext ctx) {
        if (ctx.first != null) visit(ctx.first);
        if (ctx.second != null) {
            BaseType second_type = visit(ctx.second).getType();
            if (!(second_type instanceof BoolType)) {
                String expression_type_name = second_type.getClassName();
                errorReport("[STATEMENT ERROR] Wrong Condition Expression: A bool expression is expected at condition area, but got a \"" + expression_type_name + "\".", ctx);
            }
        }
        if (ctx.third != null) visit(ctx.third);
        SemanticNode loop_node = new SemanticLoopNode();
        variable_scope_stack.push(new VariableList(null, variable_scope_stack.peek()));
        loop_stack.push(loop_node);
        visit(ctx.noScope_block());
        variable_scope_stack.pop();
        loop_stack.pop();
        return null;
    }

    @Override
    public SemanticNode visitWHILE_STATE(MxParser.WHILE_STATEContext ctx) {
        BaseType expression_type = visit(ctx.expression()).getType();
        if (!(expression_type instanceof BoolType)) {
            String expression_type_name = expression_type.getClassName();
            errorReport("[STATEMENT ERROR] Wrong Condition Expression: A bool expression is expected at condition area, but got a \"" + expression_type_name + "\".", ctx);
        }
        SemanticNode loop_node = new SemanticLoopNode();
        variable_scope_stack.push(new VariableList(null, variable_scope_stack.peek()));
        loop_stack.push(loop_node);
        visit(ctx.noScope_block());
        variable_scope_stack.pop();
        loop_stack.pop();
        return null;
    }

    @Override
    public SemanticNode visitRETURN_STATE(MxParser.RETURN_STATEContext ctx) {
        if (function_stack.empty())
            errorReport("[STATEMENT ERROR] Invalidate RETURN Statement: The return statement should be in a function.", ctx);
        BaseType expression_type;
        String expression_type_name;
        if (ctx.expression() != null) {
            expression_type = visit(ctx.expression()).getType();
            expression_type_name = expression_type.getClassName();
        } else {
            expression_type = null;
            expression_type_name = null;
        }
        BaseType return_type = ((FunctionType) function_stack.peek().getType()).getReturnType();
        if (return_type instanceof VoidType) {
            if (expression_type != null)
                errorReport("[STATEMENT ERROR] Wrong RETURN Type: A void return function cannot have a \"" + expression_type_name + "\" return statement", ctx);
        } else if (!return_type.assignment_check(expression_type))
            errorReport("[STATEMENT ERROR] Wrong RETURN Type: The return type should be consistent with function define.", ctx);
        return new SemanticExpressionNode(expression_type, false);
    }

    @Override
    public SemanticNode visitBREAK_STATE(MxParser.BREAK_STATEContext ctx) {
        if (loop_stack.empty())
            errorReport("[STATEMENT ERROR] Invalidate BREAK Statement: Break Statement is expected in a loop block.", ctx);
        return null;
    }

    @Override
    public SemanticNode visitCONTINUE_STATE(MxParser.CONTINUE_STATEContext ctx) {
        if (loop_stack.empty())
            errorReport("[STATEMENT ERROR] Invalidate CONTINUE Statement: Continue Statement is expected in a loop block.", ctx);
        return null;
    }

    @Override
    public SemanticNode visitFUNCTION_USE(MxParser.FUNCTION_USEContext ctx) {
        String function_name = ctx.Identifier().getText();
        FunctionType function_type;
        function_type = function_scope_stack.peek().getFunctionType(function_name);
        if (function_type == null)
            errorReport("[FUNCTION ERROR] Invalidate Function Use: The program has no such function named \"" + function_name + "\"", ctx);
        Vector<BaseType> parameters;
        if (ctx.expressionList() != null)
            parameters = ((SemanticParameterNode) visit(ctx.expressionList())).getParameterTypeList();
        else
            parameters = new Vector<>();
        Vector<BaseType> parameter_list;
        if (function_type.getParameterTypeList() != null)
            parameter_list = function_type.getParameterTypeList();
        else
            parameter_list = new Vector<>();
        if (parameters.size() != parameter_list.size())
            errorReport("[FUNCTION ERROR] Inconsistent Parameter List: The parameters' number is not consistent with function define.", ctx);
        for (int i = 0; i < parameters.size(); ++i) {
            if (!parameter_list.get(i).assignment_check(parameters.get(i))) {
                String type_left = parameter_list.get(i).getClassName();
                String type_right = parameters.get(i).getClassName();
                errorReport("[FUNCTION ERROR] Inconsistent Parameter List: A " + type_left + " type is expected, but got a " + type_right + " type.", ctx);
            }
        }
        return new SemanticExpressionNode(function_type.getReturnType(), false);
    }

    @Override
    public SemanticNode visitMEMBER_VARIABLE(MxParser.MEMBER_VARIABLEContext ctx) {
        String variable_name = ctx.Identifier().getText();
        BaseType class_type = visit(ctx.expression()).getType();
        String class_name = class_type.getClassName();
        BaseType variable_type = class_type.getMemberVariableType(variable_name);
        if (variable_type == null)
            errorReport("[STATEMENT ERROR] Invalidate Member Variable: The class" + class_name + " has no such member variable \"" + variable_name + "\".", ctx);
        return new SemanticExpressionNode(variable_type, true);
    }

    @Override
    public SemanticNode visitMEMBER_FUNCTION(MxParser.MEMBER_FUNCTIONContext ctx) {
        String function_name = ctx.Identifier().getText();
        BaseType class_type = visit(ctx.expression()).getType();
        String class_name = class_type.getClassName();
        FunctionType function_type = class_type.getMemberFunctionType(function_name);
        if (function_type == null)
            errorReport("[STATEMENT ERROR] Invalidate Member Function: The class" + class_name + " has no such member variable \"" + function_name + "\".", ctx);
        Vector<BaseType> parameters;
        if (ctx.expressionList() != null)
            parameters = ((SemanticParameterNode) visit(ctx.expressionList())).getParameterTypeList();
        else
            parameters = new Vector<>();
        Vector<BaseType> parameter_list;
        if (function_type.getParameterTypeList() != null)
            parameter_list = function_type.getParameterTypeList();
        else
            parameter_list = new Vector<>();
        if (parameters.size() != parameter_list.size())
            errorReport("[FUNCTION ERROR] Inconsistent Parameter List: The parameters' number is not consistent with function define.", ctx);
        for (int i = 0; i < parameters.size(); ++i) {
            if (!parameter_list.get(i).assignment_check(parameters.get(i))) {
                String type_left = parameter_list.get(i).getClassName();
                String type_right = parameters.get(i).getClassName();
                errorReport("[FUNCTION ERROR] Inconsistent Parameter List: A " + type_left + " type is expected, but got a " + type_right + " type.", ctx);
            }
        }
        return new SemanticExpressionNode(function_type.getReturnType(), false);
    }

    @Override
    public SemanticNode visitExpressionList(MxParser.ExpressionListContext ctx) {
        Vector<BaseType> expression_list = new Vector<>();
        expression_list.add(visit(ctx.expression()).getType());
        if (ctx.expressionList() != null)
            expression_list.addAll(((SemanticParameterNode) visit(ctx.expressionList())).getParameterTypeList());
        return new SemanticParameterNode(expression_list, null);
    }

    @Override
    public SemanticNode visitARRAY(MxParser.ARRAYContext ctx) {
        SemanticNode array_node = visit(ctx.expression(0));
        String array_name = ctx.expression(0).getText();
        BaseType array_type = array_node.getType();
        BaseType subscript = visit(ctx.expression(1)).getType();
        String subscript_type_name = subscript.getClassName();
        if (!(array_type instanceof ArrayType))
            errorReport("[STATEMENT ERROR] Invalidate ARRAY Variable: \"" + array_name + "\" is not an array type.", ctx);
        if (!(subscript instanceof IntType))
            errorReport("[STATEMENT ERROR] Invalidate Subscript Expression: A int expression is expected, but got a " + subscript_type_name + ".", ctx);
        return new SemanticExpressionNode(((ArrayType) array_type).getBasicArrayType(), true);
    }

    @Override
    public SemanticNode visitPOSTFIX(MxParser.POSTFIXContext ctx) {
        SemanticNode expression_node = visit(ctx.expression());
        String expression = ctx.expression().getText();
        BaseType expression_type = expression_node.getType();
        String expression_type_name = expression_type.getClassName();
        if (!(expression_type instanceof IntType))
            errorReport("[STATEMENT ERROR] Invalidate Postfix Expression: A int expression is expected, but got a " + expression_type_name + ".", ctx);
        if (!expression_node.getLeafValue())
            errorReport("[STATEMENT ERROR] Invalidate Postfix Expression: \"" + expression + "\" is not a left value expression", ctx);
        return new SemanticExpressionNode(expression_type, false);
    }

    @Override
    public SemanticNode visitPREFIX(MxParser.PREFIXContext ctx) {
        SemanticNode expression_node = visit(ctx.expression());
        String expression = ctx.expression().getText();
        BaseType expression_type = expression_node.getType();
        String expression_type_name = expression_type.getClassName();
        if (!(expression_type instanceof IntType))
            errorReport("[STATEMENT ERROR] Invalidate Prefix Expression: A int expression is expected, but got a " + expression_type_name + ".", ctx);
        if (!expression_node.getLeafValue())
            errorReport("[STATEMENT ERROR] Invalidate Prefix Expression: \"" + expression + "\" is not a left value expression", ctx);
        return new SemanticExpressionNode(expression_type, false);
    }

    @Override
    public SemanticNode visitUNARY(MxParser.UNARYContext ctx) {
        BaseType expression_type = visit(ctx.expression()).getType();
        String expression_type_name = expression_type.getClassName();
        if (!(expression_type instanceof IntType))
            errorReport("[STATEMENT ERROR] Invalidate Unary Expression: A int expression is expected, but got a " + expression_type_name + ".", ctx);
        return new SemanticExpressionNode(expression_type, false);
    }

    @Override
    public SemanticNode visitNOT(MxParser.NOTContext ctx) {
        BaseType expression_type = visit(ctx.expression()).getType();
        String expression_type_name = expression_type.getClassName();
        if (ctx.op.getText().equals("!")) {
            if (!(expression_type instanceof BoolType))
                errorReport("[STATEMENT ERROR] Invalidate NOT Expression: A bool expression is expected, but got a " + expression_type_name + ".", ctx);
        } else if (ctx.op.getText().equals("~"))
            if (!(expression_type instanceof IntType))
                errorReport("[STATEMENT ERROR] Invalidate NOT Expression: A int expression is expected, but got a " + expression_type_name + ".", ctx);
        return new SemanticExpressionNode(expression_type, false);
    }

    @Override
    public SemanticNode visitWRONG_CREATOR(MxParser.WRONG_CREATORContext ctx) {
        String creator = ctx.getText();
        errorReport("[STATEMENT ERROR] Invalidate NEW Expression: Cannot new a array type like " + creator + ".", ctx);
        return null;
    }

    @Override
    public SemanticNode visitNEW_CREATOR(MxParser.NEW_CREATORContext ctx) {
        return visit(ctx.creator());
    }

    @Override
    public SemanticNode visitCreator(MxParser.CreatorContext ctx) {
        if (ctx.subCreator() != null)
            return visit(ctx.subCreator());
        else if (ctx.creator() != null) {
            BaseType basic_type = visit(ctx.creator()).getType();
            BaseType creator_type = new ArrayType(basic_type);
            return new SemanticExpressionNode(creator_type, false);
        } else return null;
    }

    @Override
    public SemanticNode visitSUB_CREATOR(MxParser.SUB_CREATORContext ctx) {
        BaseType basic_type = visit(ctx.subCreator()).getType();
        BaseType expression_type = visit(ctx.expression()).getType();
        String expression_type_name = expression_type.getClassName();
        if (!(expression_type instanceof IntType))
            errorReport("[STATEMENT ERROR] Invalidate NEW Expression: A int expression is expected, but got a " + expression_type_name + ".", ctx);
        BaseType creator_type = new ArrayType(basic_type);
        return new SemanticExpressionNode(creator_type, false);
    }

    @Override
    public SemanticNode visitTYPE_NEW(MxParser.TYPE_NEWContext ctx) {
        String class_name = ctx.class_name().getText();
        BaseType class_type = class_list.getClassType(class_name);
        if (class_type == null)
            errorReport("[STATEMENT ERROR] Wrong Basic Type: The program has no such type \"" + class_name + "\".", ctx);
        if (class_type instanceof VoidType)
            errorReport("[STATEMENT ERROR] Invalidate NEW Expression: Cannot new a void type.", ctx);
        return new SemanticExpressionNode(class_type, false);
    }

    @Override
    public SemanticNode visitFUNCTION_NEW(MxParser.FUNCTION_NEWContext ctx) {
        String class_name = ctx.class_name().getText();
        FunctionType function_type = function_scope_stack.peek().getFunctionType(class_name);
        BaseType class_type = class_list.getClassType(class_name);
        Vector<BaseType> parameters;
        if (ctx.expressionList() != null)
            parameters = ((SemanticParameterNode) visit(ctx.expressionList())).getParameterTypeList();
        else
            parameters = new Vector<>();
        Vector<BaseType> parameter_list = function_type.getParameterTypeList();
        if (parameter_list == null) parameter_list = new Vector<>();
        if (parameters.size() != parameter_list.size())
            errorReport("[FUNCTION ERROR] Inconsistent Parameter List: The parameters' number is not consistent with member function define.", ctx);
        for (int i = 0; i < parameters.size(); ++i) {
            if (!parameter_list.get(i).assignment_check(parameters.get(i))) {
                String type_left = parameter_list.get(i).getClassName();
                String type_right = parameters.get(i).getClassName();
                errorReport("[FUNCTION ERROR] Inconsistent Parameter List: A " + type_left + " type is expected, but got a " + type_right + " type.", ctx);
            }
        }
        return new SemanticExpressionNode(class_type, false);
    }

    @Override
    public SemanticNode visitARITHMETIC(MxParser.ARITHMETICContext ctx) {
        BaseType left_expression_type = visit(ctx.expression(0)).getType();
        String left_type_name = left_expression_type.getClassName();
        BaseType right_expression_type = visit(ctx.expression(1)).getType();
        String right_type_name = right_expression_type.getClassName();
        if (ctx.op.getText().equals("+")) {
            if (!(left_expression_type instanceof IntType) && !(left_expression_type instanceof StringType))
                errorReport("[STATEMENT ERROR] Invalidate Arithmetic Expression: A int or string expression is expected, but got a " + left_type_name + "on the left.", ctx);
            if (!(right_expression_type instanceof IntType) && !(right_expression_type instanceof StringType))
                errorReport("[STATEMENT ERROR] Invalidate Arithmetic Expression: A int or string expression is expected, but got a " + right_type_name + "on the right.", ctx);
            if (!left_expression_type.assignment_check(right_expression_type))
                errorReport("[STATEMENT ERROR] Invalidate Arithmetic Expression: The types on both sides of arithmetic expression should be consistent with each other.", ctx);
        } else {
            if (!(left_expression_type instanceof IntType))
                errorReport("[STATEMENT ERROR] Invalidate Arithmetic Expression: A int expression is expected, but got a " + left_type_name + "on the left.", ctx);
            if (!(right_expression_type instanceof IntType))
                errorReport("[STATEMENT ERROR] Invalidate Arithmetic Expression: A int expression is expected, but got a " + right_type_name + "on the right.", ctx);
        }
        return new SemanticExpressionNode(left_expression_type, false);
    }

    @Override
    public SemanticNode visitRELATION(MxParser.RELATIONContext ctx) {
        BaseType left_expression_type = visit(ctx.expression(0)).getType();
        String left_type_name = left_expression_type.getClassName();
        BaseType right_expression_type = visit(ctx.expression(1)).getType();
        String right_type_name = right_expression_type.getClassName();
        if (ctx.op.getText().equals("==") || ctx.op.getText().equals("!=")) {
            if (!left_expression_type.assignment_check(right_expression_type))
                errorReport("[STATEMENT ERROR] Invalidate Relation Expression: The types on both sides of relation expression should be consistent with each other.", ctx);
        } else {
            if (!(left_expression_type instanceof IntType) && !(left_expression_type instanceof StringType))
                errorReport("[STATEMENT ERROR] Invalidate Relation Expression: A int or string expression is expected, but got a " + left_type_name + "on the left.", ctx);
            if (!(right_expression_type instanceof IntType) && !(right_expression_type instanceof StringType))
                errorReport("[STATEMENT ERROR] Invalidate Relation Expression: A int or string expression is expected, but got a " + right_type_name + "on the right.", ctx);
            if (!left_expression_type.assignment_check(right_expression_type))
                errorReport("[STATEMENT ERROR] Invalidate Relation Expression: The types on both sides of relation expression should be consistent with each other.", ctx);
        }
        return new SemanticExpressionNode(class_list.getClassType("bool"), false);
    }

    @Override
    public SemanticNode visitLOGIC_RELATION(MxParser.LOGIC_RELATIONContext ctx) {
        BaseType left_expression_type = visit(ctx.expression(0)).getType();
        String left_type_name = left_expression_type.getClassName();
        BaseType right_expression_type = visit(ctx.expression(1)).getType();
        String right_type_name = right_expression_type.getClassName();
        if (!(left_expression_type instanceof BoolType))
            errorReport("[STATEMENT ERROR] Invalidate Relation Expression: A bool expression is expected, but got a " + left_type_name + "on the left.", ctx);
        if (!(right_expression_type instanceof BoolType))
            errorReport("[STATEMENT ERROR] Invalidate Relation Expression: A bool expression is expected, but got a " + right_type_name + "on the right.", ctx);
        return new SemanticExpressionNode(left_expression_type, false);
    }

    @Override
    public SemanticNode visitLOGIC_ARI(MxParser.LOGIC_ARIContext ctx) {
        BaseType left_expression_type = visit(ctx.expression(0)).getType();
        String left_type_name = left_expression_type.getClassName();
        BaseType right_expression_type = visit(ctx.expression(1)).getType();
        String right_type_name = right_expression_type.getClassName();
        if (!(left_expression_type instanceof IntType))
            errorReport("[STATEMENT ERROR] Invalidate Arithmetic Expression: A int expression is expected, but got a " + left_type_name + "on the left.", ctx);
        if (!(right_expression_type instanceof IntType))
            errorReport("[STATEMENT ERROR] Invalidate Arithmetic Expression: A int expression is expected, but got a " + right_type_name + "on the right.", ctx);
        return new SemanticExpressionNode(left_expression_type, false);
    }

    @Override
    public SemanticNode visitASSIGN(MxParser.ASSIGNContext ctx) {
        SemanticNode left_node = visit(ctx.expression(0));
        String expression = ctx.expression(1).getText();
        BaseType left_expression_type = left_node.getType();
        BaseType right_expression_type = visit(ctx.expression(1)).getType();
        if (!left_node.getLeafValue())
            errorReport("[STATEMENT ERROR] Invalidate Assign Expression: The expression \"" + expression + "\" on the left is not a left value expression", ctx);
        if (!left_expression_type.assignment_check(right_expression_type))
            errorReport("[STATEMENT ERROR] Invalidate Assign Expression: The types on both sides of assign expression should be consistent with each other.", ctx);
        return new SemanticExpressionNode(left_expression_type, false);
    }

    @Override
    public SemanticNode visitBRACKET(MxParser.BRACKETContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public SemanticNode visitIDENTIFIER(MxParser.IDENTIFIERContext ctx) {
        BaseType variable_type = variable_scope_stack.peek().getVariableType(ctx.getText());
        String variable_name = ctx.getText();
        if (variable_type == null)
            errorReport("[STATEMENT ERROR] Invalidate Variable: The program has no such variable named \"" + variable_name + "\".", ctx);
        return new SemanticExpressionNode(variable_type, true);
    }

    @Override
    public SemanticNode visitCONST_INT(MxParser.CONST_INTContext ctx) {
        return new SemanticExpressionNode(class_list.getClassType("int"), false);
    }

    @Override
    public SemanticNode visitCONST_BOOL(MxParser.CONST_BOOLContext ctx) {
        return new SemanticExpressionNode(class_list.getClassType("bool"), false);
    }

    @Override
    public SemanticNode visitCONST_STRING(MxParser.CONST_STRINGContext ctx) {
        return new SemanticExpressionNode(class_list.getClassType("string"), false);
    }

    @Override
    public SemanticNode visitSELF_POINTER(MxParser.SELF_POINTERContext ctx) {
        if (class_stack.empty() || function_stack.empty())
            errorReport("[STATEMENT ERROR] Invalidate THIS: THIS is expected in a member function.", ctx);
        return new SemanticExpressionNode(class_stack.peek().getType(), false);
    }

    @Override
    public SemanticNode visitNull(MxParser.NullContext ctx) {
        return new SemanticExpressionNode(class_list.getClassType("null"), false);
    }
}
