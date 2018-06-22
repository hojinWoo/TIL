package day14;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class URLTest2 {

	public static void main(String[] args) {
		String imageName= "c:/iotest/duke1.jpg";  //오버라이딩모드
		try{
			URL req= new URL("http://fardamento.netshoes.net/BR/LNetshoes/production/20141031/BADGE/20141031BADGE0014096.jpg");
			InputStream is = req.openStream(); //URL코드를 오픈하기위한 중간다리역할
			BufferedImage bi=ImageIO.read(is); //이미지는 다른 txt문서와 다르게 Read를 생략할수있다
			for(int y=0;y<bi.getHeight();y++){
				for(int x=0;x<bi.getWidth();x++){
					Color color=new Color(bi.getRGB(x,y));
					int Y=(int)(0.299*color.getRed()+0.587*color.getGreen()+0.114*color.getBlue());
					bi.setRGB(x, y,new Color(Y,Y,Y).getRGB());
				}
			}
		
			FileOutputStream fos=new FileOutputStream(imageName); //비어있는 jpg파일을 생성
		ImageIO.write(bi, "JPG", fos);
		fos.close();
			System.out.println(imageName+"가 성공적으로 생성");
		}catch(MalformedURLException e){
			System.out.println("URL문자열 오류:"+e.getMessage());
		}catch(IOException e){
			System.out.print("IO오류:"+e.getMessage());
		}
	

	}

}
