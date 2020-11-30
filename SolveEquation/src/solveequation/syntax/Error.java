package solveequation.syntax;

/**
 *
 * @author Admin
 */
import javax.swing.JOptionPane;

public class Error {
    public Error(){
        
    }
    public void makeError(String error){
//        System.out.println(error);
       JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE); 
    }
}
