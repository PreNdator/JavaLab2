package expression.expressions;

import expression.interfaces.IExpression;

public class NumberExpression implements IExpression {
    private final double _number;

    public  NumberExpression(double number){
        _number = number;
    }

    @Override
    public double result(){
        return  _number;
    }

}
