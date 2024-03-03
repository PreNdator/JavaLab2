package expression.expressions;

import expression.functional.SingleExpressionOperation;
import expression.interfaces.IExpression;

public class ExpressionFunction implements IExpression {
    private IExpression _expression;
    private SingleExpressionOperation _function;

    @Override
    public double result() {
        return _function.applyOperation(_expression);
    }
}