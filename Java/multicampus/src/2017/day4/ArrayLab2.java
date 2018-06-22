package day4;
public class ArrayLab2 {
	public static void main(String[] args) {
		//배열 선언 생성 초기
		char[] letter = {'J', 'a', 'v', 'a', 'D', 'u', 'k', 'e'};
		for(int i=0; i<letter.length; i++){
			//소문자이면
			if(letter[i] >= 'a' && letter[i] <= 'z'){
				letter[i] = (char)(letter[i] - 32);
				}
			//대문자이면
			else if(letter[i] >= 65 && letter[i] <= 90){
				letter[i] = (char)(letter[i] + 32);
				}
		}
		for(int j=0; j<letter.length; j++){
			if(j<letter.length-1){
				System.out.print(letter[j] + ",");
			}
			else if(j==letter.length-1){
				System.out.print(letter[j]);
				break;
			}
		}		
	}
}

/*

소문자와 대문자의 아스키 코드의 차이가 32인것을 활용한다.

예를 들어, A 에서 a 로 만들려면 32를 더하고 반대의 경우엔 빼면 된다. 

알파벳 아스키 코드는 다음과 같다. 알파벳은 총 26개 문자로 되어 있음. 

A-65  Z-90  a-97  z-122 

65<= char <=90 이면 대문자, 97<= char <= 122 이면 소문자이다. 

또는

'A'<= char <='Z' , 'a'<=char <'z' 
*/