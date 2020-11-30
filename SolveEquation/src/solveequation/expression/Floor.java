package solveequation.expression;

/**
 *
 * @author Admin
 */
public class Floor extends Unary{
    public Floor(Quantity q){
        super(q);
    }
    public double getValue(){
        double val = realValue(q);
        return Math.floor(val);
    }
}
