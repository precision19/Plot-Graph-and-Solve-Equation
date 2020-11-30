package solveequation.expression;

/**
 *
 * @author Admin
 */
public class SquareRoot extends Unary{
    public SquareRoot(Quantity q){
        super(q);
    }
    public double getValue(){
        double val = realValue(q);
        return Math.sqrt(val);
    }
}
