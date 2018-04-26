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
		RULE_parameter = 5, RULE_statement = 6, RULE_expression = 7, RULE_instantiation = 8, 
		RULE_class_statement = 9, RULE_class_name = 10;
	public static final String[] ruleNames = {
		"all", "program", "block", "typeDefine", "function", "parameter", "statement", 
		"expression", "instantiation", "class_statement", "class_name"
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
			setState(22);
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
			setState(27); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(27);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(24);
					typeDefine();
					}
					break;
				case 2:
					{
					setState(25);
					instantiation();
					}
					break;
				case 3:
					{
					setState(26);
					function();
					}
					break;
				}
				}
				setState(29); 
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
			setState(31);
			match(T__0);
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << Number) | (1L << ConstString) | (1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << NULL) | (1L << VOID) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << BREAK) | (1L << CONTINUE) | (1L << RETURN) | (1L << NEW) | (1L << THIS) | (1L << Identifier))) != 0)) {
				{
				{
				setState(32);
				statement();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(38);
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
			setState(40);
			match(CLASS);
			setState(41);
			match(Identifier);
			setState(42);
			match(T__0);
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << CLASS) | (1L << Identifier))) != 0)) {
				{
				setState(43);
				program();
				}
			}

			setState(46);
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
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(48);
				class_statement(0);
				}
				break;
			}
			setState(51);
			match(Identifier);
			setState(52);
			match(T__2);
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << Identifier))) != 0)) {
				{
				setState(53);
				parameter();
				}
			}

			setState(56);
			match(T__3);
			setState(57);
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
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				class_statement(0);
				setState(60);
				match(Identifier);
				setState(61);
				match(T__4);
				setState(62);
				parameter();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				class_statement(0);
				setState(65);
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
			setState(117);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new IF_STATEContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				match(IF);
				setState(70);
				match(T__2);
				setState(71);
				expression(0);
				setState(72);
				match(T__3);
				setState(73);
				statement();
				}
				break;
			case 2:
				_localctx = new IFELSE_STATEContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				match(IF);
				setState(76);
				match(T__2);
				setState(77);
				expression(0);
				setState(78);
				match(T__3);
				setState(79);
				statement();
				setState(80);
				match(ELSE);
				setState(81);
				statement();
				}
				break;
			case 3:
				_localctx = new FOR_STATEContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(83);
				match(FOR);
				setState(84);
				match(T__2);
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << Number) | (1L << ConstString) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << Identifier))) != 0)) {
					{
					setState(85);
					((FOR_STATEContext)_localctx).first = expression(0);
					}
				}

				setState(88);
				match(T__5);
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << Number) | (1L << ConstString) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << Identifier))) != 0)) {
					{
					setState(89);
					((FOR_STATEContext)_localctx).second = expression(0);
					}
				}

				setState(92);
				match(T__5);
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << Number) | (1L << ConstString) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << NEW) | (1L << THIS) | (1L << Identifier))) != 0)) {
					{
					setState(93);
					((FOR_STATEContext)_localctx).third = expression(0);
					}
				}

				setState(96);
				match(T__3);
				setState(97);
				statement();
				}
				break;
			case 4:
				_localctx = new WHILE_STATEContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(98);
				match(WHILE);
				setState(99);
				match(T__2);
				setState(100);
				expression(0);
				setState(101);
				match(T__3);
				setState(102);
				statement();
				}
				break;
			case 5:
				_localctx = new RETURN_STATEContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(104);
				match(RETURN);
				setState(105);
				expression(0);
				setState(106);
				match(T__5);
				}
				break;
			case 6:
				_localctx = new BREAK_STATEContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(108);
				match(BREAK);
				setState(109);
				match(T__5);
				}
				break;
			case 7:
				_localctx = new CONTINUE_STATEContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(110);
				match(CONTINUE);
				setState(111);
				match(T__5);
				}
				break;
			case 8:
				_localctx = new EXPR_STATEContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(112);
				expression(0);
				setState(113);
				match(T__5);
				}
				break;
			case 9:
				_localctx = new INS_STATEContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(115);
				instantiation();
				}
				break;
			case 10:
				_localctx = new BLOCK_STATEContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(116);
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
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
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
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
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
			setState(161);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				_localctx = new FUNCTION_USEContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(120);
				match(Identifier);
				setState(121);
				match(T__2);
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << Identifier))) != 0)) {
					{
					setState(122);
					parameter();
					}
				}

				setState(125);
				match(T__3);
				}
				break;
			case 2:
				{
				_localctx = new IDENTIFIERContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(126);
				match(Identifier);
				}
				break;
			case 3:
				{
				_localctx = new CONST_INTContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(127);
				match(Number);
				}
				break;
			case 4:
				{
				_localctx = new CONST_BOOLContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				match(TRUE);
				}
				break;
			case 5:
				{
				_localctx = new CONST_BOOLContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(129);
				match(FALSE);
				}
				break;
			case 6:
				{
				_localctx = new NullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				match(NULL);
				}
				break;
			case 7:
				{
				_localctx = new CONST_STRINGContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(131);
				match(ConstString);
				}
				break;
			case 8:
				{
				_localctx = new SELF_POINTERContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				match(THIS);
				}
				break;
			case 9:
				{
				_localctx = new PREFIXContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(133);
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
				setState(134);
				expression(16);
				}
				break;
			case 10:
				{
				_localctx = new UNARYContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(135);
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
				setState(136);
				expression(15);
				}
				break;
			case 11:
				{
				_localctx = new NOTContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(137);
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
				setState(138);
				expression(14);
				}
				break;
			case 12:
				{
				_localctx = new DYNAMIC_INSContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(139);
				match(NEW);
				setState(140);
				class_name();
				setState(155);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(145); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(141);
							match(T__6);
							setState(142);
							expression(0);
							setState(143);
							match(T__7);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(147); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					setState(152);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(149);
							match(T__15);
							}
							} 
						}
						setState(154);
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
				setState(157);
				match(T__2);
				setState(158);
				expression(0);
				setState(159);
				match(T__3);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(216);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(214);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new ARITHMETICContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(163);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(164);
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
						setState(165);
						expression(13);
						}
						break;
					case 2:
						{
						_localctx = new ARITHMETICContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(166);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(167);
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
						setState(168);
						expression(12);
						}
						break;
					case 3:
						{
						_localctx = new ARITHMETICContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(169);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(170);
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
						setState(171);
						expression(11);
						}
						break;
					case 4:
						{
						_localctx = new RELATIONContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(172);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(173);
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
						setState(174);
						expression(10);
						}
						break;
					case 5:
						{
						_localctx = new RELATIONContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(175);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(176);
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
						setState(177);
						expression(9);
						}
						break;
					case 6:
						{
						_localctx = new LOGIC_ARIContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(178);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(179);
						((LOGIC_ARIContext)_localctx).op = match(T__27);
						setState(180);
						expression(8);
						}
						break;
					case 7:
						{
						_localctx = new LOGIC_ARIContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(182);
						((LOGIC_ARIContext)_localctx).op = match(T__28);
						setState(183);
						expression(7);
						}
						break;
					case 8:
						{
						_localctx = new LOGIC_ARIContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(184);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(185);
						((LOGIC_ARIContext)_localctx).op = match(T__29);
						setState(186);
						expression(6);
						}
						break;
					case 9:
						{
						_localctx = new LOGIC_RELATIONContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(187);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(188);
						((LOGIC_RELATIONContext)_localctx).op = match(T__30);
						setState(189);
						expression(5);
						}
						break;
					case 10:
						{
						_localctx = new LOGIC_RELATIONContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(190);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(191);
						((LOGIC_RELATIONContext)_localctx).op = match(T__31);
						setState(192);
						expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ASSIGNContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(193);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(194);
						match(T__32);
						setState(195);
						expression(2);
						}
						break;
					case 12:
						{
						_localctx = new ARRAYContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(196);
						if (!(precpred(_ctx, 27))) throw new FailedPredicateException(this, "precpred(_ctx, 27)");
						setState(197);
						match(T__6);
						setState(198);
						expression(0);
						setState(199);
						match(T__7);
						}
						break;
					case 13:
						{
						_localctx = new MEMBER_VARIABLEContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(201);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(202);
						match(T__8);
						setState(203);
						match(Identifier);
						}
						break;
					case 14:
						{
						_localctx = new MEMBER_FUNCTIONContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(204);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(205);
						match(T__8);
						setState(206);
						match(Identifier);
						setState(207);
						match(T__2);
						setState(209);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << Identifier))) != 0)) {
							{
							setState(208);
							parameter();
							}
						}

						setState(211);
						match(T__3);
						}
						break;
					case 15:
						{
						_localctx = new POSTFIXContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(212);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(213);
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
				setState(218);
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
		enterRule(_localctx, 16, RULE_instantiation);
		try {
			setState(229);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				_localctx = new NORMAL_INSContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(219);
				class_statement(0);
				setState(220);
				match(Identifier);
				setState(221);
				match(T__5);
				}
				break;
			case 2:
				_localctx = new ASSIGN_INSContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(223);
				class_statement(0);
				setState(224);
				match(Identifier);
				setState(225);
				match(T__32);
				setState(226);
				expression(0);
				setState(227);
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
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_class_statement, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new SINGLE_VARContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(232);
			class_name();
			}
			_ctx.stop = _input.LT(-1);
			setState(238);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ARRAY_VARContext(new Class_statementContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_class_statement);
					setState(234);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(235);
					match(T__15);
					}
					} 
				}
				setState(240);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
		enterRule(_localctx, 20, RULE_class_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
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
		case 9:
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
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u00f6\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\3\2\3\3\3\3\3\3\6\3\36\n\3\r\3\16\3\37\3\4\3\4\7\4$\n"+
		"\4\f\4\16\4\'\13\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5/\n\5\3\5\3\5\3\6\5\6\64"+
		"\n\6\3\6\3\6\3\6\5\69\n\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\5\7F\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\5\bY\n\b\3\b\3\b\5\b]\n\b\3\b\3\b\5\ba\n\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b"+
		"x\n\b\3\t\3\t\3\t\3\t\5\t~\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\6\t\u0094\n\t\r\t\16\t\u0095"+
		"\3\t\7\t\u0099\n\t\f\t\16\t\u009c\13\t\5\t\u009e\n\t\3\t\3\t\3\t\3\t\5"+
		"\t\u00a4\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00d4\n\t"+
		"\3\t\3\t\3\t\7\t\u00d9\n\t\f\t\16\t\u00dc\13\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\5\n\u00e8\n\n\3\13\3\13\3\13\3\13\3\13\7\13\u00ef\n\13"+
		"\f\13\16\13\u00f2\13\13\3\f\3\f\3\f\2\4\20\24\r\2\4\6\b\n\f\16\20\22\24"+
		"\26\2\n\3\2\f\r\3\2\16\17\3\2\20\21\3\2\23\25\3\2\26\27\3\2\30\33\3\2"+
		"\34\35\5\2\')++88\2\u0120\2\30\3\2\2\2\4\35\3\2\2\2\6!\3\2\2\2\b*\3\2"+
		"\2\2\n\63\3\2\2\2\fE\3\2\2\2\16w\3\2\2\2\20\u00a3\3\2\2\2\22\u00e7\3\2"+
		"\2\2\24\u00e9\3\2\2\2\26\u00f3\3\2\2\2\30\31\5\4\3\2\31\3\3\2\2\2\32\36"+
		"\5\b\5\2\33\36\5\22\n\2\34\36\5\n\6\2\35\32\3\2\2\2\35\33\3\2\2\2\35\34"+
		"\3\2\2\2\36\37\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 \5\3\2\2\2!%\7\3\2\2"+
		"\"$\5\16\b\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&(\3\2\2\2\'%\3"+
		"\2\2\2()\7\4\2\2)\7\3\2\2\2*+\7\66\2\2+,\78\2\2,.\7\3\2\2-/\5\4\3\2.-"+
		"\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\7\4\2\2\61\t\3\2\2\2\62\64\5\24\13"+
		"\2\63\62\3\2\2\2\63\64\3\2\2\2\64\65\3\2\2\2\65\66\78\2\2\668\7\5\2\2"+
		"\679\5\f\7\28\67\3\2\2\289\3\2\2\29:\3\2\2\2:;\7\6\2\2;<\5\6\4\2<\13\3"+
		"\2\2\2=>\5\24\13\2>?\78\2\2?@\7\7\2\2@A\5\f\7\2AF\3\2\2\2BC\5\24\13\2"+
		"CD\78\2\2DF\3\2\2\2E=\3\2\2\2EB\3\2\2\2F\r\3\2\2\2GH\7.\2\2HI\7\5\2\2"+
		"IJ\5\20\t\2JK\7\6\2\2KL\5\16\b\2Lx\3\2\2\2MN\7.\2\2NO\7\5\2\2OP\5\20\t"+
		"\2PQ\7\6\2\2QR\5\16\b\2RS\7/\2\2ST\5\16\b\2Tx\3\2\2\2UV\7\60\2\2VX\7\5"+
		"\2\2WY\5\20\t\2XW\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z\\\7\b\2\2[]\5\20\t\2\\"+
		"[\3\2\2\2\\]\3\2\2\2]^\3\2\2\2^`\7\b\2\2_a\5\20\t\2`_\3\2\2\2`a\3\2\2"+
		"\2ab\3\2\2\2bc\7\6\2\2cx\5\16\b\2de\7\61\2\2ef\7\5\2\2fg\5\20\t\2gh\7"+
		"\6\2\2hi\5\16\b\2ix\3\2\2\2jk\7\64\2\2kl\5\20\t\2lm\7\b\2\2mx\3\2\2\2"+
		"no\7\62\2\2ox\7\b\2\2pq\7\63\2\2qx\7\b\2\2rs\5\20\t\2st\7\b\2\2tx\3\2"+
		"\2\2ux\5\22\n\2vx\5\6\4\2wG\3\2\2\2wM\3\2\2\2wU\3\2\2\2wd\3\2\2\2wj\3"+
		"\2\2\2wn\3\2\2\2wp\3\2\2\2wr\3\2\2\2wu\3\2\2\2wv\3\2\2\2x\17\3\2\2\2y"+
		"z\b\t\1\2z{\78\2\2{}\7\5\2\2|~\5\f\7\2}|\3\2\2\2}~\3\2\2\2~\177\3\2\2"+
		"\2\177\u00a4\7\6\2\2\u0080\u00a4\78\2\2\u0081\u00a4\7$\2\2\u0082\u00a4"+
		"\7,\2\2\u0083\u00a4\7-\2\2\u0084\u00a4\7*\2\2\u0085\u00a4\7%\2\2\u0086"+
		"\u00a4\7\67\2\2\u0087\u0088\t\2\2\2\u0088\u00a4\5\20\t\22\u0089\u008a"+
		"\t\3\2\2\u008a\u00a4\5\20\t\21\u008b\u008c\t\4\2\2\u008c\u00a4\5\20\t"+
		"\20\u008d\u008e\7\65\2\2\u008e\u009d\5\26\f\2\u008f\u0090\7\t\2\2\u0090"+
		"\u0091\5\20\t\2\u0091\u0092\7\n\2\2\u0092\u0094\3\2\2\2\u0093\u008f\3"+
		"\2\2\2\u0094\u0095\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u009a\3\2\2\2\u0097\u0099\7\22\2\2\u0098\u0097\3\2\2\2\u0099\u009c\3"+
		"\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009e\3\2\2\2\u009c"+
		"\u009a\3\2\2\2\u009d\u0093\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u00a4\3\2"+
		"\2\2\u009f\u00a0\7\5\2\2\u00a0\u00a1\5\20\t\2\u00a1\u00a2\7\6\2\2\u00a2"+
		"\u00a4\3\2\2\2\u00a3y\3\2\2\2\u00a3\u0080\3\2\2\2\u00a3\u0081\3\2\2\2"+
		"\u00a3\u0082\3\2\2\2\u00a3\u0083\3\2\2\2\u00a3\u0084\3\2\2\2\u00a3\u0085"+
		"\3\2\2\2\u00a3\u0086\3\2\2\2\u00a3\u0087\3\2\2\2\u00a3\u0089\3\2\2\2\u00a3"+
		"\u008b\3\2\2\2\u00a3\u008d\3\2\2\2\u00a3\u009f\3\2\2\2\u00a4\u00da\3\2"+
		"\2\2\u00a5\u00a6\f\16\2\2\u00a6\u00a7\t\5\2\2\u00a7\u00d9\5\20\t\17\u00a8"+
		"\u00a9\f\r\2\2\u00a9\u00aa\t\3\2\2\u00aa\u00d9\5\20\t\16\u00ab\u00ac\f"+
		"\f\2\2\u00ac\u00ad\t\6\2\2\u00ad\u00d9\5\20\t\r\u00ae\u00af\f\13\2\2\u00af"+
		"\u00b0\t\7\2\2\u00b0\u00d9\5\20\t\f\u00b1\u00b2\f\n\2\2\u00b2\u00b3\t"+
		"\b\2\2\u00b3\u00d9\5\20\t\13\u00b4\u00b5\f\t\2\2\u00b5\u00b6\7\36\2\2"+
		"\u00b6\u00d9\5\20\t\n\u00b7\u00b8\f\b\2\2\u00b8\u00b9\7\37\2\2\u00b9\u00d9"+
		"\5\20\t\t\u00ba\u00bb\f\7\2\2\u00bb\u00bc\7 \2\2\u00bc\u00d9\5\20\t\b"+
		"\u00bd\u00be\f\6\2\2\u00be\u00bf\7!\2\2\u00bf\u00d9\5\20\t\7\u00c0\u00c1"+
		"\f\5\2\2\u00c1\u00c2\7\"\2\2\u00c2\u00d9\5\20\t\6\u00c3\u00c4\f\4\2\2"+
		"\u00c4\u00c5\7#\2\2\u00c5\u00d9\5\20\t\4\u00c6\u00c7\f\35\2\2\u00c7\u00c8"+
		"\7\t\2\2\u00c8\u00c9\5\20\t\2\u00c9\u00ca\7\n\2\2\u00ca\u00d9\3\2\2\2"+
		"\u00cb\u00cc\f\34\2\2\u00cc\u00cd\7\13\2\2\u00cd\u00d9\78\2\2\u00ce\u00cf"+
		"\f\33\2\2\u00cf\u00d0\7\13\2\2\u00d0\u00d1\78\2\2\u00d1\u00d3\7\5\2\2"+
		"\u00d2\u00d4\5\f\7\2\u00d3\u00d2\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d5"+
		"\3\2\2\2\u00d5\u00d9\7\6\2\2\u00d6\u00d7\f\23\2\2\u00d7\u00d9\t\2\2\2"+
		"\u00d8\u00a5\3\2\2\2\u00d8\u00a8\3\2\2\2\u00d8\u00ab\3\2\2\2\u00d8\u00ae"+
		"\3\2\2\2\u00d8\u00b1\3\2\2\2\u00d8\u00b4\3\2\2\2\u00d8\u00b7\3\2\2\2\u00d8"+
		"\u00ba\3\2\2\2\u00d8\u00bd\3\2\2\2\u00d8\u00c0\3\2\2\2\u00d8\u00c3\3\2"+
		"\2\2\u00d8\u00c6\3\2\2\2\u00d8\u00cb\3\2\2\2\u00d8\u00ce\3\2\2\2\u00d8"+
		"\u00d6\3\2\2\2\u00d9\u00dc\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2"+
		"\2\2\u00db\21\3\2\2\2\u00dc\u00da\3\2\2\2\u00dd\u00de\5\24\13\2\u00de"+
		"\u00df\78\2\2\u00df\u00e0\7\b\2\2\u00e0\u00e8\3\2\2\2\u00e1\u00e2\5\24"+
		"\13\2\u00e2\u00e3\78\2\2\u00e3\u00e4\7#\2\2\u00e4\u00e5\5\20\t\2\u00e5"+
		"\u00e6\7\b\2\2\u00e6\u00e8\3\2\2\2\u00e7\u00dd\3\2\2\2\u00e7\u00e1\3\2"+
		"\2\2\u00e8\23\3\2\2\2\u00e9\u00ea\b\13\1\2\u00ea\u00eb\5\26\f\2\u00eb"+
		"\u00f0\3\2\2\2\u00ec\u00ed\f\4\2\2\u00ed\u00ef\7\22\2\2\u00ee\u00ec\3"+
		"\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1"+
		"\25\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f4\t\t\2\2\u00f4\27\3\2\2\2\27"+
		"\35\37%.\638EX\\`w}\u0095\u009a\u009d\u00a3\u00d3\u00d8\u00da\u00e7\u00f0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}