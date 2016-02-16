//
//  GridShunxu
//
//  Created by Administrator on 2015-10-06 18:20:40
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

import com.mdx.framework.widget.MImageView;
import com.work.xuance.R;
import com.work.xuance.model.FileFather.FileSon;

public class GridShunxu extends BaseItem {
	public MImageView mMImageView;
	public ImageView mImageView_left;
	public ImageView mImageView_right;

	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_grid_shunxu, null);
		convertView.setTag(new GridShunxu(convertView));
		return convertView;
	}

	public GridShunxu(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		mMImageView = (MImageView) contentview.findViewById(R.id.mMImageView);
		mImageView_left = (ImageView) contentview
				.findViewById(R.id.mImageView_left);
		mImageView_right = (ImageView) contentview
				.findViewById(R.id.mImageView_right);

	}

	public void set(FileSon item, int position, int size) {
		if (position == 0) {
			mImageView_left.setVisibility(View.VISIBLE);
			mImageView_right.setVisibility(View.GONE);
		} else if (position == (size - 1)) {
			mImageView_left.setVisibility(View.GONE);
			mImageView_right.setVisibility(View.VISIBLE);
		} else {
			mImageView_left.setVisibility(View.GONE);
			mImageView_right.setVisibility(View.GONE);
		}
		mMImageView.setObj(item.getImg());
	}

}