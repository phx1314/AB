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
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.widget.MImageView;
import com.work.xuance.F;
import com.work.xuance.R;
import com.work.xuance.frg.FrgTailiYulan;
import com.work.xuance.model.Model2Son;
import com.work.xuance.view.DataBtimap;

public class TailiYulan extends BaseItem {
	public MImageView mMImageView_top;
	public MImageView mMImageView_right;
	public MImageView mMImageView_bg;
	public Model2Son item;
	public Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			DataBtimap mDataBtimap = (DataBtimap) msg.obj;
			mMImageView_top.setImageBitmap(mDataBtimap.getmBitmap1());
			mMImageView_bg.setImageBitmap(mDataBtimap.getmBitmap2());
			if (item.getmFileSon2().getImg().startsWith("File:")) {
				mMImageView_right.setImageBitmap(mDataBtimap.getmBitmap3());
			} else {
				mMImageView_right.setObj(item.getmFileSon2().getImg());
			}
		}
	};

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

	public void set(final Model2Son item, final int position) {
		this.item = item;
		// mMImageView_top.setObj(F.mMPhotoList.photos.get(position * 2).img);
		// mMImageView_bg.setObj(F.mMPhotoList.photos.get(position * 2+1).img);
		// mMImageView_top.setObj("file:"
		// + Environment.getExternalStorageDirectory() + "/"
		// + F.pub_name + "/"
		// + F.mMPhotoList.photos.get(position * 2).img + ".png");
		// mMImageView_bg.setObj("file:"
		// + Environment.getExternalStorageDirectory() + "/"
		// + F.pub_name + "/"
		// + F.mMPhotoList.photos.get(position * 2 + 1).img +
		// ".png");
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Bitmap mBitmap1 = com.mdx.framework.utility.BitmapRead
							.decodeSampledBitmapFromFile(
									Environment.getExternalStorageDirectory()
											+ "/"
											+ F.pub_name
											+ "/"
											+ F.mMPhotoList.photos
													.get(position * 2).img
											+ ".png", FrgTailiYulan.size, 0);
					Bitmap mBitmap2 = com.mdx.framework.utility.BitmapRead
							.decodeSampledBitmapFromFile(
									Environment.getExternalStorageDirectory()
											+ "/"
											+ F.pub_name
											+ "/"
											+ F.mMPhotoList.photos
													.get(position * 2 + 1).img
											+ ".png", FrgTailiYulan.size, 0);
					Bitmap mBitmap3 = com.mdx.framework.utility.BitmapRead
							.decodeSampledBitmapFromFile(item.getmFileSon2()
									.getPath(), FrgTailiYulan.size2, 0);
					DataBtimap mDataBtimap = new DataBtimap(mBitmap1, mBitmap2,
							mBitmap3);
					Message mMessage = new Message();
					mMessage.obj = mDataBtimap;
					mHandler.sendMessage(mMessage);
				} catch (Exception e) {
				}
			}
		}).start();
	}
}