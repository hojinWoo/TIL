package mobile;
public abstract class Mobile {
	private String mobileName;
	private int batterySize;
	private String osType;	
	public Mobile(){}
	public Mobile(String mobileName, int batterySize, String osType){
		this.mobileName=mobileName;
		this.batterySize=batterySize;
		this.osType=osType;
	}	
	public abstract void operate(int time);
	public abstract void charge(int time);	
	public void setBatterySize(int time){
		this.batterySize=time;
	}
	public String getMobileName(){
		return mobileName;
	}
	public int getBatterySize(){
		return batterySize; 
	}
	public String getOsType(){
		return osType;
	}
}
