//
//  Zhaopian
//
//  Created by Administrator on 2015-10-03 15:26:12
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.item;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.work.xuance.F;
import com.work.xuance.R;
import com.work.xuance.frg.FrgXuanzezhaopian;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Zhaopian extends BaseItem {
	public ImageView clk_mImageView_mxp;
	public ImageView clk_mImageView_tl;
	public ImageView mImageView_1;
	public ImageView mImageView_2;
	public TextView clk_mTextView_dzh;
	public TextView clk_mTextView_dzshm;

	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_zhaopian, null);
		convertView.setTag(new Zhaopian(convertView));
		return convertView;
	}

	public Zhaopian(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		clk_mImageView_mxp = (ImageView) contentview
				.findViewById(R.id.clk_mImageView_mxp);
		clk_mImageView_tl = (ImageView) contentview
				.findViewById(R.id.clk_mImageView_tl);
		mImageView_1 = (ImageView) contentview.findViewById(R.id.mImageView_1);
		mImageView_2 = (ImageView) contentview.findViewById(R.id.mImageView_2);
		clk_mTextView_dzh = (TextView) contentview
				.findViewById(R.id.clk_mTextView_dzh);
		clk_mTextView_dzshm = (TextView) contentview
				.findViewById(R.id.clk_mTextView_dzshm);

		clk_mImageView_tl.setOnClickListener(com.mdx.framework.utility.Helper
				.delayClickLitener(this));
		clk_mTextView_dzh.setOnClickListener(com.mdx.framework.utility.Helper
				.delayClickLitener(this));
		clk_mTextView_dzshm.setOnClickListener(com.mdx.framework.utility.Helper
				.delayClickLitener(this));
		try {
			if (F.photo_price.substring(0, 1).equals("1")) {
				mImageView_1.setImageResource(R.drawable.t_1);
			} else if (F.photo_price.substring(0, 1).equals("2")) {
				mImageView_1.setImageResource(R.drawable.t_2);
			} else if (F.photo_price.substring(0, 1).equals("3")) {
				mImageView_1.setImageResource(R.drawable.t_3);
			} else if (F.photo_price.substring(0, 1).equals("4")) {
				mImageView_1.setImageResource(R.drawable.t_4);
			} else if (F.photo_price.substring(0, 1).equals("5")) {
				mImageView_1.setImageResource(R.drawable.t_5);
			} else if (F.photo_price.substring(0, 1).equals("6")) {
				mImageView_1.setImageResource(R.drawable.t_6);
			} else if (F.photo_price.substring(0, 1).equals("7")) {
				mImageView_1.setImageResource(R.drawable.t_7);
			} else if (F.photo_price.substring(0, 1).equals("8")) {
				mImageView_1.setImageResource(R.drawable.t_8);
			} else if (F.photo_price.substring(0, 1).equals("9")) {
				mImageView_1.setImageResource(R.drawable.t_9);
			}
			if (F.photo_price.substring(1, 2).equals("1")) {
				mImageView_2.setImageResource(R.drawable.t_1);
			} else if (F.photo_price.substring(1, 2).equals("2")) {
				mImageView_2.setImageResource(R.drawable.t_2);
			} else if (F.photo_price.substring(1, 2).equals("3")) {
				mImageView_2.setImageResource(R.drawable.t_3);
			} else if (F.photo_price.substring(1, 2).equals("4")) {
				mImageView_2.setImageResource(R.drawable.t_4);
			} else if (F.photo_price.substring(1, 2).equals("5")) {
				mImageView_2.setImageResource(R.drawable.t_5);
			} else if (F.photo_price.substring(1, 2).equals("6")) {
				mImageView_2.setImageResource(R.drawable.t_6);
			} else if (F.photo_price.substring(1, 2).equals("7")) {
				mImageView_2.setImageResource(R.drawable.t_7);
			} else if (F.photo_price.substring(1, 2).equals("8")) {
				mImageView_2.setImageResource(R.drawable.t_8);
			} else if (F.photo_price.substring(1, 2).equals("9")) {
				mImageView_2.setImageResource(R.drawable.t_9);
			} else if (F.photo_price.substring(1, 2).equals("0")) {
				mImageView_2.setImageResource(R.drawable.t_0);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public void set(String item) {

	}

	@Override
	public void onClick(android.view.View v) {
		if (R.id.clk_mImageView_tl == v.getId()) {
			Frame.HANDLES.sentAll("FrgDingzhi", 1, null);
		} else if (R.id.clk_mTextView_dzh == v.getId()) {
			Helper.startActivity(context, FrgXuanzezhaopian.class,
					TitleAct.class, "size", 21);
		} else if (R.id.clk_mTextView_dzshm == v.getId()) {

		}
	}

}