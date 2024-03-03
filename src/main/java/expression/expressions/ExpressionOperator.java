package expression.expressions;

import expression.constants.OperationsConstants;
import expression.constants.StringOperationsConstants;
import expression.functional.ExpressionOperation;

public class ExpressionOperator implements IExpression {
    private final IExpression _left, _right;
    private final ExpressionOperation _operation;

    public ExpressionOperator(IExpression left, IExpression right, String operator){
        _left = left;
        _right = right;
        _operation = getOperation(operator);
    }

    private ExpressionOperation getOperation(String operator){
        switch (operator) {
            case StringOperationsConstants.ADD:
                return OperationsConstants.ADD;
            case StringOperationsConstants.SUB:
                return OperationsConstants.SUB;
            case StringOperationsConstants.MUL:
                return OperationsConstants.MUL;
            case StringOperationsConstants.DIV:
                return OperationsConstants.DIV;
            case StringOperationsConstants.POW:
                return OperationsConstants.POW;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    @Override
    public double result() {
        return _operation.applyOperation(_left, _right);
    }
}
