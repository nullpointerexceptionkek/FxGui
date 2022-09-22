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
	
	private static Thread runloop;
	
	public static void init() {
		FileManager.init();
		discordRP.init();
		upm = new UpdateManager();
		
		
	}
	
	public static void initCallBack() {
		discordRP.LaunchReadyCallBack();
		isRunning = true;
		
	}
	
	public static void startUpdate() {
		if(runloop == null) {
			runloop = new Thread("RunLoop") {
				@Override
				public void run() {
					if(upm.getUpdates().getSize()==1) {
						excuteUpdate(upm.getUpdates().getUpdates(0));
						DiscordRPC.discordRunCallbacks();
						return;
					}
					
					
					while(isRunning) {
						for(int i = 0; i< upm.getUpdates().getSize(); i++) {
							if(!isRunning) 
								return;
							excuteUpdate(upm.getUpdates().getUpdates(i));
							DiscordRPC.discordRunCallbacks();
						}
						
					}
				}
			};
			runloop.start();
			return;
		}
	}
	
	public static void closeCallBack() {
		System.out.println();
		discordRP.shutdown();
		isRunning = false;
	}
	
	
	
	
	
	private static void excuteUpdate(Updates update) {
		System.out.println("Sented Update Request, trans: " + update);
		if(update.getWait() == -1) {
			discordRP.update(update.getImage(),update.getFl(), update.getSl());
			return;
		}
		
		try {
			//Thread.sleep((update.getWait() <= 3000 )? 3000 : update.getWait());
			Thread.sleep(update.getWait());
			discordRP.update(update.getImage(),update.getFl(), update.getSl());
			
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
	
	public static void saveScripToFile() {
		upm.saveScriptToFile();
	}
	
	
	

	
}
