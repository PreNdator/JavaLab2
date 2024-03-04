package expression;

import expression.constants.StringOperationsConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressionParser {

    private final char[] _operators;
    private final String[] _functions;

    /**
     * Constructs an ExpressionParser object with specified arrays of operators and functions.
     *
     * @param operators an array of operator symbols
     * @param functions an array of function names
     */
    public ExpressionParser(char[] operators, String[] functions) {
        _operators = Arrays.copyOf(operators, operators.length);
        _functions = Arrays.copyOf(functions, functions.length);
    }

    /**
     * Constructs an ExpressionParser object with arrays of operators and functions
     * taken from StringOperationsConstants class.
     */
    public ExpressionParser(){
        _operators = StringOperationsConstants.getOperators();
        _functions = StringOperationsConstants.getFunctions();
    }

    /**
     * Parses a mathematical expression into an array of tokens.
     * Tokens can be numbers, operators, or function names.
     *
     * @param expression the mathematical expression to parse
     * @return an array of tokens representing the expression
     * @throws IllegalArgumentException if the expression contains invalid characters
     *                                  or if a number has an invalid format
     */
    public String[] parseExpression(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentNumber = new StringBuilder();
        boolean isNumHaveDot = false;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c) || (c == '.')) {
                if (c == '.') {
                    if (isNumHaveDot) {
                        throw new IllegalArgumentException("Invalid number format: " + currentNumber + c);
                    }
                    else{
                        if(currentNumber.length() == 0){
                            currentNumber.append('0');
                        }
                    }
                    isNumHaveDot = true;
                }
                currentNumber.append(c);
            } else {
                if (currentNumber.length() > 0) {
                    tokens.add(currentNumber.toString());
                    isNumHaveDot = false;
                    currentNumber = new StringBuilder();
                }
                if (isOperator(c) || c == '(' || c == ')') {
                    tokens.add(String.valueOf(c));
                } else if (Character.isLetter(c)) {
                    i = parseFunction(expression, i, tokens);
                }
            }
        }

        if (currentNumber.length() > 0) {
            tokens.add(currentNumber.toString());
        }

        return tokens.toArray(new String[0]);
    }

    private int parseFunction(String expression, int startIndex, List<String> tokens) {
        int endIndex = findEndOfFunction(expression, startIndex);
        String functionName = expression.substring(startIndex, endIndex);
        if (isFunction(functionName)) {
            tokens.add(functionName);
        } else {
            throw new IllegalArgumentException("Invalid function: " + functionName);
        }
        return endIndex - 1;
    }

    private boolean isOperator(char token) {
        for (char operator : _operators) {
            if (operator == token) {
                return true;
            }
        }
        return false;
    }


    private boolean isFunction(String token) {
        for (String function : _functions) {
            if (function.equals(token)) {
                return true;
            }
        }
        return false;
    }

    private int findEndOfFunction(String expression, int startIndex) {
        int i = startIndex;
        boolean isFind = false;
        while (i < expression.length() && !isFind) {
            if(expression.charAt(i)=='('){
                isFind = true;
            }
            i++;
        }
        return i;
    }
}