package solveequation.expression;

/**
 *
 * @author Admin
 */
// a ^ (1/b)
public class NthRoot extends Binary{
    public NthRoot(Quantity q1, Quantity q2){
        super(q1, q2);
    }
    public double getValue(){
        double val1 = realValue(q1);
        double val2 = realValue(q2);
        return  Math.pow(val1, 1.0/val2);
    }
}
