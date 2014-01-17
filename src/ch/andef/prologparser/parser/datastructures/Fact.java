package ch.andef.prologparser.parser.datastructures;

import java.util.List;

public class Fact implements Argument {
    protected String functor = null;
    protected List<Argument> arguments = null; // other facts, atoms, numbers, lists...

    public String getFunctor() {
        return functor;
    }

    public void setFunctor(String functor) {
        this.functor = functor;
    }

    public void setArguments(List<Argument> arguments) {
        this.arguments = arguments;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Fact [functor=");
        builder.append(functor);
        builder.append(", arguments=");
        builder.append(arguments);
        builder.append("]");
        return builder.toString();
    }
}


