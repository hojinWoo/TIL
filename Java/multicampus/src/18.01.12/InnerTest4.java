package day16;
interface Testable{
	final static String fs="Interface Test";
	abstract void output(String s);
}
public class InnerTest4 {
	void pr(Testable o){
		o.output("Test");
		System.out.println(Testable.fs);
	}
	public static void main(String[] args) {
		System.out.println("Main start!!");
		InnerTest4 n=new InnerTest4();
		//이 이름없는 이너클래스는 Testable와 Object의 자손
		n.pr(new Testable(){ //어떤클래스의 자식클래스가 되느냐의 따라서 이름없는 이너클래스
		int su=100;
		public void output(String s){
			System.out.println("Anonymous Class:"+s);
			System.out.println("Anonymous Class:"+su);
		}
	});
}
}
