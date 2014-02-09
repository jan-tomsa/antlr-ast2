package com.ness.plsqlparser.parser;

import com.ness.plsqlparser.PlSqlTokenStream;
import com.ness.plsqlparser.model.PlSqlBlock;
import com.ness.plsqlparser.model.PlSqlElementList;
import com.ness.plsqlparser.tokens.PlSqlToken;
import com.ness.plsqlparser.tokens.TType;

public class PlSqlScriptParser {
    protected PlSqlTokenStream tokens;
    protected boolean parsed;
    protected boolean valid;

    public PlSqlScriptParser() {
        this.tokens = new PlSqlTokenStream();
        this.parsed = false;
        this.valid = false;
    }

    public PlSqlScriptParser(PlSqlTokenStream tokens) {
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
	    PlSqlToken token;
	    while (!tokens.isAtLastToken()) {
		    token = tokens.nextToken();
		    System.out.println("PlSqlScriptParser#1 - tokens." + tokens.toString());
		    if (token.getType() == TType.BEGIN || token.getType() == TType.DECLARE) {
			    PlSqlBlockParser parser = new PlSqlBlockParser(tokens);
			    PlSqlBlock block = (PlSqlBlock) parser.parse();
			    result.add(block);
			    if (!parser.isValid())
				    this.valid = false;
		    }
	    }
	    System.out.println("PlSqlScriptParser#2 - tokens." + tokens.toString());
//        List<PlSqlToken> workingTokens = tokens;
//        while (workingTokens != null && !workingTokens.isEmpty()) {
//            workingTokens = processWorkingTokens(result, workingTokens);
//        }
        return result;
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
