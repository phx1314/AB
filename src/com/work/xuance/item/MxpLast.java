//
//  MxpLast
//
//  Created by Administrator on 2015-10-24 13:19:53
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.udows.common.proto.MPhotoList;
import com.work.xuance.R;

public class MxpLast extends BaseItem {
	public LinearLayout clk_LinearLayout_style1;
	public LinearLayout clk_LinearLayout_style2;
	public TextView clk_mTextView_dzh;
	public ImageView mImageView_dui2;
	public ImageView mImageView_dui1;
	public ImageView mImageView_center;

	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_mxp_last, null);
		convertView.setTag(new MxpLast(convertView));
		return convertView;
	}

	public MxpLast(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		clk_LinearLayout_style1 = (LinearLayout) contentview
				.findViewById(R.id.clk_LinearLayout_style1);
		clk_LinearLayout_style2 = (LinearLayout) contentview
				.findViewById(R.id.clk_LinearLayout_style2);
		mImageView_dui1 = (ImageView) contentview
				.findViewById(R.id.mImageView_dui1);
		mImageView_center = (ImageView) contentview
				.findViewById(R.id.mImageView_center);
		mImageView_dui2 = (ImageView) contentview
				.findViewById(R.id.mImageView_dui2);
		clk_mTextView_dzh = (TextView) contentview
				.findViewById(R.id.clk_mTextView_dzh);

		clk_LinearLayout_style1
				.setOnClickListener(com.mdx.framework.utility.Helper
						.delayClickLitener(this));
		clk_LinearLayout_style2
				.setOnClickListener(com.mdx.framework.utility.Helper
						.delayClickLitener(this));
		clk_mTextView_dzh.setOnClickListener(com.mdx.framework.utility.Helper
				.delayClickLitener(this));

	}

	public void set(MPhotoList item) {
		if (item != null) {
			if (item.color.equals("1")) {
				mImageView_dui1.setVisibility(View.VISIBLE);
				mImageView_dui2.setVisibility(View.INVISIBLE);
				mImageView_center.setImageResource(R.drawable.bg_mxp1);
				Frame.HANDLES.sentAll("FrgMxpZs", 1, "1");
			} else {
				mImageView_dui1.setVisibility(View.INVISIBLE);
				mImageView_dui2.setVisibility(View.VISIBLE);
				mImageView_center.setImageResource(R.drawable.bg_mxp2);
				Frame.HANDLES.sentAll("FrgMxpZs", 1, "2");
			}
		}
	}

	@Override
	public void onClick(android.view.View v) {
		if (R.id.clk_LinearLayout_style1 == v.getId()) {
			mImageView_dui1.setVisibility(View.VISIBLE);
			mImageView_dui2.setVisibility(View.INVISIBLE);
			mImageView_center.setImageResource(R.drawable.bg_mxp1);
			Frame.HANDLES.sentAll("FrgMxpZs", 1, "1");
		} else if (R.id.clk_LinearLayout_style2 == v.getId()) {
			mImageView_dui1.setVisibility(View.INVISIBLE);
			mImageView_dui2.setVisibility(View.VISIBLE);
			mImageView_center.setImageResource(R.drawable.bg_mxp2);
			Frame.HANDLES.sentAll("FrgMxpZs", 1, "2");
		} else if (R.id.clk_mTextView_dzh == v.getId()) {
			Frame.HANDLES.sentAll("FrgMxpZs", 0, null);
		}
	}

}