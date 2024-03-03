package expression.constants;

import expression.functional.ExpressionOperation;
import expression.functional.SingleExpressionOperation;

public class OperationsConstants {
    public static final SingleExpressionOperation SIN = expression -> Math.sin(expression.result());
    public static final SingleExpressionOperation COS = expression -> Math.cos(expression.result());
    public static final SingleExpressionOperation TAN = expression -> Math.tan(expression.result());
    public static final SingleExpressionOperation SQRT = expression -> Math.sqrt(expression.result());

    public static final ExpressionOperation ADD = (expression1, expression2) -> expression1.result() + expression2.result();
    public static final ExpressionOperation SUB = (expression1, expression2) -> expression1.result() - expression2.result();
    public static final ExpressionOperation MUL= (expression1, expression2) -> expression1.result() * expression2.result();
    public static final ExpressionOperation DIV = (expression1, expression2) -> expression1.result() / expression2.result();
    public static final ExpressionOperation POW = (expression1, expression2) -> Math.pow(expression1.result(), expression2.result());
}