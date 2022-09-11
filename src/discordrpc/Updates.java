package discordrpc;


public class Updates {

	private long wait = 3000;
	
	private String fl,sl;
	
	
	public Updates(long time, String f1, String sl) {
		this.wait = time;
		this.fl = f1;
		this.sl = sl;
		
	}
	
	public long getWait() {
		return wait;
	}
	
	public String getFl() {
		return fl;
	}
	
	public String getSl() {
		return sl;
	}
	
	public static Updates fromUpdates(long time, String fline, String sline) {
		return new Updates(time,fline,sline);
	}
	
	@Override
	public String toString() {
		return wait + ", " + fl + ", " + sl;
	}
	
}
