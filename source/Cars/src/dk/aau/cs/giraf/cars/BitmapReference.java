package dk.aau.cs.giraf.cars;

import android.graphics.Bitmap;

/**
 * Class encapsulating a recolored bitmap and its matching ID.
 */
public class BitmapReference {
	public int id;
	public Bitmap bitmap;
	
	/**
	 * @param id		ID of the recolored bitmap.
	 * @param bitmap	The recolored bitmap.
	 */
	public BitmapReference(int id, Bitmap bitmap) {
		this.id = id;
		this.bitmap = bitmap;
	}
}
