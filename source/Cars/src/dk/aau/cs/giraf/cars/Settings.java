package dk.aau.cs.giraf.cars;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import dk.aau.cs.giraf.cars.gamecode.GameInfo;

import android.content.Context;
import android.graphics.Color;

/**
 * Class responsible for saving and loading a users profile data.
 */
public abstract class Settings {
	/**
	 * Loads user settings and sets them in the GameInfo class.
	 * 
	 * @param userId	Profile ID to load from.
	 * @param context	Activity context, can be obtained with "getContext()" in an activity.
	 * @return			Returns false if an error occurred, else true.
	 */
	public static boolean load(long userId, Context context) {
		String fileString = context.getFilesDir() + String.valueOf(userId);
		try {
			FileReader reader = new FileReader(fileString);

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
			loadDefaultSettings();
			System.out.println("IO Error");
		} catch (NumberFormatException e) {
			System.out.println("Error in save file");
			return false;
		}
		
		return true;
	}
	
	public static void loadDefaultSettings() {
		GameInfo.carSpeed = 0.75f;
		GameInfo.numberOfObstacles = 2;
		GameInfo.color1 = Color.WHITE;
		GameInfo.color2 = Color.WHITE;
		GameInfo.color3 = Color.WHITE;
		GameInfo.setHighFreq(1500);
		GameInfo.setLowFreq(500);
	}
	
	private static String readToSpace(FileReader reader) throws IOException {
		String returnStr = "";
		
		int charRead = reader.read();
		while (charRead != -1) {
			returnStr += (char)charRead;
			
			charRead = reader.read();
			if ((char)charRead == ' ') {
				break;
			}
		}
		
		return returnStr;
	}
	
	/**
	 * Saves user settings to specific user ID.
	 * 
	 * @param userId	Profile ID to save to.
	 * @param context	Activity context, can be obtained with "getContext()" in an activity.
	 * @return			Returns false if an error occurred, else true.
	 */
	public static boolean save(long userId, Context context) {
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
			System.out.println("Error while saving");
			return false;
		}
		
		return true;
	}
}
