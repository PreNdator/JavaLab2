package expression;

import expression.constants.OperationsConstants;
import expression.constants.StringOperationsConstants;


import java.util.*;

/**
 * The ExpressionCalculator class evaluates mathematical expressions given in infix notation.
 * It supports basic arithmetic operations (+, -, *, /, ^) and common trigonometric functions (sin, cos, tan, sqrt).
 */
public class ExpressionCalculator {
    private final HashMap<String, Integer> _operators;
    private final String[] _functions;

    private boolean isOperator(String token) {
        return _operators.containsKey(token);
    }

    /**
     * Constructs an ExpressionCalculator object with specified arrays of operators and functions.
     *
     * @param operators an array of operator symbols
     * @param priority an array of priority values corresponding to the operators
     * @param functions an array of function names
     * @throws IllegalArgumentException if operators and priority have different length
     */
    public ExpressionCalculator(char[] operators, int[] priority, String[] functions) {
        if (operators.length != priority.length) {
            throw new IllegalArgumentException("Arrays operators and priority must be of the same length");
        }
        _operators = new HashMap<>();
        for (int i = 0; i < operators.length; ++i) {
            _operators.put(String.valueOf(operators[i]), priority[i]);
        }
        _functions = Arrays.copyOf(functions, functions.length);
    }

    /**
     * Constructs an ExpressionCalculator object with arrays of operators and functions
     * taken from StringOperationsConstants class.
     */
    public ExpressionCalculator() {
        _operators = new HashMap<>();
        _operators.put(String.valueOf(StringOperationsConstants.ADD), 1);
        _operators.put(String.valueOf(StringOperationsConstants.SUB), 1);
        _operators.put(String.valueOf(StringOperationsConstants.MUL), 2);
        _operators.put(String.valueOf(StringOperationsConstants.DIV), 2);
        _operators.put(String.valueOf(StringOperationsConstants.POW), 3);

        _functions = StringOperationsConstants.getFunctions();
    }

    private int getPriority(String operator){
        if(_operators.containsKey(operator)) {
            return _operators.get(operator);
        }
        else {
            throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    private boolean isFunction(String token) {
        for (String function : _functions) {
            if (function.equals(token)) {
                return true;
            }
        }
        return false;
    }

    private String[] polishNotation(String[] tokens){
        List<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        
        for (String token : tokens) {
            char lastChar = token.charAt(token.length()-1);
            if (Character.isDigit(token.charAt(0))) {
                result.add(token);
            }
            else if(isOperator(token)) {
                while(!stack.isEmpty() && stack.peek().charAt(stack.peek().length()-1)!='(' && getPriority(stack.peek())>=getPriority(token)){
                    result.add(stack.pop());
                }
                stack.push(token);
            }
            else if(lastChar == '('){
                stack.push(token);
            }
            else if(token.equals(")")){
                boolean isFound = false;
                while (!isFound){
                    if(stack.isEmpty()){
                        throw new IllegalArgumentException("Incorrect placement of brackets");
                    }
                    String top = stack.peek();
                    if(top.charAt(top.length()-1) == '('){
                        if(!(top.equals("("))){
                            result.add(top);
                        }
                        isFound = true;
                    }
                    else{
                        result.add(top);
                    }

                    stack.pop();

                }
            }
            else{
                throw new IllegalArgumentException("Unexpected token" + token);
            }
        }

        while (!stack.isEmpty()){
            if(isFunction(stack.peek()) || stack.peek().equals("(")){
                throw new IllegalArgumentException("Incorrect placement of brackets");
            }
            else{
                result.add(stack.pop());
            }
        }

        return result.toArray(new String[0]);
    }

    private double calculate(String[] expression) {
        Stack<Double> stack = new Stack<>();

        for (String token : expression) {
            if (Character.isDigit(token.charAt(0))) {
                stack.push(Double.parseDouble(token));
            } else if (isFunction(token)) {
                double argument = stack.pop();
                double result;

                switch (token) {
                    case StringOperationsConstants.SIN:
                        result = OperationsConstants.SIN.apply(argument);
                        break;
                    case StringOperationsConstants.COS:
                        result = OperationsConstants.COS.apply(argument);
                        break;
                    case StringOperationsConstants.TAN:
                        result = OperationsConstants.TAN.apply(argument);
                        break;
                    case StringOperationsConstants.SQRT:
                        result = OperationsConstants.SQRT.apply(argument);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown function: " + token);
                }

                stack.push(result);
            } else {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result;

                switch (token.charAt(0)) {
                    case StringOperationsConstants.ADD:
                        result = OperationsConstants.ADD.apply(operand1, operand2);
                        break;
                    case StringOperationsConstants.SUB:
                        result = OperationsConstants.SUB.apply(operand1, operand2);
                        break;
                    case StringOperationsConstants.MUL:
                        result = OperationsConstants.MUL.apply(operand1, operand2);
                        break;
                    case StringOperationsConstants.DIV:
                        if(operand2 == 0){
                            throw new IllegalArgumentException("Division by zero");
                        }
                        result = OperationsConstants.DIV.apply(operand1, operand2);
                        break;
                    case StringOperationsConstants.POW:
                        result = OperationsConstants.POW.apply(operand1, operand2);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operator: " + token);
                }

                stack.push(result);
            }
        }

        return stack.pop();
    }

    /**
     * Parses and calculates the result of the given mathematical expression.
     *
     * @param expression the mathematical expression to be calculated
     * @return the result of the calculation
     * @throws IllegalArgumentException if the expression is invalid or contains errors such as division by zero
     */
    public double calculate(String expression){
        ExpressionParser parser = new ExpressionParser();
        String[] tokens = parser.parseExpression(expression);
        String[] notation = polishNotation(tokens);
        return calculate(notation);
    }
}
