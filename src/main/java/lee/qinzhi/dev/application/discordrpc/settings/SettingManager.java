package lee.qinzhi.dev.application.discordrpc.settings;

import lee.qinzhi.dev.application.jsonreader.FileManager;

import java.io.File;



public class SettingManager {
	
	private static Settings settings;
	
	public static void init() {
		settings = loadKeyFromJson();
	}
	
	public static Settings loadKeyFromJson() {
		Settings loaded = FileManager.readFromJson(new File(FileManager.getROOT_DIR(),"Settings.json"),Settings.class);
		if(loaded == null) {
			settings = new Settings();
			Settings.setTheme(Theme.dark);
			Settings.setDiscordAPIKey("");
			loaded = settings;
			saveSettingToFile();
		}
		return loaded;
		
	}
	public static void saveSettingToFile() {
		FileManager.writeJsonTofile(new File(FileManager.getROOT_DIR(), "Settings.json"), settings);
	}
	
	public static void setSettings(Settings settings) {
		SettingManager.settings = settings;
	}
	
	public static Settings getSettings() {
		return settings;
	}
	
}
