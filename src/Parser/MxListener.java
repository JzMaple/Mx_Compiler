// Generated from C:/Users/JZT/Desktop/Mx_Compiler/src/Parser\Mx.g4 by ANTLR 4.7
package Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#all}.
	 * @param ctx the parse tree
	 */
	void enterAll(MxParser.AllContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#all}.
	 * @param ctx the parse tree
	 */
	void exitAll(MxParser.AllContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MxParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MxParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#typeDefine}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefine(MxParser.TypeDefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#typeDefine}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefine(MxParser.TypeDefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(MxParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(MxParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(MxParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(MxParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IF_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIF_STATE(MxParser.IF_STATEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IF_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIF_STATE(MxParser.IF_STATEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IFELSE_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIFELSE_STATE(MxParser.IFELSE_STATEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IFELSE_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIFELSE_STATE(MxParser.IFELSE_STATEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FOR_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterFOR_STATE(MxParser.FOR_STATEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FOR_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitFOR_STATE(MxParser.FOR_STATEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WHILE_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWHILE_STATE(MxParser.WHILE_STATEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WHILE_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWHILE_STATE(MxParser.WHILE_STATEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RETURN_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterRETURN_STATE(MxParser.RETURN_STATEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RETURN_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitRETURN_STATE(MxParser.RETURN_STATEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BREAK_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBREAK_STATE(MxParser.BREAK_STATEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BREAK_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBREAK_STATE(MxParser.BREAK_STATEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CONTINUE_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCONTINUE_STATE(MxParser.CONTINUE_STATEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CONTINUE_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCONTINUE_STATE(MxParser.CONTINUE_STATEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EXPR_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEXPR_STATE(MxParser.EXPR_STATEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EXPR_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEXPR_STATE(MxParser.EXPR_STATEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code INS_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterINS_STATE(MxParser.INS_STATEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code INS_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitINS_STATE(MxParser.INS_STATEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BLOCK_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBLOCK_STATE(MxParser.BLOCK_STATEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BLOCK_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBLOCK_STATE(MxParser.BLOCK_STATEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LOGIC_ARI}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLOGIC_ARI(MxParser.LOGIC_ARIContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LOGIC_ARI}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLOGIC_ARI(MxParser.LOGIC_ARIContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Null}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNull(MxParser.NullContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Null}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNull(MxParser.NullContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ARRAY}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterARRAY(MxParser.ARRAYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ARRAY}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitARRAY(MxParser.ARRAYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RELATION}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRELATION(MxParser.RELATIONContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RELATION}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRELATION(MxParser.RELATIONContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ARITHMETIC}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterARITHMETIC(MxParser.ARITHMETICContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ARITHMETIC}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitARITHMETIC(MxParser.ARITHMETICContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LOGIC_RELATION}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLOGIC_RELATION(MxParser.LOGIC_RELATIONContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LOGIC_RELATION}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLOGIC_RELATION(MxParser.LOGIC_RELATIONContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CONST_INT}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCONST_INT(MxParser.CONST_INTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CONST_INT}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCONST_INT(MxParser.CONST_INTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ASSIGN}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterASSIGN(MxParser.ASSIGNContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ASSIGN}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitASSIGN(MxParser.ASSIGNContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CONST_BOOL}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCONST_BOOL(MxParser.CONST_BOOLContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CONST_BOOL}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCONST_BOOL(MxParser.CONST_BOOLContext ctx);
	/**
	 * Enter a parse tree produced by the {@code POSTFIX}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPOSTFIX(MxParser.POSTFIXContext ctx);
	/**
	 * Exit a parse tree produced by the {@code POSTFIX}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPOSTFIX(MxParser.POSTFIXContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CONST_STRING}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCONST_STRING(MxParser.CONST_STRINGContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CONST_STRING}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCONST_STRING(MxParser.CONST_STRINGContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SELF_POINTER}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSELF_POINTER(MxParser.SELF_POINTERContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SELF_POINTER}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSELF_POINTER(MxParser.SELF_POINTERContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NOT}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNOT(MxParser.NOTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NOT}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNOT(MxParser.NOTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DYNAMIC_INS}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDYNAMIC_INS(MxParser.DYNAMIC_INSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DYNAMIC_INS}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDYNAMIC_INS(MxParser.DYNAMIC_INSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BRACKET}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBRACKET(MxParser.BRACKETContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BRACKET}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBRACKET(MxParser.BRACKETContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PREFIX}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPREFIX(MxParser.PREFIXContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PREFIX}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPREFIX(MxParser.PREFIXContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MEMBER_VARIABLE}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMEMBER_VARIABLE(MxParser.MEMBER_VARIABLEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MEMBER_VARIABLE}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMEMBER_VARIABLE(MxParser.MEMBER_VARIABLEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IDENTIFIER}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIDENTIFIER(MxParser.IDENTIFIERContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IDENTIFIER}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIDENTIFIER(MxParser.IDENTIFIERContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FUNCTION_USE}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFUNCTION_USE(MxParser.FUNCTION_USEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FUNCTION_USE}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFUNCTION_USE(MxParser.FUNCTION_USEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UNARY}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUNARY(MxParser.UNARYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UNARY}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUNARY(MxParser.UNARYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MEMBER_FUNCTION}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMEMBER_FUNCTION(MxParser.MEMBER_FUNCTIONContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MEMBER_FUNCTION}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMEMBER_FUNCTION(MxParser.MEMBER_FUNCTIONContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(MxParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(MxParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NORMAL_INS}
	 * labeled alternative in {@link MxParser#instantiation}.
	 * @param ctx the parse tree
	 */
	void enterNORMAL_INS(MxParser.NORMAL_INSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NORMAL_INS}
	 * labeled alternative in {@link MxParser#instantiation}.
	 * @param ctx the parse tree
	 */
	void exitNORMAL_INS(MxParser.NORMAL_INSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ASSIGN_INS}
	 * labeled alternative in {@link MxParser#instantiation}.
	 * @param ctx the parse tree
	 */
	void enterASSIGN_INS(MxParser.ASSIGN_INSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ASSIGN_INS}
	 * labeled alternative in {@link MxParser#instantiation}.
	 * @param ctx the parse tree
	 */
	void exitASSIGN_INS(MxParser.ASSIGN_INSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SINGLE_VAR}
	 * labeled alternative in {@link MxParser#class_statement}.
	 * @param ctx the parse tree
	 */
	void enterSINGLE_VAR(MxParser.SINGLE_VARContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SINGLE_VAR}
	 * labeled alternative in {@link MxParser#class_statement}.
	 * @param ctx the parse tree
	 */
	void exitSINGLE_VAR(MxParser.SINGLE_VARContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ARRAY_VAR}
	 * labeled alternative in {@link MxParser#class_statement}.
	 * @param ctx the parse tree
	 */
	void enterARRAY_VAR(MxParser.ARRAY_VARContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ARRAY_VAR}
	 * labeled alternative in {@link MxParser#class_statement}.
	 * @param ctx the parse tree
	 */
	void exitARRAY_VAR(MxParser.ARRAY_VARContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#class_name}.
	 * @param ctx the parse tree
	 */
	void enterClass_name(MxParser.Class_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#class_name}.
	 * @param ctx the parse tree
	 */
	void exitClass_name(MxParser.Class_nameContext ctx);
}