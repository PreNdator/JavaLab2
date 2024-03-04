import expression.ExpressionParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ExpressionParserTest {
    static ExpressionParser _parser;
    @BeforeAll
    public static void onStart(){
        _parser = new ExpressionParser();
    }

    @Test
    public void testParseExpression() {
        String expression = "3.14+sin(30) * 5    -sqrt(  25*49)";
        String[] expectedTokens = {"3.14", "+", "sin(", "30", ")", "*", "5", "-", "sqrt(", "25","*","49", ")"};
        assertArrayEquals(expectedTokens, _parser.parseExpression(expression));
    }

    @Test
    public void testParseExpressionWithInvalidFunction() {
        String expression = "2 * invalid(5)";
        assertThrows(IllegalArgumentException.class, () -> _parser.parseExpression(expression));
    }

    @Test
    public void testParseExpressionWithEmptyString() {
        String expression = "";
        String[] expectedTokens = {};
        assertArrayEquals(expectedTokens, _parser.parseExpression(expression));
    }

    @Test
    public void testFirstDot() {
        String expression = ".2";
        String expected = "0.2";
        assertEquals(expected, _parser.parseExpression(expression)[0]);
    }

    @Test
    public void testManyDotsInNumber() {
        String expression = "1.2.3";
        assertThrows(IllegalArgumentException.class, () -> _parser.parseExpression(expression));
    }
}