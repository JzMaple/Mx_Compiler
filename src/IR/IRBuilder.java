package IR;

import IR.IRInstruction.*;
import IR.IRInstruction.Operand.Immediate;
import IR.IRInstruction.Operand.Memory;
import IR.IRInstruction.Operand.Operand;
import IR.IRInstruction.Operand.Variable;
import IR.IRNode.IRFunction;
import IR.IRNode.IRParameter;
import IR.IRNode.IRScope;
import Nasm.StackAllocator;
import Parser.MxBaseVisitor;
import Parser.MxParser;
import Type.*;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.*;

public class IRBuilder extends MxBaseVisitor<IR> {
    private List<IRInstruction> statements = new ArrayList<>();
    private List<IRInstruction> global_statements = new ArrayList<>();
    private Map<String, IRFunction> functions = new HashMap<>();
    private Map<String, IRFunction> inFunctions = new HashMap<>();
    private Map<Variable, String> const_string = new HashMap<>();
    private ClassList class_list;
    private Set<String> variable_rename_set = new HashSet<>();
    private Stack<IRScope> variable_scope = new Stack<>();
    private StackAllocator global_stackAlloc;
    static private StackAllocator current_stackAlloc;
    private BaseType current_class;
    private IRFunction current_function;
    private Label current_loop_condition = null;
    private Label current_loop_end = null;
    private Immediate const_one = new Immediate(1);
    private Immediate const_zero = new Immediate(0);
    private Boolean dangerous = false;

    private Variable getNewVar(String name, BaseType class_type) {
        String tmp_rename = variableRename(name);
        return new Variable(tmp_rename, class_type, false, current_stackAlloc);
    }

    private void setMalloc() {
        String malloc_name = "malloc";
        FunctionType functionType = new FunctionType(class_list.getClassType("int"), null, null);
        IRFunction malloc_func = new IRFunction(malloc_name, functionType);
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
        FunctionType functionType = new FunctionType(class_list.getClassType("int"), null, null);
        IRFunction length_func = new IRFunction(length_name, functionType);
        inFunctions.put(length_name, length_func);
    }

    private void setParseInt() {
        String parseInt_name = "parseInt";
        FunctionType functionType = new FunctionType(class_list.getClassType("int"), null, null);
        IRFunction parseInt_func = new IRFunction(parseInt_name, functionType);
        inFunctions.put(parseInt_name, parseInt_func);
    }

    private void setOrd() {
        String ord_name = "ord";
        FunctionType functionType = new FunctionType(class_list.getClassType("int"), null, null);
        IRFunction ord_func = new IRFunction(ord_name, functionType);
        inFunctions.put(ord_name, ord_func);
    }

    private void setSubString() {
        String subString_name = "substring";
        FunctionType functionType = new FunctionType(class_list.getClassType("string"), null, null);
        IRFunction subString_func = new IRFunction(subString_name, functionType);
        inFunctions.put(subString_name, subString_func);
    }

    private void getStrCombine() {
        String strCombine_name = "strCombine";
        FunctionType functionType = new FunctionType(class_list.getClassType("string"), null, null);
        IRFunction strCombine_func = new IRFunction(strCombine_name, functionType);
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
            if (_class_list.getClassType(function_name) != null) {
                current_stackAlloc = function_node.getStackAlloc();
                Variable tmp = getNewVar("self", _class_list.getClassType(function_name));
                List<Variable> parameters = new ArrayList<>();
                parameters.add(tmp);
                function_node.setParameters(parameters);
                current_stackAlloc = null;
            }
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
        IRInstruction ins = statements.get(statements.size() - 1);
        if (ins instanceof Cmp) {
            statements.remove(ins);
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
        Boolean flag = false;
        if (dangerous) flag = true;
        dangerous = true;

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
        dangerous = flag;
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
            String class_name = current_class.getClassName();
            if (function_name.equals(class_name))
                function_rename = function_name;
            else
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
        }
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
        if (current_class != null && current_function == null) return null;
        String variable_name = ctx.Identifier().getText();
        BaseType class_type = class_list.getClassType(ctx.class_statement().getText());
        Boolean isGlobal = current_function == null && current_class == null;
        Variable var = new Variable(variableRename(variable_name), class_type, isGlobal, current_stackAlloc);
        IRScope scope = variable_scope.peek();
        scope.insert(variable_name, var);
        return null;
    }

