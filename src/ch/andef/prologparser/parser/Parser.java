package ch.andef.prologparser.parser;

import java.util.ArrayList;
import java.util.List;

import ch.andef.prologparser.parser.datastructures.Argument;
import ch.andef.prologparser.parser.datastructures.Fact;
import ch.andef.prologparser.parser.datastructures.Rule;
import ch.andef.prologparser.parser.datastructures.Rule.OrGoals;
import ch.andef.prologparser.tokenizer.Token;
import ch.andef.prologparser.tokenizer.TokenType;

public class Parser {
    private List<Token> tokens = null;
    private ArrayList<Fact> facts = null;
    private int position = 0;

    public List<Fact> parse(List<Token> tokens) {
        this.tokens = tokens;
        this.facts = new ArrayList<Fact>();
        this.position = 0;

        while (position <= facts.size()) {
            facts.add(parseFact());
        }

        return facts;

    }

    private Fact parseFact() {
        Fact fact = null;
        expect(TokenType.IDENTIFIER);
        String functor = getData();
        expect(TokenType.LPAREN);

        List<Argument> arguments = parseArgumentList();

        expect(TokenType.RPAREN);

        if (accept(TokenType.RULE_ASSIGNMENT)) {
            fact = new Rule(parseRule());
        } else {
            fact = new Fact();
        }
        expect(TokenType.PERIOD);
        fact.setFunctor(functor);
        fact.setArguments(arguments);
        return fact;
    }


    private List<OrGoals> parseRule() {
        // TODO Auto-generated method stub
        return null;
    }

    private List<Argument> parseArgumentList() {
        // TODO Auto-generated method stub
        return null;
    }

    private boolean accept(TokenType type) {
        if (tokens.get(position).getType() == type) {
            position++;
            return true;
        }
        return false;
    }

    private void expect(TokenType type) {
        if (!accept(type)) {
            throw new RuntimeException(String.format("Parser error on token %s", tokens.get(position)));
        }
    }

    private String getData() {
        return tokens.get(position).getData();
    }


}
