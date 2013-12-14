package ch.andef.prologparser.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private List<Token> tokens = null;
    private int position = 0;
    private String program = null;

    public List<Token> tokenize(String program) {
        this.program = program;
        tokens = new ArrayList<Token>();
        position = 0;

        while (!isEndOfProgram()) {
            if (getCurrentChar().matches("[\t\n ]+")) {
            } else if (getCurrentChar().matches("[a-z]+")) {
                parseIdentifier();
            } else if (getCurrentChar().matches("[0-9]+")) {
                parseNumber();
            } else if (getCurrentChar().matches("[A-Z]+")) {
                parseVariable();
            } else if (getCurrentChar().matches("\\(")) {
                tokens.add(new Token(TokenType.LPAREN));
            } else if (getCurrentChar().matches("\\)")) {
                tokens.add(new Token(TokenType.RPAREN));
            } else if (getCurrentChar().matches("\\[")) {
                tokens.add(new Token(TokenType.LBRACKET));
            } else if (getCurrentChar().matches("\\]")) {
                tokens.add(new Token(TokenType.RBRACKET));
            } else if (getCurrentChar().matches("\\.")) {
                tokens.add(new Token(TokenType.PERIOD));
            } else if (getCurrentChar().matches("\\,")) {
                tokens.add(new Token(TokenType.COMMA));
            } else {
                throw new RuntimeException(String.format("parse error on position %s '%s'", position,
                        program.substring(position - 1, position + 1)));
            }
            nextChar();
        }
        return tokens;
    }

    private void parseVariable() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getCurrentChar());
        while (!isEndOfProgram()) {
            if (getNextChar().matches("[a-zA-Z0-9]+")) {
                nextChar();
                buffer.append(getCurrentChar());
            } else {
                break;
            }
        }
        tokens.add(new Token(TokenType.VARIABLE, buffer.toString()));
        return;
    }

    private void parseNumber() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getCurrentChar());
        while (!isEndOfProgram()) {
            if (getNextChar().matches("[0-9]+")) {
                nextChar();
                buffer.append(getCurrentChar());
            } else {
                break;
            }
        }
        tokens.add(new Token(TokenType.NUMBER, buffer.toString()));
        return;
    }

    private void parseIdentifier() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getCurrentChar());
        while (!isEndOfProgram()) {
            if (getNextChar().matches("[a-zA-Z0-9]+")) {
                nextChar();
                buffer.append(getCurrentChar());
            } else {
                break;
            }
        }
        tokens.add(new Token(TokenType.IDENTIFIER, buffer.toString()));
        return;
    }

    private void nextChar() {
        position++;
    }

    private String getCurrentChar() {
        return program.substring(position, position + 1);
    }

    private String getNextChar() {
        return program.substring(position + 1, position + 2);
    }

    private boolean isEndOfProgram() {
        return position >= program.length();
    }
}
