//
//  FrgXuanzezhaopian
//
//  Created by Administrator on 2015-10-04 14:37:02
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.work.xuance.F;
import com.work.xuance.R;
import com.work.xuance.act.TitleActDf;
import com.work.xuance.ada.AdaBottomImg;
import com.work.xuance.ada.AdaGridFather;
import com.work.xuance.model.FileFather;
import com.work.xuance.model.ModelData;
import com.work.xuance.model.FileFather.FileSon;
import com.work.xuance.view.DFHorizontalListView;

public class FrgXuanzezhaopian extends BaseFrg {

	public GridView mGridView;
	public TextView mTextView_left;
	public TextView mTextView_right;
	public DFHorizontalListView mHorizontalScrollView;
	public LinearLayout mLinearLayout_bottom;
	public AdaGridFather mAdaGridFather;
	public List<FileFather> mFileFathers;
	public int size;
	public static List<FileSon> mFileSons = new ArrayList<FileFather.FileSon>();
	public AdaBottomImg mAdaBottomImg;
	public ModelData mModelData;

	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_xuanzezhaopian);
		size = getActivity().getIntent().getIntExtra("size", 0);
		initView();
		loaddata();
	}

	@Override
	public void disposeMsg(int type, Object obj) {
		switch (type) {
		case 0:
			mModelData = (ModelData) obj;
			boolean isMeiYou = true;
			for (int i = 0; i < mFileSons.size(); i++) {
				if (mFileSons.get(i).getImageId()
						.equals(mModelData.getmFileSon().getImageId())) {
					isMeiYou = false;
					break;
				}
			}
			if (isMeiYou) {
				mFileSons.add(mModelData.getmFileSon());
			}
			change();
			break;
		case 1:
			mModelData = (ModelData) obj;
			int position = -1;
			for (int i = 0; i < mFileSons.size(); i++) {
				if (mFileSons.get(i).getImageId()
						.equals(mModelData.getmFileSon().getImageId())) {
					position = i;
					break;
				}
			}
			if (position != -1) {
				mFileSons.remove(position);
			}
			change();
			break;
		case 010:
			FrgXuanzezhaopian.this.finish();
			break;
		}
	}

	private void change() {
		if (mFileSons.size() > 0) {
			mLinearLayout_bottom.setVisibility(View.VISIBLE);
			mHorizontalScrollView.setVisibility(View.VISIBLE);
			mTextView_left.setText("已选" + mFileSons.size() + "张");
		} else {
			mLinearLayout_bottom.setVisibility(View.GONE);
			mHorizontalScrollView.setVisibility(View.GONE);
		}
		if (mHeadlayout != null) {
			if (size == 21) {
				if (mFileSons.size() >= 2) {
					mHeadlayout.setRightBacgroud(R.drawable.bt_next_n);
				} else {
					mHeadlayout.setRightGone();
				}
			} else {
				if (mFileSons.size() >= size) {
					mHeadlayout.setRightBacgroud(R.drawable.bt_next_n);
				} else {
					mHeadlayout.setRightGone();
				}
			}
		}
		mAdaBottomImg.clearNoChange();
		mAdaBottomImg.AddAll(mFileSons);
		new Handler().postDelayed(new Runnable() {
			public void run() {
				mHorizontalScrollView.setSelection(mFileSons.size());
			}
		}, 350);
		mFileFathers.get(mModelData.getPosition_father()).getmFileSons()
				.remove(mModelData.getPosition_son());
		mFileFathers.get(mModelData.getPosition_father()).getmFileSons()
				.add(mModelData.getPosition_son(), mModelData.getmFileSon());
	}

	private void initView() {
		mGridView = (GridView) findViewById(R.id.mGridView);
		mLinearLayout_bottom = (LinearLayout) findViewById(R.id.mLinearLayout_bottom);
		mTextView_left = (TextView) findViewById(R.id.mTextView_left);
		mTextView_right = (TextView) findViewById(R.id.mTextView_right);
		mHorizontalScrollView = (DFHorizontalListView) findViewById(R.id.mHorizontalScrollView);
		mFileFathers = F.getFileList(getContext());
		mFileSons.clear();
	}

	public void loaddata() {
		mAdaGridFather = new AdaGridFather(getContext(), mFileFathers, size);
		mGridView.setAdapter(mAdaGridFather);
		mAdaBottomImg = new AdaBottomImg(getContext(), mFileSons);
		mHorizontalScrollView.setAdapter(mAdaBottomImg);

	}

	@Override
	public void setActionBar(ActionBar actionBar, Context context) {
		super.setActionBar(actionBar, context);
		mHeadlayout.setTitle("选择" + size + "张照片");
		mHeadlayout.setRightOnclicker(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mHeadlayout.setRightEnable(false);
				if (size == 21) {
					Helper.startActivity(getContext(), FrgZhaopian.class,
							TitleActDf.class, "data", mFileSons,"isxiugai","1");
				} else if (size == 13) {
					Helper.startActivity(getContext(), FrgTailiYulan.class,
							TitleAct.class, "data", mFileSons);
				} else {
					Helper.startActivity(getContext(), FrgMxpZs.class,
							TitleActDf.class, "data", mFileSons);
				}
				mHeadlayout.setRightEnable(true);
			}
		});
	}

}