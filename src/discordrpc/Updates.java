package discordrpc;


public class Updates {

	private long wait = 3000;
	
	private String image, fl,sl;
	
	
	public Updates(long time, String image, String f1, String sl) {
		this.wait = time;
		this.fl = f1;
		this.sl = sl;
		this.image = image;
		
	}
	
	public Updates(long time, String f1, String sl) {
		this.wait = time;
		this.fl = f1;
		this.sl = sl;
		this.image = null;
		
	} 
	
	public long getWait() {
		return wait;
	}
	
	public String getImage() {
		return image;
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
	
	public static Updates fromUpdates(long time, String image, String fline, String sline) {
		return new Updates(time,image,fline,sline);
	}
	
	@Override
	public String toString() {
		return wait + ", " + image + ", " + fl + ", " + sl;
	}
	
}
