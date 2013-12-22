package ch.andef.prologparser.parser.datastructures;

class Number implements DataStructure {
    private int value;

    public Number(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}