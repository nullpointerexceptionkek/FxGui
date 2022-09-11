package application;

import discordrpc.DiscordRP;
import discordrpc.UpdateManager;
import discordrpc.Updates;
import jsonreader.FileManager;

public class LaunchManager extends Thread{
	
	public static boolean isRunning = false;
	
	private static DiscordRP discordRP = new DiscordRP();
	
	private static UpdateManager upm;
	
	public static void init() {
		isRunning = true;
		FileManager.init();
		discordRP.start();
		
		upm = new UpdateManager();
		
		LaunchManager runloop = new LaunchManager();
		runloop.start();
		
		
		
	}
	
	
	@Override
	public void run() {
		
		while(isRunning) {
			
			for(int i = 0; i< upm.getUpdates().getSize(); i++) {
				excuteUpdate(upm.getUpdates().getUpdates(i));
			}
			
		}
		
		LaunchManager.onClose();
		
	}
	
	private void excuteUpdate(Updates update) {
		System.out.println(update);
		if(update.getWait() == -1) {
			discordRP.update(update.getFl(), update.getSl());
			return;
		}
		
		try {
			currentThread();
			Thread.sleep((update.getWait() <= 3000 )? 3000 : update.getWait());
			discordRP.update(update.getFl(), update.getSl());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	public static void onClose() {
		//discordRP.saveKeyToFile();
		//upm.saveScriptToFile();
		System.exit(0);
	}
	
	
	

	
}
