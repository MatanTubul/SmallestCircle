
public class Circle {
	
	private Point center;
	private double radius;
	public Circle(Point p,double r)
	{
		this.center = new Point(p.getX(),p.getY());
		this.radius = r;
		
	}
	
	public Point getCenter()
	{
		return this.center;
	}
	public double getRadius()
	{
		return this.radius;
	}

}
