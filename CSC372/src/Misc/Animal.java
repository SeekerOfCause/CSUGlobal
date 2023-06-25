package Misc;

public class Animal {
	
		  protected String name;
		  
		  public Animal(String name) {
		    this.name = name;
		  }
		  
		  public void speak() {
		    System.out.println("Sound, sound... I am animal...");
		  }
		}

		class Dog extends Animal {
		  public Dog(String name) {
		    super(name);
		  }
		  
		  public void speak() {
		    System.out.println("Bark, bark... I am dog...");
		  }
		}
		


