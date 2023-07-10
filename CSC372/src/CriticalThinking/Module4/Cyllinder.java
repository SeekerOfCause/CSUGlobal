package CriticalThinking.Module4;

public class Cyllinder extends Shape {
	
	double radius;
	double height;
	double pi = Math.PI;

	public Cyllinder(double rad, double ht) {
		radius = rad;
		height = ht;
	}
	
	public String toString() {
		
		String calcs = null;
		
		System.out.println("The surface area of this cyllinder is: " + surface_area(this.radius, this.height));
		System.out.println("The volume of this cyllinder is: " + volume(this.radius, this.height));
		
		
		return calcs;
	}
	
	private double surface_area(double rad, double ht) {
		double surfaceArea = 0.0;
		
		surfaceArea = ((2 * pi) * rad * ht) + ((2 * pi) * (rad * rad));
		//2pi r h + 2 pi r squared
		
		return surfaceArea;
	}
	
	private double volume(double rad, double ht) {
		double volume = 0.0;
		
		volume = pi * (rad * rad) * ht;
		//pi r squared height
		
		return volume;
		
	}
	

}
