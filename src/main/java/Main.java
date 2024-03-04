import expression.ExpressionCalculator;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a mathematical expression:");
        String expression = scanner.nextLine();

        ExpressionCalculator calculator = new ExpressionCalculator();

        try {
            double result = calculator.calculate(expression);
            System.out.println("Result of calculation: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