    @Override
    public IR visitASSIGN_INS(MxParser.ASSIGN_INSContext ctx) {
        if (current_class != null && current_function == null) return null;
        String variable_name = ctx.Identifier().getText();
        BaseType class_type = class_list.getClassType(ctx.class_statement().getText());
        Boolean isGlobal = current_function == null && current_class == null;
        Variable var = new Variable(variableRename(variable_name), class_type, isGlobal, current_stackAlloc);
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
        Boolean flag = false;
        if (dangerous) flag = true;
        addLabel(then_label);
        variable_scope.push(new IRScope(variable_scope.peek()));
        visit(ctx.noScope_block());
        variable_scope.pop();
        addLabel(end_label);
        dangerous = flag;
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
        Boolean flag = false;
        if (dangerous) flag = true;
        dangerous = true;
        variable_scope.push(new IRScope(variable_scope.peek()));
        visit(ctx.noScope_block(0));
        statements.add(new Jump(end_label));
        variable_scope.pop();
        addLabel(else_label);
        variable_scope.push(new IRScope(variable_scope.peek()));
        visit(ctx.noScope_block(1));
        variable_scope.pop();
        addLabel(end_label);
        dangerous = flag;
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
//        statements.add(new Label(";"+ctx.getText()));
        if (ctx.expression() != null) {
            Operand ret = (Operand) visit(ctx.expression());
            statements.add(new Return(ret));
        }
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
//        System.out.println(ctx.getText());
        String function_name = ctx.Identifier().getText();
        Vector<Operand> parameters = new Vector<>();
        IRFunction function_node = functions.get(function_name);
        if (function_node == null) function_node = inFunctions.get(function_name);
        if (function_node == null && current_class != null) {
            function_name = current_class.getClassName() + "_" + function_name;
            function_node = functions.get(function_name);
            parameters.add(current_function.getParameter(0));
        }
        if (ctx.expressionList() != null)
            parameters.addAll(((IRParameter) visit(ctx.expressionList())).getParameters());
        Variable return_tmp = getNewVar("return", function_node.getFunctionType().getReturnType());
        Call stmt = new Call(function_node, new IRParameter(parameters), return_tmp);
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
        Variable var_pointer = null;
        Variable var_offset = null;
        if (pointer instanceof Memory) {
            var_pointer = getNewVar("base", null);
            statements.add(new Move(var_pointer, pointer));
        }
        if (offset instanceof Memory) {
            var_offset = getNewVar("base", null);
            statements.add(new Move(var_offset, offset));
        }
        Memory mem;
        if (pointer instanceof Memory && offset instanceof Memory)
            mem = new Memory(var_pointer, var_offset, 8, 0, type);
        else if (pointer instanceof Memory)
            mem = new Memory(var_pointer, offset, 8, 0, type);
        else if (offset instanceof Memory)
            mem = new Memory(pointer, var_offset, 8, 0, type);
        else
            mem = new Memory(pointer, offset, 8, 0, type);
        if (type instanceof StringType) mem.setIsString(true);
        return mem;
    }

    @Override
    public IR visitMEMBER_VARIABLE(MxParser.MEMBER_VARIABLEContext ctx) {
        Operand pointer = (Operand) visit(ctx.expression());

        BaseType class_type;
        if (pointer instanceof Variable)
            class_type = ((Variable) pointer).getType();
        else if (pointer instanceof Memory)
            class_type = ((Memory) pointer).getType();
        else return null;
        String member_variable_name = ctx.Identifier().getText();
        int offset = class_type.getOffset(member_variable_name);
        BaseType type = class_type.offsetToType(class_type.getOffset(member_variable_name));

        Variable var;
        if (pointer instanceof Memory) {
            var = getNewVar("base", class_type);
            Move move = new Move(var, pointer);
            statements.add(move);
            Memory mem = new Memory(var, null, 8, offset, type);
            if (type instanceof StringType) mem.setIsString(true);
            return mem;
        } else {
            Memory mem = new Memory(pointer, null, 8, offset, type);
            if (type instanceof StringType) mem.setIsString(true);
            return mem;
        }
    }

