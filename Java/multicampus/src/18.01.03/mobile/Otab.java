package mobile;

public class Otab extends Mobile{
	public Otab(){}
	public Otab(String mobileName, int batterySize, String osType){
		super(mobileName, batterySize, osType);
	}
	public void operate(int time){
		int battery=super.getBatterySize();
		battery-=time*12;
		super.setBatterySize(battery);
	}
	public void charge(int time){
		int battery=super.getBatterySize();
		battery+=time*8;
		super.setBatterySize(battery);
	}
	
}
