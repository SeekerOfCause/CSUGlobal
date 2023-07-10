package CriticalThinking.Module4;

public class Sphere extends Shape{
	
	double radius;
	double pi = Math.PI;
	
	public Sphere(double rad) {
		radius = rad;
		
		
	}
	
	public String toString() {
		
		String calcs = null;
		
		System.out.println("The suface area of this sphere is: " + surface_area(this.radius));
		System.out.println("The volume of this sphere is: " + volume(this.radius));
		
		
		return calcs;
	}
	
	private double surface_area(double rad) {
		double surfaceArea = 0.0;
		
		surfaceArea = 4 * pi * (rad * rad);
		//4 pi r squared
		
		return surfaceArea;
	}
	
	private double volume(double rad) {
		double volume = 0.0;
		
		volume = (4 / 3) * pi * (rad * rad);
		//4/3 pi r squared
		
		
		return volume;
		
	}

}
