package ch.andef.prologparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import ch.andef.prologparser.parser.Parser;
import ch.andef.prologparser.parser.datastructures.Fact;
import ch.andef.prologparser.tokenizer.Token;
import ch.andef.prologparser.tokenizer.Tokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("example.pl"));
        String line = null;
        StringBuffer buffer = new StringBuffer();
        while ((line = b.readLine()) != null) {
            buffer.append(line);
            buffer.append('\n');
        }
        b.close();

        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize(buffer.toString());

        Parser parser = new Parser();
        List<Fact> facts = parser.parse(tokens);

        for (Fact fact : facts) {
            System.out.println(fact);
        }
    }
}
