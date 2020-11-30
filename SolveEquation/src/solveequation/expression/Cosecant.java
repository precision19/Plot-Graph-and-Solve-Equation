package solveequation.expression;

/**
 *
 * @author Admin
 */
public class Cosecant extends Unary{
    public Cosecant(Quantity q){
        super(q);
    }
    public double getValue(){
        double val = realValue(q);
        return 1.0/Math.sin(val);
    }
}
