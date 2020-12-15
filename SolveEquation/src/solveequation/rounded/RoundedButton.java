package solveequation.rounded;

/**
 *
 * @author Admin
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class RoundedButton extends JButton implements MouseListener{
    private boolean rounded;
    private boolean backgroundPainted;
    private boolean linePainted;
    private boolean entered;
    private boolean pressed;

    private Color enteredColor;
    private Color pressedColor;
    private Color gradientBackgroundColor;
    private Color gradientLineColor;
    private Color lineColor;

    public RoundedButton(){
        super();
        rounded = false;
        backgroundPainted = true;
        linePainted = true;
        entered = false;
        pressed = false;

        enteredColor = getBackground().brighter();
        pressedColor = getBackground().darker();
        lineColor = Color.BLACK;
        setContentAreaFilled(false);
        setFocusPainted(false);
        
    }

    public void setBackground(Color color){
        super.setBackground(color);
        enteredColor = color.brighter();
        pressedColor = color.darker();
    }
    
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Shape s = (rounded) ? new RoundRectangle2D.Float(1, 1, getWidth()-2, getHeight()-2, getHeight()-2, getHeight()-2)
                : new Rectangle2D.Float(1, 1, getWidth()-2, getHeight()-2);
        if(backgroundPainted || (pressed && !backgroundPainted)){
            if(gradientBackgroundColor == null){
                g2.setColor(color());
            }
            else{
                GradientPaint paint = new GradientPaint(0, 0, getBackground(), getWidth(), getHeight(), gradientBackgroundColor);
                g2.setPaint(paint);
            }
            g2.fill(s);
        }
        if(linePainted){
            if(gradientLineColor == null){
                g2.setColor(isEnabled() ? lineColor : new Color(204, 204, 204));
            }
            else{
                GradientPaint paint = new GradientPaint(0, 0, lineColor, getWidth(), getHeight(), gradientLineColor);
                g2.setPaint(paint);
            }
            g2.draw(s);
        }
        super.paintComponent(g);
    }
    
    private Color color(){
        if(!isEnabled()){
            return new Color(204, 204, 204);
        }
        Color temp = getBackground();
        if(pressed)return pressedColor;
        if(entered) return enteredColor;
        return temp;
    }

    public void setRounded(boolean rounded) {
        this.rounded = rounded;
    }

    public void setBackgroundPainted(boolean backgroundPainted) {
        this.backgroundPainted = backgroundPainted;
    }

    public void setLinePainted(boolean linePainted) {
        this.linePainted = linePainted;
    }

    public void setEnteredColor(Color enteredColor) {
        this.enteredColor = enteredColor;
    }

    public void setPressedColor(Color pressedColor) {
        this.pressedColor = pressedColor;
    }

    public void setGradientBackgroundColor(Color gradientBackgroundColor) {
        this.gradientBackgroundColor = gradientBackgroundColor;
    }

    public void setGradientLineColor(Color gradientLineColor) {
        this.gradientLineColor = gradientLineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        entered = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        entered = false;
    }

    
    
} 

