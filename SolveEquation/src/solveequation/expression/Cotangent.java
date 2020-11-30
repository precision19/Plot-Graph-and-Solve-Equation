package solveequation.expression;

/**
 *
 * @author Admin
 */
public class Cotangent extends Unary{
    public Cotangent(Quantity q){
        super(q);
    }
    public double getValue(){
        double val = realValue(q);
        return 1.0/Math.tan(val);
    }
}
