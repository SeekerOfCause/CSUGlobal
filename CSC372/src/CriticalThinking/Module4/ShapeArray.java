package CriticalThinking.Module4;

public class ShapeArray {

	public static void main(String[] args) {


		Sphere sphere = new Sphere(4);
		Cyllinder cyll = new Cyllinder(7.2, 5.5);
		Cone cone = new Cone(3.9, 4.8);
		Shape[] shapes = new Shape[3];
		
		shapes[0] = sphere;
		shapes[1] = cyll;
		shapes[2] = cone;
		

		for (int i = 0; i < shapes.length; i++) {
			shapes[i].toString();
		}
	}

}
