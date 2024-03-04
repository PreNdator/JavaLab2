import expression.ExpressionCalculator;


public class Main {
    public static void main(String[] args) {
        ExpressionCalculator calculator = new ExpressionCalculator();

        System.out.println(calculator.calculate("sin(3.14) + 2 * (4 - 1)"));
    }
}
