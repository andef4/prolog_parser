package ch.andef.prologparser;

import java.util.List;

import ch.andef.prologparser.parser.Parser;
import ch.andef.prologparser.parser.datastructures.Fact;
import ch.andef.prologparser.tokenizer.Token;
import ch.andef.prologparser.tokenizer.Tokenizer;

public class Main {

    public static void main(String[] args) {
        String input = "cat(tom).\n" +
                "    parent(Fritz, 1234).\n" +
                "listTest(a, [b,c,d]).\n";
        System.out.println(input);
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize(input);

        Parser parser = new Parser();
        List<Fact> terms = parser.parse(tokens);

        for (Fact term : terms) {
            System.out.println(term);
        }
    }
}
