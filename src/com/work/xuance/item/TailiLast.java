//
//  TailiLast
//
//  Created by Administrator on 2015-10-14 16:25:27
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.item;

import com.work.xuance.F;
import com.work.xuance.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import com.mdx.framework.Frame;
import com.mdx.framework.widget.MImageView;

import android.widget.TextView;

public class TailiLast extends BaseItem {
	public MImageView mMImageView_top;
	public TextView clk_mTextView_dzh;

	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_taili_last, null);
		convertView.setTag(new TailiLast(convertView));
		return convertView;
	}

	public TailiLast(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		mMImageView_top = (MImageView) contentview
				.findViewById(R.id.mMImageView_top);
		clk_mTextView_dzh = (TextView) contentview
				.findViewById(R.id.clk_mTextView_dzh);

		clk_mTextView_dzh.setOnClickListener(com.mdx.framework.utility.Helper
				.delayClickLitener(this));
		try {
			mMImageView_top.setImageBitmap(com.mdx.framework.utility.BitmapRead
					.decodeSampledBitmapFromFile(
							Environment.getExternalStorageDirectory() + "/"
									+ F.pub_name + "/"
									+ F.mMPhotoList.photos.get(1).img + ".png",
							320, 0));
		} catch (Exception e) {
		}
	}

	public void set(String item) {

	}

	@Override
	public void onClick(android.view.View v) {
		if (R.id.clk_mTextView_dzh == v.getId()) {
			Frame.HANDLES.sentAll("FrgTailiYulan", 0, null);
		}
	}

}