package com.ness.sourcecodeparser;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenRewriteStream;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.Tree;
import org.antlr.runtime.tree.TreeAdaptor;

import com.ness.plsql.parser.PLSQLLexer;
import com.ness.plsql.parser.PLSQLParser;
import com.ness.plsqlparser.PlSqlTokenStream;
import com.ness.plsqlparser.model.PlSqlElementList;
import com.ness.plsqlparser.parser.PlSqlScriptParser;
import com.ness.plsqlparser.tokens.PlSqlToken;
import com.ness.plsqlparser.tokens.TokenAs;
import com.ness.plsqlparser.tokens.TokenBegin;
import com.ness.plsqlparser.tokens.TokenComma;
import com.ness.plsqlparser.tokens.TokenCreate;
import com.ness.plsqlparser.tokens.TokenDeclare;
import com.ness.plsqlparser.tokens.TokenEnd;
import com.ness.plsqlparser.tokens.TokenEof;
import com.ness.plsqlparser.tokens.TokenIdentifier;
import com.ness.plsqlparser.tokens.TokenLeftParen;
import com.ness.plsqlparser.tokens.TokenNot;
import com.ness.plsqlparser.tokens.TokenNull;
import com.ness.plsqlparser.tokens.TokenObject;
import com.ness.plsqlparser.tokens.TokenOr;
import com.ness.plsqlparser.tokens.TokenReplace;
import com.ness.plsqlparser.tokens.TokenRightParen;
import com.ness.plsqlparser.tokens.TokenSemicolon;
import com.ness.plsqlparser.tokens.TokenSeparator;
import com.ness.plsqlparser.tokens.TokenSolidus;
import com.ness.plsqlparser.tokens.TokenType;
import com.ness.plsqlparser.tokens.TokenUnsignedInteger;

public class SourceCodeParser {

	private PlSqlElementList ast;

	public PlSqlElementList parseSource(String source) {
	    PLSQLLexer lexer = new PLSQLLexer(new ANTLRStringStream(source));
	    PLSQLParser parser = new PLSQLParser(new TokenRewriteStream(lexer));

		PlSqlTokenStream plSqlTokens = null;
		try {
			PLSQLParser.seq_of_statements_return psrReturn = parser.seq_of_statements();
			TreeAdaptor adaptor = new PlSqlTreeParserAdaptor();
			//parser.setTreeAdaptor(adaptor);  //TODO: prepared for variant with tree adaptor
			//PLSQLParser.compilation_unit_return psrReturn = parser.compilation_unit();
			//PLSQLParser.create_type_return psrReturn = parser.create_type();
			//System.out.println(parser.getTokenStream().toString());
			plSqlTokens = translateTokens(parser.getTokenStream());

			for (PlSqlToken pst : plSqlTokens) {
				System.out.println(pst.toString());
			}
			//parser.setTreeAdaptor(adaptor);
			//PLSQLParser.sql_script_return psrReturn = parser.sql_script();
			//PLSQLParser.swallow_to_semi_return psrReturn = parser.swallow_to_semi();
			Tree tree = (CommonTree)psrReturn.getTree();
			System.out.println(tree.toString());

		} catch (RecognitionException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
/*		TreeAdaptor adaptor = new PlSqlTreeParserAdaptor();

	    try {
		    parser.setTreeAdaptor(adaptor);
		    //PLSQLParser.sql_script_return psrReturn = parser.sql_script();
		    PLSQLParser.swallow_to_semi_return psrReturn = parser.swallow_to_semi();
		    Tree tree = (CommonTree)psrReturn.getTree();      */
		    ast = constructAST(plSqlTokens);
/*			    } catch (RecognitionException e) {
		    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	    }
  */
        return ast;
    }

	private PlSqlTokenStream translateTokens(TokenStream tokenStream) {
		PlSqlTokenStream plSqlTokens = new PlSqlTokenStream();
		for (int i = 0; i <=tokenStream.size()-1; i++) {
			Token token = tokenStream.get(i);
			final PlSqlToken plSqlToken = translateToken(token);
			plSqlTokens.add(plSqlToken);
		}
		return plSqlTokens;
	}

	private PlSqlToken translateToken(Token token) {
		if (token != null) {
			final String tokenText = token.getText();
			switch (token.getType()) {
				case PLSQLLexer.SQL92_RESERVED_BEGIN : return new TokenBegin(tokenText);
				case PLSQLLexer.SQL92_RESERVED_END : return new TokenEnd(tokenText);
				case PLSQLLexer.SQL92_RESERVED_NULL : return new TokenNull(tokenText);
				case PLSQLLexer.SQL92_RESERVED_DECLARE : return new TokenDeclare(tokenText);
				case PLSQLLexer.SQL92_RESERVED_CREATE : return new TokenCreate(tokenText);
				case PLSQLLexer.SQL92_RESERVED_OR : return new TokenOr(tokenText);
				case PLSQLLexer.SQL92_RESERVED_AS : return new TokenAs(tokenText);
				case PLSQLLexer.SQL92_RESERVED_NOT : return new TokenNot(tokenText);
				case PLSQLLexer.REGULAR_ID : return translateRegularId(tokenText);
				case PLSQLLexer.SEMICOLON : return new TokenSemicolon();
				case PLSQLLexer.SEPARATOR : return new TokenSeparator(tokenText);
				case PLSQLLexer.LEFT_PAREN : return new TokenLeftParen();
				case PLSQLLexer.RIGHT_PAREN : return new TokenRightParen();
				case PLSQLLexer.EOF : return new TokenEof();
				case PLSQLLexer.COMMA : return new TokenComma();
				case PLSQLLexer.UNSIGNED_INTEGER : return new TokenUnsignedInteger(tokenText);
				case PLSQLLexer.SOLIDUS : return new TokenSolidus();
			}
			return new PlSqlToken(tokenText);
		} else {
			return null;
		}
	}

	private PlSqlToken translateRegularId(String tokenText) {
		switch (tokenText.toLowerCase()) {
			case "replace": return new TokenReplace(tokenText);
			case "type": return new TokenType(tokenText);
			case "object": return new TokenObject(tokenText);
			default: return new TokenIdentifier(tokenText);
		}
	}

	private PlSqlElementList constructAST(PlSqlTokenStream plSqlTokens) {
		plSqlTokens.reset();
		PlSqlScriptParser parser = new PlSqlScriptParser(plSqlTokens);
		PlSqlElementList lAST = parser.parse();
		return lAST;
	}

	private class PlSqlTreeParserAdaptor extends CommonTreeAdaptor implements TreeAdaptor {
		@Override
		public Object create(Token payload) {
			//return super.create(payload);
			if (payload != null) {
				switch (payload.getType()) {
					case PLSQLLexer.SQL92_RESERVED_BEGIN : return new TokenBegin(payload.getText());
					case PLSQLLexer.SQL92_RESERVED_NULL : return new TokenNull(payload.getText());
					case PLSQLLexer.SQL92_RESERVED_DECLARE : return new TokenDeclare(payload.getText());
				}
				return new PlSqlToken(payload.getText());
			} else {
				return new PlSqlToken("nil");
			}
		}

		@Override
		public void addChild(Object tree, Object child) {
			//super.addChild(tree, child);
			((PlSqlToken)tree).addChild((PlSqlToken) child);
			if (tree != null)
				System.out.println("tree: " + tree.toString());
			if (child != null)
				System.out.println("child: " + child.toString());
		}
	}
}
