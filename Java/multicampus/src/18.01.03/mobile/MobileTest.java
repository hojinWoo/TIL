package mobile;

public class MobileTest {

	public static void main(String[] args) {
		Mobile[] mb = new Mobile[2];
		mb[0]= new Ltab("Ltab",500,"ABC-01");
		mb[1]= new Otab("Otab",1000,"XYZ-20");
		printMobile(mb);
		for(Mobile temp : mb){
			temp.charge(10);
		}
		System.out.println("[10분 충전]");		
		printMobile(mb);
		for(Mobile temp : mb){
			temp.operate(5);
		}
		System.out.println("[5분 통화]");	
		printMobile(mb);
	}
	public static void printMobile(Mobile[] mobile){
		printTitle();
		for (Mobile mob : mobile){
			System.out.println(mob.getMobileName()+"\t\t"+mob.getBatterySize()+"\t\t"+mob.getOsType());
		}
		System.out.println();
		
		
	}
	public static void printTitle(){
		System.out.println("Mobile\t\tBattery\t\tOS");
		System.out.println("-----------------------------------------------");
		
	}

}
