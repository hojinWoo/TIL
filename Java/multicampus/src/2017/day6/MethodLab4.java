package day6;

public class MethodLab4 {

	public static void main(String[] args) {
		int array1[]={10,20,30};                               //매개변수타입 int[]
		int array2[]={100,500,300,200,400};
		int array3[]={1,10,3,4,5,8,7,6,9,2};
		
		int max1=maxNumArray(array1);                         //메서드명 지정
		int max2=maxNumArray(array2);
		int max3=maxNumArray(array3);
		
		System.out.printf("가장 큰 값은 %d입니다.%n",max1);   //%d는 num변수에의해 십진수로 내보내겠다
		System.out.printf("가장 큰 값은 %d입니다.%n",max2);
		System.out.printf("가장 큰 값은 %d입니다.%n",max3);
		}
	
	public static int maxNumArray(int nums[]){       //메서드명정의:maxNumArray,리턴값 int로지정
		int maxResult=nums[0];  
		for(int i=0;i<nums.length;i++){   
			if(maxResult<nums[i])
				maxResult=nums[i];
		}
		
		return maxResult;
}
}
/*[ 실습 4 ]

1. 클래스명 : MethodLab4
2. 정의해야 하는 메서드  
   메서드명 : maxNumArray
   매개변수 타입 : int[]
   리턴값의 타입 : int

매개변수에 전달된 배열의 원소값들중에서 가장 큰값리턴
   
3. main() 메서드에서 maxNumArray 를 3번 호출한다.
   다음 배열들을 전달하여
   배열 1 : 10, 20, 30
   배열 2 : 100, 500, 300, 200, 400
   배열 3 : 1, 10, 3, 4, 5, 8, 7, 6, 9, 2
  
   가장 큰 값은 30 입니다.
   가장 큰 값은 500 입니다.
   가장 큰 값은 10 입니다.*/
