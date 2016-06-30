import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class MyMouseListener implements MouseListener, MouseMotionListener {
	
	private FermatPoint tester;
	Point draggedPoint;
	
	public MyMouseListener (FermatPoint tester) {
		this.tester = tester;
		draggedPoint = null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(draggedPoint == null) {
			Point mouse = new Point(e.getX(), e.getY());
			
			if (distance(mouse, tester.p1) < (int)tester.RADIUS) {
				draggedPoint = tester.p1;
			}
			else if (distance(mouse, tester.p2) < (int)tester.RADIUS) {
				draggedPoint = tester.p2;
			}
			else if (distance(mouse, tester.p3) < (int)tester.RADIUS) {
				draggedPoint = tester.p3;
			}
			else {
				draggedPoint = null;
			}
		}
		else {
			draggedPoint = null;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//draggedPoint = null;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		if(draggedPoint != null) {
			draggedPoint.setLocation(e.getX(), e.getY());
			tester.repaint();
		}
	}
	
	private double distance(Point p1, Point p2) {
		return Math.sqrt(Math.abs(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2)));
	}
}
