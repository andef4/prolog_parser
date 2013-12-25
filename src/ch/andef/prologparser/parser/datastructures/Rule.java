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
}