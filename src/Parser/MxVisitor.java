// Generated from C:/Users/JZT/Desktop/Mx_Compiler/src/Parser\Mx.g4 by ANTLR 4.7
package Parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxParser#all}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAll(MxParser.AllContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MxParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#typeDefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefine(MxParser.TypeDefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(MxParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(MxParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IF_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIF_STATE(MxParser.IF_STATEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IFELSE_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIFELSE_STATE(MxParser.IFELSE_STATEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FOR_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFOR_STATE(MxParser.FOR_STATEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WHILE_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWHILE_STATE(MxParser.WHILE_STATEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RETURN_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRETURN_STATE(MxParser.RETURN_STATEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BREAK_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBREAK_STATE(MxParser.BREAK_STATEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CONTINUE_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCONTINUE_STATE(MxParser.CONTINUE_STATEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EXPR_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEXPR_STATE(MxParser.EXPR_STATEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code INS_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitINS_STATE(MxParser.INS_STATEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BLOCK_STATE}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBLOCK_STATE(MxParser.BLOCK_STATEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LOGIC_ARI}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLOGIC_ARI(MxParser.LOGIC_ARIContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Null}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull(MxParser.NullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ARRAY}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitARRAY(MxParser.ARRAYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RELATION}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRELATION(MxParser.RELATIONContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ARITHMETIC}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitARITHMETIC(MxParser.ARITHMETICContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LOGIC_RELATION}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLOGIC_RELATION(MxParser.LOGIC_RELATIONContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CONST_INT}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCONST_INT(MxParser.CONST_INTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ASSIGN}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitASSIGN(MxParser.ASSIGNContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CONST_BOOL}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCONST_BOOL(MxParser.CONST_BOOLContext ctx);
	/**
	 * Visit a parse tree produced by the {@code POSTFIX}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPOSTFIX(MxParser.POSTFIXContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CONST_STRING}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCONST_STRING(MxParser.CONST_STRINGContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SELF_POINTER}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSELF_POINTER(MxParser.SELF_POINTERContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NOT}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNOT(MxParser.NOTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DYNAMIC_INS}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDYNAMIC_INS(MxParser.DYNAMIC_INSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BRACKET}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBRACKET(MxParser.BRACKETContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PREFIX}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPREFIX(MxParser.PREFIXContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MEMBER_VARIABLE}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMEMBER_VARIABLE(MxParser.MEMBER_VARIABLEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IDENTIFIER}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIDENTIFIER(MxParser.IDENTIFIERContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FUNCTION_USE}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFUNCTION_USE(MxParser.FUNCTION_USEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UNARY}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUNARY(MxParser.UNARYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MEMBER_FUNCTION}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMEMBER_FUNCTION(MxParser.MEMBER_FUNCTIONContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NORMAL_INS}
	 * labeled alternative in {@link MxParser#instantiation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNORMAL_INS(MxParser.NORMAL_INSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ASSIGN_INS}
	 * labeled alternative in {@link MxParser#instantiation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitASSIGN_INS(MxParser.ASSIGN_INSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SINGLE_VAR}
	 * labeled alternative in {@link MxParser#class_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSINGLE_VAR(MxParser.SINGLE_VARContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ARRAY_VAR}
	 * labeled alternative in {@link MxParser#class_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitARRAY_VAR(MxParser.ARRAY_VARContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#class_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_name(MxParser.Class_nameContext ctx);
}