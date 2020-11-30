package solveequation.expression;

/**
 *
 * @author Admin
 */
public class Sine extends Unary{
    public Sine(Quantity q){
        super(q);
    }
    public double getValue(){
        double val = realValue(q);
        return Math.sin(val);
    }
}
