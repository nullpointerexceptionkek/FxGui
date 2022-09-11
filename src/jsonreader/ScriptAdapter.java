package jsonreader;

import java.io.IOException;
import java.util.Arrays;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import discordrpc.Script;
import discordrpc.Updates;

public class ScriptAdapter extends TypeAdapter<Script>{

	@Override
	public Script read(JsonReader reader) throws IOException {
		if (reader.peek() == JsonToken.NULL) {
	         reader.nextNull();
	         return null;
	         }
		try {
			Script tu = new Script();
			String v = reader.nextString();
			String[] parts = v.split("-");
			for(String u : parts) {
				String[] uParts = u.split(",");
				long wait = Long.parseLong(uParts[0]);
			    String F1 = uParts[1];
			    String S1 = uParts[2];
			    tu.addUpdates(new Updates(wait, F1, S1));
			}
			return tu;
		} catch (Exception e) {
			System.out.println("Invalid File");
			return null;
		}
			
		
	}

	@Override
	public void write(JsonWriter writter, Script tu) throws IOException {
		if(tu == null || tu.getSize() == 0) {
			writter.nullValue();
			return;
		}

		writter.value(tu.toString());

		
	}



}
