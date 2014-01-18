package ch.andefgassm.prologparser.tokenizer;

/**
 * A prolog Token. Every Token has a type and some have additional data (like string context, identifier name).
 * @author andef4, gassm9
 */
public class Token {

    private TokenType type = null;
    private String data = null;

    public Token(TokenType type, String data) {
        this.type = type;
        this.data = data;
    }

    public Token(TokenType type) {
        this.type = type;
    }

    public TokenType getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        if (data != null) {
            return String.format("%s: %s", type, data);
        } else {
            return type.toString();
        }
    }

}
