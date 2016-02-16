package com.work.xuance.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapRead {

	public static int calculateInSampleSize(BitmapFactory.Options options,
			float reqWidth, float reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	public static Bitmap decodeSampledBitmapFromFile(String filePath,
			float reqWidth, float reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}

	public static Bitmap decodeSampledBitmapFromByte(byte[] bytes,
			float reqWidth, float reqHeight) {
		return decodeSampledBitmapFromByte(bytes, 0, bytes.length, reqWidth,
				reqHeight);
	}

	public static Bitmap decodeSampledBitmapFromByte(byte[] bytes, int offset,
			int length, float reqWidth, float reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		if (reqWidth != 0 || reqHeight != 0) {
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeByteArray(bytes, offset, length, options);
			options.inSampleSize = calculateInSampleSize(options, reqWidth,
					reqHeight);
			options.inJustDecodeBounds = false;
		}
		return BitmapFactory.decodeByteArray(bytes, offset, length, options);
	}
}
