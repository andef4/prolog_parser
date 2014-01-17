package ch.andef.prologparser.parser.datastructures;

public class Variable implements Argument {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Variable: %s", name);
    }

}