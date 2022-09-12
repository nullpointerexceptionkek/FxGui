package discordrpc;

import java.util.ArrayList;

public class Script {
	private static ArrayList<Updates> totalupdates;
	
	public Script() {
		totalupdates = new ArrayList<Updates>();
		totalupdates.add(new Updates(3000,"Here we are","Riding the sky"));
		totalupdates.add(new Updates(3000,"Painting the night with sun","You and I"));
		totalupdates.add(new Updates(3000,"mirrors of light","Twin flames of fire"));
		totalupdates.add(new Updates(3000,"Lit in another time and place","I knew your name"));
		totalupdates.add(new Updates(3000,"I knew your face","Your love and grace"));
		totalupdates.add(new Updates(3000,"Past and present now embrace","Worlds collide in inner space"));
		totalupdates.add(new Updates(3000,"Unstoppable, the song we play","Burn the page for me"));
		totalupdates.add(new Updates(3000,"I cannot erase the time of sleep","I cannot be loved so set me free"));
		totalupdates.add(new Updates(3000,"I cannot deliver your love","Or caress your soul so"));
		totalupdates.add(new Updates(3000,"Turn that page for me","I cannot embrace the touch that you give"));
		totalupdates.add(new Updates(3000,"I cannot find solace in your words","I cannot deliver you your love"));
		totalupdates.add(new Updates(3000,"Or caress your soul","Age to age"));
		totalupdates.add(new Updates(3000,"I feel the call","Memory of future dreams"));
		totalupdates.add(new Updates(3000,"You and I, riding the sky","Keeping the fire bright"));
		totalupdates.add(new Updates(3000,"From another time and place","I know your name"));
		totalupdates.add(new Updates(3000,"I know your face","Your touch and grace"));
		totalupdates.add(new Updates(3000,"What our hearts remember stays","Forever on a song we play"));
		totalupdates.add(new Updates(3000,"Burn the page for me","I cannot erase the time of sleep"));
		totalupdates.add(new Updates(3000,"I cannot be loved so set me free","I cannot deliver your love"));
		totalupdates.add(new Updates(3000,"Or caress your soul so","Turn that page for me"));
		totalupdates.add(new Updates(3000,"I cannot embrace the touch that you give","I cannot find solice in your words"));
		totalupdates.add(new Updates(3000,"I cannot deliver you your love","Or caress your soul"));
	}
	
	public static ArrayList<Updates> getTotalupdates() {
		return totalupdates;
	}
	
	public void clearTotalupdates() {
		totalupdates.removeAll(totalupdates);

	}
	
	public void addUpdates(Updates updates) {
		totalupdates.add(updates);

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
		return new Script();
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
