package solveequation.main;

import javax.swing.JFrame;

import solveequation.view.MainFrame;
import solveequation.syntax.ExpressionSyntax;
import solveequation.syntax.TokenString;


public class Main {
	
	public static void main(String[] args) {
		
            MainFrame frame = new MainFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1360, 800);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
	}
}
