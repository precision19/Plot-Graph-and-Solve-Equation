package solveequation.function;

import java.text.DecimalFormat;

/**
 *
 * @author Admin
 */
public class Quadratic implements Equation{
    private double a, b, c, sol1, sol2;
    private Message message;

    public Quadratic(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
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

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getSol1() {
        return sol1;
    }

    public void setSol1(double sol) {
        this.sol1 = sol1;
    }
    
    public double getSol2(){
        return sol2;
    }
    
    public void setSol2(double sol){
        this.sol2 = sol2;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
    
    
    
    public void solve(){
        DecimalFormat df = new DecimalFormat("#.#####");
        if(a == 0){
            Linear linear = new Linear(b, c);
            linear.solve();
            message = linear.getMessage();
            return;
        }
        else{
            double delta = b*b  - 4*a*c;
            if(delta == 0){
                sol1 = -b/(2*a);
                sol2 = -b/(2*a);
                message = new Message("∆ = 0\nPhương trình có nghiệm kép: \nx1 = x2 = " + df.format(sol2));                
                return;
            }
            else if(delta > 0){
                sol1 = (-b+Math.sqrt(delta))/(2*a);
                sol2 = (-b-Math.sqrt(delta))/(2*a);
                message = new Message("∆ = " + df.format(delta) + " > 0\nPhương trình có 2 nghiệm phân biệt: \n"
                        + "x1 = " + df.format(sol1) + "\n" + "x2 = " + df.format(sol2));    
            }
            else{
                message = new Message("∆ = " + df.format(delta) + " < 0\nPhương trình có 2 nghiệm phức: \n" + 
                        "x1 = " + df.format(-b/2/a) + " + " + df.format(Math.sqrt(-delta)/2/a) + "i" + "\n" + "x2 = " + df.format(-b/2/a) + " + " + df.format(Math.sqrt(-delta)/2/a) + "i");
            } 
        }
        
    }

}
