package ch.andef.prologparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import ch.andef.prologparser.tokenizer.Token;
import ch.andef.prologparser.tokenizer.Tokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        // parse command line arguments
        if (args.length != 1) {
            System.out.println("Usage: java -jar prologparser.jar <prolog_file>");
            System.exit(1);
        }

        // read file
        String fileName = args[0];
        BufferedReader b = new BufferedReader(new FileReader(fileName));
        String line = null;
        StringBuffer buffer = new StringBuffer();
        while ((line = b.readLine()) != null) {
            buffer.append(line);
            buffer.append('\n');
        }
        b.close();

        // invoke tokenizer
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokens = tokenizer.tokenize(buffer.toString());

        // TODO gassm9: pretty print the tokens
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
