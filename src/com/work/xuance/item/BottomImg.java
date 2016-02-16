//
//  BottomImg
//
//  Created by Administrator on 2015-10-04 14:37:02
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdx.framework.widget.MImageView;
import com.work.xuance.R;
import com.work.xuance.model.FileFather.FileSon;

public class BottomImg extends BaseItem {
	public MImageView mMImageView;
	public TextView mTextView_name;

	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_bottom_img, null);
		convertView.setTag(new BottomImg(convertView));
		return convertView;
	}

	public BottomImg(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		mMImageView = (MImageView) contentview.findViewById(R.id.mMImageView);
		mTextView_name = (TextView) contentview
				.findViewById(R.id.mTextView_name);

	}

	public void set(FileSon item) {
		mMImageView.setObj(item.getImg());
		if (item.getDiji() == 0) {
			mTextView_name.setText("封面");
		} else {
			mTextView_name.setText(item.getDiji() + "");
		}
	}

}