package day6;

public class MethodLab5 {

	public static void main(String[] args) {
		int r1[] = powerArray(2);                    //메인메서드에서 powerArray 를 3번 호출한다.
		int r2[] = powerArray(3);                    //매개변수 타입 : int
		int r3[] = powerArray(4);


		for (int i = 0; i < 10; i++)
			System.out.print(r1[i] + " ");
		System.out.println();
		for (int i = 0; i < 10; i++)
			System.out.print(r2[i] + " ");
		System.out.println();
		for (int i = 0; i < 10; i++)
			System.out.print(r3[i] + " ");
		System.out.println();
		
	}

	/*
	 * public static int getRandom(int n) 1 부터 n 까지 범위의 난수 리턴 public static int
	 * getRandom(int n1, int n2) n1 부터 n2 범위의 난수 리턴
	 */

	public static int[] powerArray(int nums) {  //리턴값의 타입 : int[]
		int arr[] = new int[10];          //1부터 10사이의 자연수 배열을 만들어서
		for (int i = 0; i < 10; i++)
			arr[i] = (i + 1) * nums;  //각각의 원소를 매개변수에 전달된 값만큼 배수로 만들어
		return arr;                       //리턴한다

	}

}


/*[ 실습 5 ]

1. 클래스명 : MethodLab5
2. 정의해야 하는 메서드  
   메서드명 : powerArray
   매개변수 타입 : int
   리턴값의 타입 : int[]

   1부터 10사이의 자연수 배열을 만들어서
   각각의 원소를 매개변수에 전달된 값만큼 배수로 만들어
   리턴한다.

3. main() 메서드에서 powerArray 를 3번 호출한다.
   
	int r1[] = powerArray(2);
	int r2[] = powerArray(3);
	int r3[] = powerArray(4);

                [ 결과 출력 ]
	2 4 6 8 10 12 14 16 18 20
 	3 6 9 12 15 18 21 24 27 30
	4 8 12 16 20 24 28 32 36 40*/	

