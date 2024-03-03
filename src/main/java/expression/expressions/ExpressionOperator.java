package expression.expressions;

import expression.functional.ExpressionOperation;
import expression.interfaces.IExpression;

public class ExpressionOperator implements IExpression {
    private IExpression _left, _right;
    private ExpressionOperation _operation;

    @Override
    public double result() {
        return _operation.applyOperation(_left, _right);
    }
}
