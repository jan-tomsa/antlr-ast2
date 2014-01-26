package com.ness.plsqlparser.model;

import java.util.ArrayList;
import java.util.List;

public class PlSqlBlock extends PlSqlElement {
    private List<PlSqlDeclaration> declarations;
    private List<PlSqlCommand> commands;
    private PlSqlExceptionBlock exceptionBlock;

    public PlSqlBlock() {
        this.declarations = new ArrayList<PlSqlDeclaration>();
        this.commands = new ArrayList<PlSqlCommand>();
        this.exceptionBlock = new PlSqlExceptionBlock();
    }

    public List<PlSqlDeclaration> getDeclarations() {
        return declarations;
    }

    public List<PlSqlCommand> getCommands() {
        return commands;
    }

    public PlSqlExceptionBlock getExceptionBlock() {
        return exceptionBlock;
    }

    public void addCommand(PlSqlCommand command) {
        commands.add(command);
    }

    public void addDeclaration(PlSqlDeclaration declaration) {
        declarations.add(declaration);
    }
}
