package day3;
public class ForLab1 {
	public static void main(String[] args) {
		int count = (int)(Math.random()*8)+3;
		int deco = (int)(Math.random()*3)+1;
		char munja ='E' ;
		switch(deco){
		case 1:			
			munja = '*';
			break;
		case 2:			
			munja = '$';
			break;
		case 3:
			munja = '#';			
		}
		for(int i=1; i<=count; i++) { 
			System.out.print(munja);
		}
	}
}
