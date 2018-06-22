package day14;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Date;

import day6.Student;
public class SeralTest4 {
	public static void main(String[] args) throws Exception{
	FileInputStream fis= new FileInputStream("c:/iotest/test2.ser");
	ObjectInputStream ois = new ObjectInputStream(fis);
	Date value1=(Date)ois.readObject(); 
	Date value2=(Date)ois.readObject();
	Student st=(Student)ois.readObject();
	System.out.println("Date객체 데이터:"+value1);
	System.out.println("Date객체 데이터:"+value2);
	System.out.println("Student객체 데이터:");
	st.studentInfo();
	ois.close();
	fis.close();
	System.out.println("직렬화 출력 완료");

	}

}
