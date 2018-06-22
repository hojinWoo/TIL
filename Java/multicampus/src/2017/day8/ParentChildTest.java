package day8;
class Parent extends java.lang.Object{
	int x=1;
	int y=2;
	public String toString() {
		return super.toString() + " : Parent 클래스의 객체임!!";
	}
}
class Child extends Parent{
	int x=10;
	void pr() {
		int x=100;
		System.out.println(x);				// 100
		System.out.println(this.x);		// 10
		System.out.println(super.x);		// 1
		System.out.println(y);				// 2
		System.out.println(this.y);		// 2
		System.out.println(super.y);		// 2
		//System.out.println(z);				// 
	}	
}

public class ParentChildTest {
	public static void main(String[] args) {		
		Parent p = new Parent();
		System.out.println(p.toString());
		System.out.println(p);
		System.out.println("----------------");
		Child c = new Child();
		System.out.println(c.toString());
		System.out.println(c);
		c.pr();
	}
}
