package day8;
import java.util.Scanner;
class GradeExpr1{
	int[] jumsu;
	GradeExpr1(int[] jumsu){
		this.jumsu =jumsu;
	}
	//평균점수
	double getAverage(){
		double average = (double)getTotal()/jumsu.length;		
		return average;
	}
	//총합
	int getTotal(){
		int total=0;
		for (int i = 0; i < jumsu.length; i++) {
			total = total+jumsu[i];
		}
		return total;
	}
	//최고점수
	int getGoodScore(){
		int best=jumsu[0];
		for (int i = 0; i < jumsu.length; i++) {
			if(best<jumsu[i]) best=jumsu[i];
		}
		return best;
	}
	//최저점수
	int getBadScore(){
		int bad=jumsu[0];
		for (int i=0; i < jumsu.length; i++) {
			if(bad>jumsu[i]) bad=jumsu[i];
		}
		return bad;
	}
}

public class GradeTest1 {
	public static void main(String[] args) {
		System.out.print("몇 개의 데이터를 입력하시겠어요? : ");
		Scanner scan = new Scanner(System.in);
		int dataCount = scan.nextInt();
		int score[]=new int[dataCount];		
		System.out.print("데이터를 입력해 주세요 : ");
		for (int i = 0; i < score.length; i++) {
			score[i]=scan.nextInt();
		}
		System.out.println();
		//점수 출력
		System.out.print("점수들 : ");
		for (int i = 0; i < score.length; i++) {
			if (i < score.length - 1)
				System.out.print(score[i] + ", ");
			else
				System.out.print(score[i]);
		}
		System.out.println();
		//클래스 생성
		GradeExpr1 ge = new GradeExpr1(score);
		//총점 출력
		System.out.print("총점 : "+ ge.getTotal()+"\n");
		System.out.printf("평균 : %.2f%n",ge.getAverage());
		System.out.printf("최고 점수 : %d%n",ge.getGoodScore());
		System.out.printf("최저 점수 : %d%n",ge.getBadScore());
		scan.close();
	}
}



