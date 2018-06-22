package day3;
public class ForLab3 {
	public static void main(String[] args) {
		for(int i=9; i>=5; i--){
			System.out.print(i+"´Ü:");
			for(int j=1; j<=9; j++){
				System.out.print(i+"x"+j+"="+(i*j)+"\t");
			}
			System.out.println();
		}
	}
}
