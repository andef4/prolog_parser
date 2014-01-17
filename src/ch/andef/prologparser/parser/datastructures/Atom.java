package ch.andef.prologparser.parser.datastructures;

public class Atom implements Argument {
    private String name;

    public Atom(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Atom: %s", name);
    }

}