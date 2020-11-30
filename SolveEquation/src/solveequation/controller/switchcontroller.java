package solveequation.controller;

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

public class switchcontroller{
    private JFrame root;
    private JPanel jp;
    private String selected = "";
    private List<Category> listItem;
    
    public switchcontroller(){
        
    }
    
    public switchcontroller(JFrame root){
        this.root = root;
    }
    
    public void setView(JLabel jb){
        selected = "HomePage";
//        jp.setBackground(new Color(96, 100, 191));
        jb.setBackground(new Color(96, 100, 191));
//        root.removeAll();
//        root.setLayout(new BorderLayout());
//        root.add(new HomePage());
//        root.validate();
//        root.repaint();
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
                case "Graph":
                    node = new Graph();
                    break;
                case "Equation":
                    node = new Equation();
                    break;
                case "Exit":
                    node = new Exit();
                    break;
                default:
                    node = new MainFrame();
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
            jpn.setBackground(new Color(83, 220, 148));
            jlb.setBackground(new Color(83, 220, 148));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
//            jpn.setBackground(new Color(42, 42, 42));
//            jlb.setBackground(new Color(42, 42, 42));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpn.setBackground(new Color(83, 220, 148));
            jlb.setBackground(new Color(83, 220, 148));
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
                x.getJpn().setBackground(new Color(83, 220, 148));
                x.getJlb().setBackground(new Color(83, 220, 148));
            }
            else{
                x.getJpn().setBackground(new Color(255, 255, 255));
                x.getJlb().setBackground(new Color(255, 255, 255));
            }
        }
    }
}
