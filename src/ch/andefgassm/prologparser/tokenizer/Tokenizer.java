package ch.andefgassm.prologparser.tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * A tokenizer for a subset of prolog
 * @author andef4, gassm9
 */
public class Tokenizer {
    private String program = null;
    private List<Token> tokens = null;
    private int position = 0;

    // positions for error reporting
    int line = 1;
    int line_position = 1;

    // set this to the same as your editors tab with to correctly report
    // parse error position
    private static final int TAB_WIDTH = 4;

    /**
     * Tokenize a prolog program
     * @param program The program to do the lexical analysis on
     * @return A list of found tokens
     */
    public List<Token> tokenize(String program) {
        this.program = program.replace("\r\n", "\n").replace("\r", "\n"); // normalize line endings
        this.tokens = new ArrayList<Token>();
        this.position = 0;

        while (!isEndOfProgram()) {
            // whitespaces
            if (getCurrentChar().equals("\n")) {
                line++;
                line_position = 0;
            } else if (getCurrentChar().equals("\t")) {
                line_position += TAB_WIDTH - 1;
            } else  if (getCurrentChar().matches(" ")) {
                // ignore space
            }

            // complex multi character tokens
            else if (getCurrentChar().matches("[a-z]+")) {
                parseIdentifier();
            } else if (getCurrentChar().matches("[A-Z]+")) {
                parseVariable();
            } else if (getCurrentChar().matches("[0-9]+")) {
                parseNumber();
            } else if (getCurrentChar().matches("'")) {
                parseString();
            } else if (getCurrentChar().equals(":") && getNextChar().equals("-")) {
                tokens.add(new Token(TokenType.RULE_ASSIGNMENT));
                nextChar();
            }

            // simple single character tokens
            else if (getCurrentChar().equals("_")) {
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
            }

            // comments
            else if (getCurrentChar().equals("%")) {
                parseComment();
            } else if (getCurrentChar().equals("/") && getNextChar().equals("*")) {
                nextChar();
                nextChar();
                parseCComment();
            }

            // error
            else {
                throwParseError("Illegal token: " + getCurrentChar());
            }
            nextChar();
        }
        return tokens;
    }

    /**
     * parse an identifier and adds it to the tokens
     */
    private void parseIdentifier() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getCurrentChar());
        while (!isEndOfProgram()) {
            if (getNextChar().matches("[a-zA-Z0-9_]+")) {
                nextChar();
                buffer.append(getCurrentChar());
            } else {
                break;
            }
        }
        tokens.add(new Token(TokenType.IDENTIFIER, buffer.toString()));
        return;
    }

    /**
     * parse a variable and adds it to the tokens
     */
    private void parseVariable() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getCurrentChar());
        while (!isEndOfProgram()) {
            if (getNextChar().matches("[a-zA-Z0-9_]+")) {
                nextChar();
                buffer.append(getCurrentChar());
            } else {
                break;
            }
        }
        tokens.add(new Token(TokenType.VARIABLE, buffer.toString()));
        return;
    }

    /**
     * parse a number and adds it to the tokens
     */
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

    /**
     * parse a string and adds it to the tokens
     */
    private void parseString() {
        nextChar();
        StringBuffer buffer = new StringBuffer();

        while (!getCurrentChar().equals("'")) {
            if (getCurrentChar().equals("\n")) {
                throwParseError("Unclosed string on end of line");
            }
            buffer.append(getCurrentChar());
            nextChar();
            if (isEndOfProgram()) {
                throwParseError("Unclosed string on end of program");
            }
        }
        tokens.add(new Token(TokenType.STRING, buffer.toString()));

    }

    /**
     * parse a one line comment and ignore it
     */
    private void parseComment() {
        nextChar();
        while (!isEndOfProgram() && !getNextChar().equals("\n")) {
            nextChar();
        }
    }

    /**
     * parse a c style comment and inore it
     */
    private void parseCComment() {
        while (!(getCurrentChar().equals("*") && getNextChar().equals("/"))) {
            nextChar();
            if (isEndOfProgram()) {
                throwParseError("Not closed C style comment on end of program");
            }
        }
        nextChar();
    }

    /**
     * throw a RuntimeException with the current position and the supplied message
     * @param The message to add in the exception
     */
    private void throwParseError(String message) {
        throw new RuntimeException(String.format("Parse error on (%d, %d): %s", line, line_position, message));
    }

    /**
     * moves to the next character
     */
    private void nextChar() {
        position++;
        line_position++;
    }

    /**
     * @return the current analyzed character
     */
    private String getCurrentChar() {
        return program.substring(position, position + 1);
    }

    /**
     * @return the character after the current
     */
    private String getNextChar() {
        return program.substring(position + 1, position + 2);
    }

    /**
     * checks if the end of the program has been reached
     * @return true if end program
     */
    private boolean isEndOfProgram() {
        return position >= program.length();
    }
}
