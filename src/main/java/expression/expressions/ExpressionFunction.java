package expression.expressions;

import expression.constants.OperationsConstants;
import expression.constants.StringOperationsConstants;
import expression.functional.SingleExpressionOperation;

public class ExpressionFunction implements IExpression {
    private final IExpression _expression;
    private final SingleExpressionOperation _function;

    public ExpressionFunction(IExpression expression, String function){
        _expression = expression;
        _function = getFunction(function);
    }

    private SingleExpressionOperation getFunction(String function){
        switch (function) {
            case StringOperationsConstants.COS:
                return OperationsConstants.COS;
            case StringOperationsConstants.SIN:
                return OperationsConstants.SIN;
            case StringOperationsConstants.TAN:
                return OperationsConstants.TAN;
            case StringOperationsConstants.SQRT:
                return OperationsConstants.SQRT;
            default:
                throw new IllegalArgumentException("Unknown function: " + function);
        }
    }

    @Override
    public double result() {
        return _function.applyOperation(_expression);
    }
}
