package Visitor;

import IR.IRInstruction.*;
import IR.IRInstruction.Operand.*;
import IR.IRNode.*;
import IR.IR;
import IR.IRInstruction.Operand.Variable;
import NasmTranslate.StackAlloc;
import Parser.*;
import Type.*;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.*;

public class IRBuilder extends MxBaseVisitor<IR> {
    private List<IRInstruction> statements = new LinkedList<>();
    private List<IRInstruction> global_statements = new LinkedList<>();
    private Map<String, IRFunction> functions = new HashMap<>();
    private Map<String, IRFunction> inFunctions = new HashMap<>();
    private Map<Variable, String> const_string = new HashMap<>();
    private ClassList class_list;
    private Set<String> variable_rename_set = new HashSet<>();
    private Stack<IRScope> variable_scope = new Stack<>();
    private StackAlloc global_stackAlloc;
    static private StackAlloc current_stackAlloc;
    private BaseType current_class;
    private IRFunction current_function;
    private Label current_loop_condition = null;
    private Label current_loop_end = null;
    private Immediate const_one = new Immediate(1);
    private Immediate const_zero = new Immediate(0);

    private Variable getNewVar(String name, BaseType class_type) {
        String tmp_rename = variableRename(name);
        return new Variable(tmp_rename, class_type, false);
    }

    private void setMalloc() {
        String malloc_name = "malloc";
        IRFunction malloc_func = new IRFunction(malloc_name, null);
        inFunctions.put(malloc_name, malloc_func);
    }

    private void setPrint() {
        String print_name = "print";
        FunctionType functionType = new FunctionType(class_list.getClassType("void"), null, null);
        IRFunction print_func = new IRFunction(print_name, functionType);
        inFunctions.put(print_name, print_func);
    }

    private void setPrintln() {
        String println_name = "println";
        FunctionType functionType = new FunctionType(class_list.getClassType("void"), null, null);
        IRFunction println_func = new IRFunction(println_name, functionType);
        inFunctions.put(println_name, println_func);
    }

    private void setGetString() {
        String getString_name = "getString";
        FunctionType functionType = new FunctionType(class_list.getClassType("string"), null, null);
        IRFunction getString_func = new IRFunction(getString_name, functionType);
        inFunctions.put(getString_name, getString_func);
    }

    private void setGetInt() {
        String getInt_name = "getInt";
        FunctionType functionType = new FunctionType(class_list.getClassType("int"), null, null);
        IRFunction getInt_func = new IRFunction(getInt_name, functionType);
        inFunctions.put(getInt_name, getInt_func);
    }

    private void setToString() {
        String toString_name = "toString";
        FunctionType functionType = new FunctionType(class_list.getClassType("string"), null, null);
        IRFunction toString_func = new IRFunction(toString_name, functionType);
        inFunctions.put(toString_name, toString_func);
    }

    private void setLength() {
        String length_name = "strlen";
        IRFunction length_func = new IRFunction(length_name, null);
        inFunctions.put(length_name, length_func);
    }

    private void setParseInt() {
        String parseInt_name = "parseInt";
        IRFunction parseInt_func = new IRFunction(parseInt_name, null);
        inFunctions.put(parseInt_name, parseInt_func);
    }

    private void setOrd() {
        String ord_name = "ord";
        IRFunction ord_func = new IRFunction(ord_name, null);
        inFunctions.put(ord_name, ord_func);
    }

    private void setSubString() {
        String subString_name = "substring";
        IRFunction subString_func = new IRFunction(subString_name, null);
        inFunctions.put(subString_name, subString_func);
    }

    private void getStrCombine() {
        String strCombine_name = "strCombine";
        IRFunction strCombine_func = new IRFunction(strCombine_name, null);
        inFunctions.put(strCombine_name, strCombine_func);
    }

    private void setInFunction() {
        setMalloc();
        setPrint();
        setPrintln();
        setGetString();
        setGetInt();
        setToString();
        setLength();
        setParseInt();
        setOrd();
        setSubString();
        getStrCombine();
    }

