package calculator;

import token.Token;
import token.TokenType;
import utils.Stack;

public class RPN {

    private Stack<Integer> operands;

    public RPN() {
        operands = new Stack<>();
    }

    public void saveOperand(Token token) throws RuntimeException {
        if (token.type == TokenType.NUM) {
            operands.push(Integer.valueOf(token.lexeme));
        } else {
            throw new RuntimeException("Operando Inválido");
        }

    }

    public Integer RpnStacker(Token token) throws RuntimeException {

        Integer fistOperand = operands.pop();
        Integer secondOperand = operands.pop();
        Integer result = 0;

        switch (token.type) {
            case PLUS:
                result =  secondOperand + fistOperand;
                break;
            case MINUS:
                result = secondOperand - fistOperand;
                break;
            case STAR:
                result = secondOperand * fistOperand;
                break;
            case SLASH:
                result = secondOperand / fistOperand;
                break;
            default:
                throw new RuntimeException("Operador Inválido");
        }

        operands.push(result);
        return result;
    }

    public Integer getResult() {
        return operands.pop();
    }

}
