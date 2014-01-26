package com.ness.sourcecodeparser;

public class SourceCodeTokenizer {
/*    public List<PlSqlToken> tokenize(String source) {
        SourceData sourceData = analyzeSource(source);
        List<PlSqlToken> result = new ArrayList<PlSqlToken>();
        Token[] tokens = sourceData.getTokenArray();
        for(Token token : tokens) {
            PlSqlToken plSqlToken = translatePlSqlToken(token);
            if (plSqlToken != null)
                result.add(plSqlToken);
        }
        return result;
    }

    private PlSqlToken translatePlSqlToken(Token token) {
        if (token.getType() == Token.TOKEN_TYPE_KEYWORD)
            return translateTokenKeyword(token);
        if (token.getType() == Token.TOKEN_TYPE_DELIMITER)
            return translateTokenDelimiter(token);
        return null;
    }

    private TokenDelimiter translateTokenDelimiter(Token token) {
        if (token.getText().equals(";"))
            return new TokenSemicolon(token.getText());
        else
            return new TokenDelimiter(token.getText());
    }

    private TokenKeyword translateTokenKeyword(Token token) {
        if (token.getText().equalsIgnoreCase("NULL"))
            return new TokenNull(token.getText());
        else if (token.getText().equalsIgnoreCase("BEGIN"))
            return new TokenBegin(token.getText());
        else if (token.getText().equalsIgnoreCase("END"))
            return new TokenEnd(token.getText());
        else if (token.getText().equalsIgnoreCase("DECLARE"))
            return new TokenDeclare(token.getText());
        else
            return new TokenKeyword(token.getText());
    }

    private SourceData analyzeSource(String source) {
        SourceData sourceData = new SourceData(source);
        Tokenizer tokenizer = new Tokenizer();
        Analyzer analyzer = new Analyzer();
        tokenizer.tokenize(sourceData);
        analyzer.analyze(sourceData);
        return sourceData;
    }
*/
}
