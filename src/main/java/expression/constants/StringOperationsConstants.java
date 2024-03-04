package expression.constants;

public class StringOperationsConstants {

    //Functions
    public static final String COS = "cos(";
    public static final String SIN = "sin(";
    public static final String TAN = "tg(";
    public static final String SQRT = "sqrt(";


    //Operators
    public static final char ADD = '+';
    public static final char SUB = '-';
    public static final char MUL = '*';
    public static final char DIV = '/';
    public static final char POW = '^';


    /**
     * @return An array of strings representing mathematical functions.
     */
    public static String[] getFunctions() {
        return new String[]{COS, SIN, TAN, SQRT};
    }

    /**
     * @return An array of strings representing mathematical operators.
     */
    public static char[] getOperators() {
        return new char[]{ADD, SUB, MUL, DIV, POW};
    }
}