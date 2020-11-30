package solveequation.expression;

/**
 *
 * @author Admin
 */
public class Secant extends Unary{
    public Secant(Quantity q){
        super(q);
    }
    public double getValue(){
        double val = realValue(q);
        return 1.0/Math.cos(val);
    }
}
