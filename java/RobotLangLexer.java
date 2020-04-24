// Generated from RobotLang.g4 by ANTLR 4.7.2

        import java.util.Map;
        import java.util.HashMap;
        import java.util.LinkedHashMap;
        import java.util.Set;
        import java.util.HashSet;
        import java.util.ArrayList;


import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RobotLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, LINE_COMMENT=5, COMMENT=6, WHITESPACE=7, 
		TAB=8, NEWLINE=9, SKIPS=10, FUNC=11, WHILE=12, FOR=13, BREAK=14, ONE_OF=15, 
		ANY_OF=16, CASE=17, IF=18, ELSE=19, MOVE_WORD=20, STOP_WORD=21, PRINT_WORD=22, 
		OR=23, AND=24, EQUAL_OR_GREATER=25, EQUAL_OR_SMALLER=26, FUNC_TYPE_ASSIGN=27, 
		EQUALS_SYMBOL=28, GREATER_THAN=29, SMALLER_THAN=30, EQUALS=31, DIFF_FROM=32, 
		DIV=33, MULT=34, PLUS=35, MINUS=36, COMMA=37, COLON=38, END=39, END_FUNCTION=40, 
		END_FOR=41, END_WHILE=42, END_CHOOSE=43, END_IF=44, RETURN_WORD=45, IN=46, 
		INTWORD=47, STRINGWORD=48, REALWORD=49, IMPORT_WORD=50, TRUE=51, FALSE=52, 
		IDENTIFIER=53, STRING=54, REAL=55, INTEGER=56, ERROR=57;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "LINE_COMMENT", "COMMENT", "WHITESPACE", 
			"TAB", "NEWLINE", "SKIPS", "FUNC", "WHILE", "FOR", "BREAK", "ONE_OF", 
			"ANY_OF", "CASE", "IF", "ELSE", "MOVE_WORD", "STOP_WORD", "PRINT_WORD", 
			"OR", "AND", "EQUAL_OR_GREATER", "EQUAL_OR_SMALLER", "FUNC_TYPE_ASSIGN", 
			"EQUALS_SYMBOL", "GREATER_THAN", "SMALLER_THAN", "EQUALS", "DIFF_FROM", 
			"DIV", "MULT", "PLUS", "MINUS", "COMMA", "COLON", "END", "END_FUNCTION", 
			"END_FOR", "END_WHILE", "END_CHOOSE", "END_IF", "RETURN_WORD", "IN", 
			"INTWORD", "STRINGWORD", "REALWORD", "IMPORT_WORD", "TRUE", "FALSE", 
			"IDENTIFIER", "STRING", "REAL", "INTEGER", "ERROR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'['", "']'", null, null, null, null, "'\n'", null, 
			"'function'", "'while'", "'for'", "'break'", "'one of'", "'any of'", 
			"'case'", "'if'", "'else'", "'move'", "'stop'", "'print'", "'or'", "'and'", 
			"'>='", "'<='", "'->'", "'='", "'>'", "'<'", "'equals'", "'different from'", 
			"'/'", "'*'", "'+'", "'-'", "','", "':'", "'end'", "'endfunction'", "'endfor'", 
			"'endwhile'", "'endchoose'", "'endif'", "'return'", "'in'", "'integer'", 
			"'string'", "'real'", "'import'", "'true'", "'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "LINE_COMMENT", "COMMENT", "WHITESPACE", 
			"TAB", "NEWLINE", "SKIPS", "FUNC", "WHILE", "FOR", "BREAK", "ONE_OF", 
			"ANY_OF", "CASE", "IF", "ELSE", "MOVE_WORD", "STOP_WORD", "PRINT_WORD", 
			"OR", "AND", "EQUAL_OR_GREATER", "EQUAL_OR_SMALLER", "FUNC_TYPE_ASSIGN", 
			"EQUALS_SYMBOL", "GREATER_THAN", "SMALLER_THAN", "EQUALS", "DIFF_FROM", 
			"DIV", "MULT", "PLUS", "MINUS", "COMMA", "COLON", "END", "END_FUNCTION", 
			"END_FOR", "END_WHILE", "END_CHOOSE", "END_IF", "RETURN_WORD", "IN", 
			"INTWORD", "STRINGWORD", "REALWORD", "IMPORT_WORD", "TRUE", "FALSE", 
			"IDENTIFIER", "STRING", "REAL", "INTEGER", "ERROR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public RobotLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RobotLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2;\u01af\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\3\2\3\2\3\3\3\3\3"+
		"\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\7\6\u0082\n\6\f\6\16\6\u0085\13\6\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\7\7\u008f\n\7\f\7\16\7\u0092\13\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\b\6\b\u009a\n\b\r\b\16\b\u009b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\5\13\u00ab\n\13\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37"+
		"\3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3\""+
		"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)"+
		"\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3+\3,\3,"+
		"\3,\3,\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3/\3/"+
		"\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3"+
		"\61\3\61\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3"+
		"\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\66\6\66\u018c"+
		"\n\66\r\66\16\66\u018d\3\66\7\66\u0191\n\66\f\66\16\66\u0194\13\66\3\67"+
		"\3\67\7\67\u0198\n\67\f\67\16\67\u019b\13\67\3\67\3\67\38\38\38\68\u01a2"+
		"\n8\r8\168\u01a3\39\59\u01a7\n9\39\69\u01aa\n9\r9\169\u01ab\3:\3:\5\u0083"+
		"\u0090\u0199\2;\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64"+
		"g\65i\66k\67m8o9q:s;\3\2\7\4\2\17\17\"\"\3\2\13\13\5\2C\\aac|\6\2\62;"+
		"C\\aac|\3\2\62;\2\u01b9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2"+
		"Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3"+
		"\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2"+
		"\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\3u\3\2\2\2\5"+
		"w\3\2\2\2\7y\3\2\2\2\t{\3\2\2\2\13}\3\2\2\2\r\u008a\3\2\2\2\17\u0099\3"+
		"\2\2\2\21\u009f\3\2\2\2\23\u00a3\3\2\2\2\25\u00aa\3\2\2\2\27\u00ae\3\2"+
		"\2\2\31\u00b7\3\2\2\2\33\u00bd\3\2\2\2\35\u00c1\3\2\2\2\37\u00c7\3\2\2"+
		"\2!\u00ce\3\2\2\2#\u00d5\3\2\2\2%\u00da\3\2\2\2\'\u00dd\3\2\2\2)\u00e2"+
		"\3\2\2\2+\u00e7\3\2\2\2-\u00ec\3\2\2\2/\u00f2\3\2\2\2\61\u00f5\3\2\2\2"+
		"\63\u00f9\3\2\2\2\65\u00fc\3\2\2\2\67\u00ff\3\2\2\29\u0102\3\2\2\2;\u0104"+
		"\3\2\2\2=\u0106\3\2\2\2?\u0108\3\2\2\2A\u010f\3\2\2\2C\u011e\3\2\2\2E"+
		"\u0120\3\2\2\2G\u0122\3\2\2\2I\u0124\3\2\2\2K\u0126\3\2\2\2M\u0128\3\2"+
		"\2\2O\u012a\3\2\2\2Q\u012e\3\2\2\2S\u013a\3\2\2\2U\u0141\3\2\2\2W\u014a"+
		"\3\2\2\2Y\u0154\3\2\2\2[\u015a\3\2\2\2]\u0161\3\2\2\2_\u0164\3\2\2\2a"+
		"\u016c\3\2\2\2c\u0173\3\2\2\2e\u0178\3\2\2\2g\u017f\3\2\2\2i\u0184\3\2"+
		"\2\2k\u018b\3\2\2\2m\u0195\3\2\2\2o\u019e\3\2\2\2q\u01a6\3\2\2\2s\u01ad"+
		"\3\2\2\2uv\7*\2\2v\4\3\2\2\2wx\7+\2\2x\6\3\2\2\2yz\7]\2\2z\b\3\2\2\2{"+
		"|\7_\2\2|\n\3\2\2\2}~\7\61\2\2~\177\7\61\2\2\177\u0083\3\2\2\2\u0080\u0082"+
		"\13\2\2\2\u0081\u0080\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0084\3\2\2\2"+
		"\u0083\u0081\3\2\2\2\u0084\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0087"+
		"\7\f\2\2\u0087\u0088\3\2\2\2\u0088\u0089\b\6\2\2\u0089\f\3\2\2\2\u008a"+
		"\u008b\7\61\2\2\u008b\u008c\7,\2\2\u008c\u0090\3\2\2\2\u008d\u008f\13"+
		"\2\2\2\u008e\u008d\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u0091\3\2\2\2\u0090"+
		"\u008e\3\2\2\2\u0091\u0093\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0094\7,"+
		"\2\2\u0094\u0095\7\61\2\2\u0095\u0096\3\2\2\2\u0096\u0097\b\7\2\2\u0097"+
		"\16\3\2\2\2\u0098\u009a\t\2\2\2\u0099\u0098\3\2\2\2\u009a\u009b\3\2\2"+
		"\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e"+
		"\b\b\2\2\u009e\20\3\2\2\2\u009f\u00a0\t\3\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u00a2\b\t\2\2\u00a2\22\3\2\2\2\u00a3\u00a4\7\f\2\2\u00a4\u00a5\3\2\2"+
		"\2\u00a5\u00a6\b\n\2\2\u00a6\24\3\2\2\2\u00a7\u00ab\5\r\7\2\u00a8\u00ab"+
		"\5\13\6\2\u00a9\u00ab\5\17\b\2\u00aa\u00a7\3\2\2\2\u00aa\u00a8\3\2\2\2"+
		"\u00aa\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\b\13\2\2\u00ad\26"+
		"\3\2\2\2\u00ae\u00af\7h\2\2\u00af\u00b0\7w\2\2\u00b0\u00b1\7p\2\2\u00b1"+
		"\u00b2\7e\2\2\u00b2\u00b3\7v\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7q\2\2"+
		"\u00b5\u00b6\7p\2\2\u00b6\30\3\2\2\2\u00b7\u00b8\7y\2\2\u00b8\u00b9\7"+
		"j\2\2\u00b9\u00ba\7k\2\2\u00ba\u00bb\7n\2\2\u00bb\u00bc\7g\2\2\u00bc\32"+
		"\3\2\2\2\u00bd\u00be\7h\2\2\u00be\u00bf\7q\2\2\u00bf\u00c0\7t\2\2\u00c0"+
		"\34\3\2\2\2\u00c1\u00c2\7d\2\2\u00c2\u00c3\7t\2\2\u00c3\u00c4\7g\2\2\u00c4"+
		"\u00c5\7c\2\2\u00c5\u00c6\7m\2\2\u00c6\36\3\2\2\2\u00c7\u00c8\7q\2\2\u00c8"+
		"\u00c9\7p\2\2\u00c9\u00ca\7g\2\2\u00ca\u00cb\7\"\2\2\u00cb\u00cc\7q\2"+
		"\2\u00cc\u00cd\7h\2\2\u00cd \3\2\2\2\u00ce\u00cf\7c\2\2\u00cf\u00d0\7"+
		"p\2\2\u00d0\u00d1\7{\2\2\u00d1\u00d2\7\"\2\2\u00d2\u00d3\7q\2\2\u00d3"+
		"\u00d4\7h\2\2\u00d4\"\3\2\2\2\u00d5\u00d6\7e\2\2\u00d6\u00d7\7c\2\2\u00d7"+
		"\u00d8\7u\2\2\u00d8\u00d9\7g\2\2\u00d9$\3\2\2\2\u00da\u00db\7k\2\2\u00db"+
		"\u00dc\7h\2\2\u00dc&\3\2\2\2\u00dd\u00de\7g\2\2\u00de\u00df\7n\2\2\u00df"+
		"\u00e0\7u\2\2\u00e0\u00e1\7g\2\2\u00e1(\3\2\2\2\u00e2\u00e3\7o\2\2\u00e3"+
		"\u00e4\7q\2\2\u00e4\u00e5\7x\2\2\u00e5\u00e6\7g\2\2\u00e6*\3\2\2\2\u00e7"+
		"\u00e8\7u\2\2\u00e8\u00e9\7v\2\2\u00e9\u00ea\7q\2\2\u00ea\u00eb\7r\2\2"+
		"\u00eb,\3\2\2\2\u00ec\u00ed\7r\2\2\u00ed\u00ee\7t\2\2\u00ee\u00ef\7k\2"+
		"\2\u00ef\u00f0\7p\2\2\u00f0\u00f1\7v\2\2\u00f1.\3\2\2\2\u00f2\u00f3\7"+
		"q\2\2\u00f3\u00f4\7t\2\2\u00f4\60\3\2\2\2\u00f5\u00f6\7c\2\2\u00f6\u00f7"+
		"\7p\2\2\u00f7\u00f8\7f\2\2\u00f8\62\3\2\2\2\u00f9\u00fa\7@\2\2\u00fa\u00fb"+
		"\7?\2\2\u00fb\64\3\2\2\2\u00fc\u00fd\7>\2\2\u00fd\u00fe\7?\2\2\u00fe\66"+
		"\3\2\2\2\u00ff\u0100\7/\2\2\u0100\u0101\7@\2\2\u01018\3\2\2\2\u0102\u0103"+
		"\7?\2\2\u0103:\3\2\2\2\u0104\u0105\7@\2\2\u0105<\3\2\2\2\u0106\u0107\7"+
		">\2\2\u0107>\3\2\2\2\u0108\u0109\7g\2\2\u0109\u010a\7s\2\2\u010a\u010b"+
		"\7w\2\2\u010b\u010c\7c\2\2\u010c\u010d\7n\2\2\u010d\u010e\7u\2\2\u010e"+
		"@\3\2\2\2\u010f\u0110\7f\2\2\u0110\u0111\7k\2\2\u0111\u0112\7h\2\2\u0112"+
		"\u0113\7h\2\2\u0113\u0114\7g\2\2\u0114\u0115\7t\2\2\u0115\u0116\7g\2\2"+
		"\u0116\u0117\7p\2\2\u0117\u0118\7v\2\2\u0118\u0119\7\"\2\2\u0119\u011a"+
		"\7h\2\2\u011a\u011b\7t\2\2\u011b\u011c\7q\2\2\u011c\u011d\7o\2\2\u011d"+
		"B\3\2\2\2\u011e\u011f\7\61\2\2\u011fD\3\2\2\2\u0120\u0121\7,\2\2\u0121"+
		"F\3\2\2\2\u0122\u0123\7-\2\2\u0123H\3\2\2\2\u0124\u0125\7/\2\2\u0125J"+
		"\3\2\2\2\u0126\u0127\7.\2\2\u0127L\3\2\2\2\u0128\u0129\7<\2\2\u0129N\3"+
		"\2\2\2\u012a\u012b\7g\2\2\u012b\u012c\7p\2\2\u012c\u012d\7f\2\2\u012d"+
		"P\3\2\2\2\u012e\u012f\7g\2\2\u012f\u0130\7p\2\2\u0130\u0131\7f\2\2\u0131"+
		"\u0132\7h\2\2\u0132\u0133\7w\2\2\u0133\u0134\7p\2\2\u0134\u0135\7e\2\2"+
		"\u0135\u0136\7v\2\2\u0136\u0137\7k\2\2\u0137\u0138\7q\2\2\u0138\u0139"+
		"\7p\2\2\u0139R\3\2\2\2\u013a\u013b\7g\2\2\u013b\u013c\7p\2\2\u013c\u013d"+
		"\7f\2\2\u013d\u013e\7h\2\2\u013e\u013f\7q\2\2\u013f\u0140\7t\2\2\u0140"+
		"T\3\2\2\2\u0141\u0142\7g\2\2\u0142\u0143\7p\2\2\u0143\u0144\7f\2\2\u0144"+
		"\u0145\7y\2\2\u0145\u0146\7j\2\2\u0146\u0147\7k\2\2\u0147\u0148\7n\2\2"+
		"\u0148\u0149\7g\2\2\u0149V\3\2\2\2\u014a\u014b\7g\2\2\u014b\u014c\7p\2"+
		"\2\u014c\u014d\7f\2\2\u014d\u014e\7e\2\2\u014e\u014f\7j\2\2\u014f\u0150"+
		"\7q\2\2\u0150\u0151\7q\2\2\u0151\u0152\7u\2\2\u0152\u0153\7g\2\2\u0153"+
		"X\3\2\2\2\u0154\u0155\7g\2\2\u0155\u0156\7p\2\2\u0156\u0157\7f\2\2\u0157"+
		"\u0158\7k\2\2\u0158\u0159\7h\2\2\u0159Z\3\2\2\2\u015a\u015b\7t\2\2\u015b"+
		"\u015c\7g\2\2\u015c\u015d\7v\2\2\u015d\u015e\7w\2\2\u015e\u015f\7t\2\2"+
		"\u015f\u0160\7p\2\2\u0160\\\3\2\2\2\u0161\u0162\7k\2\2\u0162\u0163\7p"+
		"\2\2\u0163^\3\2\2\2\u0164\u0165\7k\2\2\u0165\u0166\7p\2\2\u0166\u0167"+
		"\7v\2\2\u0167\u0168\7g\2\2\u0168\u0169\7i\2\2\u0169\u016a\7g\2\2\u016a"+
		"\u016b\7t\2\2\u016b`\3\2\2\2\u016c\u016d\7u\2\2\u016d\u016e\7v\2\2\u016e"+
		"\u016f\7t\2\2\u016f\u0170\7k\2\2\u0170\u0171\7p\2\2\u0171\u0172\7i\2\2"+
		"\u0172b\3\2\2\2\u0173\u0174\7t\2\2\u0174\u0175\7g\2\2\u0175\u0176\7c\2"+
		"\2\u0176\u0177\7n\2\2\u0177d\3\2\2\2\u0178\u0179\7k\2\2\u0179\u017a\7"+
		"o\2\2\u017a\u017b\7r\2\2\u017b\u017c\7q\2\2\u017c\u017d\7t\2\2\u017d\u017e"+
		"\7v\2\2\u017ef\3\2\2\2\u017f\u0180\7v\2\2\u0180\u0181\7t\2\2\u0181\u0182"+
		"\7w\2\2\u0182\u0183\7g\2\2\u0183h\3\2\2\2\u0184\u0185\7h\2\2\u0185\u0186"+
		"\7c\2\2\u0186\u0187\7n\2\2\u0187\u0188\7u\2\2\u0188\u0189\7g\2\2\u0189"+
		"j\3\2\2\2\u018a\u018c\t\4\2\2\u018b\u018a\3\2\2\2\u018c\u018d\3\2\2\2"+
		"\u018d\u018b\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u0192\3\2\2\2\u018f\u0191"+
		"\t\5\2\2\u0190\u018f\3\2\2\2\u0191\u0194\3\2\2\2\u0192\u0190\3\2\2\2\u0192"+
		"\u0193\3\2\2\2\u0193l\3\2\2\2\u0194\u0192\3\2\2\2\u0195\u0199\7)\2\2\u0196"+
		"\u0198\13\2\2\2\u0197\u0196\3\2\2\2\u0198\u019b\3\2\2\2\u0199\u019a\3"+
		"\2\2\2\u0199\u0197\3\2\2\2\u019a\u019c\3\2\2\2\u019b\u0199\3\2\2\2\u019c"+
		"\u019d\7)\2\2\u019dn\3\2\2\2\u019e\u019f\5q9\2\u019f\u01a1\7\60\2\2\u01a0"+
		"\u01a2\t\6\2\2\u01a1\u01a0\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a1\3\2"+
		"\2\2\u01a3\u01a4\3\2\2\2\u01a4p\3\2\2\2\u01a5\u01a7\7/\2\2\u01a6\u01a5"+
		"\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a9\3\2\2\2\u01a8\u01aa\t\6\2\2\u01a9"+
		"\u01a8\3\2\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01a9\3\2\2\2\u01ab\u01ac\3\2"+
		"\2\2\u01acr\3\2\2\2\u01ad\u01ae\13\2\2\2\u01aet\3\2\2\2\r\2\u0083\u0090"+
		"\u009b\u00aa\u018d\u0192\u0199\u01a3\u01a6\u01ab\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}