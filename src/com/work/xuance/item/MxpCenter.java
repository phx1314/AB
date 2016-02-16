//
//  MxpCenter
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
import android.widget.EditText;

import com.mdx.framework.widget.MImageView;
import com.work.xuance.R;
import com.work.xuance.model.FileFather.FileSon;

public class MxpCenter extends BaseItem {
	public MImageView mImageView;
	public EditText mEditText;

	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_mxp_center, null);
		convertView.setTag(new MxpCenter(convertView));
		return convertView;
	}

	public MxpCenter(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		mImageView = (MImageView) contentview.findViewById(R.id.mImageView);
		mEditText = (EditText) contentview.findViewById(R.id.mEditText);

	}

	public void set(FileSon item) {
		mImageView.setObj(item.getImg());
	}
	public void setIsEdit(boolean isTrue) {
		mEditText.setEnabled(isTrue);
	}
}