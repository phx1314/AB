//
//  ZhaopianShy
//
//  Created by Administrator on 2015-10-06 10:34:24
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.MActivityActionbar;
import com.mdx.framework.widget.MImageView;
import com.work.xuance.F;
import com.work.xuance.R;
import com.work.xuance.model.Model2Son;

public class ZhaopianShy extends BaseItem {
	public EditText mEditText_title;
	public MImageView mMImageView_right;
	public TextView mTextView_bianji;
	public TextView mTextView_bianjizhu;
	public TextView mTextView_bianjifu;
	public LinearLayout mLinearLayout_bianji;
	public LinearLayout clk_LinearLayout_style1;
	public LinearLayout clk_LinearLayout_style2;
	public LinearLayout mLinearLayout_fenmian;
	public ImageView mImageView_dui1;
	public ImageView mImageView_dui2;
	public ImageView mImageView_fan;
	public EditText mEditText_time;
	public Model2Son item;

	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_zhaopian_shy, null);
		convertView.setTag(new ZhaopianShy(convertView));
		return convertView;
	}

	public ZhaopianShy(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		mEditText_title = (EditText) contentview
				.findViewById(R.id.mEditText_title);
		mLinearLayout_fenmian = (LinearLayout) contentview
				.findViewById(R.id.mLinearLayout_fenmian);
		mTextView_bianji = (TextView) contentview
				.findViewById(R.id.mTextView_bianji);
		mTextView_bianjizhu = (TextView) contentview
				.findViewById(R.id.mTextView_bianjizhu);
		mTextView_bianjifu = (TextView) contentview
				.findViewById(R.id.mTextView_bianjifu);
		mLinearLayout_bianji = (LinearLayout) contentview
				.findViewById(R.id.mLinearLayout_bianji);
		clk_LinearLayout_style1 = (LinearLayout) contentview
				.findViewById(R.id.clk_LinearLayout_style1);
		clk_LinearLayout_style2 = (LinearLayout) contentview
				.findViewById(R.id.clk_LinearLayout_style2);
		mMImageView_right = (MImageView) contentview
				.findViewById(R.id.mMImageView_right);
		mImageView_dui1 = (ImageView) contentview
				.findViewById(R.id.mImageView_dui1);
		mImageView_fan = (ImageView) contentview
				.findViewById(R.id.mImageView_fan);
		mImageView_dui2 = (ImageView) contentview
				.findViewById(R.id.mImageView_dui2);
		mEditText_time = (EditText) contentview
				.findViewById(R.id.mEditText_time);
		mEditText_title.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				item.getmFileSon2().setTitle_beifen(
						mEditText_title.getText().toString());
			}
		});
		mEditText_time.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				item.getmFileSon2().setSubtitle_beifen(
						mEditText_time.getText().toString());
			}
		});
		clk_LinearLayout_style1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Frame.HANDLES.sentAll("FrgZhaopian", 2, "1");
			}
		});
		clk_LinearLayout_style2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Frame.HANDLES.sentAll("FrgZhaopian", 2, "2");
			}
		});
		mTextView_bianjizhu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mEditText_title.requestFocus();
				F.tanChuSoftKey((MActivityActionbar) context);
			}
		});
		mTextView_bianjifu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mEditText_time.requestFocus();
				F.tanChuSoftKey((MActivityActionbar) context);
			}
		});
	}

	public void set(Model2Son item, int type, String color) {
		this.item = item;
		mMImageView_right.setObj(item.getmFileSon2().getImg());
		mEditText_title.setText(item.getmFileSon2().getTitle());
		mEditText_time.setText(item.getmFileSon2().getSubtitle());
		if (type == 0) {
			mEditText_title.setEnabled(false);
			mEditText_time.setEnabled(false);
			mImageView_fan.setVisibility(View.VISIBLE);
			mTextView_bianji.setVisibility(View.GONE);
			mLinearLayout_bianji.setVisibility(View.GONE);
		} else {
			mEditText_title.setEnabled(true);
			mEditText_time.setEnabled(true);
			mImageView_fan.setVisibility(View.GONE);
			mTextView_bianji.setVisibility(View.VISIBLE);
			mLinearLayout_bianji.setVisibility(View.VISIBLE);
		}
		if (color != null) {
			if (color.equals("1")) {
				mImageView_dui1.setVisibility(View.VISIBLE);
				mImageView_dui2.setVisibility(View.INVISIBLE);
				mLinearLayout_fenmian
						.setBackgroundResource(R.drawable.bg_cezifengmian);
				mEditText_title.setTextColor(Color.parseColor("#2363e6"));
				mEditText_time.setTextColor(Color.parseColor("#e6239f"));
			} else {
				mImageView_dui1.setVisibility(View.INVISIBLE);
				mImageView_dui2.setVisibility(View.VISIBLE);
				mLinearLayout_fenmian
						.setBackgroundResource(R.drawable.bg_jianbianxiangce);
				mEditText_title.setTextColor(Color.parseColor("#ffffff"));
				mEditText_time.setTextColor(Color.parseColor("#ffffff"));
			}
		}
	}

}