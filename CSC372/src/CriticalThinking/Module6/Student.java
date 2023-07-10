package CriticalThinking.Module6;

public class Student {
    private int rollno;
    private String name;
    private int age;
    private String address;
    

    public Student(int rollno, String name, int age, String address) {
        this.rollno = rollno;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Student(String numTitle, String nameTitle, String ageTitle, String addressTitle) {
		
	}

	public int getRollno() {
        return rollno;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return String.format("\n%-5d\t%-15s\t%-5d\t%-20s", rollno, name, age, address);
    }

	public int getAge() {
		// TODO Auto-generated method stub
		return age;
	}

}
