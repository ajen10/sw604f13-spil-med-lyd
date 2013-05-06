package dk.aau.cs.giraf.cars;

import java.io.IOException;
import java.io.StringWriter;
import android.util.JsonWriter;

public abstract class JSON_querying {
	public static void databaseRead(String certificate) throws IOException {
		StringWriter temp = new StringWriter();
		JsonWriter writer = new JsonWriter(temp);
		
		writer.setIndent("    ");
		writer.beginObject();

		writeAuth(certificate, writer);
		writer.name("action").value("read");
		writer.name("data");
		writer.beginObject();
		
		writer.name("type").value("application");
		writer.name("view").value("list");
		writer.name("ids").nullValue();
		
		writer.endObject();
		
		writer.endObject();
		writer.close();
	}
	
	public static void databaseRead(String certificate, String temp) throws IOException {
		StringWriter temp2 = new StringWriter();
		JsonWriter writer = new JsonWriter(temp2);
		
		writer.setIndent("    ");
		writer.beginObject();

		writeAuth(certificate, writer);
		writer.name("action").value("read");
		writer.name("data");
		writer.beginObject();
		
		writer.name("type").value("application");
		writer.name("view").value("details");
		writeIds(temp, writer);
		
		writer.endObject();
		
		writer.endObject();
		writer.close();
		
		System.out.println(temp2.toString());
	}
	private static void writeIds(String ids, JsonWriter writer) throws IOException {
		writer.name("ids");
		
		while (true) {
			writer.value(0);
		}
	}
	
	private static void writeAuth(String certificate, JsonWriter writer) throws IOException {
		writer.name("auth");
		writer.beginObject();
		writer.name("certificate").value(certificate);
		writer.endObject();
	}
}
