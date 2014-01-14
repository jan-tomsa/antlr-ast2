package com.ness.astdemo.main;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import com.ness.antlrdemo.parser.TLexer;
import com.ness.antlrdemo.parser.TParser;
import com.ness.antlrdemo.parser.TTree;

/**
 * Hello world!
 *
 */
public class App 
{

	private String src;

	public App(String src) {
		this.src = src;
	}

	public static void main( String[] args )
    {
	    App app = new App("1+1;\nKeyser Soze;\n\"Hello world\";");
	    app.execute();
    }

	private void execute() {
		TLexer lexer = new TLexer(new ANTLRStringStream(src));
		TParser parser = new TParser(new CommonTokenStream(lexer));

		//walk(parser);

		walkMore(lexer);

	}

	private void walkMore(TLexer lexer) {
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
	}

	private void printTree(CommonTree tree, int indent) {
		if (tree != null) {
			StringBuilder sb = new StringBuilder(indent);
			if (tree.getParent() == null) {
				System.out.println(sb.toString() + tree.getText());
			}
			appendNTimes(sb, indent, "  ");
			if (tree.getChildCount() > 0) {
				for (int i=0; i < tree.getChildCount(); i++) {
					System.out.println(sb.toString() + tree.getText());
					printTree((CommonTree) tree.getChild(i), indent+1);
				}
			} else {
				System.out.println(sb.toString() + tree.getText());
			}
		}
	}

	private void appendNTimes(StringBuilder sb, int indent, String filler) {
		for (int i=1; i<=indent; i++)
			sb.append(filler);
	}

	private void walk(TParser parser) {
		try {
			TParser.a_return psrReturn = parser.a();
			Tree t = (Tree)psrReturn.getTree();
			TTree walker = new TTree(new CommonTreeNodeStream(t));
			walker.a();
		} catch (RecognitionException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}
}
