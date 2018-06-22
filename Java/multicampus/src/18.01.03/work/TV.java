package work;

public class TV {
	private String model;
	private int size, channel;
	public TV() {}
	public TV(String model,int size, int channel) {
		this.model = model;
		this.size = size;
		this.channel = channel;
	}
	public void channelUp() {
		channel++;
		if(channel>10) channel = 1;
	}
	public void channelDown() {
		channel--;
		if(channel<1) channel = 10;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	
}
