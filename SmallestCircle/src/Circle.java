import java.util.Collection;


public class Circle {
		
	public final Point middlePoint;   
	public final double radius; 
	
	
	public Circle(Point middleP, double rad) {
		middlePoint = middleP;
		radius = rad;
	}
	
	//check if the point inside the circle
	public boolean PointInsideCircle(Point point) {
		if (middlePoint.distanceToP(point) <= radius)
			return true;
		return false;
	}
	
	//check if all the points inside the circle
	public boolean TotalPointsInCircle(Collection<Point> points) {
		
		for (Point currentPoint : points) {
			if (PointInsideCircle(currentPoint)==false)
				return false;
		}
		return true;
	}
}
