package solveequation.expression;

/**
 *
 * @author Admin
 */
public class Number extends Quantity{
    protected double number;
    
    public Number(double num){
        this.number = num;
    }
    
    public double getValue(){
        return number;
    }
}
