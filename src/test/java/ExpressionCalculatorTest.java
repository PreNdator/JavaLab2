import expression.ExpressionCalculator;
import expression.constants.OperationsConstants;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ExpressionCalculatorTest {

    @Test
    public void testCalculate() {
        ExpressionCalculator calculator = new ExpressionCalculator();

        String expression1 = "(2+2)*2.5+2*(12+8)/2";
        assertEquals((2 + 2) * 2.5 + (double) (2 * (12 + 8)) / 2, calculator.calculate(expression1), 0.0001);

        String expression2 = "sin(3*(2+2)) * (3 + 2)";
        assertEquals(OperationsConstants.SIN.apply(3 * (2 + 2)) * (3 + 2), calculator.calculate(expression2), 0.0001);

        String expression3 = "(3.33 + 4) * 5.55";
        assertEquals((3.33+4)*5.55, calculator.calculate(expression3), 0.0001);

        String expression4 = "0";
        assertEquals(0, calculator.calculate(expression4), 0.0001);
    }

    @Test
    public void testInvalidExpression() {
        ExpressionCalculator calculator = new ExpressionCalculator();

        String invalidExpression = "3 + 4) * 5";
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(invalidExpression));

        String invalidExpression2 = "(3 + 4 * 5";
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(invalidExpression2));
    }

    @Test
    public void testDivisionByZero() {
        ExpressionCalculator calculator = new ExpressionCalculator();

        String invalidExpression = "3/0";
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(invalidExpression));
    }
}