    public IRBuilder(ClassList _class_list, FunctionList _global_function_list) {
        Map<String, BaseType> class_list = _class_list.getClassList();
        for (String class_name : class_list.keySet()) {
            class_list.get(class_name).setSize();
        }
        this.class_list = _class_list;
        setInFunction();
        Map<String, FunctionType> global_function_list = _global_function_list.getFunctionList();
        for (String function_name : global_function_list.keySet()) {
            if (function_name.equals("print") || function_name.equals("println") || function_name.equals("getString")
                    || function_name.equals("getInt") || function_name.equals("toString"))
                continue;
            FunctionType function_type = global_function_list.get(function_name);
            IRFunction function_node = new IRFunction(function_name, function_type);
            functions.put(function_name, function_node);
            if (function_name.equals("main")) global_stackAlloc = function_node.getStackAlloc();
        }
        for (String class_name : class_list.keySet()) {
            BaseType class_type = class_list.get(class_name);
            if (class_type instanceof UserType) {
                Map<String, FunctionType> member_function_list = class_type.getMemberFunctionList().getFunctionList();
                for (String function_name : member_function_list.keySet()) {
                    FunctionType function_type = member_function_list.get(function_name);
                    String function_rename = class_name + "_" + function_name;
                    IRFunction function_node = new IRFunction(function_rename, function_type);
                    functions.put(function_rename, function_node);
                }
            }
        }
        variable_scope.push(new IRScope(null));
        current_stackAlloc = global_stackAlloc;
    }

    private int labelCounter = 0;
    private void addLabel(Label label) {
        label.setName(label.getName() + "_" + labelCounter++);
        statements.add(label);
    }

    private String variableRename(String variable_name) {
        int counter = 0;
        String rename = "_" + variable_name;
        while (variable_rename_set.contains(rename)) {
            rename = "_" + variable_name + counter++;
        }
        variable_rename_set.add(rename);
        return rename;
    }

    private void addCJump(Operand condition, Label true_label, Label false_label) {
        IRInstruction ins = ((LinkedList<IRInstruction>) statements).getLast();
        if (ins instanceof Cmp) {
            ((LinkedList<IRInstruction>) statements).removeLast();
            statements.add(new CJump((Cmp) ins, true_label, false_label));
        } else {
            statements.add(new CJump(condition, true_label, false_label));
        }
    }

    private void addLoop(ParserRuleContext init, ParserRuleContext condition, ParserRuleContext inc, ParserRuleContext body) {
        if (init != null) visit(init);
        Label begin_label = new Label("loop_begin");
        Label condition_label = new Label("loop_cond");
        Label end_label = new Label("loop_end");

        Label old_condition = current_loop_condition;
        Label old_end = current_loop_end;
        current_loop_condition = condition_label;
        current_loop_end = end_label;

        statements.add(new Jump(condition_label));
        addLabel(begin_label);
        variable_scope.push(new IRScope(variable_scope.peek()));
        visit(body);
        variable_scope.pop();
        if (inc != null) visit(inc);
        addLabel(condition_label);
        Operand cond;
        if (condition == null)
            cond = null;
        else
            cond = (Operand) visit(condition);
        if (cond == null) {
            statements.add(new Jump(begin_label));
        } else {
            if (!(cond instanceof Immediate))
                addCJump(cond, begin_label, end_label);
            else {
                int value = ((Immediate) cond).getValue();
                if (value == 1)
                    statements.add(new Jump(begin_label));
            }
        }
        addLabel(end_label);
        current_loop_condition = old_condition;
        current_loop_end = old_end;
    }

    @Override
    public IR visitTypeDefine(MxParser.TypeDefineContext ctx) {
        String class_name = ctx.Identifier().getText();
        current_class = class_list.getClassType(class_name);
        variable_scope.push(new IRScope(variable_scope.peek()));
        if (ctx.program() != null) visit(ctx.program());
        current_class = null;
        variable_scope.pop();
        return null;
    }

