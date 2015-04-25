
public class Point {
	
	public final double x;
	public final double y;
	
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	
	public Point subtract(Point p) {
		return new Point(x - p.x, y - p.y);
	}
	
	//calculating Distance between 2 points
	public double distance(Point p) {
		return Math.sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y));
	}
	
	
	// Signed area / determinant thing
	public double cross(Point p) {
		return x * p.y - y * p.x;
	}
	
	
	// Magnitude squared
	public double norm() {
		return x * x + y * y;
	}
}
