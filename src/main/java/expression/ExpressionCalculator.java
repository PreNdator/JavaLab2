package expression;

import expression.expressions.*;

public class ExpressionCalculator {

    private static IExpression parse(String expression) {
        IExpression result = null;

        if(expression.isEmpty()){
            result = new NumberExpression(0);
        }
        else {
            int size = expression.length();


            for(int i=0; i<size; ++i){
                System.out.println(expression.charAt(i));
            }
        }

        return result;
    }

    public static double calculate(String expression){
        IExpression parsedExpression = parse(expression);
        return parsedExpression.result();
    }
}
