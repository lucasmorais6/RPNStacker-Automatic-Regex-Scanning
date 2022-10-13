import calculator.RPN;
import token.Token;
import token.TokenType;
import utils.Value;
import lexer.Regex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            List<Token> tokens = scan(System.getProperty("user.dir")+ "\\src\\input.txt");
            RPN calculator = new RPN();
            int result;

            while (!tokens.isEmpty()) {
                Token token = tokens.remove(0);
                System.out.println(token);

                if (token.type == TokenType.NUM) {
                    calculator.saveOperand(token);
                } else if (token.type != TokenType.NUM) {
                    calculator.RpnStacker(token);
                }
            }

            result = calculator.getResult();
            System.out.println("\nSaida: " + result + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Token> scan(String filename) throws FileNotFoundException {
        File file = new File(filename);

        Scanner scan = new Scanner(file);
        List<Token> tokens = new ArrayList<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine().trim();

            Token token;

            if(Regex.isNum(line)) {
                token = new Token(TokenType.NUM, line);
            } else if (Regex.isOP(line)) {
                token = new Token(Regex.getOPTokenType(line), line);
            } else {
                throw new RuntimeException("Unexpected character: "+ line);
            }

            tokens.add(token);
        }

        scan.close();

        return tokens;
    }
}
