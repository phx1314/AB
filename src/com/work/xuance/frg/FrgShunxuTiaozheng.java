//
//  FrgShunxuTiaozheng
//
//  Created by Administrator on 2015-10-06 18:20:37
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.MActivityActionbar;
import com.mdx.framework.widget.ActionBar;
import com.work.xuance.R;
import com.work.xuance.ada.AdaGridShunxu;
import com.work.xuance.model.FileFather;
import com.work.xuance.model.FileFather.FileSon;
import com.work.xuance.view.DragGridView;
import com.work.xuance.view.DragGridView.OnChanageListener;

public class FrgShunxuTiaozheng extends BaseFrg {

	public DragGridView dragGridView;
	public LinearLayout mLinearLayout_bottom;
	public LinearLayout clk_left;
	public LinearLayout clk_right;
	public List<FileSon> mFileSons = new ArrayList<FileFather.FileSon>();
	public List<FileSon> mFileSons_old = new ArrayList<FileFather.FileSon>();
	public AdaGridShunxu mAdaGridShunxu;

	@SuppressWarnings("unchecked")
	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_shunxu_tiaozheng);
		((MActivityActionbar) getActivity()).setSwipeBackEnable(false);
		mFileSons = (List<FileSon>) getActivity().getIntent()
				.getSerializableExtra("data");
		for (FileSon mFileSon : mFileSons) {
			mFileSons_old.add(mFileSon);
		}
		initView();
		loaddata();
	}

	private void initView() {
		dragGridView = (DragGridView) findViewById(R.id.dragGridView);
		mLinearLayout_bottom = (LinearLayout) findViewById(R.id.mLinearLayout_bottom);
		clk_left = (LinearLayout) findViewById(R.id.clk_left);
		clk_right = (LinearLayout) findViewById(R.id.clk_right);

		clk_left.setOnClickListener(com.mdx.framework.utility.Helper
				.delayClickLitener(this));
		clk_right.setOnClickListener(com.mdx.framework.utility.Helper
				.delayClickLitener(this));
		mAdaGridShunxu = new AdaGridShunxu(getContext(), mFileSons);
		dragGridView.setAdapter(mAdaGridShunxu);
		dragGridView.setOnChangeListener(new OnChanageListener() {
			@Override
			public void onChange(int from, int to) {
				FileSon mFileSon = mAdaGridShunxu.get(from);
				// 这里的处理需要注意下
				if (from < to) {
					for (int i = from; i < to; i++) {
						Collections.swap(mAdaGridShunxu.getList(), i, i + 1);
					}
				} else if (from > to) {
					for (int i = from; i > to; i--) {
						Collections.swap(mAdaGridShunxu.getList(), i, i - 1);
					}
				}
				mAdaGridShunxu.getList().set(to, mFileSon);
				mAdaGridShunxu.notifyDataSetChanged();
			}
		});
	}

	public void loaddata() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(android.view.View v) {
		if (R.id.clk_left == v.getId()) {
			Collections.shuffle(mFileSons);
			mAdaGridShunxu.clear();
			mAdaGridShunxu.AddAll(mFileSons);
		} else if (R.id.clk_right == v.getId()) {
			mAdaGridShunxu.clear();
			mFileSons.clear();
			for (FileSon mFileSon : mFileSons_old) {
				mFileSons.add(mFileSon);
			}
			mAdaGridShunxu.AddAll(mFileSons);
		}
	}

	@Override
	public void setActionBar(ActionBar actionBar, Context context) {
		super.setActionBar(actionBar, context);
		mHeadlayout.setTitle("页面调整");
		mHeadlayout.setRText("保存");
		mHeadlayout.setRightOnclicker(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Frame.HANDLES.sentAll("FrgZhaopian", 0,
						mAdaGridShunxu.getList());
				FrgShunxuTiaozheng.this.finish();
			}
		});
	}
}