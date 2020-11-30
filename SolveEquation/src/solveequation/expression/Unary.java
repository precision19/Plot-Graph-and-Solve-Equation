package solveequation.expression;

/**
 *
 * @author Admin
 */
public abstract class Unary extends Quantity{
    protected Quantity q;
    
    public Unary(Quantity q){
        this.q = q;
    }
    public abstract double getValue();
}
