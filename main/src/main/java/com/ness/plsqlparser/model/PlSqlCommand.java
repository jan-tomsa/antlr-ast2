package com.ness.plsqlparser.model;

public class PlSqlCommand extends PlSqlElement {
    private String source;

    public PlSqlCommand(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
