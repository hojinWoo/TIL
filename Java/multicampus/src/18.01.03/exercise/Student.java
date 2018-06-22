package exercise;

public class Student extends Human{
	private String number, major;
	public Student(){	}
	public Student(String name, int age, int height, int weight, String number, String major){
		super(name, age, height, weight);
		this.number = number;
		this.major = major;
	}	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String printInfomation(){
		return super.printInfomation() + "\t\t" + number + "\t\t" + major;
	}
}
