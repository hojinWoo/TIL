package day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTest1 {

	public static void main(String[] args) {
		try{
			URL req= new URL("http://www.weather.go.kr/wid/queryDFSRSS.jsp?zone=1153053000");
			InputStream is = req.openStream(); 
			InputStreamReader isr = new InputStreamReader(is,"UTF-8");  //문자 한개씩읽는 작업 수행
			BufferedReader br = new BufferedReader(isr);                //문자 한줄씩읽는 작업 수행
			
			while(true){
				String data=br.readLine();//한바이트씩 읽는다
				if(data==null)
					break;
				System.out.print(data);
			}
		}catch(MalformedURLException e){
			System.out.println("URL문자열 오류:"+e.getMessage());
		}catch(IOException e){
			System.out.print("IO오류:"+e.getMessage());
		}
	

	}

}
