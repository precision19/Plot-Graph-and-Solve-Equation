package solveequation.function;

import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Message {
    public String message;
    public Message(String message){
        this.message = message;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return message;
    }
}
