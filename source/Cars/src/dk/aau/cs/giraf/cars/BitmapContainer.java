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
	public static int add(int bitmapId, int newColor) {
		int largestId = 0x7f020011 + 99;
		for (Integer id : bitmapIds) {
			if (id > largestId) {
				largestId = id;
			}
		}
		
		int newBitmapId = largestId + 1;
		Bitmap newBitmap = recolorBitmap(bitmapId, newColor);
		bitmapIds.add(newBitmapId);
		recoloredList.add(new BitmapReference(newBitmapId, newBitmap));
		return newBitmapId;
	}
	private static Bitmap recolorBitmap(int bitmapId, int color) {
		int whiteValue = 0xFFFFFFFF;
		int recolorValue = color;
		
		Bitmap newBitmap = get(bitmapId).copy(Bitmap.Config.ARGB_8888, true);
		for (int x = 0; x < newBitmap.getWidth(); x++) {
			for (int y = 0; y < newBitmap.getHeight(); y++) {
				if (newBitmap.getPixel(x, y) == whiteValue) {
					newBitmap.setPixel(x, y, recolorValue);
				}
			}
		}
		
		return newBitmap;
	}
	
	public static int[] add(int bitmapId, int[] newColors) {
		int largestId = 0x7f020011 + 99;
		for (Integer id : bitmapIds) {
			if (id > largestId) {
				largestId = id;
			}
		}
		
		int[] newBitmapIds = new int[newColors.length];
		for (int i = 0; i < newColors.length; i++) {
			newBitmapIds[i] = largestId + i + 1;
		}
		Bitmap[] newBitmap = recolorBitmaps(bitmapId, newColors);
		for (int i = 0; i < newColors.length; i++) {
			bitmapIds.add(newBitmapIds[i]);
			recoloredList.add(new BitmapReference(newBitmapIds[i], newBitmap[i]));
		}
		return newBitmapIds;
	}
	private static Bitmap[] recolorBitmaps(int bitmapId, int[] colors) {
		int whiteValue = 0xFFFFFFFF;
		int[] recolorValue = colors;
		
		Bitmap[] newBitmaps = new Bitmap[colors.length];
		for (int i = 0; i < colors.length; i++) {
			newBitmaps[i] = get(bitmapId).copy(Bitmap.Config.ARGB_8888, true);
		}
		
		for (int x = 0; x < newBitmaps[0].getWidth(); x++) {
			for (int y = 0; y < newBitmaps[0].getHeight(); y++) {
				if (newBitmaps[0].getPixel(x, y) == whiteValue) {
					for (int i = 0; i < colors.length; i++) {
						newBitmaps[i].setPixel(x, y, recolorValue[i]);
					}
				}
			}
		}
		
		return newBitmaps;
	}
	
	public static Bitmap get(int bitmapId) {
		for (Integer id : resourceList) {
			if (id == bitmapId) {
				return BitmapFactory.decodeResource(resources, bitmapId);
			}
		}
		for (BitmapReference reference : recoloredList) {
			if (reference.id == bitmapId) {
				return reference.bitmap;
			}
		}
		
		System.out.println("Did not find bitmap");
		return null;
	}

	public static int[] getBitmapIds() {
		int[] arrayBitmapIds = new int[size()];
		
		int i = 0;
		for (Integer id : bitmapIds) {
			arrayBitmapIds[i] = id;
			i++;
		}
		
		return arrayBitmapIds;
	}
	
	public static int size() {
		return bitmapIds.size();
	}
	
	public static void setResources(Resources resources) {
		BitmapContainer.resources = resources; 
	}
	
	public static void clear() {
		bitmapIds.clear();
		resourceList.clear();
		recoloredList.clear();
	}
	
}
