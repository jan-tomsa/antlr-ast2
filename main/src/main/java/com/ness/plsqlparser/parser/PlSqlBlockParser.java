package com.ness.plsqlparser.parser;

import java.util.ArrayList;
import java.util.List;

import com.ness.plsqlparser.model.PlSqlBlock;
import com.ness.plsqlparser.model.PlSqlCommand;
import com.ness.plsqlparser.model.PlSqlDeclaration;
import com.ness.plsqlparser.model.PlSqlElement;
import com.ness.plsqlparser.parser.PlSqlCommandParser;
import com.ness.plsqlparser.parser.PlSqlDeclarationParser;
import com.ness.plsqlparser.parser.PlSqlParser;
import com.ness.plsqlparser.tokens.PlSqlToken;
import com.ness.plsqlparser.tokens.TType;
import com.ness.plsqlparser.tokens.TokenBegin;
import com.ness.plsqlparser.tokens.TokenDeclare;

public class PlSqlBlockParser extends PlSqlParser {
    private PlSqlBlock block;
    private List<PlSqlToken> unprocessedTokens = null;
    private List<PlSqlCommand> commands;
    private List<PlSqlDeclaration> declarations;

    public PlSqlBlockParser(List<PlSqlToken> tokens) {
        super(tokens);
        block = new PlSqlBlock();
        unprocessedTokens = tokens;
        commands = new ArrayList<PlSqlCommand>();
        declarations = new ArrayList<PlSqlDeclaration>();
    }

    @Override
    public PlSqlElement parse() {
        valid = false;
        unprocessedTokens = tokens;
        PlSqlToken firstToken = unprocessedTokens.get(0);
        if (firstToken instanceof TokenDeclare) {
            unprocessedTokens = unprocessedTokens.subList(1,unprocessedTokens.size());
            while (unprocessedTokens != null
                    && !unprocessedTokens.isEmpty()
                    && (unprocessedTokens.get(0).getType() != TType.BEGIN)) {
                PlSqlDeclarationParser declarationParser = new PlSqlDeclarationParser(unprocessedTokens);
                PlSqlDeclaration declaration = (PlSqlDeclaration) declarationParser.parse();
                declarations.add(declaration);
                block.addDeclaration(declaration);
                unprocessedTokens = declarationParser.getRemainingTokens();
            }
        }
        firstToken = unprocessedTokens.get(0);
        if (firstToken instanceof TokenBegin) {
            unprocessedTokens = unprocessedTokens.subList(1,unprocessedTokens.size());
            while (unprocessedTokens != null
                    && !unprocessedTokens.isEmpty()
                    && (unprocessedTokens.get(0).getType() != TType.END)) {
                PlSqlCommandParser commandParser = new PlSqlCommandParser(unprocessedTokens);
                PlSqlCommand command = commandParser.parse();
                commands.add(command);
                block.addCommand(command);
                unprocessedTokens = commandParser.getRemainingTokens();
            }
            if (commands.size() > 0)
                valid = true;
        } else {
            valid = false;
        }
        parsed = true;
        return block;
    }

    public List<PlSqlToken> remainingTokens() {
        return unprocessedTokens;
    }
}
