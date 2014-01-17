package ch.andef.prologparser.parser.datastructures;

public class PrologNumber implements Argument {

    private int number;

    public PrologNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("Number: %s", number);
    }

}
