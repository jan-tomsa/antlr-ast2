package com.ness.plsqlparser.parser;

import com.ness.plsqlparser.PlSqlTokenStream;
import com.ness.plsqlparser.model.PlSqlBlock;
import com.ness.plsqlparser.model.PlSqlCommand;
import com.ness.plsqlparser.model.PlSqlDeclaration;
import com.ness.plsqlparser.model.PlSqlElement;
import com.ness.plsqlparser.tokens.TType;

public class PlSqlBlockParser extends PlSqlParser {
    private PlSqlBlock block;
    //private List<PlSqlCommand> commands;
    //private List<PlSqlDeclaration> declarations;

    public PlSqlBlockParser(PlSqlTokenStream tokens) {
        super(tokens);
        block = new PlSqlBlock();
        //commands = new ArrayList<PlSqlCommand>();
        //declarations = new ArrayList<PlSqlDeclaration>();
    }

    @Override
    public PlSqlElement parse() {
        valid = false;
	    block = new PlSqlBlock();
	    if (tokens.previousToken().getType() == TType.DECLARE)
		    try {
			    processDeclarations();
		    } catch (PlSqlDeclarationParser.MissingDeclarationException e) {
			    valid = false;
			    return block;
		    }
	    if (tokens.currentToken().getType() == TType.BEGIN)
		    tokens.swallowCurrent();
	    while (tokens.currentToken().getType() != TType.END) {
		    System.out.println("PlSqlBlockParser#block - tokens." + tokens.toString());
		    if (tokens.currentToken().getType() == TType.SEPARATOR)
			    tokens.swallowCurrent();
		    else {
				PlSqlCommandParser parser = new PlSqlCommandParser(tokens);
				PlSqlCommand command = parser.parse();
				block.addCommand(command);
		    }
		}
	    valid = !block.getCommands().isEmpty();
        parsed = true;
        return block;
    }

	private void processDeclarations() throws PlSqlDeclarationParser.MissingDeclarationException {
		if (tokens.currentToken().getType() == TType.DECLARE)
			tokens.swallowCurrent();
		while (tokens.currentToken().getType() != TType.BEGIN) {
			System.out.println("PlSqlBlockParser#declarations#1 - tokens." + tokens.toString());
			swallowSeparators();
			System.out.println("PlSqlBlockParser#declarations#2 - tokens." + tokens.toString());
			PlSqlDeclarationParser parser = new PlSqlDeclarationParser(tokens);
			PlSqlDeclaration declaration = parser.parse();
			System.out.println("PlSqlBlockParser#declarations#3 - tokens." + tokens.toString());
			swallowSeparators();
			System.out.println("PlSqlBlockParser#declarations#4 - tokens." + tokens.toString());
			block.addDeclaration(declaration);
		}
	}

	private void swallowSeparators() {
		while (tokens.currentToken().getType() == TType.SEPARATOR) tokens.swallowCurrent();
	}
}
