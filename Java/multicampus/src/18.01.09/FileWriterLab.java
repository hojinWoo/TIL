package day13;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

class KoreanDay {
	public static String day;
	public static String birth;
	static String korDayName[] = { "", "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" };
	static {
		GregorianCalendar cal = new GregorianCalendar();
		int numDay = cal.get(Calendar.DAY_OF_WEEK);
		day = korDayName[numDay];
		
		cal.set(Calendar.YEAR,1992);
		cal.set(Calendar.MONTH,Calendar.JANUARY);
		cal.set(Calendar.DATE,28);
		int birthday =cal.get(Calendar.DAY_OF_WEEK);
		birth=korDayName[birthday];
	}
}

public class FileWriterLab {

	public static void main(String[] args) {

		String path ="C:/iotest";
		File isDir=new File(path);
		if(!isDir.exists()){
			isDir.mkdirs();
		}
		try (FileWriter writer=new FileWriter("c:/iotest/lab_0109.txt");){
			writer.write("오늘은 ");
			writer.write(KoreanDay.day);
			writer.write("입니다.");	
			writer.write("\r\n");
			
			
			writer.write("이태우는 ");
			writer.write(KoreanDay.birth);
			writer.write("에 태어났습니다.");	
			System.out.println("수행 종료");
		}catch (IOException ioe){
			System.out.println("파일로 출력할수 없다");
		}

	}

}
