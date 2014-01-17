package ch.andef.prologparser.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private String program = null;
    private List<Token> tokens = null;
    private int position = 0;

    public List<Token> tokenize(String program) {
        this.program = program;
        this.tokens = new ArrayList<Token>();
        this.position = 0;

        while (!isEndOfProgram()) {
            if (getCurrentChar().matches("[\t\n ]+")) {
            } else if (getCurrentChar().matches("[a-z]+")) {
                parseIdentifier();
            } else if (getCurrentChar().matches("[0-9]+")) {
                parseNumber();
            } else if (getCurrentChar().matches("[A-Z]+")) {
                parseVariable();
            } else if (getCurrentChar().equals("_")) {
                tokens.add(new Token(TokenType.VARIABLE, "_"));
            } else if (getCurrentChar().equals("(")) {
                tokens.add(new Token(TokenType.LPAREN));
            } else if (getCurrentChar().equals(")")) {
                tokens.add(new Token(TokenType.RPAREN));
            } else if (getCurrentChar().equals("[")) {
                tokens.add(new Token(TokenType.LBRACKET));
            } else if (getCurrentChar().equals("]")) {
                tokens.add(new Token(TokenType.RBRACKET));
            } else if (getCurrentChar().equals(".")) {
                tokens.add(new Token(TokenType.PERIOD));
            } else if (getCurrentChar().equals(",")) {
                tokens.add(new Token(TokenType.COMMA));
            } else if (getCurrentChar().equals(";")) {
                tokens.add(new Token(TokenType.SEMICOLON));
            } else if (getCurrentChar().equals("|")) {
                tokens.add(new Token(TokenType.PIPE));
            } else if (getCurrentChar().equals("!")) {
                tokens.add(new Token(TokenType.EXCLAMATION_MARK));
            } else if (getCurrentChar().equals("%")) {
                parseComment();
            } else if (getCurrentChar().equals("/") && getNextChar().equals("*")) {
                nextChar();
                nextChar();
                parseCComment();
            } else if (getCurrentChar().equals(":") && getNextChar().equals("-")) {
                tokens.add(new Token(TokenType.RULE_ASSIGNMENT));
                nextChar();
            } else {
                throwParseError();
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

    private void parseComment() {
        nextChar();
        while (!isEndOfProgram() && !getCurrentChar().equals("\n")) {
            nextChar();
        }
    }

    private void parseCComment() {
        while (!getCurrentChar().equals("*")) {
            nextChar();
            if (isEndOfProgram()) {
                throwParseError();
            }
            if (getNextChar().equals("/")) {
                return;
            }
        }
    }

    private void throwParseError() {
        throw new RuntimeException(String.format("parse error on position %s '%s'", position,
                program.substring(position - 1, position + 1)));
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
