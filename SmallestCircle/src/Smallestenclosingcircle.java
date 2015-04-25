import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Smallestenclosingcircle extends JPanel {
	
	public static int SIZE = 30;
	public static double rangeMin= -15;
	public static double rangeMax= 150;
	static Circle circ;
	private static List <Point> ar = new ArrayList <Point>();
	
	public static void main(String[] args) {
		
		Random rand = new Random();
		Double tempx,tempy;
		
		
		for(int i=0;i<SIZE ; i++)
		{
			tempx = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
			tempy = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
			Point pt = new Point(tempx,tempy);
			ar.add(pt);
		}
		circ = makeCircle(ar);
		System.out.println(circ.r);
		JFrame f = new JFrame();

        f.setContentPane(new Smallestenclosingcircle());
        f.setSize(500,500);
        f.setVisible(true);
	
		
		
		System.out.println("the end");
		

	}
	
	public static Circle makeCircle(List<Point> points) {
		// Clone list to preserve the caller's data, randomize order
		List<Point> shuffled = new ArrayList<Point>(points);
		Collections.shuffle(shuffled, new Random());
		
		// Progressively add points to circle or recompute circle
		Circle c = null;
		for (int i = 0; i < shuffled.size(); i++) {
			Point p = shuffled.get(i);
			if (c == null || !c.contains(p))
				c = makeCircleOnePoint(shuffled.subList(0, i + 1), p);
		}
		return c;
	}
	
	
	// One boundary point known
	private static Circle makeCircleOnePoint(List<Point> points, Point p) {
		Circle c = new Circle(p, 0);
		for (int i = 0; i < points.size(); i++) {
			Point q = points.get(i);
			if (!c.contains(q)) {
				if (c.r == 0)
					c = makeDiameter(p, q);
				else
					c = makeCircleTwoPoints(points.subList(0, i + 1), p, q);
			}
		}
		return c;
	}
	
	
	// Two boundary points known
	private static Circle makeCircleTwoPoints(List<Point> points, Point p, Point q) {
		Circle temp = makeDiameter(p, q);
		if (temp.contains(points))
			return temp;
		
		Circle left = null;
		Circle right = null;
		for (Point r : points) {  // Form a circumcircle with each point
			Point pq = q.subtract(p);
			double cross = pq.cross(r.subtract(p));
			Circle c = makeCircumcircle(p, q, r);
			if (c == null)
				continue;
			else if (cross > 0 && (left == null || pq.cross(c.c.subtract(p)) > pq.cross(left.c.subtract(p))))
				left = c;
			else if (cross < 0 && (right == null || pq.cross(c.c.subtract(p)) < pq.cross(right.c.subtract(p))))
				right = c;
		}
		return right == null || left != null && left.r <= right.r ? left : right;
		
	}
	
	
	static Circle makeDiameter(Point a, Point b) {
		return new Circle(new Point((a.x + b.x)/ 2, (a.y + b.y) / 2), a.distance(b) / 2);
	}
	
	
	static Circle makeCircumcircle(Point a, Point b, Point c) {
		// Mathematical algorithm from Wikipedia: Circumscribed circle
		double d = (a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) * 2;
		if (d == 0)
			return null;
		double x = (a.norm() * (b.y - c.y) + b.norm() * (c.y - a.y) + c.norm() * (a.y - b.y)) / d;
		double y = (a.norm() * (c.x - b.x) + b.norm() * (a.x - c.x) + c.norm() * (b.x - a.x)) / d;
		Point p = new Point(x, y);
		return new Circle(p, p.distance(a));
	}
	 public void paint(Graphics g)
	    {
	        super.paint(g);
	       g.setColor(Color.blue);
			
	       
	        
	       g.drawOval((int)(circ.c.x-circ.r+150), (int)(circ.c.y-circ.r+150), (int)(2*circ.r), (int)(2*circ.r));
	        for (int i=0 ; i<ar.size(); i++){
	        	g.setColor(Color.red);
	        	g.drawLine((int)ar.get(i).x+150, (int)ar.get(i).y+150, (int)ar.get(i).x+1+150, (int)ar.get(i).y+1+150);
	        	
	        	}
	        
	        }
	    
	

}
