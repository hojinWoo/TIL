package day4;
public class ArrayLab1 {
	public static void main(String[] args) {
		System.out.print("모든 원소의 값 : " );
		int nums[] = new int[10];
		//배열에 4-20 난수 저장
		for(int i=0; i<nums.length; i++){
			nums[i] = (int)(Math.random()*17)+4;
			if(i<nums.length-1){
				System.out.print(nums[i] + ",");
			} else {
				System.out.print(nums[i]);				
			}
		}		
		System.out.println();		
		//모든 원소의 합
		int total=0;
		for(int i=0; i<nums.length; i++){
			total+=nums[i];
		}		
		System.out.print("모든 원소의 합 : " + total);
	}
}