    @Override
    public IR visitFunction(MxParser.FunctionContext ctx) {
        global_statements.addAll(statements);
        statements.clear();
        String function_name = ctx.Identifier().getText();
        String function_rename;
        if (current_class == null) {
            function_rename = function_name;
        } else {
            function_rename =  current_class.getClassName() + "_" + function_name;
        }
        IRFunction function_node = functions.get(function_rename);
        current_function = function_node;
        current_stackAlloc = function_node.getStackAlloc();
        FunctionType function_type = function_node.getFunctionType();
        Vector<BaseType> parameters_type = function_type.getParameterTypeList();
        Vector<String> parameters_name = function_type.getParameterNameList();
        IRScope scope = new IRScope(variable_scope.peek());
        Vector<Variable> parameters = new Vector<>();
        if (current_class != null) {
            Variable tmp = getNewVar("self", current_class);
            parameters.add(tmp);
        }
        for (int i = 0; i < parameters_name.size(); ++i) {
            String parameter_name = parameters_name.get(i);
            String class_name = parameters_type.get(i).getClassName();
            BaseType class_type = class_list.getClassType(class_name);
            Variable para = getNewVar(parameter_name, class_type);
            if (class_type instanceof StringType) para.setIsString(true);
            parameters.add(para);
            scope.insert(parameter_name, para);
//            System.out.println(parameter_name);
        }
//        System.out.println(parameters_type.size());
        function_node.setParameters(parameters);
        variable_scope.push(scope);
        visit(ctx.noScope_block());
        current_function = null;
        current_stackAlloc = global_stackAlloc;
        variable_scope.pop();
        function_node.setStatements(statements);
        statements = new LinkedList<>();
        return null;
    }

    @Override
    public IR visitScope_block(MxParser.Scope_blockContext ctx) {
        variable_scope.push(new IRScope(variable_scope.peek()));
        visitChildren(ctx);
        variable_scope.pop();
        return null;
    }

    @Override
    public IR visitNORMAL_INS(MxParser.NORMAL_INSContext ctx) {
        if (current_class != null) return null;
        String variable_name = ctx.Identifier().getText();
        BaseType class_type = class_list.getClassType(ctx.class_statement().getText());
        Boolean isGlobal = current_function == null && current_class == null;
        Variable var = new Variable(variableRename(variable_name), class_type, isGlobal);
        IRScope scope = variable_scope.peek();
        scope.insert(variable_name, var);
        return null;
    }

    @Override
    public IR visitASSIGN_INS(MxParser.ASSIGN_INSContext ctx) {
        if (current_class != null) return null;
        String variable_name = ctx.Identifier().getText();
        BaseType class_type = class_list.getClassType(ctx.class_statement().getText());
        Boolean isGlobal = current_function == null && current_class == null;
        Variable var = new Variable(variableRename(variable_name), class_type, isGlobal);
        if (class_type instanceof StringType) var.setIsString(true);
        IRScope scope = variable_scope.peek();
        scope.insert(variable_name, var);
        Operand init = (Operand) visit(ctx.expression());
        statements.add(new Move(var, init));
        return null;
    }

    @Override
    public IR visitIF_STATE(MxParser.IF_STATEContext ctx) {
        Operand condition = (Operand) visit(ctx.expression());
        Label then_label = new Label("if_then");
        Label end_label = new Label("if_end");
        if (!(condition instanceof Immediate))
            addCJump(condition, then_label, end_label);
        else {
            int value = ((Immediate) condition).getValue();
            if (value == 0)
                statements.add(new Jump(end_label));
        }
        addLabel(then_label);
        variable_scope.push(new IRScope(variable_scope.peek()));
        visit(ctx.noScope_block());
        variable_scope.pop();
        addLabel(end_label);
        return null;
    }

    @Override
    public IR visitIFELSE_STATE(MxParser.IFELSE_STATEContext ctx) {
        Operand condition = (Operand) visit(ctx.expression());
        Label then_label = new Label("if_then");
        Label else_label = new Label("if_else");
        Label end_label = new Label("if_end");
        if (!(condition instanceof Immediate))
            addCJump(condition, then_label, else_label);
        else {
            int value = ((Immediate) condition).getValue();
            if (value == 0)
                statements.add(new Jump(else_label));
        }
        addLabel(then_label);
        variable_scope.push(new IRScope(variable_scope.peek()));
        visit(ctx.noScope_block(0));
        statements.add(new Jump(end_label));
        variable_scope.pop();
        addLabel(else_label);
        variable_scope.push(new IRScope(variable_scope.peek()));
        visit(ctx.noScope_block(1));
        variable_scope.pop();
        addLabel(end_label);
        return null;
    }

    @Override
    public IR visitFOR_STATE(MxParser.FOR_STATEContext ctx) {
        addLoop(ctx.first, ctx.second, ctx.third, ctx.noScope_block());
        return null;
    }

