package com.work.xuance.view;

import java.io.Serializable;

import android.graphics.Bitmap;

public class DataBtimap implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Bitmap mBitmap1;
	public Bitmap mBitmap2;
	public Bitmap mBitmap3;

	public Bitmap getmBitmap1() {
		return mBitmap1;
	}

	public void setmBitmap1(Bitmap mBitmap1) {
		this.mBitmap1 = mBitmap1;
	}

	public Bitmap getmBitmap2() {
		return mBitmap2;
	}

	public void setmBitmap2(Bitmap mBitmap2) {
		this.mBitmap2 = mBitmap2;
	}

	public Bitmap getmBitmap3() {
		return mBitmap3;
	}

	public void setmBitmap3(Bitmap mBitmap3) {
		this.mBitmap3 = mBitmap3;
	}

	public DataBtimap(Bitmap mBitmap1, Bitmap mBitmap2, Bitmap mBitmap3) {
		super();
		this.mBitmap1 = mBitmap1;
		this.mBitmap2 = mBitmap2;
		this.mBitmap3 = mBitmap3;
	}

}
