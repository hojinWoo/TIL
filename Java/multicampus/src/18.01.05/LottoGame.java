package day11;

import java.util.Random;

class LottoMachine {
	private int nums[];

	public LottoMachine() {
		nums = new int[6];
	}

	public void createLottoNums() {
		Random r = new Random();
		for (int i = 0; i < nums.length; i++)
			nums[i] = r.nextInt(20) + 1;

	}

	public void checkLottoNums() throws DuplicateException {
		for (int i = 0; i < nums.length; i++) { // 배열을 0부터 5까지 순서대로접근
			for (int j = i + 1; j < nums.length; j++) { // 1부터 6까지 순서대로 접근
				if (nums[i] == nums[j]) { // nums에 i위치와 i+1(=j)위치를 비교 하고 똑같으면
											// throw 발동
					throw new DuplicateException(); // 만약에 숫자가 겹치면 제를 객체로 던진다
				}
			}
		}
	}

	public int[] getNums() {  
		return nums;
	}
}

class DuplicateException extends Exception {
	public DuplicateException() {
		super("중복된 로또 번호가 발생했습니다.");
	}
}

public class LottoGame {

	public static void main(String[] args) {
		LottoMachine lotto = new LottoMachine();
		lotto.createLottoNums();
		
		try { // exception처리가 필요한 함수가 있기때문에
			lotto.checkLottoNums(); // 중복체크
			for (int i = 0; i < lotto.getNums().length; i++) {
				System.out.print(lotto.getNums()[i]);
				if (i < lotto.getNums().length - 1) {
					System.out.print(",");
				}
			}
		} catch (DuplicateException e) {
			System.out.println(e.getMessage()); // getmessage=예외메세지
		}

	}

}
