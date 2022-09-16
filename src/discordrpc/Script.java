package discordrpc;

import java.util.ArrayList;

public class Script {
	private static ArrayList<Updates> totalupdates;
	
	public Script(boolean createnew) {
		totalupdates = new ArrayList<Updates>();
		if(!createnew) return;
		//totalupdates.add(new Updates(3000,"This is the default message","if the config cannot be loaded"));
	}
	
	
	public static ArrayList<Updates> getTotalupdates() {
		return totalupdates;
	}
	
	
	public static void setTotalupdates(ArrayList<Updates> u) {
		totalupdates = u;
	}
	
	public static void addUpdates(Updates... updates) {
		for(Updates u : updates) {
			totalupdates.add(u);
		}

	}
	public static void setUpdates(Updates u, int index) {
		totalupdates.set(index, u);
	}
	
	public void removeUpdates(int list) {
		totalupdates.remove(list);

	}
	
	public Updates getUpdates(int list) {
		return totalupdates.get(list);
	}
	
	public int getSize() {
		return totalupdates.size();
		
	}
	
	public static Script fromTotalUpdates() {
		return new Script(false);
	}
	
	@Override
	public String toString() {
		
		String ts = "";
		
		for(Updates u: totalupdates) {
			ts += u + "-";
		}
		
		return ts;
		
	}
	
}
