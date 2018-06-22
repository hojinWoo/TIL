package day8;
import java.util.Scanner;
class GradeExpr2 {
	int[] jumsu;
	GradeExpr2(int[] jumsu) {
		this.jumsu = new int[jumsu.length];
		for (int i = 0; i < jumsu.length; i++) {
			this.jumsu[i] = jumsu[i];
		}
	}
	double getAverage() {
		double avg;
		avg = getTotal() / jumsu.length;
		return avg;
	}
	int getTotal() {
		int sum = 0;
		for (int i = 0; i < jumsu.length; i++) {
			sum += jumsu[i];
		}
		return sum;
	}
	int getGoodScore() {
		int good = 0;
		for (int i = 0; i < jumsu.length; i++) {
			if (good < jumsu[i])
				good = jumsu[i];
		}
		return good;
	}
	int getBadScore() {
		int bad = 10000000;
		for (int i = 0; i < jumsu.length; i++) {
			if (bad > jumsu[i])
				bad = jumsu[i];
		}
		return bad;
	}
}
public class GradeTest2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("처리할 점수의 갯수를 입력해주세요 : ");
		int count = sc.nextInt();
		int jumsu[] = new int[count];
		System.out.print("점수들을 입력해주세요 : ");
		for (int i = 0; i < count; i++) {
			jumsu[i] = sc.nextInt();
		}
		GradeExpr2 g = new GradeExpr2(jumsu);
		sc.close();
		printScore(jumsu);
		System.out.printf("총점 : %d\n", g.getTotal());
		System.out.printf("평균 : %.2f\n", g.getAverage());
		System.out.printf("최고점수 : %d\n", g.getGoodScore());
		System.out.printf("최저점수 : %d\n", g.getBadScore());
	}
	public static void printScore(int[] jumsu) {
		System.out.print("점수들 : ");
		for (int i = 0; i < jumsu.length; i++) {
			if (jumsu.length == (i + 1))
				System.out.printf("%d", jumsu[i]);
			else
				System.out.printf("%d,", jumsu[i]);
		}
		System.out.println();
	}
}
