//
//  MxpFirst
//
//  Created by Administrator on 2015-10-24 13:19:53
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mdx.framework.Frame;
import com.mdx.framework.widget.MImageView;
import com.work.xuance.R;
import com.work.xuance.model.FileFather.FileSon;

public class MxpFirst extends BaseItem {
	public MImageView mImageView;
	public EditText mEditText;
	public LinearLayout mLinearLayout_bianji;
	public FileSon item;
	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_mxp_first, null);
		convertView.setTag(new MxpFirst(convertView));
		return convertView;
	}

	public MxpFirst(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		mImageView = (MImageView) contentview.findViewById(R.id.mImageView);
		mEditText = (EditText) contentview.findViewById(R.id.mEditText);
		mLinearLayout_bianji = (LinearLayout) contentview
				.findViewById(R.id.mLinearLayout_bianji);
		mLinearLayout_bianji.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mLinearLayout_bianji.setVisibility(View.GONE);
				Frame.HANDLES.sentAll("FrgMxpZs", 2, null);
			}
		});
		mEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				item.setTitle_beifen(mEditText.getText().toString());
			}
		});
	}

	public void setShow() {
		mLinearLayout_bianji.setVisibility(View.VISIBLE);
	}

	public void set(FileSon item) {
		this.item=item;
		mImageView.setObj(item.getImg());
	}

	public void setIsEdit(boolean isTrue) {
		mEditText.setEnabled(isTrue);
	}
}