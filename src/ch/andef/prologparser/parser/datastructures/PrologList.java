package ch.andef.prologparser.parser.datastructures;

import java.util.List;

public class PrologList implements Argument {
    private List<Argument> items;

    public PrologList(List<Argument> items) {
        this.items = items;
    }

    public List<Argument> getItems() {
        return items;
    }
}