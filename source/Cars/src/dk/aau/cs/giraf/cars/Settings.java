package dk.aau.cs.giraf.cars;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import dk.aau.cs.giraf.cars.gamecode.GameInfo;

import android.content.Context;

public abstract class Settings {
	public static boolean load(long userId, Context context) {
		String fileString = context.getFilesDir() + String.valueOf(userId);
		try {
			FileReader reader = new FileReader(fileString);

			System.out.println("------------------------");
			String str = readToSpace(reader);
			GameInfo.carSpeed = Float.valueOf(str);
			GameInfo.numberOfObstacles = Integer.valueOf(readToSpace(reader));
			GameInfo.color1 = Integer.valueOf(readToSpace(reader));
			GameInfo.color2 = Integer.valueOf(readToSpace(reader));
			GameInfo.color3 = Integer.valueOf(readToSpace(reader));
			GameInfo.setHighFreq(Integer.valueOf(readToSpace(reader)));
			GameInfo.setLowFreq(Integer.valueOf(readToSpace(reader)));
			
			reader.close();
		} catch (IOException e) {
			System.out.println("Error1");
		} catch (NumberFormatException e) {
			System.out.println("Error2");
			return true;
		}
		
		return false;
	}
	private static String readToSpace(FileReader reader) throws IOException {
		String returnStr = "";
		System.out.println("Initial string: " + returnStr);
		
		int charRead = reader.read();
		while (charRead != -1) {
			returnStr += (char)charRead;

			System.out.println("Added '" + (char)charRead + "'; Int: " + charRead);
			
			charRead = reader.read();

			System.out.println("Current string: " + returnStr + "; String length: " + returnStr.length());
			if ((char)charRead == ' ') {
				System.out.println("BREAKING");
				break;
			}
		}

		System.out.println("Returning: " + returnStr);
		System.out.println("------------------------");
		return returnStr;
	}
	
	public static boolean save(long userId, Context context) {
		System.out.println("Saving");
		
		try {
			String fileString = context.getFilesDir() + String.valueOf(userId);
			FileWriter writer = new FileWriter(fileString, false);
			
			writer.write(String.valueOf(GameInfo.carSpeed));
			writer.write(" ");
			writer.write(String.valueOf(GameInfo.numberOfObstacles));
			writer.write(" ");
			writer.write(String.valueOf(GameInfo.color1));
			writer.write(" ");
			writer.write(String.valueOf(GameInfo.color2));
			writer.write(" ");
			writer.write(String.valueOf(GameInfo.color3));
			writer.write(" ");
			writer.write(String.valueOf(GameInfo.getHighFreq()));
			writer.write(" ");
			writer.write(String.valueOf(GameInfo.getLowFreq()));
			writer.close();
		} catch (IOException e) {
			System.out.println("Error3");
		}
		
		return true;
	}
}
