//
//  ZhaopianLast
//
//  Created by Administrator on 2015-10-06 10:34:24
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.item;

import com.mdx.framework.Frame;
import com.work.xuance.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.view.View;
import android.widget.TextView;

public class ZhaopianLast extends BaseItem {
	public TextView clk_mTextView_dzh;

	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_zhaopian_last, null);
		convertView.setTag(new ZhaopianLast(convertView));
		return convertView;
	}

	public ZhaopianLast(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		clk_mTextView_dzh = (TextView) contentview
				.findViewById(R.id.clk_mTextView_dzh);

		clk_mTextView_dzh.setOnClickListener(com.mdx.framework.utility.Helper
				.delayClickLitener(this));

	}

	public void set(String item) {

	}

	@Override
	public void onClick(android.view.View v) {
		if (R.id.clk_mTextView_dzh == v.getId()) {
			Frame.HANDLES.sentAll("FrgZhaopian", 1, null);
		}
	}

}