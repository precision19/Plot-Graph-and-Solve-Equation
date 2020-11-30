package solveequation.expression;

/**
 *
 * @author Admin
 */
public class Tangent extends Unary{
    public Tangent(Quantity q){
        super(q);
    }
    public double getValue(){
        double val = realValue(q);
        return Math.tan(val);
    }
}
