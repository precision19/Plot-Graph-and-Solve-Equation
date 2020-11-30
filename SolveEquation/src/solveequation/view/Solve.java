package solveequation.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import solveequation.expression.Function;
import solveequation.syntax.ExpressionSyntax;

public class Solve extends JPanel implements MouseWheelListener, KeyListener, Runnable {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;

	private BufferedImage buff;
	private Graphics2D g2d;
	
	private ExpressionSyntax parser;
	private Function function;
	
	private double windowX, windowY, windowWidth, windowHeight;
	private Point mousePt;
	
	private String textBox;
        
        Set<Double>solution = new HashSet<Double>();
	
	public Solve() {
		
		addKeyListener(this);
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();
                repaint();
            }
        });
        
		setFocusable(true);
//		requestFocusInWindow();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		
		buff = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g2d = buff.createGraphics();
		
		parser = new ExpressionSyntax();
		textBox = "";
	}
	
	// Time variables
	private double yVar = 0.0;	// Constantly increasing
	private double zVar = 0.0;	// Cycles smoothly from -1 to 1
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);

		          System.out.println(textBox);
                List<Double> xs = new ArrayList<>();
                List<Double> ys = new ArrayList<>();
                for (int x = 0; x < WIDTH; x++) {
                        double yy = 0.0;
                        double scaledX = x;
                        xs.add(scaledX);
                }					
                //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setFont(new Font("Times new roman", Font.ITALIC, 40));
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillRect(0, HEIGHT - g2d.getFontMetrics().getHeight(), WIDTH, HEIGHT);
                g2d.setColor(Color.BLACK);
                g2d.drawString("f(x) = " + textBox, 0.0f, HEIGHT - 10.0f);
			
		
		
		//g.drawImage(buff, 0, 0, null);
	}
	
	@Override
	public void run() {
		boolean running = true;
//		
//		long oldTime = 0;
//		double dt = 0.0;
//		
		while (running) {
//			
//			dt = (newTime - oldTime) / 1000000000.0;
//			oldTime = newTime;
//			
////			updateDT(dt);
			repaint();
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			if (textBox.length() > 0) {
				textBox = textBox.substring(0, textBox.length() - 1);
			}
		} else if (Character.isLetterOrDigit(e.getKeyChar()) || e.getKeyChar() == '^' || e.getKeyChar() == '-' ||
				e.getKeyChar() == '+' || e.getKeyChar() == '*' || e.getKeyChar() == '/' || e.getKeyChar() == '(' ||
				e.getKeyChar() == ')' || e.getKeyChar() == '%' || e.getKeyChar() == ',' || e.getKeyChar() == '.') {
			textBox += e.getKeyChar();
                }
                else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    function = parser.parse(textBox);
                    if (function == null) {
                            textBox = "";
                    }
		}
//                    else if(e.getKeyCode() == KeyEvent.VK_SHIFT){
//                        continue;
//                    }
//                else textBox += e.getKeyChar();
            }

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}
        
        
}
