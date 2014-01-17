package ch.andef.prologparser.parser;

import java.util.ArrayList;
import java.util.List;

import ch.andef.prologparser.parser.datastructures.Argument;
import ch.andef.prologparser.parser.datastructures.Atom;
import ch.andef.prologparser.parser.datastructures.Fact;
import ch.andef.prologparser.parser.datastructures.PrologNumber;
import ch.andef.prologparser.parser.datastructures.Rule;
import ch.andef.prologparser.parser.datastructures.Rule.OrGoals;
import ch.andef.prologparser.parser.datastructures.Variable;
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

        while (true) {
            facts.add(parseFact());
            if (position + 1 == tokens.size()) {
                break;
            }
            nextToken();
        }
        return facts;
    }

    private Fact parseFact() {
        Fact fact = null;
        expect(TokenType.IDENTIFIER);
        String functor = getLastToken().getData();
        expect(TokenType.LPAREN);

        List<Argument> arguments = parseArgumentList();

        expect(TokenType.RPAREN);

        if (accept(TokenType.RULE_ASSIGNMENT)) {
            fact = new Rule(parseRule());
        } else {
            fact = new Fact();
        }

        if (getCurrentToken().getType() != TokenType.PERIOD) {
            // TODO exception
            throw new RuntimeException("bad fact/rule");
        }
        fact.setFunctor(functor);
        fact.setArguments(arguments);
        return fact;
    }


    private List<OrGoals> parseRule() {
        // TODO Auto-generated method stub
        return null;
    }

    private List<Argument> parseArgumentList() {
        List<Argument> arguments = new ArrayList<Argument>();
        while (true) {
            if (accept(TokenType.IDENTIFIER)) {
                // TODO parse recursive facts
                arguments.add(new Atom(getLastToken().getData()));
            } else if (accept(TokenType.VARIABLE)) {
                arguments.add(new Variable(getLastToken().getData()));
            } else if (accept(TokenType.NUMBER)) {
                arguments.add(new PrologNumber(Integer.parseInt(getLastToken().getData())));
            } else if (accept(TokenType.LBRACKET)) {
                //TODO: parseList();
            }

            if (getCurrentToken().getType() == TokenType.RPAREN) {
                break;
            } else if (!accept(TokenType.COMMA)){
                throw new RuntimeException("Error parsing arguments");
            }
        }
        return arguments;
    }

    private boolean accept(TokenType type) {
        if (getCurrentToken().getType() == type) {
            nextToken();
            return true;
        }
        return false;
    }

    private void expect(TokenType type) {
        if (!accept(type)) {
            throw new RuntimeException(String.format("Parser error on token %s", tokens.get(position)));
        }
    }

    public void nextToken() {
        position++;
        if (position >= tokens.size()) {
            throw new RuntimeException("Unexpected end of proram.");
        }
    }

    public Token getCurrentToken() {
        return tokens.get(position);
    }

    public Token getLastToken() {
        return tokens.get(position - 1);
    }


}
