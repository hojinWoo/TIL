package day4;
public class ArrayLab3 {
	public static void main(String[] args) {
		// 1-26난수 배열
		int nums[] = new int[10];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = (int) (Math.random() * 26) + 1;
		}
		char ch[] = new char[nums.length];
		for (int j = 0; j < ch.length; j++) {
				ch[j]=(char)(nums[j]+64);

			
			
			
			/*switch (nums[j]) {
			case 1:
				ch[j] = 'A';
				break;
			case 2:
				ch[j] = 'B';
				break;
			case 3:
				ch[j] = 'C';
				break;
			case 4:
				ch[j] = 'D';
				break;
			case 5:
				ch[j] = 'E';
				break;
			case 6:
				ch[j] = 'F';
				break;
			case 7:
				ch[j] = 'G';
				break;
			case 8:
				ch[j] = 'H';
				break;
			case 9:
				ch[j] = 'I';
				break;
			case 10:
				ch[j] = 'J';
				break;
			case 11:
				ch[j] = 'K';
				break;
			case 12:
				ch[j] = 'L';
				break;
			case 13:
				ch[j] = 'M';
				break;
			case 14:
				ch[j] = 'N';
				break;
			case 15:
				ch[j] = 'O';
				break;
			case 16:
				ch[j] = 'P';
				break;
			case 17:
				ch[j] = 'Q';
				break;
			case 18:
				ch[j] = 'R';
				break;
			case 19:
				ch[j] = 'S';
				break;
			case 20:
				ch[j] = 'T';
				break;
			case 21:
				ch[j] = 'U';
				break;
			case 22:
				ch[j] = 'V';
				break;
			case 23:
				ch[j] = 'W';
				break;
			case 24:
				ch[j] = 'X';
				break;
			case 25:
				ch[j] = 'Y';
				break;
			case 26:
				ch[j] = 'Z';
				break;
			}*/

		}
		for (int i = 0; i < nums.length; i++) {
			if(i<nums.length-1){
				System.out.print(nums[i] + ",");
			}
			else {
				System.out.print(nums[i]);				
			}
		}

		System.out.println();
		for (int i = 0; i < ch.length; i++) {
			if(i<ch.length-1){
				System.out.print(ch[i] + ",");
			}
			else if(i==ch.length-1){
				System.out.print(ch[i]);
				break;
			}
		}
	}
}
