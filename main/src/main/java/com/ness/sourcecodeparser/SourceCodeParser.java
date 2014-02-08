package com.ness.sourcecodeparser;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.Tree;
import org.antlr.runtime.tree.TreeAdaptor;

import com.ness.plsql.parser.PLSQLLexer;
import com.ness.plsql.parser.PLSQLParser;
import com.ness.plsqlparser.PlSqlTokenStream;
import com.ness.plsqlparser.model.PlSqlBlock;
import com.ness.plsqlparser.model.PlSqlCommandNull;
import com.ness.plsqlparser.model.PlSqlElementList;
import com.ness.plsqlparser.tokens.*;

public class SourceCodeParser {

	private PlSqlElementList ast;

	public PlSqlElementList parseSource(String source) {
	    PLSQLLexer lexer = new PLSQLLexer(new ANTLRStringStream(source));
	    PLSQLParser parser = new PLSQLParser(new TokenRewriteStream(lexer));

		PlSqlTokenStream plSqlTokens = null;
		try {
			PLSQLParser.seq_of_statements_return psrReturn = parser.seq_of_statements();
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
				case PLSQLLexer.REGULAR_ID : return new TokenIdentifier(tokenText);
				case PLSQLLexer.SEMICOLON : return new TokenSemicolon();
				case PLSQLLexer.SEPARATOR : return new TokenSeparator(tokenText);
				case PLSQLLexer.EOF : return new TokenEof();
			}
			return new PlSqlToken(tokenText);
		} else {
			return null;
		}
	}

	private PlSqlElementList constructAST(PlSqlTokenStream plSqlTokens) {
		PlSqlElementList lAST = new PlSqlElementList();
		if (plSqlTokens != null) {
			plSqlTokens.reset();
			for (PlSqlToken token : plSqlTokens) {
				if (token.getType() == TType.BEGIN)
					lAST.add(new PlSqlBlock() {{
						this.addCommand(new PlSqlCommandNull("null"));
					}}
					);
			}
			//lAST.add();
		}
		return lAST;
	}

	private class PlSqlTreeParserAdaptor extends CommonTreeAdaptor implements TreeAdaptor {
		@Override
		public Object create(Token payload) {
			if (payload != null) {
				switch (payload.getType()) {
					case PLSQLLexer.SQL92_RESERVED_BEGIN : return new TokenBegin(payload.getText());
					case PLSQLLexer.SQL92_RESERVED_NULL : return new TokenNull(payload.getText());
					case PLSQLLexer.SQL92_RESERVED_DECLARE : return new TokenDeclare(payload.getText());
				}
				return new PlSqlToken(payload.getText());
			} else {
				return null;
			}
		}
	}
}