    @Override
    public IR visitWHILE_STATE(MxParser.WHILE_STATEContext ctx) {
        addLoop(null, ctx.expression(), null, ctx.noScope_block());
        return null;
    }

    @Override
    public IR visitRETURN_STATE(MxParser.RETURN_STATEContext ctx) {
        statements.add(new Label(";"+ctx.getText()));
        Operand ret = (Operand) visit(ctx.expression());
        statements.add(new Return(ret));
        statements.add(new Jump(current_function.getEndLabel()));
        return null;
    }

    @Override
    public IR visitBREAK_STATE(MxParser.BREAK_STATEContext ctx) {
        statements.add(new Jump(current_loop_end));
        return null;
    }

    @Override
    public IR visitCONTINUE_STATE(MxParser.CONTINUE_STATEContext ctx) {
        statements.add(new Jump(current_loop_condition));
        return null;
    }

    @Override
    public IR visitFUNCTION_USE(MxParser.FUNCTION_USEContext ctx) {
        String function_name = ctx.Identifier().getText();
        IRFunction function_node = functions.get(function_name);
        if (function_node == null) function_node = inFunctions.get(function_name);
        IRParameter parameters = new IRParameter(new Vector<>());
        if (ctx.expressionList() != null)
            parameters = (IRParameter) visit(ctx.expressionList());
        Call stmt = new Call(function_node, parameters);
        statements.add(stmt);
        if (function_node.getFunctionType().getReturnType() instanceof StringType)
            stmt.getTmp_return().setIsString(true);
        return stmt.getTmp_return();
    }

    @Override
    public IR visitExpressionList(MxParser.ExpressionListContext ctx) {
        Vector<Operand> parameters = new Vector<>();
        Operand parameter =(Operand) visit(ctx.expression());
        parameters.add(parameter);
        if (ctx.expressionList() != null)
            parameters.addAll(((IRParameter) visit(ctx.expressionList())).getParameters());
        return new IRParameter(parameters);
    }

    @Override
    public IR visitARRAY(MxParser.ARRAYContext ctx) {
        Operand pointer = (Operand) visit(ctx.expression(0));
        Operand offset = (Operand) visit(ctx.expression(1));
        BaseType type;
        if (pointer instanceof Variable && ((Variable) pointer).getType() instanceof ArrayType)
            type = ((ArrayType) ((Variable) pointer).getType()).getBasicArrayType();
        else if (pointer instanceof Memory && ((Memory) pointer).getType() instanceof ArrayType)
            type = ((ArrayType) ((Memory) pointer).getType()).getBasicArrayType();
        else return null;
        return new Memory(pointer, offset, 8, 0, type);
    }

    @Override
    public IR visitMEMBER_VARIABLE(MxParser.MEMBER_VARIABLEContext ctx) {
        Operand pointer = (Operand) visit(ctx.expression());
        BaseType class_type;
        if (pointer instanceof Variable) class_type = ((Variable) pointer).getType();
        else if (pointer instanceof Memory) class_type = ((Memory) pointer).getType();
        else return null;
        String member_variable_name = ctx.Identifier().getText();
        Immediate offset = new Immediate(class_type.getOffset(member_variable_name));
        BaseType type = class_type.offsetToType(class_type.getOffset(member_variable_name));
        return new Memory(pointer, offset, 1, 0, type);
    }

    @Override
    public IR visitMEMBER_FUNCTION(MxParser.MEMBER_FUNCTIONContext ctx) {
        Operand pointer = (Operand) visit(ctx.expression());
        BaseType class_type;
        if (pointer instanceof Variable) class_type = ((Variable) pointer).getType();
        else if (pointer instanceof Memory) class_type = ((Memory) pointer).getType();
        else return null;
        String class_name = class_type.getClassName();
        String function_name = ctx.Identifier().getText();
        Vector<Operand> parameters = new Vector<>();
        parameters.add(pointer);
        if (ctx.expressionList() != null)
            parameters.addAll(((IRParameter) visit(ctx.expressionList())).getParameters());
        if (class_type instanceof ArrayType) {
            if (function_name.equals("size")) {
                return new Memory(pointer, null, 1, -8, class_list.getClassType("int"));
            }
        }
        if (class_type instanceof StringType) {
            if (function_name.equals("length")) {
                Call call = new Call(inFunctions.get("strlen"), new IRParameter(parameters));
                statements.add(call);
                return call.getTmp_return();
            } else {
                Call call = new Call(inFunctions.get(function_name), new IRParameter(parameters));
                statements.add(call);
                if (function_name.equals("substring"))
                    call.getTmp_return().setIsString(true);
                return call.getTmp_return();
            }
        }
        String function_rename = class_name + "_" + function_name;
        IRFunction function_node = functions.get(function_rename);
        Call call = new Call(function_node, new IRParameter(parameters));
        statements.add(call);
        if (function_node.getFunctionType().getReturnType() instanceof StringType)
            call.getTmp_return().setIsString(true);
        return call.getTmp_return();
    }

