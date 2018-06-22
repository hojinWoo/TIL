package work;
public class TVTest {
	public static void main(String[] args) {
		SaleTV s = new SaleTV(300000,"SALETV-1",40,1);
		RentalTV r = new RentalTV(100000,"RENTALTV-10",42,1);
		for(int i=0; i<2; i++)
			s.channelUp();
		for(int i=0; i<3; i++)
			r.channelDown();
		printAllTV(s);
		printAllTV(r);
		printRentalTV(r);
	}
	static void printAllTV(TV tv){
		System.out.println(tv.toString());
		if(tv instanceof SaleTV) {
			((SaleTV)tv).play();
			((SaleTV) tv).sale();
		}
		else ((RentalTV)tv).play();
	}
	static void printRentalTV(Rentable tv){
		tv.rent();
	}
}