    @Override
    public IR visitMEMBER_FUNCTION(MxParser.MEMBER_FUNCTIONContext ctx) {
//        System.out.println(ctx.getText());
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
                if (pointer instanceof Memory) {
                    Variable var = getNewVar("base", null);
                    statements.add(new Move(var, pointer));
                    return new Memory(var, null, 1, -8, class_list.getClassType("int"));
                } else
                    return new Memory(pointer, null, 1, -8, class_list.getClassType("int"));
            }
        }
        if (class_type instanceof StringType) {
            if (function_name.equals("length")) {
                Variable return_tmp = getNewVar("return", class_list.getClassType("int"));
                Call call = new Call(inFunctions.get("strlen"), new IRParameter(parameters), return_tmp);
                statements.add(call);
                return call.getTmp_return();
            } else {
                IRFunction function = inFunctions.get(function_name);
                Variable return_tmp = getNewVar("return", function.getFunctionType().getReturnType());
                Call call = new Call(function, new IRParameter(parameters), return_tmp);
                statements.add(call);
                if (function_name.equals("substring"))
                    call.getTmp_return().setIsString(true);
                return call.getTmp_return();
            }
        }
        String function_rename = class_name + "_" + function_name;
        IRFunction function_node = functions.get(function_rename);
        Variable return_tmp = getNewVar("return", function_node.getFunctionType().getReturnType());
        Call call = new Call(function_node, new IRParameter(parameters), return_tmp);
        statements.add(call);
        if (function_node.getFunctionType().getReturnType() instanceof StringType)
            call.getTmp_return().setIsString(true);
        return call.getTmp_return();
    }

    @Override
    public IR visitIDENTIFIER(MxParser.IDENTIFIERContext ctx) {
        Variable id = variable_scope.peek().get(ctx.getText());
        if (id != null)
            return id;
        else if (current_class != null) {
            String var_name = ctx.getText();
            Operand pointer = current_function.getParameter(0);
            Immediate offset = new Immediate(current_class.getOffset(var_name));
            BaseType type = current_class.offsetToType(current_class.getOffset(var_name));
            Memory mem = new Memory(pointer, offset, 1, 0, type);
            if (type instanceof StringType) mem.setIsString(true);
            return mem;
        } else
            return null;
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
    public IR visitNull(MxParser.NullContext ctx) {
        return new Immediate(0);
    }

    @Override
    public IR visitCONST_STRING(MxParser.CONST_STRINGContext ctx) {
        Variable tmp = new Variable(variableRename("str"), class_list.getClassType("string"), true, null);
        tmp.setIsString(true);
        tmp.setIsConstant(true);
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
        if (ctx.op.getText().equals("++")) {
            statements.add(new Inc(expr));
        }
        else if (ctx.op.getText().equals("--")) {
            statements.add(new Dec(expr));
        }
        return var;
    }

    @Override
    public IR visitPREFIX(MxParser.PREFIXContext ctx) {
        Operand expr = (Operand) visit(ctx.expression());
        if (ctx.op.getText().equals("++")) {
            statements.add(new Inc(expr));
        }
        else if (ctx.op.getText().equals("--")) {
            statements.add(new Dec(expr));
        }
        Variable var = getNewVar("tmp", class_list.getClassType("int"));
        statements.add(new Move(var, expr));
        return var;
    }

    @Override
    public IR visitUNARY(MxParser.UNARYContext ctx) {
        Operand expr = (Operand) visit(ctx.expression());
        if (ctx.op.getText().equals("-")) {
            if (!(expr instanceof Immediate)) {
                Variable dest = getNewVar("dest", class_list.getClassType("int"));
                Neg neg = new Neg(expr, dest);
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
                Variable dest = getNewVar("dest", class_list.getClassType("int"));
                Not not = new Not(expr, dest);
                statements.add(not);
                return not.getDest();
            }
        } else {
            if (expr instanceof Immediate) {
                int value = ((Immediate) expr).getValue();
                return new Immediate(value == 0 ? 1 : 0);
            } else {
                Variable dest = getNewVar("dest", class_list.getClassType("int"));
                Xor xor = new Xor(expr, new Immediate(1), dest);
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
            Binary stmt;
            Vector<Operand> parameters = new Vector<>();
            Call call;
            if (lhs.getIsString() && rhs.getIsString() && op.equals("+")) {
                parameters.add(lhs);
                parameters.add(rhs);
                IRFunction function = inFunctions.get("strCombine");
                Variable return_tmp = getNewVar("return", function.getFunctionType().getReturnType());
                call = new Call(function, new IRParameter(parameters), return_tmp);
                statements.add(call);
                call.getTmp_return().setIsString(true);
                return call.getTmp_return();
            }
            Variable dest = getNewVar("dest", class_list.getClassType("int"));
            switch (op) {
                case "+":
                    statements.add(stmt = new Add(lhs, rhs, dest));
                    break;
                case "-":
                    statements.add(stmt = new Sub(lhs, rhs, dest));
                    break;
                case "*":
                    statements.add(stmt = new Mul(lhs, rhs, dest));
                    break;
                case "/":
                    statements.add(stmt = new Div(lhs, rhs, dest));
                    break;
                case "%":
                    statements.add(stmt = new Mod(lhs, rhs, dest));
                    break;
                case "<<":
                    statements.add(stmt = new Sal(lhs, rhs, dest));
                    break;
                case ">>":
                    statements.add(stmt = new Sar(lhs, rhs, dest));
                    break;
                case "&":
                    statements.add(stmt = new And(lhs, rhs, dest));
                    break;
                case "|":
                    statements.add(stmt = new Or(lhs, rhs, dest));
                    break;
                case "^":
                    statements.add(stmt = new Xor(lhs, rhs, dest));
                    break;
                case "<":
                    statements.add(stmt = new Cmp(lhs, rhs, op, dest));
                    break;
                case ">":
                    statements.add(stmt = new Cmp(lhs, rhs, op, dest));
                    break;
                case "<=":
                    statements.add(stmt = new Cmp(lhs, rhs, op, dest));
                    break;
                case ">=":
                    statements.add(stmt = new Cmp(lhs, rhs, op, dest));
                    break;
                case "==":
                    statements.add(stmt = new Cmp(lhs, rhs, op, dest));
                    break;
                case "!=":
                    statements.add(stmt = new Cmp(lhs, rhs, op, dest));
                    break;
                default:
                    stmt = new Add(null, null, null);
            }
            return stmt.getDest();
        }
    }

    @Override
    public IR visitARITHMETIC(MxParser.ARITHMETICContext ctx) {
//        System.out.println(ctx.getText());
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
        Boolean flag = false;
        if (dangerous) flag = true;
        dangerous = true;
        if (ctx.op.getText().equals("&&")) {
            Variable dest = getNewVar("dest", class_list.getClassType("int"));
            statements.add(new CJump(new Cmp(tmp, const_one, "==", dest), goon_label, end_label));
            addLabel(goon_label);
            Operand rhs = (Operand) visit(ctx.expression(1));
            statements.add(new Move(tmp, rhs));
            addLabel(end_label);
        } else {
            Variable dest = getNewVar("dest", class_list.getClassType("int"));
            statements.add(new CJump(new Cmp(tmp, const_one, "==",dest), end_label, goon_label));
            addLabel(goon_label);
            Operand rhs = (Operand) visit(ctx.expression(1));
            statements.add(new Move(tmp, rhs));
            addLabel(end_label);
        }
        dangerous = flag;
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
        Move move = new Move(lhs, rhs);
        statements.add(move);
        if (rhs.getIsString()) lhs.setIsString(true);
        return move.getLhs();
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

    private void create(Variable base, BaseType class_type, Boolean isFunctionNew, Vector<Operand> args) {
        Operand dim = array_creator.pop();
        List<Operand> parameters = new ArrayList<>();
        Variable var_cnt = getNewVar("cnt", class_list.getClassType("int"));

        Label begin_label = new Label("loop_begin");
        Label condition_label = new Label("loop_condition");
        Label end_label = new Label("loop_end");

        Variable var_tmp = getNewVar("tmp", class_list.getClassType("int"));
        if (array_creator.empty()) {
            if (!isFunctionNew) {
                if (class_type instanceof UserType) {
                    statements.add(new Move(var_tmp, dim));
                    Variable dest = getNewVar("dest", class_list.getClassType("int"));
                    Mul mul = new Mul(var_tmp, new Immediate(8), dest);
                    statements.add(mul);
                    dest = getNewVar("dest", class_list.getClassType("int"));
                    Add add = new Add(mul.getDest(), new Immediate(8), dest);
                    statements.add(add);
                    parameters.add(add.getDest());
                    Variable return_tmp = getNewVar("return", null);
                    Call stmt = new Call(inFunctions.get("malloc"), new IRParameter(parameters), return_tmp);
                    statements.add(stmt);
                    statements.add(new Move(base, stmt.getTmp_return()));
                    statements.add(new Move(new Memory(base, null, 8, 0, null), dim));
                    dest = getNewVar("dest", class_list.getClassType("int"));
                    add = new Add(base, new Immediate(8), dest);
                    statements.add(add);
                    statements.add(new Move(base, add.getDest()));
                    parameters = new Vector<>();
                    parameters.add(new Immediate(class_type.getSize()));
                    statements.add(new Move(var_cnt, const_zero));
                    Boolean flag = false;
                    if (dangerous) flag = true;
                    dangerous = true;
                    addLabel(begin_label);
                    return_tmp = getNewVar("return", null);
                    stmt = new Call(inFunctions.get("malloc"), new IRParameter(parameters), return_tmp);
                    statements.add(stmt);
                    statements.add(new Move(new Memory(base, var_cnt, 8, 0, null), stmt.getTmp_return()));
                    dest = getNewVar("dest", class_list.getClassType("int"));
                    statements.add(new Add(new Memory(base, var_cnt, 8, 0, null), new Immediate(8), dest));
                    dest = getNewVar("dest", class_list.getClassType("int"));
                    add = new Add(var_cnt, const_one, dest);
                    statements.add(add);
                    statements.add(new Move(var_cnt, add.getDest()));
                    addLabel(condition_label);
                    dest = getNewVar("dest", class_list.getClassType("int"));
                    statements.add(new CJump(new Cmp(var_cnt, dim, "<", dest), begin_label, end_label));
                    addLabel(end_label);
                    dangerous = flag;
                } else {
//                    ArrayList<Operand> param = new ArrayList<>();
//                    param.add(new Immediate(1));
//                    Call call;
//                    statements.add(call = new Call(inFunctions.get("toString"), new IRParameter(param)));
//                    ArrayList<Operand> pa = new ArrayList<>();
//                    pa.add(call.getTmp_return());
//                    statements.add(new Call(inFunctions.get("println"), new IRParameter(pa)))
                    statements.add(new Move(var_tmp, dim));
                    Variable dest = getNewVar("dest", class_list.getClassType("int"));
                    Mul mul = new Mul(var_tmp, new Immediate(class_type.getSize()), dest);
                    statements.add(mul);
                    dest = getNewVar("dest", class_list.getClassType("int"));
                    Add add = new Add(mul.getDest(), new Immediate(8), dest);
                    statements.add(add);
                    parameters.add(add.getDest());
                    Variable return_tmp = getNewVar("return", null);
                    Call stmt = new Call(inFunctions.get("malloc"), new IRParameter(parameters), return_tmp);
                    statements.add(stmt);
                    statements.add(new Move(base, stmt.getTmp_return()));
                    statements.add(new Move(new Memory(base, null, 1, 0, null), dim));
                    dest = getNewVar("dest", class_list.getClassType("int"));
                    add = new Add(base, new Immediate(8), dest);
                    statements.add(add);
                    statements.add(new Move(base, add.getDest()));
                }
            }
        } else {
            statements.add(new Move(var_tmp, dim));
            Variable dest = getNewVar("dest", class_list.getClassType("int"));
            Mul mul = new Mul(var_tmp, new Immediate(8), dest);
            statements.add(mul);
            dest = getNewVar("dest", class_list.getClassType("int"));
            Add add = new Add(mul.getDest(), new Immediate(8), dest);
            statements.add(add);
            parameters.add(add.getDest());
            Variable return_tmp = getNewVar("return", null);
            Call stmt = new Call(inFunctions.get("malloc"), new IRParameter(parameters), return_tmp);
            statements.add(stmt);
            statements.add(new Move(base, stmt.getTmp_return()));
            statements.add(new Move(new Memory(base, null, 8, 0, null), dim));
            dest = getNewVar("dest", class_list.getClassType("int"));
            add = new Add(base, new Immediate(8), dest);
            statements.add(add);
            statements.add(new Move(base, add.getDest()));
            statements.add(new Move(var_cnt, const_zero));
            Boolean flag = false;
            if (dangerous) flag = true;
            dangerous = true;
            addLabel(begin_label);
            Variable base_tmp = getNewVar("base", null);
            create(base_tmp, class_type, isFunctionNew, args);
            statements.add(new Move(new Memory(base, var_cnt, 8, 0, null), base_tmp));
            dest = getNewVar("dest", class_list.getClassType("int"));
            add = new Add(var_cnt, const_one, dest);
            statements.add(add);
            statements.add(new Move(var_cnt, add.getDest()));
            addLabel(condition_label);
            dest = getNewVar("dest", class_list.getClassType("int"));
            statements.add(new CJump(new Cmp(var_cnt, dim, "<", dest), begin_label, end_label));
            addLabel(end_label);
            dangerous = flag;
        }
    }

    @Override
    public IR visitTYPE_NEW(MxParser.TYPE_NEWContext ctx) {
        String class_name = ctx.class_name().getText();
        BaseType class_type = class_list.getClassType(class_name);
        if (array_creator.empty()) {
            if (!(class_type instanceof UserType)) {
                Variable var_tmp = getNewVar("base", class_list.getClassType("int"));
                Vector<Operand> parameters = new Vector<>();
                parameters.add(new Immediate(class_type.getSize()));
                Variable return_tmp = getNewVar("return", null);
                Call stmt = new Call(inFunctions.get("malloc"), new IRParameter(parameters), return_tmp);
                statements.add(stmt);
                statements.add(new Move(var_tmp, stmt.getTmp_return()));
                return var_tmp;
            } else {
                Variable var_tmp = getNewVar("base", class_list.getClassType("int"));
                Vector<Operand> parameters = new Vector<>();
                parameters.add(new Immediate(class_type.getSize()));
                Variable return_tmp = getNewVar("return", null);
                Call stmt = new Call(inFunctions.get("malloc"), new IRParameter(parameters), return_tmp);
                statements.add(stmt);
                statements.add(new Move(var_tmp, stmt.getTmp_return()));
                IRFunction function = functions.get(class_name);
                parameters = new Vector<>();
                parameters.add(var_tmp);
                return_tmp = getNewVar("return", null);
                statements.add(new Call(function, new IRParameter(parameters), return_tmp));
                return var_tmp;
            }
        } else {
            Variable var_tmp = getNewVar("base", class_list.getClassType("int"));
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
            Variable var_tmp = getNewVar("base", class_list.getClassType("int"));
            parameters.add(new Immediate(class_node.getSize()));
            Variable return_tmp = getNewVar("return", null);
            Call stmt = new Call(inFunctions.get("malloc"), new IRParameter(parameters), return_tmp);
            statements.add(stmt);
            statements.add(new Move(var_tmp, stmt.getTmp_return()));
            IRFunction function = functions.get(class_name);
            parameters = new Vector<>();
            parameters.add(var_tmp);
            if (ctx.expressionList() != null)
                parameters.addAll(((IRParameter) visit(ctx.expressionList())).getParameters());
            return_tmp = getNewVar("return", null);
            statements.add(new Call(function, new IRParameter(parameters), return_tmp));
            return var_tmp;
        } else {
            Variable var_tmp = getNewVar("base", class_list.getClassType("int"));
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

//    @Override
//    public IR visitEXPR_STATE(MxParser.EXPR_STATEContext ctx) {
//        statements.add(new Label(";" + ctx.getText()));
//        visitChildren(ctx);
//        return null;
//    }
//
//    @Override
//    public IR visitINS_STATE(MxParser.INS_STATEContext ctx) {
//        statements.add(new Label(";" + ctx.getText()));
//        visitChildren(ctx);
//        return null;
//    }
}
