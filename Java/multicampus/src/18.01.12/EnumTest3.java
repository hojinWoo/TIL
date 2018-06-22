package day16;
class Tour{
enum Season {
	SPRING,SUMMER,FALL,WINTER
}
}
public class EnumTest3 {

	public static void main(String[] args) {
		Tour.Season day[]=Tour.Season.values();//values는 모든상수를 배열로 추출한다
		for(Tour.Season value : day)           //valueof는 괄호안의 상수를 호출
			System.out.println(value);
		Tour.Season season = Tour.Season.valueOf("SUMMER"); 
		System.out.println("Tour.Season.valueOf(\"SUMMER\"):");
	}

}
