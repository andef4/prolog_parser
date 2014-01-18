package ch.andef.prologparser.tokenizer;

public enum TokenType {

    /*
     * a prolog identifier. Starts with a lower case letter and goes on with
     * letters and numbers
     */
    IDENTIFIER,

    // prolog integers
    NUMBER,

    /*
     * A prolog variable. Starts with a upper case letter and goes on with
     * letters and numbers
     */
    VARIABLE,

    // A string contains any characters and is enclosed in '
    STRING,

    LPAREN, // (
    RPAREN, // )

    LBRACKET, // [
    RBRACKET, // ]

    PERIOD, // .
    COMMA, // ,
    SEMICOLON, // ;
    PIPE, // |
    EXCLAMATION_MARK, // !

    RULE_ASSIGNMENT, // :-
}