    @Override
    public IR visitIDENTIFIER(MxParser.IDENTIFIERContext ctx) {
//        System.out.println(ctx.getText());
//        while (!variable_scope.empty())
//            System.out.println(variable_scope.pop().getScope());
        return variable_scope.peek().get(ctx.getText());
    }

    @Override
    public IR visitCONST_INT(MxParser.CONST_INTContext ctx) {
        return new Immediate(Integer.parseInt(ctx.getText()));
    }

    @Override
    public IR visitCONST_BOOL(MxParser.CONST_BOOLContext ctx) {
        return new Immediate(ctx.getText().equals("true") ? 1 : 0);
    }

    @Override
    public IR visitCONST_STRING(MxParser.CONST_STRINGContext ctx) {
        Variable tmp = new Variable(variableRename("str"), class_list.getClassType("string"), true);
        tmp.setIsString(true);
        String str = ctx.getText();
        const_string.put(tmp, str.substring(1,str.length()-1));
        return tmp;
    }

    @Override
    public IR visitSELF_POINTER(MxParser.SELF_POINTERContext ctx) {
        return current_function.getParameter(0);
    }

    @Override
    public IR visitPOSTFIX(MxParser.POSTFIXContext ctx) {
        Operand expr = (Operand) visit(ctx.expression());
        Variable var = getNewVar("tmp", class_list.getClassType("int"));
        statements.add(new Move(var, expr));
        if (ctx.op.getText().equals("++"))
            statements.add(new Inc(expr));
        else if (ctx.op.getText().equals("--"))
            statements.add(new Dec(expr));
        return var;
    }

    @Override
    public IR visitPREFIX(MxParser.PREFIXContext ctx) {
        Operand expr = (Operand) visit(ctx.expression());
        if (ctx.op.getText().equals("++"))
            statements.add(new Inc(expr));
        else if (ctx.op.getText().equals("--"))
            statements.add(new Dec(expr));
        Variable var = getNewVar("tmp", class_list.getClassType("int"));
        statements.add(new Move(var, expr));
        return var;
    }

    @Override
    public IR visitUNARY(MxParser.UNARYContext ctx) {
        Operand expr = (Operand) visit(ctx.expression());
        if (ctx.op.getText().equals("-")) {
            if (!(expr instanceof Immediate)) {
                Neg neg = new Neg(expr);
                statements.add(neg);
                return neg.getDest();
            } else {
                int value = ((Immediate) expr).getValue();
                return new Immediate(-value);
            }
        }
        else return expr;
    }

    @Override
    public IR visitNOT(MxParser.NOTContext ctx) {
        Operand expr = (Operand) visit(ctx.expression());
        String op = ctx.op.getText();
        if (op.equals("~")) {
            if (expr instanceof Immediate) {
                int value = ((Immediate) expr).getValue();
                return new Immediate(~value);
            } else {
                Not not = new Not(expr);
                statements.add(not);
                return not.getDest();
            }
        } else {
            if (expr instanceof Immediate) {
                int value = ((Immediate) expr).getValue();
                return new Immediate(value == 0 ? 1 : 0);
            } else {
                Xor xor = new Xor(expr, new Immediate(1));
                statements.add(xor);
                return xor.getDest();
            }
        }
    }

