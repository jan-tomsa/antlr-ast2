package com.ness.astdemo.main;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;

import com.ness.antlrdemo.parser.TLexer;
import com.ness.antlrdemo.parser.TParser;

/**
 * Hello world!
 *
 */
public class App 
{
	private String src;
	private String result;

	public App(String src) {
		this.src = src;
	}

	public static void main( String[] args )
    {
	    App app = new App("1+2+4;\n"
			             +"Keyser Soze;\n"
			             +"\"Hello world\";");
	    System.out.println(app.execute());
    }

	String execute() {
		TLexer lexer = new TLexer(new ANTLRStringStream(src));
		TParser parser = new TParser(new CommonTokenStream(lexer));

		result = "";
		return walkMore(lexer);
	}

	private String walkMore(TLexer lexer) {
		TokenRewriteStream tokens = new TokenRewriteStream(lexer);
		TParser grammar = new TParser(tokens);
		// The AST Magic
		TreeAdaptor adaptor = new CommonTreeAdaptor() {
			public Object create(Token payload) {
				return new MyAST(payload);
			}
		};
		// Hooking Things Together
		try {
			grammar.setTreeAdaptor(adaptor);
			TParser.a_return psrReturn = grammar.a();
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
					//System.out.println(sb.toString() + tree.getText());
					println(sb.toString() + ((MyAST) tree).eval());
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
