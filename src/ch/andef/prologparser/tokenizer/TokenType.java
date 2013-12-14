package ch.andef.prologparser.tokenizer;

public enum TokenType {

    /*
     * Beispielsweise sind likes, mary, book, wine Atome. Das erste Zeichen
     * eines solchen Atoms ist ein kleingeschriebener Buchstabe und die
     * folgenden Zeichen sind entweder Buchstaben oder Ziffern. Es gibt aber
     * auch Atome, die lediglich aus Symbolen bestehen, wie beispielsweise ‚:-‘.
     * Diese bestehen nur aus solchen Sonderzeichen.
     */
    IDENTIFIER,

    /* ganze zahlen */
    NUMBER,

    /*
     * Variablen sind wie die erste Gruppe von Atomen aufgebaut, aber fangen mit
     * einem grossen Buchstaben an. Beispiele sind X, Y und Z. Manchmal braucht
     * man eine Variable, deren Namen nicht benötigt wird, das ist die
     * sogenannte anonyme Variable ,_‘.
     */
    VARIABLE,

    LPAREN, // (
    RPAREN, // )

    LBRACKET, // [
    RBRACKET, // ]

    PERIOD, // .
    COMMA, // ,
}
