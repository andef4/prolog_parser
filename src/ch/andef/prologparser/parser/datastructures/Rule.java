package ch.andef.prologparser.parser.datastructures;

import java.util.List;

public class Rule extends Fact implements Argument {
    private List<OrGoals> goals;

    public Rule(List<OrGoals> goals) {
        this.goals = goals;
    }
    public class OrGoals {
        List<Fact> andFacts;
    }

    public List<OrGoals> getGoals() {
        return goals;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Rule [functor=");
        builder.append(functor);
        builder.append(", arguments=");
        builder.append(arguments);
        builder.append(", goals=");
        builder.append(goals);
        builder.append("]");
        return builder.toString();
    }
}
