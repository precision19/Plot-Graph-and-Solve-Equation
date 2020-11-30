package solveequation.expression;

/**
 *
 * @author Admin
 */
public class Cosine extends Unary{
    public Cosine(Quantity q){
        super(q);
    }
    public double getValue(){
        double val = realValue(q);
        return Math.cos(val);
    }
}
