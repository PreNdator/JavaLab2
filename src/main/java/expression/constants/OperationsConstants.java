package expression.constants;

import expression.functional.TwoNumOperation;
import expression.functional.OneNumOperation;

public class OperationsConstants {


    public static final OneNumOperation SIN = Math::sin;
    public static final OneNumOperation COS = Math::cos;
    public static final OneNumOperation TAN = Math::tan;
    public static final OneNumOperation SQRT = Math::sqrt;

    public static final TwoNumOperation ADD = Double::sum;
    public static final TwoNumOperation SUB = (num1, num2) -> num1 - num2;
    public static final TwoNumOperation MUL= (num1, num2) -> num1 * num2;
    public static final TwoNumOperation DIV = (num1, num2) -> num1 / num2;
    public static final TwoNumOperation POW = Math::pow;
}