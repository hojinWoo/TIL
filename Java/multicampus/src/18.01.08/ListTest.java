package day12;

import java.util.ArrayList;

public class ListTest {

	public static void main(String[] args) {
		int array[]={3,4,2,5,2,3,6,7,5,7,9};
		ConvertList con = new ConvertList(); 
		ArrayList <Integer>list = con.convertList(array);
		for(int result: list)
			System.out.println(result);
	}

}


