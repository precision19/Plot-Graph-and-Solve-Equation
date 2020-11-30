package solveequation.expression;

/**
 *
 * @author Admin
 */
public class Ceiling extends Unary {
    public Ceiling(Quantity q){
        super(q);
    }
    public double getValue(){
        double val = realValue(q);
        return Math.ceil(val);
    }
}
