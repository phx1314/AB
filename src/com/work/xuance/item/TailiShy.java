//
//  TailiShy
//
//  Created by Administrator on 2015-10-14 16:25:27
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mdx.framework.Frame;
import com.mdx.framework.widget.MImageView;
import com.work.xuance.F;
import com.work.xuance.R;
import com.work.xuance.frg.FrgTailiYulan;
import com.work.xuance.model.Model2Son;
import com.work.xuance.view.DataBtimap;

public class TailiShy extends BaseItem {
	public MImageView mMImageView_right;
	public MImageView mMImageView_bg;
	public LinearLayout clk_LinearLayout_style1;
	public LinearLayout clk_LinearLayout_style2;
	public ImageView mImageView_dui1;
	public ImageView mImageView_dui2;
	public Model2Son item;
	public String color;
	public Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			DataBtimap mDataBtimap = (DataBtimap) msg.obj;
			if (item.getmFileSon2().getImg().startsWith("File:")) {
				mMImageView_right.setImageBitmap(mDataBtimap.getmBitmap1());
			} else {
				mMImageView_right.setObj(item.getmFileSon2().getImg());
			}
			if (color != null) {
				if (color.equals("1")) {
					mImageView_dui1.setVisibility(View.VISIBLE);
					mImageView_dui2.setVisibility(View.INVISIBLE);
					mMImageView_bg.setImageBitmap(mDataBtimap.getmBitmap2());
				} else {
					mImageView_dui1.setVisibility(View.INVISIBLE);
					mImageView_dui2.setVisibility(View.VISIBLE);
					if (F.mMPhotoList.photos.size() >= 27)
						mMImageView_bg
								.setImageBitmap(mDataBtimap.getmBitmap3());
				}
			}
		}
	};

	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_taili_shy, null);
		convertView.setTag(new TailiShy(convertView));
		return convertView;
	}

	public TailiShy(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		mMImageView_right = (MImageView) contentview
				.findViewById(R.id.mMImageView_right);
		mMImageView_bg = (MImageView) contentview
				.findViewById(R.id.mMImageView_bg);
		clk_LinearLayout_style1 = (LinearLayout) contentview
				.findViewById(R.id.clk_LinearLayout_style1);
		clk_LinearLayout_style2 = (LinearLayout) contentview
				.findViewById(R.id.clk_LinearLayout_style2);
		mImageView_dui1 = (ImageView) contentview
				.findViewById(R.id.mImageView_dui1);
		mImageView_dui2 = (ImageView) contentview
				.findViewById(R.id.mImageView_dui2);
		clk_LinearLayout_style1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Frame.HANDLES.sentAll("FrgTailiYulan", 2, "1");
			}
		});
		clk_LinearLayout_style2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Frame.HANDLES.sentAll("FrgTailiYulan", 2, "2");
			}
		});
	}

	public void set(final Model2Son item, String color) {
		this.item = item;
		this.color = color;

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Bitmap mBitmap1 = com.mdx.framework.utility.BitmapRead
							.decodeSampledBitmapFromFile(item.getmFileSon2()
									.getPath(), FrgTailiYulan.size2, 0);
					Bitmap mBitmap2 = com.mdx.framework.utility.BitmapRead
							.decodeSampledBitmapFromFile(
									Environment.getExternalStorageDirectory()
											+ "/" + F.pub_name + "/"
											+ F.mMPhotoList.photos.get(0).img
											+ ".png", FrgTailiYulan.size, 0);
					Bitmap mBitmap3 = com.mdx.framework.utility.BitmapRead
							.decodeSampledBitmapFromFile(
									Environment.getExternalStorageDirectory()
											+ "/" + F.pub_name + "/"
											+ F.mMPhotoList.photos.get(26).img
											+ ".png", FrgTailiYulan.size, 0);
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