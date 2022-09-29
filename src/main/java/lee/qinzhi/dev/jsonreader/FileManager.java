package lee.qinzhi.dev.jsonreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lee.qinzhi.dev.discordrpc.Script;
import lee.qinzhi.dev.discordrpc.Updates;
import lee.qinzhi.dev.discordrpc.settings.Settings;

public class FileManager {

	private static Gson gson;
	
	private static File ROOT_DIR = new File("CustomDiscordRPC");
	
	public static void init() {
		if(!ROOT_DIR.exists()) { ROOT_DIR.mkdir();}
		
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Updates.class, new UpdatesAdapter());
		builder.registerTypeAdapter(Script.class, new ScriptAdapter());
		builder.registerTypeAdapter(Settings.class, new SettingsAdapter());
		gson = builder.create();
	}
	
	public static Gson getGson() {
		return gson;
	}
	
	public static boolean writeJsonTofile(File file, Object obj) {
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(gson.toJson(obj).getBytes());
			outputStream.flush();
			outputStream.close();
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static <T extends Object> T readFromJson(File file, Class<T> c) {
		
		try {
			
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			StringBuilder builder = new StringBuilder();
			String line;
			
			while((line = bufferedReader.readLine()) != null) {
				builder.append(line);
			}
			
			bufferedReader.close();
			inputStreamReader.close();
			fileInputStream.close();
			
			return gson.fromJson(builder.toString(), c);
			
		}
		catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static File getROOT_DIR() {
		return ROOT_DIR;
	}
	
	
	
	
	
	
}
