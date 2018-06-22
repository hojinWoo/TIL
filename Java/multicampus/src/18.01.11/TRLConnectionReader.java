package day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TRLConnectionReader {

	public static void main(String[] args) {
		try{
			URL u =new URL("http://unico2013.dothome.co.kr/resweb/exam1.jtml");
			URLConnection uc =u.openConnection();
			//내용추출
			InputStream is=(InputStream)uc.getContent();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String inputLine;
			while((inputLine=in.readLine())!=null)
					System.out.println(inputLine);
			System.out.println("Content-type:"+uc.getContentType());
			System.out.println("Date:"+new java.util.Date(uc.getDate()));
			System.out.println("Content-length:" +uc.getContentLength());
			
		}catch(MalformedURLException e){
			System.out.println("URL is not a URL I understand");
		}catch (IOException e){
			System.err.println(e);
		}
	}

}
