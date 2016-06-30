import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class FermatPoint extends JPanel {
	
	public Point p1 = new Point(220, 170);
	public Point p2 = new Point(330, 200);
	public Point p3 = new Point(120, 300);
	
	private int minX, minY;
	private double distance;
	public double RADIUS = 6;
	
	private double data[][];
	
	public FermatPoint() {
		JFrame frame = new JFrame();
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setPreferredSize(new Dimension(500, 500));
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		
		this.data = new double[this.getWidth()][this.getHeight()];
		MyMouseListener listener = new MyMouseListener(this);
		this.addMouseMotionListener(listener);
		this.addMouseListener(listener);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		colorScreen(g);
		
		g.setColor(Color.GREEN);
		g.drawLine(p1.x, p1.y, p2.x, p2.y);
		g.drawLine(p2.x, p2.y, p3.x, p3.y);
		g.drawLine(p3.x, p3.y, p1.x, p1.y);
		
		int width = (int)RADIUS;
		g.setColor(Color.RED);
		g.fillOval(p1.x - width / 2, p1.y - width / 2, width, width);
		g.fillOval(p2.x - width / 2, p2.y - width / 2, width, width);
		g.fillOval(p3.x - width / 2, p3.y - width / 2, width, width);
	}
	
	private void colorScreen(Graphics g) {
		
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		
		for(int y = 0; y < this.getHeight(); ++y) {
			for (int x = 0; x < this.getWidth(); ++x) {
				
				double total = calcSum(new Point(x, y));
				data[x][y] = total;
				
				if (total < min) {
					min = total;
					distance = total / 3;
					minX = x;
					minY = y;
				}

				if (total > max) max = total;
			}
		}
		
		for(int y = 0; y < this.getHeight(); ++y) {
			for (int x = 0; x < this.getWidth(); ++x) {
				
				double low = 0.001;
				double high = 0.1;
				double value = 0.3;
				
				double ratio = (data[x][y] - min) / (max - min);
				//ratio = Math.sqrt(Math.sqrt(Math.sqrt(ratio)));
				
				if(ratio < low) {
					ratio = ratio / low * value;
				}
				else if (ratio > high) {
					ratio = 1 + (1 - value) / (1 - high) * (ratio - 1);
				}
				else {
					ratio = value;
				}
				
				ratio = Math.sqrt(ratio);
				
				g.setColor(new Color((float)ratio, (float)ratio, (float)ratio));
				g.fillRect(x, y, 1, 1);
			}
		}
	}
	
	private double calcSum(Point p) {
		
		double result = Math.sqrt(Math.abs(Math.pow(p.x - p1.x, 2) + Math.pow(p.y - p1.y, 2)));
		result += Math.sqrt(Math.abs(Math.pow(p.x - p2.x, 2) + Math.pow(p.y - p2.y, 2)));
		result += Math.sqrt(Math.abs(Math.pow(p.x - p3.x, 2) + Math.pow(p.y - p3.y, 2)));
		
		return result;
	}
	
	public static void main(String[] args) {
		new FermatPoint();
	}
}
