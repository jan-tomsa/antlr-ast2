package com.ness.astdemo.main;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenRewriteStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;

import com.ness.plsql.parser.PLSQLLexer;
import com.ness.plsql.parser.PLSQLParser;

public class PLSQLParseRunner {

	private String source;
	private String result;

	public PLSQLParseRunner(String isource) {
		source = isource;
	}

	public static void main( String[] args )
	{
		String sourceCode = "";
		PLSQLParseRunner runner = new PLSQLParseRunner(sourceCode);
		System.out.println(runner.execute());
	}

	String execute() {
		PLSQLLexer lexer = new PLSQLLexer(new ANTLRStringStream(source));
		PLSQLParser grammar = new PLSQLParser(new TokenRewriteStream(lexer));
		// The AST Magic
		TreeAdaptor adaptor = new CommonTreeAdaptor() {
			public Object create(Token payload) {
				return new MyAST(payload);
			}
		};
		// Hooking Things Together
		try {
			grammar.setTreeAdaptor(adaptor);
			//PLSQLParser.sql_script_return psrReturn = grammar.sql_script();
			PLSQLParser.swallow_to_semi_return psrReturn = grammar.swallow_to_semi();
			CommonTree tree = (CommonTree)psrReturn.getTree();
			printTree(tree, 0);
		} catch (RecognitionException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		return result;
	}

	private void printTree(CommonTree tree, int indent) {
		if (tree != null) {
			StringBuilder sb = new StringBuilder(indent);
			if (tree.getParent() == null) {
				println(sb.toString() + tree.getText());
			}
			appendNTimes(sb, indent, "  ");
			if (tree.getChildCount() > 0) {
				for (int i=0; i < tree.getChildCount(); i++) {
					println(sb.toString() + tree.getText());
					//println(sb.toString() + ((MyAST) tree).eval());
					printTree((CommonTree) tree.getChild(i), indent+1);
				}
			} else {
				println(sb.toString() + tree.getText());
			}
		}
	}

	private void println(String s) {
		result += s + "\n";
	}

	private void appendNTimes(StringBuilder sb, int indent, String filler) {
		for (int i=1; i<=indent; i++)
			sb.append(filler);
	}
}