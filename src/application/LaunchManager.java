package application;

import discordrpc.DiscordRP;
import discordrpc.UpdateManager;
import discordrpc.Updates;
import jsonreader.FileManager;
import net.arikia.dev.drpc.DiscordRPC;

public class LaunchManager {
	
	public static boolean isRunning = false;
	
	private static DiscordRP discordRP = new DiscordRP();
	
	private static UpdateManager upm;
	
	public static void init() {
		FileManager.init();
		discordRP.init();
		upm = new UpdateManager();
		
		
	}
	
	public static void initCallBack() {
		discordRP.LaunchReadyCallBack();
		isRunning = true;
		new Thread("RunLoop") {
			@Override
			public void run() {
				while(isRunning) {
					for(int i = 0; i< upm.getUpdates().getSize(); i++) {
						if(!isRunning) 
							return;
						System.out.println("im runing");
						excuteUpdate(upm.getUpdates().getUpdates(i));
						DiscordRPC.discordRunCallbacks();
					}
					
				}
			}
		}.start();
		
	}
	
	public static void closeCallBack() {
		discordRP.shutdown();
		isRunning = false;
	}
	
	
	
	
	
	private static void excuteUpdate(Updates update) {
		System.out.println(update);
		if(update.getWait() == -1) {
			discordRP.update(update.getFl(), update.getSl());
			return;
		}
		
		try {
			Thread.sleep((update.getWait() <= 3000 )? 3000 : update.getWait());
			discordRP.update(update.getFl(), update.getSl());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	public static void onClose() {
		DiscordRP.saveKeyToFile();
		upm.saveScriptToFile();
		discordRP.shutdown();
		System.exit(0);
	}
	
	
	

	
}
