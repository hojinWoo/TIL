package day4;
public class TwoArrayTest2 {
	public static void main(String[] args) {
		int[][] nums = {
								{ 1, 2, 3, 4 },
								{ 5, 6, 7, 8 },
								{ 9,10,11,12},
								{13,14,15,16}
						    };
		// 2행 3열을 출력해 본다.
		System.out.println(nums[1][2]);
		// 4행의 모든 데이터를 출력해 본다.(하나의 행에)
		for(int j=0; j < nums[3].length; j++)
			System.out.print(nums[3][j]+"\t");
		System.out.println();
		// 3열의 모든 데이터를 출력해 본다.(하나의 행에)
		for(int i=0; i < nums.length; i++)
			System.out.print(nums[i][2]+"\t");
		System.out.println();
		// 모든 행과 모든 열을 출력(행단위로 개행 처리하여)
		for(int i=0; i < nums.length; i++) {
			for(int j=0; j < nums[i].length; j++)
				System.out.print(nums[i][j]+"\t");
			System.out.println();
		}		
		for(int i=0; i < nums.length; i++) {
			for(int j=0; j < nums[i].length; j++) {
				// 왼쪽 대각선의 값들만 행단위로 출력
			}
		}
		for(int i=0; i < nums.length; i++) {
			for(int j=0; j < nums[i].length; j++) {
				// 오른쪽 대각선의 값들만 행단위로 출력
			}
		}	
	}
}








