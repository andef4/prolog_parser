package ch.andefgassm.prologparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import ch.andefgassm.prologparser.tokenizer.Token;
import ch.andefgassm.prologparser.tokenizer.Tokenizer;

/**
 * The Main class calls the tokenizer with the prolog program passed by command line argument
 * @author andef4, gassm9
 */
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

        // pretty print the tokens
        for (Token token : tokens) {

            switch (token.getType()){
            case IDENTIFIER:
                System.out.println(token.getData());
                break;
            case NUMBER:
                System.out.println(token.getData());
                break;
            case VARIABLE:
                System.out.println(token.getData());
                break;
            case STRING:
                System.out.println(token.getData());
                break;
            case LPAREN:
                System.out.println("(");
                break;
            case RPAREN:
                System.out.println(")");
                break;
            case LBRACKET:
                System.out.println("[");
                break;
            case RBRACKET:
                System.out.println("]");
                break;
            case PERIOD:
                System.out.println(".");
                break;
            case COMMA:
                System.out.println(",");
                break;
            case SEMICOLON:
                System.out.println(";");
                break;
            case PIPE:
                System.out.println("|");
                break;
            case EXCLAMATION_MARK:
                System.out.println("!");
                break;
            case RULE_ASSIGNMENT:
                System.out.println(":-");
                break;
            default:
                throw new RuntimeException("Unknown TokenType");
            }
        }

    }
}

