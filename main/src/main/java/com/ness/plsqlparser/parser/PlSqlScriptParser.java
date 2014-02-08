package com.ness.plsqlparser.parser;

import java.util.ArrayList;
import java.util.List;

import com.ness.plsqlparser.model.PlSqlBlock;
import com.ness.plsqlparser.model.PlSqlElementList;
import com.ness.plsqlparser.tokens.PlSqlToken;
import com.ness.plsqlparser.tokens.TokenBegin;
import com.ness.plsqlparser.tokens.TokenDeclare;

public class PlSqlScriptParser {
    protected List<PlSqlToken> tokens;
    protected boolean parsed;
    protected boolean valid;

    public PlSqlScriptParser() {
        this.tokens = new ArrayList<PlSqlToken>();
        this.parsed = false;
        this.valid = false;
    }

    public PlSqlScriptParser(List<PlSqlToken> tokens) {
        this.tokens = tokens;
        this.parsed = false;
        this.valid = false;
    }

    public void addToken(PlSqlToken token) {
        this.tokens.add(token);
    }

    public PlSqlElementList parse() {
        valid = true;
        PlSqlElementList result = processTokens();
        parsed = true;
        return result;
    }

    private PlSqlElementList processTokens() {
        PlSqlElementList result = new PlSqlElementList();
        if (tokens == null || tokens.size()==0) throw new RuntimeException("No tokens. Cannot parse!");
        List<PlSqlToken> workingTokens = tokens;
        while (workingTokens != null && !workingTokens.isEmpty()) {
            workingTokens = processWorkingTokens(result, workingTokens);
        }
        return result;
    }

    private List<PlSqlToken> processWorkingTokens(PlSqlElementList elementList, List<PlSqlToken> workingTokens) {
        PlSqlToken currentToken = workingTokens.get(0);
        if (currentToken instanceof TokenBegin
                || currentToken instanceof TokenDeclare) {
            PlSqlBlockParser plSqlBlockParser = new PlSqlBlockParser(workingTokens);
            PlSqlBlock block = (PlSqlBlock) plSqlBlockParser.parse();
            if (plSqlBlockParser.isValid()) {
                elementList.add(block);
            } else {
                valid = false;
            }
            return plSqlBlockParser.remainingTokens();
        }
        return null;
    }

    public boolean isValid() {
        if (isParsed())
            return valid;
        else
            throw new RuntimeException("Not parsed yet!");
    }

    public boolean isParsed() {
        return parsed;
    }
}
