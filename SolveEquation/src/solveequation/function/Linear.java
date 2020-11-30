package solveequation.function;

/**
 *
 * @author Admin
 */

// ax + b = 0;
public class Linear implements Equation{
    private Message message;
    private double a, b, sol;
    public Linear(double a, double b){
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
    
    public void setSol(double sol){
        this.sol = sol;
    }
    
    public double getSol(){
        return sol;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
    
    
    
    public void solve(){
        if(a == 0 && b == 0){
            message = new Message("Infinity solutions");
        }
        else if(a == 0 && b != 0){
            message = new Message("No solutions");
        }
        else{
            sol = -b/a;
            message = new Message("x =  " + sol + "\n");
        }
    }
}
