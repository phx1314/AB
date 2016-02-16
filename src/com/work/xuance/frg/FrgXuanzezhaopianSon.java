//
//  FrgXuanzezhaopianSon
//
//  Created by Administrator on 2015-10-04 14:37:02
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

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
import com.work.xuance.R;
import com.work.xuance.act.TitleActDf;
import com.work.xuance.ada.AdaBottomImg;
import com.work.xuance.ada.AdaGridSon;
import com.work.xuance.model.FileFather.FileSon;
import com.work.xuance.view.DFHorizontalListView;

public class FrgXuanzezhaopianSon extends BaseFrg {

	public GridView mGridView;
	public TextView mTextView_left;
	public TextView mTextView_right;
	public DFHorizontalListView mHorizontalScrollView;
	public LinearLayout mLinearLayout_bottom;
	public String title;
	public AdaGridSon mAdaGridSon;
	public List<FileSon> mFileSons;
	public int size;
	public int position_father;
	public AdaBottomImg mAdaBottomImg;

	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_xuanzezhaopian_son);
		title = getActivity().getIntent().getStringExtra("title");
		size = getActivity().getIntent().getIntExtra("size", 0);
		position_father = getActivity().getIntent().getIntExtra(
				"position_father", 0);
		mFileSons = (List<FileSon>) getActivity().getIntent()
				.getSerializableExtra("data");
		initView();
		loaddata();
	}

	@Override
	public void disposeMsg(int type, Object obj) {
		switch (type) {
		case 0:
			change();
			if (obj != null) {
				FileSon mFileSon = (FileSon) obj;
				for (int i = 0; i < mFileSons.size(); i++) {
					if (mFileSons.get(i).getDiji() > mFileSon.getDiji()) {
						mFileSons.get(i)
								.setDiji(mFileSons.get(i).getDiji() - 1);
					}
					if (mFileSons.get(i).getImageId()
							.equals(mFileSon.getImageId())) {
						mFileSons.get(i).setChoose(false);
					}
				}
				mAdaGridSon.notifyDataSetChanged();
			}
			break;
		case 010:
			FrgXuanzezhaopianSon.this.finish();
			break;
		}
	}

	private void change() {
		if (FrgXuanzezhaopian.mFileSons.size() > 0) {
			mLinearLayout_bottom.setVisibility(View.VISIBLE);
			mHorizontalScrollView.setVisibility(View.VISIBLE);
			mTextView_right.setText("已选" + FrgXuanzezhaopian.mFileSons.size()
					+ "张");
		} else {
			mLinearLayout_bottom.setVisibility(View.GONE);
			mHorizontalScrollView.setVisibility(View.GONE);
		}
		// mAdaBottomImg = new AdaBottomImg(getContext(),
		// FrgXuanzezhaopian.mFileSons);
		// mHorizontalScrollView.setAdapter(mAdaBottomImg);
		mAdaBottomImg.clearNoChange();
		mAdaBottomImg.AddAll(FrgXuanzezhaopian.mFileSons);
		new Handler().postDelayed(new Runnable() {
			public void run() {
				mHorizontalScrollView.setSelection(FrgXuanzezhaopian.mFileSons
						.size());
			}
		}, 350);
		panduan();
	}

	private void panduan() {
		if (mHeadlayout != null) {
			if (size == 21) {
				if (FrgXuanzezhaopian.mFileSons.size() >= 2) {
					mHeadlayout.setRightBacgroud(R.drawable.bt_next_n);
				} else {
					mHeadlayout.setRightGone();
				}
			} else {
				if (FrgXuanzezhaopian.mFileSons.size() >= size) {
					mHeadlayout.setRightBacgroud(R.drawable.bt_next_n);
				} else {
					mHeadlayout.setRightGone();
				}
			}
		}
	}

	private void initView() {
		mGridView = (GridView) findViewById(R.id.mGridView);
		mLinearLayout_bottom = (LinearLayout) findViewById(R.id.mLinearLayout_bottom);
		mTextView_left = (TextView) findViewById(R.id.mTextView_left);
		mTextView_right = (TextView) findViewById(R.id.mTextView_right);
		mHorizontalScrollView = (DFHorizontalListView) findViewById(R.id.mHorizontalScrollView);
		mTextView_left.setText("选择" + size + "张");
	}

	public void loaddata() {
		mAdaGridSon = new AdaGridSon(getContext(), mFileSons, position_father,
				size);
		mGridView.setAdapter(mAdaGridSon);
		mAdaBottomImg = new AdaBottomImg(getContext(),
				FrgXuanzezhaopian.mFileSons);
		mHorizontalScrollView.setAdapter(mAdaBottomImg);
		change();
	}

	@Override
	public void setActionBar(ActionBar actionBar, Context context) {
		super.setActionBar(actionBar, context);
		mHeadlayout.setTitle(title);
		panduan();
		mHeadlayout.setRightOnclicker(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mHeadlayout.setRightEnable(false);
				if (size == 21) {
					Helper.startActivity(getContext(), FrgZhaopian.class,
							TitleActDf.class, "data",
							FrgXuanzezhaopian.mFileSons, "isxiugai", "1");
				} else if (size == 13) {
					Helper.startActivity(getContext(), FrgTailiYulan.class,
							TitleAct.class, "data", FrgXuanzezhaopian.mFileSons);
				} else {
					Helper.startActivity(getContext(), FrgMxpZs.class,
							TitleActDf.class, "data",
							FrgXuanzezhaopian.mFileSons);
				}
				mHeadlayout.setRightEnable(true);
			}
		});
	}
}