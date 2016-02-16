//
//  ZhaopianYlChanggui
//
//  Created by Administrator on 2015-10-06 10:34:24
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.widget.MImageView;
import com.work.xuance.R;
import com.work.xuance.model.FileFather.FileSon;

public class ZhaopianYlChanggui extends BaseItem {
	public LinearLayout mLinearLayout_left;
	public TextView mTextView_yeshu;
	public MImageView mMImageView_left;
	public EditText mEditText_left;
	public LinearLayout mLinearLayout_left_feiye;
	public EditText mEditText_title;
	public LinearLayout mLinearLayout_right;
	public TextView mTextView_yeshu_right;
	public TextView mTextView_3;
	public TextView mTextView_d3;
	public MImageView mMImageView_right;
	public EditText mEditText_right;
	public LinearLayout mLinearLayout_right_feiye;
	public EditText mEditText_title_right;
	public FileSon item_left;
	public FileSon item_right;
	public int yema;
	public String isxiugai;

	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_zhaopian_yl_changgui,
				null);
		convertView.setTag(new ZhaopianYlChanggui(convertView));
		return convertView;
	}

	public ZhaopianYlChanggui(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		mLinearLayout_left = (LinearLayout) contentview
				.findViewById(R.id.mLinearLayout_left);
		mTextView_yeshu = (TextView) contentview
				.findViewById(R.id.mTextView_yeshu);
		mTextView_3 = (TextView) contentview.findViewById(R.id.mTextView_3);
		mTextView_d3 = (TextView) contentview.findViewById(R.id.mTextView_d3);
		mMImageView_left = (MImageView) contentview
				.findViewById(R.id.mMImageView_left);
		mEditText_left = (EditText) contentview
				.findViewById(R.id.mEditText_left);
		mLinearLayout_left_feiye = (LinearLayout) contentview
				.findViewById(R.id.mLinearLayout_left_feiye);
		mEditText_title = (EditText) contentview
				.findViewById(R.id.mEditText_title);
		mLinearLayout_right = (LinearLayout) contentview
				.findViewById(R.id.mLinearLayout_right);
		mTextView_yeshu_right = (TextView) contentview
				.findViewById(R.id.mTextView_yeshu_right);
		mMImageView_right = (MImageView) contentview
				.findViewById(R.id.mMImageView_right);
		mEditText_right = (EditText) contentview
				.findViewById(R.id.mEditText_right);
		mLinearLayout_right_feiye = (LinearLayout) contentview
				.findViewById(R.id.mLinearLayout_right_feiye);
		mEditText_title_right = (EditText) contentview
				.findViewById(R.id.mEditText_title_right);
		mEditText_right.addTextChangedListener(new TextWatcher() {
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
				if (item_right != null) {
					item_right.setTitle_beifen(mEditText_right.getText()
							.toString());
				}
			}
		});
		mEditText_left.addTextChangedListener(new TextWatcher() {
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
				if (item_left != null) {
					item_left.setTitle_beifen(mEditText_left.getText()
							.toString());
				}
			}
		});
		mTextView_d3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Frame.HANDLES.sentAll("FrgZhaopian", 010, null);
			}
		});
	}

	public void set(int i, FileSon item_left, FileSon item_right, int type,
			int position, int yema, String isxiugai) {
		this.yema = yema;
		this.item_left = item_left;
		this.item_right = item_right;
		this.isxiugai = isxiugai;
		if (i == 0) {
			mLinearLayout_left.setVisibility(View.VISIBLE);
			mLinearLayout_right.setVisibility(View.VISIBLE);
			mTextView_yeshu_right.setVisibility(View.GONE);
			mMImageView_left.setVisibility(View.GONE);
			mLinearLayout_left_feiye.setVisibility(View.GONE);
			mTextView_yeshu.setVisibility(View.GONE);
			mEditText_left.setVisibility(View.GONE);
			mMImageView_right.setVisibility(View.GONE);
			mEditText_right.setVisibility(View.GONE);
			mLinearLayout_right_feiye.setVisibility(View.VISIBLE);
			mTextView_d3.setVisibility(View.GONE);
			mTextView_3.setVisibility(View.VISIBLE);
		} else if (i == 1) {
			mLinearLayout_left.setVisibility(View.VISIBLE);
			mLinearLayout_right.setVisibility(View.VISIBLE);
			mTextView_yeshu.setVisibility(View.GONE);
			mMImageView_left.setVisibility(View.GONE);
			mEditText_left.setVisibility(View.GONE);
			mTextView_yeshu_right.setVisibility(View.VISIBLE);
			mMImageView_right.setVisibility(View.VISIBLE);
			mEditText_right.setVisibility(View.VISIBLE);
			mLinearLayout_left_feiye.setVisibility(View.VISIBLE);
			mLinearLayout_right_feiye.setVisibility(View.GONE);
			mMImageView_right.setObj(item_right.getImg());
			mTextView_yeshu_right.setText("1 / " + yema);
			mEditText_right.setText(item_right.getTitle());
		} else if (i == 2) {
			mLinearLayout_left.setVisibility(View.VISIBLE);
			mLinearLayout_right.setVisibility(View.VISIBLE);
			mTextView_yeshu.setVisibility(View.VISIBLE);
			mMImageView_left.setVisibility(View.VISIBLE);
			mEditText_left.setVisibility(View.VISIBLE);
			mTextView_yeshu_right.setVisibility(View.GONE);
			mMImageView_right.setVisibility(View.GONE);
			mEditText_right.setVisibility(View.GONE);
			mLinearLayout_left_feiye.setVisibility(View.GONE);
			mLinearLayout_right_feiye.setVisibility(View.VISIBLE);
			if (TextUtils.isEmpty(isxiugai)) {
				mTextView_d3.setVisibility(View.GONE);
				mTextView_3.setVisibility(View.VISIBLE);
			} else {
				mTextView_d3.setVisibility(View.VISIBLE);
				mTextView_3.setVisibility(View.GONE);
			}
			mMImageView_left.setObj(item_left.getImg());
			mTextView_yeshu.setText(yema + " / " + yema);
			mEditText_left.setText(item_left.getTitle());
			if (yema % 2 != 0) {
				mTextView_yeshu.setVisibility(View.GONE);
				mMImageView_left.setVisibility(View.GONE);
				mEditText_left.setVisibility(View.GONE);
			}
		} else if (i == 3) {
			mLinearLayout_left.setVisibility(View.VISIBLE);
			mLinearLayout_right.setVisibility(View.INVISIBLE);
			mTextView_yeshu.setVisibility(View.GONE);
			mMImageView_left.setVisibility(View.GONE);
			mEditText_left.setVisibility(View.GONE);
			mLinearLayout_left_feiye.setVisibility(View.VISIBLE);
		} else {
			mLinearLayout_left.setVisibility(View.VISIBLE);
			mLinearLayout_right.setVisibility(View.VISIBLE);
			mTextView_yeshu.setVisibility(View.VISIBLE);
			mMImageView_left.setVisibility(View.VISIBLE);
			mEditText_left.setVisibility(View.VISIBLE);
			mTextView_yeshu_right.setVisibility(View.VISIBLE);
			mMImageView_right.setVisibility(View.VISIBLE);
			mEditText_right.setVisibility(View.VISIBLE);
			mLinearLayout_left_feiye.setVisibility(View.GONE);
			mLinearLayout_right_feiye.setVisibility(View.GONE);
			mMImageView_left.setObj(item_left.getImg());
			mMImageView_right.setObj(item_right.getImg());
			mTextView_yeshu.setText((position * 2) + "  / " + yema);
			mTextView_yeshu_right.setText((position * 2 + 1) + " / " + yema);
			mEditText_left.setText(item_left.getTitle());
			mEditText_right.setText(item_right.getTitle());
		}
		if (type == 0) {
			mEditText_title.setEnabled(false);
			mEditText_title_right.setEnabled(false);
			mEditText_left.setEnabled(false);
			mEditText_right.setEnabled(false);
		} else {
			mEditText_title.setEnabled(true);
			mEditText_title_right.setEnabled(true);
			mEditText_left.setEnabled(true);
			mEditText_right.setEnabled(true);
		}
	}

}