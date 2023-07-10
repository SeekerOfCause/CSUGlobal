package CriticalThinking.Module4;

public class Cone extends Shape {
	
	double radius;
	double height;
	double pi = Math.PI;
	
	public Cone(double rad, double ht) {
		
		radius = rad;
		height = ht;
		
	}
	
	public String toString() {
		
		String calcs = null;
		
		System.out.println("The surface area of this cone is: " + surface_area(this.radius, this.height));
		System.out.println("The volume of this cone is: " + volume(this.radius, this.height));
		return calcs;
		
		
		
	}

	private double surface_area(double rad, double ht) {
		double surfaceArea = 0.0;
		
		surfaceArea = (pi * rad + (rad + Math.sqrt((ht * ht) + (rad + rad))));
		
		//pi r (r + sqrt(hsquared + r squared))
		
		return surfaceArea;
	}
	
	private double volume(double rad, double ht) {
		double volume = 0.0;
		
		volume = pi * (rad * rad) * (ht / 3.0);
		//pi r squared (h/3)
		
		return volume;
		
	}
	
}
