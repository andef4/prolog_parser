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

        // pretty print the tokens
        for (Token token : tokens) {

            switch (token.getType()){
            case IDENTIFIER:
                if(token.getData() != null){
                    System.out.println(token.getData());
                }
                else{
                    System.out.println(token);
                }
                break;
            case NUMBER:
                if(token.getData() != null){
                    System.out.println(token.getData());
                }
                else{
                    System.out.println(token);
                }
                break;
            case VARIABLE:
                if(token.getData() != null){
                    System.out.println(token.getData());
                }
                else{
                    System.out.println(token);
                }
                break;
            case STRING:
                if(token.getData() != null){
                    System.out.println(token.getData());
                }
                else{
                    System.out.println(token);
                }
                break;
            case LPAREN:
                if(token.getData() != null){
                    System.out.println(token.getData());
                }
                else{
                    System.out.println("(");
                }
                break;
            case RPAREN:
                if(token.getData() != null){
                    System.out.println(token.getData());
                }
                else{
                    System.out.println(")");
                }
                break;
            case LBRACKET:
                if(token.getData() != null){
                    System.out.println(token.getData());
                }
                else{
                    System.out.println("[");
                }
                break;
            case RBRACKET:
                if(token.getData() != null){
                    System.out.println(token.getData());
                }
                else{
                    System.out.println("]");
                }
                break;
            case PERIOD:
                if(token.getData() != null){
                    System.out.println(token.getData());
                }
                else{
                    System.out.println(".");
                }
                break;
            case COMMA:
                if(token.getData() != null){
                    System.out.println(token.getData());
                }
                else{
                    System.out.println(",");
                }
                break;
            case SEMICOLON:
                if(token.getData() != null){
                    System.out.println(token.getData());
                }
                else{
                    System.out.println(";");
                }
                break;
            case PIPE:
                if(token.getData() != null){
                    System.out.println(token.getData());
                }
                else{
                    System.out.println("|");
                }
                break;
            case EXCLAMATION_MARK:
                if(token.getData() != null){
                    System.out.println(token.getData());
                }
                else{
                    System.out.println("!");
                }
                break;
            case RULE_ASSIGNMENT:
                if(token.getData() != null){
                    System.out.println(token.getData());
                }
                else{
                    System.out.println(":-");
                }
                break;
            default:
                System.out.println(token);
                break;
            }
        }

    }
}

