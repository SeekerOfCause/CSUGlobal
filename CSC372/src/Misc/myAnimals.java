package Misc;

public class myAnimals {
	  public static void main(String[] args) {
	    Animal animal = new Animal("Generic Animal");
	    Dog dog = new Dog("Rex");

	    System.out.println(animal.name + ": ");
	    animal.speak();
	    System.out.println("");
	    System.out.println(dog.name + ": ");
	    dog.speak();
	    
	  }
	}