    private IR visitBinary(Operand lhs, Operand rhs, String op) {
        if (lhs instanceof Immediate && rhs instanceof Immediate) {
            int lhs_value = ((Immediate) lhs).getValue();
            int rhs_value = ((Immediate) rhs).getValue();
            switch (op) {
                case "+":
                    return new Immediate(lhs_value + rhs_value);
                case "-":
                    return new Immediate(lhs_value - rhs_value);
                case "*":
                    return new Immediate(lhs_value * rhs_value);
                case "/":
                    return new Immediate(lhs_value / rhs_value);
                case "%":
                    return new Immediate(lhs_value % rhs_value);
                case "<<":
                    return new Immediate(lhs_value << rhs_value);
                case ">>":
                    return new Immediate(lhs_value >> rhs_value);
                case "&":
                    return new Immediate(lhs_value & rhs_value);
                case "|":
                    return new Immediate(lhs_value | rhs_value);
                case "^":
                    return new Immediate(lhs_value ^ rhs_value);
                case "<":
                    return new Immediate(lhs_value < rhs_value ? 1 : 0);
                case ">":
                    return new Immediate(lhs_value > rhs_value ? 1 : 0);
                case "<=":
                    return new Immediate(lhs_value <= rhs_value ? 1 : 0);
                case ">=":
                    return new Immediate(lhs_value >= rhs_value ? 1 : 0);
                case "==":
                    return new Immediate(lhs_value == rhs_value ? 1 : 0);
                case "!=":
                    return new Immediate(lhs_value != rhs_value ? 1 : 0);
                default:
                    return null;
            }
        } else {
            Bin stmt;
            if (lhs.getIsString() && op.equals("+")) {
                Vector<Operand> parameters = new Vector<>();
                parameters.add(lhs);
                parameters.add(rhs);
                Call call = new Call(inFunctions.get("strCombine"), new IRParameter(parameters));
                statements.add(call);
                call.getTmp_return().setIsString(true);
                return call.getTmp_return();
            }
            switch (op) {
                case "+":
                    statements.add(stmt = new Add(lhs, rhs));
                    break;
                case "-":
                    statements.add(stmt = new Sub(lhs, rhs));
                    break;
                case "*":
                    statements.add(stmt = new Mul(lhs, rhs));
                    break;
                case "/":
                    statements.add(stmt = new Div(lhs, rhs));
                    break;
                case "%":
                    statements.add(stmt = new Mod(lhs, rhs));
                    break;
                case "<<":
                    statements.add(stmt = new Sal(lhs, rhs));
                    break;
                case ">>":
                    statements.add(stmt = new Sar(lhs, rhs));
                    break;
                case "&":
                    statements.add(stmt = new And(lhs, rhs));
                    break;
                case "|":
                    statements.add(stmt = new Or(lhs, rhs));
                    break;
                case "^":
                    statements.add(stmt = new Xor(lhs, rhs));
                    break;
                case "<":
                    statements.add(stmt = new Cmp(lhs, rhs, op));
                    break;
                case ">":
                    statements.add(stmt = new Cmp(lhs, rhs, op));
                    break;
                case "<=":
                    statements.add(stmt = new Cmp(lhs, rhs, op));
                    break;
                case ">=":
                    statements.add(stmt = new Cmp(lhs, rhs, op));
                    break;
                case "==":
                    statements.add(stmt = new Cmp(lhs, rhs, op));
                    break;
                case "!=":
                    statements.add(stmt = new Cmp(lhs, rhs, op));
                    break;
                default:
                    stmt = new Add(null, null);
            }
            return stmt.getDest();
        }
    }

    @Override
    public IR visitARITHMETIC(MxParser.ARITHMETICContext ctx) {
        Operand rhs = (Operand) visit(ctx.expression(1));
        Operand lhs = (Operand) visit(ctx.expression(0));
        return visitBinary(lhs, rhs, ctx.op.getText());
    }

    @Override
    public IR visitRELATION(MxParser.RELATIONContext ctx) {
        Operand rhs = (Operand) visit(ctx.expression(1));
        Operand lhs = (Operand) visit(ctx.expression(0));
        return visitBinary(lhs, rhs, ctx.op.getText());
    }

    @Override
    public IR visitLOGIC_RELATION(MxParser.LOGIC_RELATIONContext ctx) {
        Variable tmp = getNewVar("tmp", class_list.getClassType("int"));
        Label goon_label = new Label("goon");
        Label end_label = new Label("end");
        Operand lhs = (Operand) visit(ctx.expression(0));
        statements.add(new Move(tmp, lhs));
        if (ctx.op.getText().equals("&&")) {
            statements.add(new CJump(new Cmp(tmp, const_one, "=="), goon_label, end_label));
            addLabel(goon_label);
            Operand rhs = (Operand) visit(ctx.expression(1));
            statements.add(new Move(tmp, rhs));
            addLabel(end_label);
        } else {
            statements.add(new CJump(new Cmp(tmp, const_one, "=="), end_label, goon_label));
            addLabel(goon_label);
            Operand rhs = (Operand) visit(ctx.expression(1));
            statements.add(new Move(tmp, rhs));
            addLabel(end_label);
        }
        return tmp;
    }

