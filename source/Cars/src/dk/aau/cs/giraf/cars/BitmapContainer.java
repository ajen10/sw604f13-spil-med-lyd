package dk.aau.cs.giraf.cars;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public abstract class BitmapContainer {
	public static ArrayList<Integer> bitmapIds = new ArrayList<Integer>(); 
	public static ArrayList<Integer> resourceList = new ArrayList<Integer>();
	public static ArrayList<BitmapReference> recoloredList = new ArrayList<BitmapReference>();
	private static Resources resources;
	
	public static void add(int bitmapId) {
		bitmapIds.add(bitmapId);
		resourceList.add(bitmapId);
	}
	public static int add(int bitmapId, int recolorRed, int recolorGreen, int recolorBlue) {
		int largestId = 0;
		for (Integer id : bitmapIds) {
			if (id > largestId) {
				largestId = id;
			}
		}
		
		int newBitmapId = largestId + 1;
		Bitmap newBitmap = recolorBitmap(bitmapId, recolorRed, recolorGreen, recolorBlue);
		recoloredList.add(new BitmapReference(newBitmapId, newBitmap));
		return newBitmapId;
	}
	private static Bitmap recolorBitmap(int bitmapId, int recolorRed, int recolorGreen, int recolorBlue) {
		int whiteValue = 0xFFFFFFFF;
		int recolorValue = 0xFF000000 + recolorRed * 256 * 256 +
										recolorGreen * 256 +
										recolorBlue;
		
		Bitmap newBitmap = BitmapFactory.decodeResource(resources, bitmapId).copy(Bitmap.Config.ARGB_8888, true);
		for (int x = 0; x < newBitmap.getWidth(); x++) {
			for (int y = 0; y < newBitmap.getHeight(); y++) {
				if (newBitmap.getPixel(x, y) == whiteValue) {
					newBitmap.setPixel(x, y, recolorValue);
				}
			}
		}
		
		return newBitmap;
	}
	
	public static Bitmap get(int resourceId) {
		for (Integer id : resourceList) {
			if (id == resourceId) {
				return BitmapFactory.decodeResource(resources, resourceId);
			}
		}
		for (BitmapReference reference : recoloredList) {
			if (reference.id == resourceId) {
				return reference.bitmap;
			}
		}
		
		return null;
	}

	public static int[] getBitmapIds() {
		int[] bitmapIds = new int[size()];
		
		int i = 0;
		for (Integer id : resourceList) {
			bitmapIds[i] = id;
			i++;
		}
		for (BitmapReference reference : recoloredList) {
			bitmapIds[i] = reference.id;
			i++;
		}
		
		return bitmapIds;
	}
	
	public static int size() {
		return resourceList.size() + recoloredList.size();
	}
	
	public static void setResources(Resources resources) {
		BitmapContainer.resources = resources; 
	}
	
}
