package mobile;

public class Ltab extends Mobile{
	public Ltab(){}
	public Ltab(String mobileName, int batterySize, String osType){
		super(mobileName, batterySize, osType);
	}
	public void operate(int time){
		int battery=super.getBatterySize();
		battery-=time*10;
		super.setBatterySize(battery);
	}
	public void charge(int time){
		int battery=super.getBatterySize();
		battery+=time*10;
		super.setBatterySize(battery);
	}
}