    @Override
    public IR visitLOGIC_ARI(MxParser.LOGIC_ARIContext ctx) {
        Operand rhs = (Operand) visit(ctx.expression(1));
        Operand lhs = (Operand) visit(ctx.expression(0));
        return visitBinary(lhs, rhs, ctx.op.getText());
    }

    @Override
    public IR visitASSIGN(MxParser.ASSIGNContext ctx) {
        Operand rhs = (Operand) visit(ctx.expression(1));
        Operand lhs = (Operand) visit(ctx.expression(0));
        Move stmt = new Move(lhs, rhs);
        statements.add(stmt);
        if (rhs.getIsString())
            lhs.setIsString(true);
        return stmt.getLhs();
    }

    @Override
    public IR visitBRACKET(MxParser.BRACKETContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public IR visitNEW_CREATOR(MxParser.NEW_CREATORContext ctx) {
        array_creator.clear();
        return visit(ctx.creator());
    }

    @Override
    public IR visitCreator(MxParser.CreatorContext ctx) {
        if (ctx.subCreator() != null) {
            return visit(ctx.subCreator());
        } else {
            return visit(ctx.creator());
        }
    }

    private Stack<Operand> array_creator = new Stack<>();

    @Override
    public IR visitSUB_CREATOR(MxParser.SUB_CREATORContext ctx) {
        Operand expr = (Operand) visit(ctx.expression());
        array_creator.push(expr);
        return visit(ctx.subCreator());
    }

    private void create(Operand base, BaseType class_type, Boolean isFunctionNew, Vector<Operand> args) {
        Operand dim = array_creator.pop();
        Vector<Operand> parameters = new Vector<>();
        Variable var_cnt = getNewVar("cnt", class_list.getClassType("int"));

        Label begin_label = new Label("loop_begin");
        Label condition_label = new Label("loop_condition");
        Label end_label = new Label("loop_end");

        Variable var_tmp = getNewVar("tmp", class_list.getClassType("int"));
        if (array_creator.empty()) {
            if (!isFunctionNew) {
                statements.add(new Move(var_tmp, dim));
                Mul mul = new Mul(var_tmp, new Immediate(class_type.getSize()));
                statements.add(mul);
                Add add = new Add(mul.getDest(), new Immediate(8));
                statements.add(add);
                parameters.add(add.getDest());
                Call stmt = new Call(inFunctions.get("malloc"), new IRParameter(parameters));
                statements.add(stmt);
                statements.add(new Move(base, stmt.getTmp_return()));
                statements.add(new Move(new Memory(base, null, 1,0, null), dim));
                add = new Add(base, new Immediate(8));
                statements.add(add);
                statements.add(new Move(base, add.getDest()));
            } else {
                IRFunction function = functions.get(class_type.getClassName() + "_func__");
                statements.add(new Move(var_tmp, dim));
                statements.add(new Sal(var_tmp, new Immediate(3)));
                Add add = new Add(var_tmp, new Immediate(8));
                statements.add(add);
                parameters.add(add.getDest());
                Call stmt = new Call(inFunctions.get("malloc"), new IRParameter(parameters));
                statements.add(stmt);
                statements.add(new Move(base, stmt.getTmp_return()));
                statements.add(new Move(new Memory(base, null, 8, 0, null), dim));
                add = new Add(base, new Immediate(8));
                statements.add(add);
                statements.add(new Move(base, add.getDest()));
                parameters.clear();
                parameters.add(new Immediate(class_type.getSize()));
                addLabel(begin_label);
                stmt = new Call(inFunctions.get("malloc"), new IRParameter(parameters));
                statements.add(stmt);
                statements.add(new Move(new Memory(base, var_cnt, 8, 0, null), stmt.getTmp_return()));
                statements.add(new Add(new Memory(base, var_cnt, 8, 0, null), new Immediate(8)));
                args.setElementAt(new Memory(base, var_cnt, 8, 0, null), 0);
                statements.add(new Call(function, new IRParameter(args)));
                statements.add(new Add(var_cnt, const_one));
                addLabel(condition_label);
                statements.add(new CJump(new Cmp(var_cnt, dim, "<"), begin_label, end_label));
                addLabel(end_label);
            }
        } else {
            statements.add(new Move(var_tmp, dim));
            statements.add(new Sal(var_tmp, new Immediate(3)));
            statements.add(new Add(var_tmp, new Immediate(8)));
            parameters.add(var_tmp);
            Call stmt = new Call(inFunctions.get("malloc"), new IRParameter(parameters));
            statements.add(stmt);
            statements.add(new Move(base, stmt.getTmp_return()));
            statements.add(new Move(new Memory(base, null, 8, 0, null), dim));
            statements.add(new Add(base, new Immediate(8)));
            statements.add(new Move(var_cnt, const_zero));
            addLabel(begin_label);
            create(new Memory(base, var_cnt, 8, 0, null), class_type, isFunctionNew, args);
            statements.add(new Add(var_cnt, const_one));
            addLabel(condition_label);
            statements.add(new CJump(new Cmp(var_cnt, dim, "<"), begin_label, end_label));
            addLabel(end_label);
        }
    }

    @Override
    public IR visitTYPE_NEW(MxParser.TYPE_NEWContext ctx) {
        String class_name = ctx.class_name().getText();
        BaseType class_type = class_list.getClassType(class_name);
        if (array_creator.empty()) {
            Variable var_tmp = getNewVar("tmp", class_list.getClassType("int"));
            Vector<Operand> parameters = new Vector<>();
            parameters.add(new Immediate(class_type.getSize()));
            Call stmt = new Call(inFunctions.get("malloc"), new IRParameter(parameters));
            statements.add(stmt);
            statements.add(new Move(var_tmp, stmt.getTmp_return()));
            return var_tmp;
        } else {
            Variable var_tmp = getNewVar("tmp", class_list.getClassType("int"));
            create(var_tmp, class_type, false, null);
            return var_tmp;
        }
    }

    @Override
    public IR visitFUNCTION_NEW(MxParser.FUNCTION_NEWContext ctx) {
        String class_name = ctx.class_name().getText();
        BaseType class_node = class_list.getClassType(class_name);
        Vector<Operand> parameters = new Vector<>();
        if (array_creator.empty()) {
            Variable var_tmp = getNewVar("tmp", class_list.getClassType("int"));
            parameters.add(new Immediate(class_node.getSize()));
            Call stmt = new Call(inFunctions.get("malloc"), new IRParameter(parameters));
            statements.add(stmt);
            statements.add(new Move(var_tmp, stmt.getTmp_return()));
            IRFunction function = functions.get(class_name);
            parameters = new Vector<>();
            parameters.add(var_tmp);
            if (ctx.expressionList() != null)
                parameters.addAll(((IRParameter) visit(ctx.expressionList())).getParameters());
            statements.add(new Call(function, new IRParameter(parameters)));
            return var_tmp;
        } else {
            Variable var_tmp = getNewVar("tmp", class_list.getClassType("int"));
            parameters.add(null);
            if (ctx.expressionList() != null)
                parameters.addAll(((IRParameter) visit(ctx.expressionList())).getParameters());
            create(var_tmp, class_node, true, parameters);
            return var_tmp;
        }
    }

    public Map<String, IRFunction> getFunctions() {
        return functions;
    }

    public Map<String, IRFunction> getInFunctions() {
        return inFunctions;
    }

    public List<IRInstruction> getGlobalStatements() {
        return global_statements;
    }

    public IRScope getGlobalScope() {
        return variable_scope.peek();
    }

    public Map<Variable, String> getConst_string() {
        return const_string;
    }

    static public StackAlloc getCurrent_stackAlloc() {
        return current_stackAlloc;
    }

    @Override
    public IR visitINS_STATE(MxParser.INS_STATEContext ctx) {
        statements.add(new Label(";" + ctx.getText()));
        visitChildren(ctx);
        return null;
    }

    @Override
    public IR visitEXPR_STATE(MxParser.EXPR_STATEContext ctx) {
        statements.add(new Label(";" + ctx.getText()));
        visitChildren(ctx);
        return null;
    }
}
