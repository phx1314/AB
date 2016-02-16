//
//  GridSon
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.widget.MImageView;
import com.work.xuance.R;
import com.work.xuance.model.FileFather.FileSon;

public class GridSon extends BaseItem {
	public MImageView mMImageView;
	public LinearLayout mLinearLayout_xuanzhong;
	public TextView mTextView_num;
	public ImageView mImageView_gou;

	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_grid_son, null);
		convertView.setTag(new GridSon(convertView));
		return convertView;
	}

	public GridSon(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		mMImageView = (MImageView) contentview.findViewById(R.id.mMImageView);
		mLinearLayout_xuanzhong = (LinearLayout) contentview
				.findViewById(R.id.mLinearLayout_xuanzhong);
		mTextView_num = (TextView) contentview.findViewById(R.id.mTextView_num);
		mImageView_gou = (ImageView) contentview
				.findViewById(R.id.mImageView_gou);

	}

	public void set(FileSon item) {
		mMImageView.setObj(item.getImg());
		if (item.isChoose()) {
			mLinearLayout_xuanzhong.setVisibility(View.VISIBLE);
			if (item.getDiji() == 0) {
				mTextView_num.setText("封面");
			} else {
				mTextView_num.setText(item.getDiji() + "");
			}
		} else {
			mLinearLayout_xuanzhong.setVisibility(View.GONE);
		}
	}

}