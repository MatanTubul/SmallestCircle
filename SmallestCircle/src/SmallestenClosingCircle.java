import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class SmallestenClosingCircle extends JPanel {
	
	public static int MaxPoints = 30;
	public static double rangeMin= -15;
	public static double rangeMax= 150;
	static Circle circ;
	private static List <Point> pointsArrayList = new ArrayList <Point>();
	
	public static void main(String[] args) {
		
		Random rand = new Random();		
		
		for(int i=0;i<MaxPoints ; i++) //create random points
		{
			Point tempPoint = new Point(rangeMin + (rangeMax - rangeMin) * rand.nextDouble(),rangeMin + (rangeMax - rangeMin) * rand.nextDouble());
			pointsArrayList.add(tempPoint);
		}
		
		circ = createCircle(pointsArrayList);
		System.out.println("Radius = "+circ.radius);
		System.out.println("Center is: x ="+circ.middlePoint.x+" "+ "y = "+circ.middlePoint.y);
		
		//painting the circle on the window
		JFrame f = new JFrame();

        f.setContentPane(new SmallestenClosingCircle());
        f.setSize(500,500);
        f.setVisible(true);	
		

	}
	
	 public void paint(Graphics g) //paint the smallest circle on the gui
	    {
	        super.paint(g);
	       g.setColor(Color.blue);
	       g.drawOval((int)(circ.middlePoint.x-circ.radius+150), (int)(circ.middlePoint.y-circ.radius+150), (int)(2*circ.radius), (int)(2*circ.radius));
	        for (int i=0 ; i<pointsArrayList.size(); i++){
	        	g.setColor(Color.red);
	        	g.drawLine((int)pointsArrayList.get(i).x+150, (int)pointsArrayList.get(i).y+150, (int)pointsArrayList.get(i).x+1+150, (int)pointsArrayList.get(i).y+1+150);
	        	
	        	}
	        
	        }
	 
	public static Circle createCircle(List<Point> points) {
		// Clone list to preserve the caller's data, randomize order
		List<Point> randomPointsList = new ArrayList<Point>(points);
		Collections.shuffle(randomPointsList, new Random());
		
		// recompute circle by adding random points
		Circle tempCilcle = null;
		for (int i = 0; i < randomPointsList.size(); i++) {
			Point p = randomPointsList.get(i);
			if (tempCilcle == null || tempCilcle.PointInsideCircle(p)==false)
				tempCilcle = CreateCircleWithOnePoint(randomPointsList.subList(0, i + 1), p);
		}
		return tempCilcle;
	}
	
	private static Circle CreateCircleWithOnePoint(List<Point> points, Point p) {
		Circle tempCilcle = new Circle(p, 0);
		for (int i = 0; i < points.size(); i++) {
			Point q = points.get(i);
			if (!tempCilcle.PointInsideCircle(q)) {
				if (tempCilcle.radius == 0)
					tempCilcle = createDiameter(p, q);
				else
					tempCilcle = createCircleWithTwoPoints(points.subList(0, i + 1), p, q);
			}
		}
		return tempCilcle;
	}
	
	private static Circle createCircleWithTwoPoints(List<Point> points, Point p, Point q) {
		Circle temp = createDiameter(p, q);
		if (temp.TotalPointsInCircle(points))
			return temp;
		
		Circle left = null;
		Circle right = null;
		for (Point r : points) {  
			Point pq = q.subtractP(p);
			double cross = pq.cross(r.subtractP(p));
			Circle circumCircle = createCircumCircle(p, q, r);
			if (circumCircle == null)
				continue;
			else if (cross > 0 && (left == null || pq.cross(circumCircle.middlePoint.subtractP(p)) > pq.cross(left.middlePoint.subtractP(p))))
				left = circumCircle;
			else if (cross < 0 && (right == null || pq.cross(circumCircle.middlePoint.subtractP(p)) < pq.cross(right.middlePoint.subtractP(p))))
				right = circumCircle;
		}
		if (right == null || left != null && left.radius <= right.radius)
			return left;
		return right;
		
		
	}
	
	
	static Circle createDiameter(Point p1, Point p2) {
		return new Circle(new Point((p1.x + p2.x)/ 2, (p1.y + p2.y) / 2), p1.distanceToP(p2) / 2);
	}
	
	
	static Circle createCircumCircle(Point p1, Point p2, Point p3) {
		double d = (p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y)) * 2;
		if (d == 0)
			return null;
		Point p = new Point((p1.norm() * (p2.y - p3.y) + p2.norm() * (p3.y - p1.y) + p3.norm() * (p1.y - p2.y)) / d, (p1.norm() * (p3.x - p2.x) + p2.norm() * (p1.x - p3.x) + p3.norm() * (p2.x - p1.x)) / d);
		return new Circle(p, p.distanceToP(p1));
	}
	

	    
	

}
