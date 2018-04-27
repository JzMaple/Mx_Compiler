// Generated from C:/Users/JZT/Desktop/Mx_Compiler/src/Parser\Mx.g4 by ANTLR 4.7
package Parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, Number=34, ConstString=35, ESC=36, BOOL=37, INT=38, 
		STRING=39, NULL=40, VOID=41, TRUE=42, FALSE=43, IF=44, ELSE=45, FOR=46, 
		WHILE=47, BREAK=48, CONTINUE=49, RETURN=50, NEW=51, CLASS=52, THIS=53, 
		Identifier=54, WS=55, LineNote=56;
	public static final int
		RULE_all = 0, RULE_program = 1, RULE_block = 2, RULE_typeDefine = 3, RULE_function = 4, 
		RULE_parameter = 5, RULE_statement = 6, RULE_expression = 7, RULE_expressionList = 8, 
		RULE_instantiation = 9, RULE_class_statement = 10, RULE_class_name = 11;
	public static final String[] ruleNames = {
		"all", "program", "block", "typeDefine", "function", "parameter", "statement", 
		"expression", "expressionList", "instantiation", "class_statement", "class_name"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "'('", "')'", "','", "';'", "'['", "']'", "'.'", "'++'", 
		"'--'", "'+'", "'-'", "'!'", "'~'", "'[]'", "'*'", "'/'", "'%'", "'<<'", 
		"'>>'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'&'", "'^'", "'|'", 
		"'&&'", "'||'", "'='", null, null, null, "'bool'", "'int'", "'string'", 
		"'null'", "'void'", "'true'", "'false'", "'if'", "'else'", "'for'", "'while'", 
		"'break'", "'continue'", "'return'", "'new'", "'class'", "'this'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "Number", 
		"ConstString", "ESC", "BOOL", "INT", "STRING", "NULL", "VOID", "TRUE", 
		"FALSE", "IF", "ELSE", "FOR", "WHILE", "BREAK", "CONTINUE", "RETURN", 
		"NEW", "CLASS", "THIS", "Identifier", "WS", "LineNote"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class AllContext extends ParserRuleContext {
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public AllContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_all; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAll(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAll(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitAll(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllContext all() throws RecognitionException {
		AllContext _localctx = new AllContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_all);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			program();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<TypeDefineContext> typeDefine() {
			return getRuleContexts(TypeDefineContext.class);
		}
		public TypeDefineContext typeDefine(int i) {
			return getRuleContext(TypeDefineContext.class,i);
		}
		public List<InstantiationContext> instantiation() {
			return getRuleContexts(InstantiationContext.class);
		}
		public InstantiationContext instantiation(int i) {
			return getRuleContext(InstantiationContext.class,i);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(29);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(26);
					typeDefine();
					}
					break;
				case 2:
					{
					setState(27);
					instantiation();
					}
					break;
				case 3:
					{
					setState(28);
					function();
					}
					break;
				}
				}
				setState(31); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << CLASS) | (1L << Identifier))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(T__0);
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << Number) | (1L << ConstString) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << NULL) | (1L << VOID) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << BREAK) | (1L << CONTINUE) | (1L << RETURN) | (1L << NEW) | (1L << THIS) | (1L << Identifier))) != 0)) {
				{
				{
				setState(34);
				statement();
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(40);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeDefineContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(MxParser.CLASS, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public TypeDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterTypeDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitTypeDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitTypeDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDefineContext typeDefine() throws RecognitionException {
		TypeDefineContext _localctx = new TypeDefineContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_typeDefine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(CLASS);
			setState(43);
			match(Identifier);
			setState(44);
			match(T__0);
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << CLASS) | (1L << Identifier))) != 0)) {
				{
				setState(45);
				program();
				}
			}

			setState(48);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Class_statementContext class_statement() {
			return getRuleContext(Class_statementContext.class,0);
		}
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(50);
				class_statement(0);
				}
				break;
			}
			setState(53);
			match(Identifier);
			setState(54);
			match(T__2);
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << Identifier))) != 0)) {
				{
				setState(55);
				parameter();
				}
			}

			setState(58);
			match(T__3);
			setState(59);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public Class_statementContext class_statement() {
			return getRuleContext(Class_statementContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_parameter);
		try {
			setState(69);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				class_statement(0);
				setState(62);
				match(Identifier);
				setState(63);
				match(T__4);
				setState(64);
				parameter();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				class_statement(0);
				setState(67);
				match(Identifier);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IF_STATEContext extends StatementContext {
		public TerminalNode IF() { return getToken(MxParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public IF_STATEContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIF_STATE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIF_STATE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitIF_STATE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BREAK_STATEContext extends StatementContext {
		public TerminalNode BREAK() { return getToken(MxParser.BREAK, 0); }
		public BREAK_STATEContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBREAK_STATE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBREAK_STATE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBREAK_STATE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WHILE_STATEContext extends StatementContext {
		public TerminalNode WHILE() { return getToken(MxParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WHILE_STATEContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterWHILE_STATE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitWHILE_STATE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitWHILE_STATE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EXPR_STATEContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public EXPR_STATEContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterEXPR_STATE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitEXPR_STATE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitEXPR_STATE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BLOCK_STATEContext extends StatementContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BLOCK_STATEContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBLOCK_STATE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBLOCK_STATE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBLOCK_STATE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class INS_STATEContext extends StatementContext {
		public InstantiationContext instantiation() {
			return getRuleContext(InstantiationContext.class,0);
		}
		public INS_STATEContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterINS_STATE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitINS_STATE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitINS_STATE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IFELSE_STATEContext extends StatementContext {
		public TerminalNode IF() { return getToken(MxParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MxParser.ELSE, 0); }
		public IFELSE_STATEContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIFELSE_STATE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIFELSE_STATE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitIFELSE_STATE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RETURN_STATEContext extends StatementContext {
		public TerminalNode RETURN() { return getToken(MxParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RETURN_STATEContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterRETURN_STATE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitRETURN_STATE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitRETURN_STATE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FOR_STATEContext extends StatementContext {
		public ExpressionContext first;
		public ExpressionContext second;
		public ExpressionContext third;
		public TerminalNode FOR() { return getToken(MxParser.FOR, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public FOR_STATEContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFOR_STATE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFOR_STATE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFOR_STATE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CONTINUE_STATEContext extends StatementContext {
		public TerminalNode CONTINUE() { return getToken(MxParser.CONTINUE, 0); }
		public CONTINUE_STATEContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterCONTINUE_STATE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitCONTINUE_STATE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitCONTINUE_STATE(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		int _la;
		try {
			setState(119);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new IF_STATEContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				match(IF);
				setState(72);
				match(T__2);
				setState(73);
				expression(0);
				setState(74);
				match(T__3);
				setState(75);
				statement();
				}
				break;
			case 2:
				_localctx = new IFELSE_STATEContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				match(IF);
				setState(78);
				match(T__2);
				setState(79);
				expression(0);
				setState(80);
				match(T__3);
				setState(81);
				statement();
				setState(82);
				match(ELSE);
				setState(83);
				statement();
				}
				break;
			case 3:
				_localctx = new FOR_STATEContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(85);
				match(FOR);
				setState(86);
				match(T__2);
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << Number) | (1L << ConstString) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << Identifier))) != 0)) {
					{
					setState(87);
					((FOR_STATEContext)_localctx).first = expression(0);
					}
				}

				setState(90);
				match(T__5);
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << Number) | (1L << ConstString) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << Identifier))) != 0)) {
					{
					setState(91);
					((FOR_STATEContext)_localctx).second = expression(0);
					}
				}

				setState(94);
				match(T__5);
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << Number) | (1L << ConstString) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << Identifier))) != 0)) {
					{
					setState(95);
					((FOR_STATEContext)_localctx).third = expression(0);
					}
				}

				setState(98);
				match(T__3);
				setState(99);
				statement();
				}
				break;
			case 4:
				_localctx = new WHILE_STATEContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(100);
				match(WHILE);
				setState(101);
				match(T__2);
				setState(102);
				expression(0);
				setState(103);
				match(T__3);
				setState(104);
				statement();
				}
				break;
			case 5:
				_localctx = new RETURN_STATEContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(106);
				match(RETURN);
				setState(107);
				expression(0);
				setState(108);
				match(T__5);
				}
				break;
			case 6:
				_localctx = new BREAK_STATEContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(110);
				match(BREAK);
				setState(111);
				match(T__5);
				}
				break;
			case 7:
				_localctx = new CONTINUE_STATEContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(112);
				match(CONTINUE);
				setState(113);
				match(T__5);
				}
				break;
			case 8:
				_localctx = new EXPR_STATEContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(114);
				expression(0);
				setState(115);
				match(T__5);
				}
				break;
			case 9:
				_localctx = new INS_STATEContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(117);
				instantiation();
				}
				break;
			case 10:
				_localctx = new BLOCK_STATEContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(118);
				block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LOGIC_ARIContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LOGIC_ARIContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLOGIC_ARI(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLOGIC_ARI(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLOGIC_ARI(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullContext extends ExpressionContext {
		public TerminalNode NULL() { return getToken(MxParser.NULL, 0); }
		public NullContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNull(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNull(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ARRAYContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ARRAYContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterARRAY(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitARRAY(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitARRAY(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RELATIONContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public RELATIONContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterRELATION(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitRELATION(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitRELATION(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ARITHMETICContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ARITHMETICContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterARITHMETIC(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitARITHMETIC(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitARITHMETIC(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LOGIC_RELATIONContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LOGIC_RELATIONContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLOGIC_RELATION(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLOGIC_RELATION(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLOGIC_RELATION(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CONST_INTContext extends ExpressionContext {
		public TerminalNode Number() { return getToken(MxParser.Number, 0); }
		public CONST_INTContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterCONST_INT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitCONST_INT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitCONST_INT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ASSIGNContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ASSIGNContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterASSIGN(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitASSIGN(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitASSIGN(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CONST_BOOLContext extends ExpressionContext {
		public TerminalNode TRUE() { return getToken(MxParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(MxParser.FALSE, 0); }
		public CONST_BOOLContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterCONST_BOOL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitCONST_BOOL(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitCONST_BOOL(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class POSTFIXContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public POSTFIXContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPOSTFIX(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPOSTFIX(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitPOSTFIX(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CONST_STRINGContext extends ExpressionContext {
		public TerminalNode ConstString() { return getToken(MxParser.ConstString, 0); }
		public CONST_STRINGContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterCONST_STRING(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitCONST_STRING(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitCONST_STRING(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SELF_POINTERContext extends ExpressionContext {
		public TerminalNode THIS() { return getToken(MxParser.THIS, 0); }
		public SELF_POINTERContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSELF_POINTER(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSELF_POINTER(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitSELF_POINTER(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NOTContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NOTContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNOT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNOT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNOT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DYNAMIC_INSContext extends ExpressionContext {
		public TerminalNode NEW() { return getToken(MxParser.NEW, 0); }
		public Class_nameContext class_name() {
			return getRuleContext(Class_nameContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public DYNAMIC_INSContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterDYNAMIC_INS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitDYNAMIC_INS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitDYNAMIC_INS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BRACKETContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BRACKETContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBRACKET(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBRACKET(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBRACKET(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PREFIXContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PREFIXContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPREFIX(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPREFIX(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitPREFIX(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MEMBER_VARIABLEContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public MEMBER_VARIABLEContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMEMBER_VARIABLE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMEMBER_VARIABLE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitMEMBER_VARIABLE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IDENTIFIERContext extends ExpressionContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public IDENTIFIERContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIDENTIFIER(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIDENTIFIER(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitIDENTIFIER(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FUNCTION_USEContext extends ExpressionContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public FUNCTION_USEContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFUNCTION_USE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFUNCTION_USE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFUNCTION_USE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UNARYContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UNARYContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterUNARY(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitUNARY(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitUNARY(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MEMBER_FUNCTIONContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public MEMBER_FUNCTIONContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMEMBER_FUNCTION(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMEMBER_FUNCTION(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitMEMBER_FUNCTION(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				_localctx = new FUNCTION_USEContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(122);
				match(Identifier);
				setState(123);
				match(T__2);
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << Number) | (1L << ConstString) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << Identifier))) != 0)) {
					{
					setState(124);
					expressionList();
					}
				}

				setState(127);
				match(T__3);
				}
				break;
			case 2:
				{
				_localctx = new IDENTIFIERContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				match(Identifier);
				}
				break;
			case 3:
				{
				_localctx = new CONST_INTContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(129);
				match(Number);
				}
				break;
			case 4:
				{
				_localctx = new CONST_BOOLContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				match(TRUE);
				}
				break;
			case 5:
				{
				_localctx = new CONST_BOOLContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(131);
				match(FALSE);
				}
				break;
			case 6:
				{
				_localctx = new NullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				match(NULL);
				}
				break;
			case 7:
				{
				_localctx = new CONST_STRINGContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(133);
				match(ConstString);
				}
				break;
			case 8:
				{
				_localctx = new SELF_POINTERContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134);
				match(THIS);
				}
				break;
			case 9:
				{
				_localctx = new PREFIXContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(135);
				((PREFIXContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__10) ) {
					((PREFIXContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(136);
				expression(16);
				}
				break;
			case 10:
				{
				_localctx = new UNARYContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(137);
				((UNARYContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
					((UNARYContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(138);
				expression(15);
				}
				break;
			case 11:
				{
				_localctx = new NOTContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(139);
				((NOTContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
					((NOTContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(140);
				expression(14);
				}
				break;
			case 12:
				{
				_localctx = new DYNAMIC_INSContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141);
				match(NEW);
				setState(142);
				class_name();
				setState(157);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(147); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(143);
							match(T__6);
							setState(144);
							expression(0);
							setState(145);
							match(T__7);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(149); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					setState(154);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(151);
							match(T__15);
							}
							} 
						}
						setState(156);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
					}
					}
					break;
				}
				}
				break;
			case 13:
				{
				_localctx = new BRACKETContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(159);
				match(T__2);
				setState(160);
				expression(0);
				setState(161);
				match(T__3);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(218);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(216);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new ARITHMETICContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(165);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(166);
						((ARITHMETICContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
							((ARITHMETICContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(167);
						expression(13);
						}
						break;
					case 2:
						{
						_localctx = new ARITHMETICContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(168);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(169);
						((ARITHMETICContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__11 || _la==T__12) ) {
							((ARITHMETICContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(170);
						expression(12);
						}
						break;
					case 3:
						{
						_localctx = new ARITHMETICContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(171);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(172);
						((ARITHMETICContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__19 || _la==T__20) ) {
							((ARITHMETICContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(173);
						expression(11);
						}
						break;
					case 4:
						{
						_localctx = new RELATIONContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(174);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(175);
						((RELATIONContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
							((RELATIONContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(176);
						expression(10);
						}
						break;
					case 5:
						{
						_localctx = new RELATIONContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(177);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(178);
						((RELATIONContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__25 || _la==T__26) ) {
							((RELATIONContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(179);
						expression(9);
						}
						break;
					case 6:
						{
						_localctx = new LOGIC_ARIContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(180);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(181);
						((LOGIC_ARIContext)_localctx).op = match(T__27);
						setState(182);
						expression(8);
						}
						break;
					case 7:
						{
						_localctx = new LOGIC_ARIContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(183);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(184);
						((LOGIC_ARIContext)_localctx).op = match(T__28);
						setState(185);
						expression(7);
						}
						break;
					case 8:
						{
						_localctx = new LOGIC_ARIContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(186);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(187);
						((LOGIC_ARIContext)_localctx).op = match(T__29);
						setState(188);
						expression(6);
						}
						break;
					case 9:
						{
						_localctx = new LOGIC_RELATIONContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(189);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(190);
						((LOGIC_RELATIONContext)_localctx).op = match(T__30);
						setState(191);
						expression(5);
						}
						break;
					case 10:
						{
						_localctx = new LOGIC_RELATIONContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(192);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(193);
						((LOGIC_RELATIONContext)_localctx).op = match(T__31);
						setState(194);
						expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ASSIGNContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(195);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(196);
						match(T__32);
						setState(197);
						expression(2);
						}
						break;
					case 12:
						{
						_localctx = new ARRAYContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(198);
						if (!(precpred(_ctx, 27))) throw new FailedPredicateException(this, "precpred(_ctx, 27)");
						setState(199);
						match(T__6);
						setState(200);
						expression(0);
						setState(201);
						match(T__7);
						}
						break;
					case 13:
						{
						_localctx = new MEMBER_VARIABLEContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(203);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(204);
						match(T__8);
						setState(205);
						match(Identifier);
						}
						break;
					case 14:
						{
						_localctx = new MEMBER_FUNCTIONContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(206);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(207);
						match(T__8);
						setState(208);
						match(Identifier);
						setState(209);
						match(T__2);
						setState(211);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << Number) | (1L << ConstString) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << Identifier))) != 0)) {
							{
							setState(210);
							expressionList();
							}
						}

						setState(213);
						match(T__3);
						}
						break;
					case 15:
						{
						_localctx = new POSTFIXContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(214);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(215);
						((POSTFIXContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__9 || _la==T__10) ) {
							((POSTFIXContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(220);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expressionList);
		try {
			setState(226);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(221);
				expression(0);
				setState(222);
				match(T__4);
				setState(223);
				expressionList();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(225);
				expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstantiationContext extends ParserRuleContext {
		public InstantiationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instantiation; }
	 
		public InstantiationContext() { }
		public void copyFrom(InstantiationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NORMAL_INSContext extends InstantiationContext {
		public Class_statementContext class_statement() {
			return getRuleContext(Class_statementContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public NORMAL_INSContext(InstantiationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNORMAL_INS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNORMAL_INS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNORMAL_INS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ASSIGN_INSContext extends InstantiationContext {
		public Class_statementContext class_statement() {
			return getRuleContext(Class_statementContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ASSIGN_INSContext(InstantiationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterASSIGN_INS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitASSIGN_INS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitASSIGN_INS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstantiationContext instantiation() throws RecognitionException {
		InstantiationContext _localctx = new InstantiationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_instantiation);
		try {
			setState(238);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				_localctx = new NORMAL_INSContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				class_statement(0);
				setState(229);
				match(Identifier);
				setState(230);
				match(T__5);
				}
				break;
			case 2:
				_localctx = new ASSIGN_INSContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(232);
				class_statement(0);
				setState(233);
				match(Identifier);
				setState(234);
				match(T__32);
				setState(235);
				expression(0);
				setState(236);
				match(T__5);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_statementContext extends ParserRuleContext {
		public Class_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_statement; }
	 
		public Class_statementContext() { }
		public void copyFrom(Class_statementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SINGLE_VARContext extends Class_statementContext {
		public Class_nameContext class_name() {
			return getRuleContext(Class_nameContext.class,0);
		}
		public SINGLE_VARContext(Class_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSINGLE_VAR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSINGLE_VAR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitSINGLE_VAR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ARRAY_VARContext extends Class_statementContext {
		public Class_statementContext class_statement() {
			return getRuleContext(Class_statementContext.class,0);
		}
		public ARRAY_VARContext(Class_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterARRAY_VAR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitARRAY_VAR(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitARRAY_VAR(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_statementContext class_statement() throws RecognitionException {
		return class_statement(0);
	}

	private Class_statementContext class_statement(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Class_statementContext _localctx = new Class_statementContext(_ctx, _parentState);
		Class_statementContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_class_statement, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new SINGLE_VARContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(241);
			class_name();
			}
			_ctx.stop = _input.LT(-1);
			setState(248);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ARRAY_VARContext(new Class_statementContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_class_statement);
					setState(243);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					{
					setState(244);
					match(T__6);
					setState(245);
					match(T__7);
					}
					}
					} 
				}
				setState(250);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Class_nameContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(MxParser.BOOL, 0); }
		public TerminalNode INT() { return getToken(MxParser.INT, 0); }
		public TerminalNode STRING() { return getToken(MxParser.STRING, 0); }
		public TerminalNode VOID() { return getToken(MxParser.VOID, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public Class_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClass_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClass_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClass_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_nameContext class_name() throws RecognitionException {
		Class_nameContext _localctx = new Class_nameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_class_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << Identifier))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 10:
			return class_statement_sempred((Class_statementContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 12);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 7);
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 4);
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		case 11:
			return precpred(_ctx, 27);
		case 12:
			return precpred(_ctx, 26);
		case 13:
			return precpred(_ctx, 25);
		case 14:
			return precpred(_ctx, 17);
		}
		return true;
	}
	private boolean class_statement_sempred(Class_statementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 15:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u0100\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\3\3\3\3\3\6\3 \n\3\r\3\16\3!\3\4\3\4\7\4"+
		"&\n\4\f\4\16\4)\13\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5\61\n\5\3\5\3\5\3\6\5"+
		"\6\66\n\6\3\6\3\6\3\6\5\6;\n\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\5\7H\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\5\b[\n\b\3\b\3\b\5\b_\n\b\3\b\3\b\5\bc\n\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\5\bz\n\b\3\t\3\t\3\t\3\t\5\t\u0080\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\6\t\u0096\n\t\r\t\16"+
		"\t\u0097\3\t\7\t\u009b\n\t\f\t\16\t\u009e\13\t\5\t\u00a0\n\t\3\t\3\t\3"+
		"\t\3\t\5\t\u00a6\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00d6"+
		"\n\t\3\t\3\t\3\t\7\t\u00db\n\t\f\t\16\t\u00de\13\t\3\n\3\n\3\n\3\n\3\n"+
		"\5\n\u00e5\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13"+
		"\u00f1\n\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00f9\n\f\f\f\16\f\u00fc\13\f"+
		"\3\r\3\r\3\r\2\4\20\26\16\2\4\6\b\n\f\16\20\22\24\26\30\2\n\3\2\f\r\3"+
		"\2\16\17\3\2\20\21\3\2\23\25\3\2\26\27\3\2\30\33\3\2\34\35\5\2\')++88"+
		"\2\u012a\2\32\3\2\2\2\4\37\3\2\2\2\6#\3\2\2\2\b,\3\2\2\2\n\65\3\2\2\2"+
		"\fG\3\2\2\2\16y\3\2\2\2\20\u00a5\3\2\2\2\22\u00e4\3\2\2\2\24\u00f0\3\2"+
		"\2\2\26\u00f2\3\2\2\2\30\u00fd\3\2\2\2\32\33\5\4\3\2\33\3\3\2\2\2\34 "+
		"\5\b\5\2\35 \5\24\13\2\36 \5\n\6\2\37\34\3\2\2\2\37\35\3\2\2\2\37\36\3"+
		"\2\2\2 !\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"\5\3\2\2\2#\'\7\3\2\2$&\5\16"+
		"\b\2%$\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(*\3\2\2\2)\'\3\2\2\2*+"+
		"\7\4\2\2+\7\3\2\2\2,-\7\66\2\2-.\78\2\2.\60\7\3\2\2/\61\5\4\3\2\60/\3"+
		"\2\2\2\60\61\3\2\2\2\61\62\3\2\2\2\62\63\7\4\2\2\63\t\3\2\2\2\64\66\5"+
		"\26\f\2\65\64\3\2\2\2\65\66\3\2\2\2\66\67\3\2\2\2\678\78\2\28:\7\5\2\2"+
		"9;\5\f\7\2:9\3\2\2\2:;\3\2\2\2;<\3\2\2\2<=\7\6\2\2=>\5\6\4\2>\13\3\2\2"+
		"\2?@\5\26\f\2@A\78\2\2AB\7\7\2\2BC\5\f\7\2CH\3\2\2\2DE\5\26\f\2EF\78\2"+
		"\2FH\3\2\2\2G?\3\2\2\2GD\3\2\2\2H\r\3\2\2\2IJ\7.\2\2JK\7\5\2\2KL\5\20"+
		"\t\2LM\7\6\2\2MN\5\16\b\2Nz\3\2\2\2OP\7.\2\2PQ\7\5\2\2QR\5\20\t\2RS\7"+
		"\6\2\2ST\5\16\b\2TU\7/\2\2UV\5\16\b\2Vz\3\2\2\2WX\7\60\2\2XZ\7\5\2\2Y"+
		"[\5\20\t\2ZY\3\2\2\2Z[\3\2\2\2[\\\3\2\2\2\\^\7\b\2\2]_\5\20\t\2^]\3\2"+
		"\2\2^_\3\2\2\2_`\3\2\2\2`b\7\b\2\2ac\5\20\t\2ba\3\2\2\2bc\3\2\2\2cd\3"+
		"\2\2\2de\7\6\2\2ez\5\16\b\2fg\7\61\2\2gh\7\5\2\2hi\5\20\t\2ij\7\6\2\2"+
		"jk\5\16\b\2kz\3\2\2\2lm\7\64\2\2mn\5\20\t\2no\7\b\2\2oz\3\2\2\2pq\7\62"+
		"\2\2qz\7\b\2\2rs\7\63\2\2sz\7\b\2\2tu\5\20\t\2uv\7\b\2\2vz\3\2\2\2wz\5"+
		"\24\13\2xz\5\6\4\2yI\3\2\2\2yO\3\2\2\2yW\3\2\2\2yf\3\2\2\2yl\3\2\2\2y"+
		"p\3\2\2\2yr\3\2\2\2yt\3\2\2\2yw\3\2\2\2yx\3\2\2\2z\17\3\2\2\2{|\b\t\1"+
		"\2|}\78\2\2}\177\7\5\2\2~\u0080\5\22\n\2\177~\3\2\2\2\177\u0080\3\2\2"+
		"\2\u0080\u0081\3\2\2\2\u0081\u00a6\7\6\2\2\u0082\u00a6\78\2\2\u0083\u00a6"+
		"\7$\2\2\u0084\u00a6\7,\2\2\u0085\u00a6\7-\2\2\u0086\u00a6\7*\2\2\u0087"+
		"\u00a6\7%\2\2\u0088\u00a6\7\67\2\2\u0089\u008a\t\2\2\2\u008a\u00a6\5\20"+
		"\t\22\u008b\u008c\t\3\2\2\u008c\u00a6\5\20\t\21\u008d\u008e\t\4\2\2\u008e"+
		"\u00a6\5\20\t\20\u008f\u0090\7\65\2\2\u0090\u009f\5\30\r\2\u0091\u0092"+
		"\7\t\2\2\u0092\u0093\5\20\t\2\u0093\u0094\7\n\2\2\u0094\u0096\3\2\2\2"+
		"\u0095\u0091\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098"+
		"\3\2\2\2\u0098\u009c\3\2\2\2\u0099\u009b\7\22\2\2\u009a\u0099\3\2\2\2"+
		"\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u00a0"+
		"\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u0095\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\u00a6\3\2\2\2\u00a1\u00a2\7\5\2\2\u00a2\u00a3\5\20\t\2\u00a3\u00a4\7"+
		"\6\2\2\u00a4\u00a6\3\2\2\2\u00a5{\3\2\2\2\u00a5\u0082\3\2\2\2\u00a5\u0083"+
		"\3\2\2\2\u00a5\u0084\3\2\2\2\u00a5\u0085\3\2\2\2\u00a5\u0086\3\2\2\2\u00a5"+
		"\u0087\3\2\2\2\u00a5\u0088\3\2\2\2\u00a5\u0089\3\2\2\2\u00a5\u008b\3\2"+
		"\2\2\u00a5\u008d\3\2\2\2\u00a5\u008f\3\2\2\2\u00a5\u00a1\3\2\2\2\u00a6"+
		"\u00dc\3\2\2\2\u00a7\u00a8\f\16\2\2\u00a8\u00a9\t\5\2\2\u00a9\u00db\5"+
		"\20\t\17\u00aa\u00ab\f\r\2\2\u00ab\u00ac\t\3\2\2\u00ac\u00db\5\20\t\16"+
		"\u00ad\u00ae\f\f\2\2\u00ae\u00af\t\6\2\2\u00af\u00db\5\20\t\r\u00b0\u00b1"+
		"\f\13\2\2\u00b1\u00b2\t\7\2\2\u00b2\u00db\5\20\t\f\u00b3\u00b4\f\n\2\2"+
		"\u00b4\u00b5\t\b\2\2\u00b5\u00db\5\20\t\13\u00b6\u00b7\f\t\2\2\u00b7\u00b8"+
		"\7\36\2\2\u00b8\u00db\5\20\t\n\u00b9\u00ba\f\b\2\2\u00ba\u00bb\7\37\2"+
		"\2\u00bb\u00db\5\20\t\t\u00bc\u00bd\f\7\2\2\u00bd\u00be\7 \2\2\u00be\u00db"+
		"\5\20\t\b\u00bf\u00c0\f\6\2\2\u00c0\u00c1\7!\2\2\u00c1\u00db\5\20\t\7"+
		"\u00c2\u00c3\f\5\2\2\u00c3\u00c4\7\"\2\2\u00c4\u00db\5\20\t\6\u00c5\u00c6"+
		"\f\4\2\2\u00c6\u00c7\7#\2\2\u00c7\u00db\5\20\t\4\u00c8\u00c9\f\35\2\2"+
		"\u00c9\u00ca\7\t\2\2\u00ca\u00cb\5\20\t\2\u00cb\u00cc\7\n\2\2\u00cc\u00db"+
		"\3\2\2\2\u00cd\u00ce\f\34\2\2\u00ce\u00cf\7\13\2\2\u00cf\u00db\78\2\2"+
		"\u00d0\u00d1\f\33\2\2\u00d1\u00d2\7\13\2\2\u00d2\u00d3\78\2\2\u00d3\u00d5"+
		"\7\5\2\2\u00d4\u00d6\5\22\n\2\u00d5\u00d4\3\2\2\2\u00d5\u00d6\3\2\2\2"+
		"\u00d6\u00d7\3\2\2\2\u00d7\u00db\7\6\2\2\u00d8\u00d9\f\23\2\2\u00d9\u00db"+
		"\t\2\2\2\u00da\u00a7\3\2\2\2\u00da\u00aa\3\2\2\2\u00da\u00ad\3\2\2\2\u00da"+
		"\u00b0\3\2\2\2\u00da\u00b3\3\2\2\2\u00da\u00b6\3\2\2\2\u00da\u00b9\3\2"+
		"\2\2\u00da\u00bc\3\2\2\2\u00da\u00bf\3\2\2\2\u00da\u00c2\3\2\2\2\u00da"+
		"\u00c5\3\2\2\2\u00da\u00c8\3\2\2\2\u00da\u00cd\3\2\2\2\u00da\u00d0\3\2"+
		"\2\2\u00da\u00d8\3\2\2\2\u00db\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc"+
		"\u00dd\3\2\2\2\u00dd\21\3\2\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\5\20\t"+
		"\2\u00e0\u00e1\7\7\2\2\u00e1\u00e2\5\22\n\2\u00e2\u00e5\3\2\2\2\u00e3"+
		"\u00e5\5\20\t\2\u00e4\u00df\3\2\2\2\u00e4\u00e3\3\2\2\2\u00e5\23\3\2\2"+
		"\2\u00e6\u00e7\5\26\f\2\u00e7\u00e8\78\2\2\u00e8\u00e9\7\b\2\2\u00e9\u00f1"+
		"\3\2\2\2\u00ea\u00eb\5\26\f\2\u00eb\u00ec\78\2\2\u00ec\u00ed\7#\2\2\u00ed"+
		"\u00ee\5\20\t\2\u00ee\u00ef\7\b\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00e6\3"+
		"\2\2\2\u00f0\u00ea\3\2\2\2\u00f1\25\3\2\2\2\u00f2\u00f3\b\f\1\2\u00f3"+
		"\u00f4\5\30\r\2\u00f4\u00fa\3\2\2\2\u00f5\u00f6\f\3\2\2\u00f6\u00f7\7"+
		"\t\2\2\u00f7\u00f9\7\n\2\2\u00f8\u00f5\3\2\2\2\u00f9\u00fc\3\2\2\2\u00fa"+
		"\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\27\3\2\2\2\u00fc\u00fa\3\2\2"+
		"\2\u00fd\u00fe\t\t\2\2\u00fe\31\3\2\2\2\30\37!\'\60\65:GZ^by\177\u0097"+
		"\u009c\u009f\u00a5\u00d5\u00da\u00dc\u00e4\u00f0\u00fa";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}