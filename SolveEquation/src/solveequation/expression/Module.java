package solveequation.expression;

/**
 *
 * @author Admin
 */
public class Module extends Binary{
    public Module(Quantity q1, Quantity q2){
        super(q1, q2);
    }
    public double getValue(){
        double val1 = realValue(q1);
        double val2 = realValue(q2);
        return val1%val2;
    }
}
