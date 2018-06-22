package day14;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Date;
public class SeralTest2 {
	public static void main(String[] args) throws Exception{
	FileInputStream fis= new FileInputStream("c:/iotest/test1.ser");
	ObjectInputStream ois = new ObjectInputStream(fis);
	Date value1=(Date)ois.readObject(); 
	Date value2=(Date)ois.readObject();
	System.out.println("Date객체 데이터:"+value1);
	System.out.println("Date객체 데이터:"+value2);
	
	ois.close();
	fis.close();
	System.out.println("직렬화 출력 완료");

	}

}
