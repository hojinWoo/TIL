package day16;

class LocalTest{
	String name ="Java";
	void pr(final String s) { //매개변수 s
		final int su=100;  //지역변수 su , final 생략가능
		System.out.println(s+":"+su); //inner class 에서 접근할수 있는 지역변수는 final만된다 	
    	class Local {                 //final 안에서 변수를 맘대로 바꾸지못함
		void pr(String ls) {
			System.out.println("s:"+s);
			System.out.println("ls:"+ls);
			System.out.println(name);
			System.out.println(su);
		}
	}
	Local it=new Local();
	it.pr("Local Test");
}
}

public class InnerTest2 {

	public static void main(String[] args) {
		
	LocalTest l=new LocalTest();
	l.pr("Main Call");

	}

}
