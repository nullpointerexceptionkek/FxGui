package discordrpc;

import java.io.File;

import jsonreader.FileManager;

public class UpdateManager{
	protected Script updates;

	
	
	public UpdateManager() {
		updates = loadScriptFromJson();
	}
	
	
	
	public Script loadScriptFromJson() {
		Script loaded = FileManager.readFromJson(new File(FileManager.getROOT_DIR(),"UpdateScript.json"),Script.class);
		System.out.println(loaded);
		this.updates = loaded;
		
		if(loaded == null) {
			loaded = Script.fromTotalUpdates();
			this.updates = loaded;
			saveScriptToFile();	
		}
		
		
		return loaded;
		
	}
	
	public void saveScriptToFile() {
		FileManager.writeJsonTofile(new File(FileManager.getROOT_DIR(), "UpdateScript.json"), updates);
	}
	
	public Script getUpdates() {
		return updates;
	}
	
	
}
