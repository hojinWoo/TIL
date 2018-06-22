package day6;

public class MethodLab6 {

	public static void main(String[] args) {
		char a1[]={'A','b','c'};
		char a2[]={'a','b','c','D','E','F'};
		char a3[]={'z','S','Z','s'};
		System.out.printf("["+"출력결과"+"]"+"\n"+"----------"+"\n");
		printChar_befor(a1);
		converChar(a1);
		printChar_after(a1);
		printChar_befor(a2);
		converChar(a2);
		printChar_after(a2);
		printChar_befor(a3);
		converChar(a3);
		printChar_after(a3);
		
		}
	
	
	public static void converChar(char[] ch){
		for(int i=0;i<ch.length;i++)
			if('A'<=ch[i]&&ch[i]<='Z'){
				ch[i]=(char)(ch[i]+32);
				
	}else if('a'<=ch[i]&&ch[i]<='z'){
		ch[i]=(char)(ch[i]-32);
		
	}
	}
	    
		public static void printChar_befor(char[] ch){
			System.out.print("호출전:");
			for(int i=0;i<ch.length;i++){
				System.out.print(ch[i]);
				}
				System.out.println();

}


public static void printChar_after(char[] ch){
	System.out.print("호출후:");
	for(int i=0;i<ch.length;i++){
		System.out.print(ch[i]);
		}
		System.out.println();
		System.out.println("----------");


}

}







