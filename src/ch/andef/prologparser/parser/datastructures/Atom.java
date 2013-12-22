package ch.andef.prologparser.parser.datastructures;

class Atom implements DataStructure {
    private String name;

    public Atom(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}