package ch.andef.prologparser.parser.datastructures;

public class Variable implements DataStructure {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}