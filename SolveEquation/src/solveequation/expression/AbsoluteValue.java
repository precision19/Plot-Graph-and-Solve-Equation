package solveequation.expression;

/**
 *
 * @author Admin
 */
public class AbsoluteValue extends Unary{
//    private Quantity q;
    public AbsoluteValue(Quantity q){
        super(q);
    }
    public double getValue(){
        double val = realValue(q);
        return Math.abs(val);
    }
}
