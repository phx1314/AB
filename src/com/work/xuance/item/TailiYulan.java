//
//  TailiYulan
//
//  Created by Administrator on 2015-10-14 16:25:27
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.widget.MImageView;
import com.work.xuance.F;
import com.work.xuance.R;
import com.work.xuance.model.Model2Son;

public class TailiYulan extends BaseItem {
	public MImageView mMImageView_top;
	public MImageView mMImageView_right;
	public MImageView mMImageView_bg;

	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_taili_yulan, null);
		convertView.setTag(new TailiYulan(convertView));
		return convertView;
	}

	public TailiYulan(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		mMImageView_top = (MImageView) contentview
				.findViewById(R.id.mMImageView_top);
		mMImageView_bg = (MImageView) contentview
				.findViewById(R.id.mMImageView_bg);
		mMImageView_right = (MImageView) contentview
				.findViewById(R.id.mMImageView_right);
	}

	public void set(Model2Son item, int position) {
		// mMImageView_top.setObj(F.mMPhotoList.photos.get(position * 2).img);
		// mMImageView_bg.setObj(F.mMPhotoList.photos.get(position * 2+1).img);
		try {
			// mMImageView_top.setObj("file:"
			// + Environment.getExternalStorageDirectory() + "/"
			// + F.pub_name + "/"
			// + F.mMPhotoList.photos.get(position * 2).img + ".png");
			// mMImageView_bg.setObj("file:"
			// + Environment.getExternalStorageDirectory() + "/"
			// + F.pub_name + "/"
			// + F.mMPhotoList.photos.get(position * 2 + 1).img + ".png");
			mMImageView_top
					.setImageBitmap(com.mdx.framework.utility.BitmapRead
							.decodeSampledBitmapFromFile(
									Environment.getExternalStorageDirectory()
											+ "/"
											+ F.pub_name
											+ "/"
											+ F.mMPhotoList.photos
													.get(position * 2).img
											+ ".png", 320, 0));
			mMImageView_bg
					.setImageBitmap(com.mdx.framework.utility.BitmapRead
							.decodeSampledBitmapFromFile(
									Environment.getExternalStorageDirectory()
											+ "/"
											+ F.pub_name
											+ "/"
											+ F.mMPhotoList.photos
													.get(position * 2 + 1).img
											+ ".png", 320, 0));
			mMImageView_right.setObj(item.getmFileSon2().getImg());
		} catch (Exception e) {
		}
	}
}