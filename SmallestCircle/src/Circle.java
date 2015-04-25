import java.util.Collection;


public class Circle {
		
	public final Point c;   
	public final double radius; 
	
	
	public Circle(Point c, double r) {
		this.c = c;
		this.radius = r;
	}
	
	//check if point inside the circle
	
	public boolean PointInCircle(Point p) {
		return c.distance(p) <= radius;
	}
	
	//check if all the points block by circle
	
	public boolean TotalPointsInCircle(Collection<Point> ps) {
		for (Point p : ps) {
			if (!PointInCircle(p))
				return false;
		}
		return true;
	}
}
