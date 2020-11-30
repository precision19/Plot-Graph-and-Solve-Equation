package solveequation.function;

/**
 *
 * @author Admin
 */
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.List;
import solveequation.view.MainFrame;
import solveequation.list.Category;
import solveequation.view.Graph;
import solveequation.view.Equation;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import solveequation.view.Exit;

//import solveequation.view.HomePage;

public class Control{
    private String selected = "";
    private List<Category> listItem;
    
    public Control(){
        
    }
       
    public void setView(JPanel jp, JLabel jb){
//        root.add(jp);
    }
    
    public void setEven (List<Category> listItem){
        this.listItem = listItem;
        for(Category x : listItem){
            x.getJlb().addMouseListener(new LabelEvent(x.getKind(), x.getJpn(), x.getJlb()));
        }
    }
    
    class LabelEvent implements MouseListener{
        private JFrame node;
        private String kind;
        private JPanel jpn;
        private JLabel jlb;

        public LabelEvent(String kind, JPanel jpn, JLabel jlb) {
            this.kind = kind;
            this.jpn = jpn;
            this.jlb = jlb;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
//            selected = kind;
            switch(kind){
//                case "HomePage":
//                    node = new HomePage();
//                    break;
                case "Linear":
                    node = new LinearFrame();
                    break;
                case "Quadratic":
                    node = new QuadraticFrame();
                    break;
                case "Cubic":
                    node = new CubicFrame();
                    break;
                case "Find":
                    node = new FindFrame();
                default:
//                    node = new SolveFrame();
                    break;
            }
//            root.removeAll();
//            root.setLayout(new BorderLayout());
//            root.add(node);
//            root.validate();
//            root.repaint();
              setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            selected = kind;
            jpn.setBackground(new Color(255, 215, 0));
            jlb.setBackground(new Color(255, 215, 0));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            jpn.setBackground(new Color(255, 215, 0));
            jlb.setBackground(new Color(255, 215, 0));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpn.setBackground(new Color(255, 215, 0));
            jlb.setBackground(new Color(255, 215, 0));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!selected.equalsIgnoreCase(kind)){
                jpn.setBackground(new Color(255, 255, 255));
                jlb.setBackground(new Color(255, 255, 255));
            }
        }
    }
    
    private void setChangeBackground(String kind){
        for(Category x : listItem){
            if(x.getKind().equalsIgnoreCase(kind)){
                x.getJpn().setBackground(new Color(255, 215, 0));
                x.getJlb().setBackground(new Color(255, 215, 0));
            }
            else{
                x.getJpn().setBackground(new Color(255, 255, 255));
                x.getJlb().setBackground(new Color(255, 255, 255));
            }
        }
    }
}
