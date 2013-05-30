package dk.aau.cs.giraf.cars;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.test.AndroidTestCase;

import dk.aau.cs.giraf.cars.gamecode.GameInfo;
import junit.framework.TestCase;
/**
 * Unit test
 */
public class SettingsTest extends AndroidTestCase {

	protected void setUp() throws Exception {
		super.setUp();

		Context context = getContext();
		try {
			String fileString = context.getFilesDir() + "1234";
			FileWriter writer = new FileWriter(fileString, false);
			
			writer.write("0.6 1 -17 -1100 -11100 1000 500");
			writer.close();
		} catch (IOException e) {
		}
		
		GameInfo.carSpeed = 1.2F;
		GameInfo.numberOfObstacles = 3;
		GameInfo.color1 = -34;
		GameInfo.color2 = -2200;
		GameInfo.color3 = -22200;
		GameInfo.setHighFreq(2000);
		GameInfo.setLowFreq(1000);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		Settings.loadDefaultSettings();
		getContext().deleteFile("1234");
		getContext().deleteFile("4321");
	}

	public void testLoad() {
		Settings.load(1234, getContext());
		
		assertTrue(GameInfo.carSpeed == 0.6F);
		assertTrue(GameInfo.numberOfObstacles == 1);
		assertTrue(GameInfo.color1 == -17);
		assertTrue(GameInfo.color2 == -1100);
		assertTrue(GameInfo.color3 == -11100);
		assertTrue(GameInfo.getHighFreq() == 1000);
		assertTrue(GameInfo.getLowFreq() == 500);
	}

	public void testSave() throws FileNotFoundException {
		Settings.save(4321, getContext());
		
		String fileString = getContext().getFilesDir() + "4321";
		FileReader reader = new FileReader(fileString);

		String str = "";
		try {
			str = readToEnd(reader);
		} catch (IOException e) {
		}
		
		System.out.println(str);
		assertTrue(str.equals("1.2 3 -34 -2200 -22200 2000 1000"));
	}
	private static String readToEnd(FileReader reader) throws IOException {
		String returnStr = "";
		
		int charRead = reader.read();
		while (charRead != -1) {
			returnStr += (char)charRead;
			
			charRead = reader.read();
		}
		
		return returnStr;
	}
}
