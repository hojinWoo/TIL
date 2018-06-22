package day14;
public class IntegerTest {
	public static void main(String[] args) {
		String s1 = Integer.toBinaryString(12); //이진수로 바까라
		String s2 = Integer.toBinaryString(88);  
	    
		System.out.printf("%s\n", s1+8);
		System.out.printf("%s\n", s2+8);
		
		int num1 = Integer.parseInt(s1 ,2);  //s1을 십진수로 인식
		int num2 = Integer.parseInt(s2, 2);
//		int num1 = Integer.parseInt(s1);
//		int num2 = Integer.parseInt(s2);
		
		System.out.printf("%d\n", num1);
		System.out.printf("%d\n", num2);
	}
}
