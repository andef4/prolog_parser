package ch.andef.prologparser;

import java.util.List;

import ch.andef.prologparser.tokenizer.Token;
import ch.andef.prologparser.tokenizer.Tokenizer;

public class Main {

    public static void main(String[] args) {
        String input = "cat(tom).\n" +
                "    parent(Fritz, 1234).\n" +
                "listTest(a, [b,c,d]).\n";
        System.out.println(input);
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokenize = tokenizer.tokenize(input);

        for (Token token : tokenize) {
            System.out.println(token);
        }
    }
}