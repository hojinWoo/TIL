package day3;
import java.util.Scanner;
public class ContinueTest {
	public static void main(String[] args) {
		final char DECO_MARK = '$';		
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.print("숫자를 입력하세요 : ");	
			int num = scan.nextInt();
			
			for (int i = 1; i <= num; i++) {
				for (int j = 1; j <= i; j++) {
					System.out.print(DECO_MARK);
				}
				System.out.println();
			}			
			System.out.print("계속 수행하려면 y를 입력하세요.. : ");
			String answer = scan.next();
			if(!answer.equals("y")) {
				System.out.println("수행을 종료합니다.");
				break;
			} 
		}
		scan.close();
	}
}






