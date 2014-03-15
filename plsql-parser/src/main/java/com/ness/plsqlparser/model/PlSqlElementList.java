package com.ness.plsqlparser.model;

import java.util.ArrayList;
import java.util.List;

public class PlSqlElementList {
    private List<PlSqlElement> elements;

    public PlSqlElementList(List<PlSqlElement> elements) {
        this.elements = elements;
    }

    public PlSqlElementList() {
        this.elements = new ArrayList<PlSqlElement>();
    }

    public List<PlSqlElement> getElements() {
        return elements;
    }

    public void setElements(List<PlSqlElement> elements) {
        this.elements = elements;
    }

    public int size() {
        return elements.size();
    }

    public void add(PlSqlElement element) {
        this.elements.add(element);
    }

    public PlSqlElement getElement(int index) {
        return this.elements.get(index);
    }
}
