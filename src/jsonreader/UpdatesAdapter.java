package jsonreader;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import discordrpc.Updates;

public class UpdatesAdapter extends TypeAdapter<Updates>{

	@Override
	public Updates read(JsonReader reader) throws IOException {
		if (reader.peek() == JsonToken.NULL) {
	         reader.nextNull();
	         return null;
	         }
		
		String v = reader.nextString();
		String[] parts = v.split(",");
		
		long wait = Long.parseLong(parts[0]);
	    String F1 = parts[1];
	    String S1 = parts[2];
	    
		return new Updates(wait, F1, S1);
		
		
	}

	@Override
	public void write(JsonWriter writter, Updates value) throws IOException {
		if(value == null) {
			writter.nullValue();
			return;
		}
		
		writter.value(value.toString());
		
	}

}
