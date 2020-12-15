package solveequation.function;

import java.text.DecimalFormat;

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
        DecimalFormat df = new DecimalFormat("#.######");
        if(a == 0 && b == 0){
            message = new Message("Phương trình có vô số nghiệm!");
        }
        else if(a == 0 && b != 0){
            message = new Message("Phương trình vô nghiệm!");
        }
        else{
            sol = -b/a;
            message = new Message(df.format(a) + "x + " + df.format(b) + " = 0\n=> x = " + "-" + df.format(b) + "/" + df.format(a) + "\n=> x =  " + df.format(sol) + "\n");
        }
    }
}
