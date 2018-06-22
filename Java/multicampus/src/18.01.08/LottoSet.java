package day12;

import java.util.HashSet;

public class LottoSet {

	public static void main(String[] args) {
		int lotto=0;
		HashSet<Integer> set=new HashSet<Integer>();
		//HashSet 이란 배열같은것인데 데이터를 중복저장하지않는다
		
		while(set.size()!=10){
			lotto=(int)(Math.random()*21)+10;
			set.add(lotto);
		}
		int cnt =0;
		System.out.print("오늘의 로또 번호:");
		for(int tto : set){
			System.out.print(tto);
			if(cnt!=9)
				System.out.print(",");
			
			cnt++;
		}
	}

}
