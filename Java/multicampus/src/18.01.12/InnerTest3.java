package day16;

abstract class Test{
	Test(){
		System.out.println("NO Argument sample");
	}
	abstract void output(String s);
}


public class InnerTest3 {
	void pr(Test o){
		o.output("Test");
	}
	public static void main(String[] args) {
		System.out.println("Main start!!");
		InnerTest3 n=new InnerTest3();
		n.pr(new Test(){ //어떤클래스의 자식클래스가 되느냐의 따라서 이름없는 이너클래스
		int su=100;
		public void output(String s){
			System.out.println("Anonymous Class:"+s);
			System.out.println("Anonymous Class:"+su);
		}
	});

}

}
