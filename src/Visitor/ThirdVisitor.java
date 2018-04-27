package Visitor;

import java.util.*;

import Parser.*;

import IRnode.*;

import Type.*;

import Exception.*;

public class ThirdVisitor extends MxBaseVisitor<IRnode> {
    private ClassList class_list;
    private FunctionList function_list;
    private Stack<IRnode> class_stack;
    private Stack<IRnode> function_stack;
    private Stack<IRnode> loop_stack;
    private Stack<VariableList> scope_stack;
    private MyException error = new MyException();

    public ThirdVisitor(ClassList _class_list, FunctionList _function_list) {
        class_list = _class_list;
        function_list = _function_list;
        class_stack = new Stack<IRnode>();
        function_stack = new Stack<IRnode>();
        loop_stack = new Stack<IRnode>();
        scope_stack = new Stack<>();
        scope_stack.push(new VariableList(null));
    }

//    private void errorPrint() {
//        error.printException();
//        System.exit(1);
//    }

    private void checkVoidInstantiation(BaseType class_type) {
        try {
            if (class_type instanceof VoidType)
                throw new VariableException("Cannot have a void type variable");
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    private void checkBoolExpression(BaseType class_type) {
        try {
            if (!(class_type instanceof BoolType)) {
                String class_name = class_type.getClassName();
                throw new StatementException("A bool type return expression is expected here, but got a \"" + class_name +"\" type");
            }
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    private void checkIntStringCompare(BaseType a, BaseType b) {
        try {
            checkTypeConsistent(a, b);
            if (!(a instanceof IntType) && !(a instanceof StringType)){
                String a_class_name = a.getClassName();
                throw new StatementException("A int or string type is expected here, but got a \"" + a_class_name +"\" type");
            }
            if (!(b instanceof IntType) && !(b instanceof StringType)){
                String b_class_name = b.getClassName();
                throw new StatementException("A int or string type is expected here, but got a \"" + b_class_name +"\" type");
            }
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    private void checkTypeConsistent(BaseType a, BaseType b) {
        try {
            if (!a.assignment_check(b)) {
                String a_class_name = a.getClassName();
                String b_class_name = b.getClassName();
                throw new StatementException("A \"" + a_class_name + "\" type is expected, instead of \"" + b_class_name + "\"");
            }
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    private void checkReturn(BaseType a, BaseType b) {
        try {
            if (a instanceof VoidType) {
                throw new StatementException("The return expression is not expected in a void return function");
            } else if (!a.assignment_check(b)) {
                String a_class_name = a.getClassName();
                String b_class_name = b.getClassName();
                throw new StatementException("The expression's return type is expected as \"" + a_class_name + " \" instead of \"" + b_class_name + "\"");
            }
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    private void checkInFunction() {
        try {
            if (function_stack.empty()) {
                throw new StatementException("The statement is expected in a function");
            }
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    private void checkInMemberFunction() {
        try {
            if (function_stack.empty() || class_stack.empty()) {
                throw new StatementException("The statement is expected in a member function");
            }
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    private void checkInLoop() {
        try {
            if (loop_stack.empty()) {
                throw new StatementException("The statement is expected in a loop");
            }
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    private void checkLeftValue(IRnode a) {
        try {
            if (!a.getLeafValue()) {
                throw new StatementException("The assignment statement expects a left value on the left");
            }
        } catch (Exception e) {
            error.printException();
            System.exit(1);
        }
    }

    private void checkFunctionParameter(FunctionType function_type, Vector<BaseType> parameter) {
        try {
            Vector<BaseType> _parameter = function_type.getParameterTypeList();
            if (parameter.size() != _parameter.size())
                throw new FunctionException("parameters' number is not consistent");
            else {
                for (int i=0; i<parameter.size(); ++i)
                    checkTypeConsistent(_parameter.get(i), parameter.get(i));
            }
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
        scope_stack.push(new VariableList(scope_stack.peek()));
        if (ctx.program() != null) visit(ctx.program());
        class_stack.pop();
        scope_stack.pop();
        return class_node;
    }

    @Override
    public IRnode visitFunction(MxParser.FunctionContext ctx) {
        FunctionType function_type;
        if (class_stack.empty()){
            function_type = function_list.getFunctionType(ctx.Identifier().getText());
        } else {
            BaseType class_type = class_stack.peek().getType();
            function_type = class_type.getFunctionType(ctx.Identifier().getText());
        }
        IRnode function_node = new IRFunctionNode(function_type);
        function_stack.push(function_node);
        scope_stack.push(new VariableList(scope_stack.peek()));
        if (ctx.parameter() != null) visit(ctx.parameter());
        visit(ctx.block());
        function_stack.pop();
        scope_stack.pop();
        return function_node;
    }

    @Override
    public IRnode visitParameter(MxParser.ParameterContext ctx) {
        Vector<BaseType> parameter_list = new Vector<>();
        BaseType variable_type = class_list.getClass(ctx.class_statement().getText());
        String variable_name = ctx.Identifier().getText();
        parameter_list.add(variable_type);
        scope_stack.peek().insertVariable(variable_name, variable_type);
        if (ctx.parameter() != null)
            parameter_list.addAll(((IRParameterNode) visit(ctx.parameter())).getParameterTypeList());
        return new IRParameterNode(parameter_list);
    }

    @Override
    public IRnode visitNORMAL_INS(MxParser.NORMAL_INSContext ctx) {
        String variable_class_name = ctx.class_statement().getText();
        String variable_identifier_name = ctx.Identifier().getText();
        BaseType variable_type = class_list.getClass(variable_class_name);
        checkVoidInstantiation(variable_type);
        scope_stack.peek().insertVariable(variable_identifier_name, variable_type);
        return new IRVariableNode(variable_type, false);
    }

    @Override
    public IRnode visitASSIGN_INS(MxParser.ASSIGN_INSContext ctx) {
        String variable_class_name = ctx.class_statement().getText();
        String variable_identifier_name = ctx.Identifier().getText();
        BaseType variable_type = class_list.getClass(variable_class_name);
        checkVoidInstantiation(variable_type);
        scope_stack.peek().insertVariable(variable_identifier_name, variable_type);
        BaseType expression_type = ((IRExpressionNode) visit(ctx.expression())).getType();
        checkTypeConsistent(variable_type, expression_type);
        return new IRVariableNode(variable_type, false);
    }

    @Override
    public IRnode visitIF_STATE(MxParser.IF_STATEContext ctx) {
        BaseType expression_type = ((IRExpressionNode) visit(ctx.expression())).getType();
        checkBoolExpression(expression_type);
        scope_stack.push(new VariableList(scope_stack.peek()));
        visit(ctx.statement());
        scope_stack.pop();
        return null;
    }

    @Override
    public IRnode visitIFELSE_STATE(MxParser.IFELSE_STATEContext ctx) {
        BaseType expression_type = ((IRExpressionNode) visit(ctx.expression())).getType();
        checkBoolExpression(expression_type);
        scope_stack.push(new VariableList(scope_stack.peek()));
        visit(ctx.statement(0));
        scope_stack.pop();
        scope_stack.push(new VariableList(scope_stack.peek()));
        visit(ctx.statement(1));
        scope_stack.pop();
        return null;
    }

    @Override
    public IRnode visitFOR_STATE(MxParser.FOR_STATEContext ctx) {
        if (ctx.first != null) visit(ctx.first);
        if (ctx.second != null) {
            BaseType second_type = ((IRExpressionNode) visit(ctx.second)).getType();
            checkBoolExpression(second_type);
        }
        if (ctx.third != null) visit(ctx.third);
        IRnode loop_node = new IRLoopNode();
        scope_stack.push(new VariableList(scope_stack.peek()));
        loop_stack.push(loop_node);
        visit(ctx.statement());
        scope_stack.pop();
        loop_stack.pop();
        return null;
    }

    @Override
    public IRnode visitWHILE_STATE(MxParser.WHILE_STATEContext ctx) {
        BaseType expression_type = ((IRExpressionNode) visit(ctx.expression())).getType();
        checkBoolExpression(expression_type);
        IRnode loop_node = new IRLoopNode();
        scope_stack.push(new VariableList(scope_stack.peek()));
        loop_stack.push(loop_node);
        visit(ctx.statement());
        scope_stack.pop();
        loop_stack.pop();
        return null;
    }

    @Override
    public IRnode visitRETURN_STATE(MxParser.RETURN_STATEContext ctx) {
        BaseType expression_type = ((IRExpressionNode) visit(ctx.expression())).getType();
        checkInFunction();
        BaseType return_type = ((FunctionType) function_stack.peek().getType()).getReturnType();
        checkReturn(return_type, expression_type);
        return new IRExpressionNode(expression_type, false);
    }

    @Override
    public IRnode visitBREAK_STATE(MxParser.BREAK_STATEContext ctx) {
        checkInLoop();
        return null;
    }

    @Override
    public IRnode visitCONTINUE_STATE(MxParser.CONTINUE_STATEContext ctx) {
        checkInLoop();
        return null;
    }

    @Override
    public IRnode visitFUNCTION_USE(MxParser.FUNCTION_USEContext ctx) {
        String function_name = ctx.Identifier().getText();
        FunctionType function_type;
        if (class_stack.empty())
            function_type = function_list.getFunctionType(function_name);
        else {
            BaseType class_type = class_stack.peek().getType();
            function_type = class_type.getFunctionType(function_name);
        }
        Vector<BaseType> parameters;
        if (ctx.expressionList() != null)
            parameters = ((IRParameterNode) visit(ctx.expressionList())).getParameterTypeList();
        else
            parameters = new Vector<>();
        checkFunctionParameter(function_type, parameters);
        return new IRExpressionNode(function_type.getReturnType(), false);
    }

    @Override
    public IRnode visitMEMBER_VARIABLE(MxParser.MEMBER_VARIABLEContext ctx) {
        String variable_name = ctx.Identifier().getText();
        BaseType class_type = visit(ctx.expression()).getType();
        BaseType variable_type = class_type.getVariableType(variable_name);
        return new IRExpressionNode(variable_type, true);
    }

    @Override
    public IRnode visitMEMBER_FUNCTION(MxParser.MEMBER_FUNCTIONContext ctx) {
        String function_name = ctx.Identifier().getText();
        BaseType class_type = visit(ctx.expression()).getType();
        FunctionType function_type = class_type.getFunctionType(function_name);
        Vector<BaseType> parameters;
        if (ctx.expressionList() != null)
            parameters = ((IRParameterNode) visit(ctx.expressionList())).getParameterTypeList();
        else
            parameters = new Vector<>();
        checkFunctionParameter(function_type, parameters);
        return new IRExpressionNode(function_type.getReturnType(), false);
    }

    @Override
    public IRnode visitExpressionList(MxParser.ExpressionListContext ctx) {
        Vector<BaseType> expression_list = new Vector<>();
        expression_list.add(visit(ctx.expression()).getType());
        if (ctx.expressionList() != null)
            expression_list.addAll(((IRParameterNode) visit(ctx.expressionList())).getParameterTypeList());
        return new IRParameterNode(expression_list);
    }

    @Override
    public IRnode visitARRAY(MxParser.ARRAYContext ctx) {
        BaseType array_type= visit(ctx.expression(0)).getType();
        BaseType subscript = visit(ctx.expression(1)).getType();
        checkTypeConsistent(array_type, new ArrayType(null));
        checkTypeConsistent(subscript, class_list.getClass("int"));
        return new IRExpressionNode(((ArrayType) array_type).getBasicArrayType(),true);
    }

    @Override
    public IRnode visitPOSTFIX(MxParser.POSTFIXContext ctx) {
        BaseType expression_type = visit(ctx.expression()).getType();
        checkTypeConsistent(expression_type, class_list.getClass("int"));
        return new IRExpressionNode(expression_type, false);
    }

    @Override
    public IRnode visitPREFIX(MxParser.PREFIXContext ctx) {
        BaseType expression_type = visit(ctx.expression()).getType();
        checkTypeConsistent(expression_type, class_list.getClass("int"));
        return new IRExpressionNode(expression_type, false);
    }

    @Override
    public IRnode visitUNARY(MxParser.UNARYContext ctx) {
        BaseType expression_type = visit(ctx.expression()).getType();
        checkTypeConsistent(expression_type, class_list.getClass("int"));
        return new IRExpressionNode(expression_type, false);
    }

    @Override
    public IRnode visitNOT(MxParser.NOTContext ctx) {
        BaseType expression_type = visit(ctx.expression()).getType();
        if (ctx.op.getText().equals("!"))
            checkTypeConsistent(expression_type, class_list.getClass("bool"));
        else if (ctx.op.getText().equals("~"))
            checkTypeConsistent(expression_type, class_list.getClass("int"));
        return new IRExpressionNode(expression_type, false);
    }

    @Override
    public IRnode visitARITHMETIC(MxParser.ARITHMETICContext ctx) {
        BaseType left_expression_type = visit(ctx.expression(0)).getType();
        BaseType right_expression_type = visit(ctx.expression(1)).getType();
        checkTypeConsistent(left_expression_type, class_list.getClass("int"));
        checkTypeConsistent(right_expression_type, class_list.getClass("int"));
        return new IRExpressionNode(left_expression_type, false);
    }

    @Override
    public IRnode visitRELATION(MxParser.RELATIONContext ctx) {
        BaseType left_expression_type = visit(ctx.expression(0)).getType();
        BaseType right_expression_type = visit(ctx.expression(1)).getType();
        checkIntStringCompare(left_expression_type, right_expression_type);
        return new IRExpressionNode(class_list.getClass("bool"), false);
    }

    @Override
    public IRnode visitLOGIC_RELATION(MxParser.LOGIC_RELATIONContext ctx) {
        BaseType left_expression_type = visit(ctx.expression(0)).getType();
        BaseType right_expression_type = visit(ctx.expression(1)).getType();
        checkTypeConsistent(left_expression_type, class_list.getClass("int"));
        checkTypeConsistent(right_expression_type, class_list.getClass("int"));
        return new IRExpressionNode(left_expression_type, false);
    }

    @Override
    public IRnode visitLOGIC_ARI(MxParser.LOGIC_ARIContext ctx) {
        BaseType left_expression_type = visit(ctx.expression(0)).getType();
        BaseType right_expression_type = visit(ctx.expression(1)).getType();
        checkTypeConsistent(left_expression_type, class_list.getClass("int"));
        checkTypeConsistent(right_expression_type, class_list.getClass("int"));
        return new IRExpressionNode(left_expression_type, false);
    }

    @Override
    public IRnode visitASSIGN(MxParser.ASSIGNContext ctx) {
        IRnode left_node = visit(ctx.expression(0));
        BaseType left_expression_type = left_node.getType();
        BaseType right_expression_type = visit(ctx.expression(1)).getType();
        checkLeftValue(left_node);
        checkTypeConsistent(left_expression_type, right_expression_type);
        return new IRExpressionNode(left_expression_type, false);
    }

    @Override
    public IRnode visitBRACKET(MxParser.BRACKETContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public IRnode visitIDENTIFIER(MxParser.IDENTIFIERContext ctx) {
        BaseType variable_type = scope_stack.peek().getVariableType(ctx.getText());
        return new IRExpressionNode(variable_type, true);
    }

    @Override
    public IRnode visitCONST_INT(MxParser.CONST_INTContext ctx) {
        return new IRExpressionNode(class_list.getClass("int"), false);
    }

    @Override
    public IRnode visitCONST_BOOL(MxParser.CONST_BOOLContext ctx) {
        return new IRExpressionNode(class_list.getClass("bool"), false);
    }

    @Override
    public IRnode visitCONST_STRING(MxParser.CONST_STRINGContext ctx) {
        return new IRExpressionNode(class_list.getClass("string"), false);
    }

    @Override
    public IRnode visitSELF_POINTER(MxParser.SELF_POINTERContext ctx) {
        checkInMemberFunction();
        return new IRExpressionNode(class_stack.peek().getType(), true);
    }

    @Override
    public IRnode visitNull(MxParser.NullContext ctx) {
        return new IRExpressionNode(class_list.getClass("null"), false);
    }
}
