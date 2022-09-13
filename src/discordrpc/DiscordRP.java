package discordrpc;

import java.io.File;

import application.LaunchManager;
import jsonreader.FileManager;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

public class DiscordRP {
	
	private long created = 0;
	
	public static String apikey;
	
	
	public void LaunchReadyCallBack(){
		this.created = System.currentTimeMillis();
		
		DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
			
			@Override
			public void apply(DiscordUser user) {
				System.out.println("Welcome " + user.username + "#" + user.discriminator + ".");
			}
		}).build();
		
		
		DiscordRPC.discordInitialize(apikey, handlers, true);
		
		new Thread("Discord RPC Callback"){
			
			@Override
			public void run() {

				while(LaunchManager.isRunning) {
					DiscordRPC.discordRunCallbacks();
				}
			}
		}.start();
		
	}
	
	
	public void init() {
		apikey = loadKeyFromJson();
	}
	
	public void shutdown() {
		DiscordRPC.discordShutdown();
	}
	

	public void update(String firstLine, String secondLine) {
		DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder(secondLine);
		presence.setBigImage("large", "");
		presence.setDetails(firstLine);
		presence.setStartTimestamps(created);
		//presence.setSmallImage("large", "");
		//presence.setParty("party", 2, 3);
		DiscordRPC.discordUpdatePresence(presence.build());
		
	}
	
	public String loadKeyFromJson() {
		String loaded = FileManager.readFromJson(new File(FileManager.getROOT_DIR(),"key.json"),String.class);
		apikey = loaded;
		System.out.println(loaded);
		
		/*
		if(loaded == null) {
			loaded = "1002317125899272332";
			apikey = loaded;
			saveKeyToFile();	
		}
		*/
		
		
		return loaded;
		
	}
	
	public static void saveKeyToFile() {
		FileManager.writeJsonTofile(new File(FileManager.getROOT_DIR(), "key.json"), apikey);
	}
	
	
}

