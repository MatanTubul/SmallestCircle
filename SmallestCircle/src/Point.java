
public class Point {
	
	private double x;
	private double y;
	
	public Point(double n1,double n2)
	{
		this.x = n1;
		this.y = n2;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public double getDist(Point p)
	{
		return Math.sqrt((this.x - p.x)*(this.x - p.x) + (this.y - p.y)*(this.y - p.y));
	}

}
