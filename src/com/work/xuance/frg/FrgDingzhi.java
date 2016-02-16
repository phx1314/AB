//
//  FrgDingzhi
//
//  Created by Administrator on 2015-10-03 17:59:53
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.framewidget.MyViewPagerAdapter;
import com.mdx.framework.widget.MViewPager;
import com.work.xuance.R;
import com.work.xuance.item.Headlayout;
import com.work.xuance.item.Mxp;
import com.work.xuance.item.Taili;
import com.work.xuance.item.Zhaopian;

public class FrgDingzhi extends BaseFrg {

	public Headlayout mHeadlayout;
	public MViewPager mViewPager;

	public MyViewPagerAdapter mMPagerAdapter;
	public List<View> views = new ArrayList<View>();
	public View view_1;
	public View view_2;
	public View view_3;

	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_dingzhi);
		initView();
		loaddata();
	}

	@Override
	public void disposeMsg(int arg0, Object arg1) {
		switch (arg0) {
		case 0:
			mViewPager.setCurrentItem(0);
			break;
		case 1:
			mViewPager.setCurrentItem(1);
			break;
		case 2:
			mViewPager.setCurrentItem(2);
			break;
		}
	}

	private void initView() {
		mHeadlayout = (Headlayout) findViewById(R.id.mHeadlayout);
		mViewPager = (MViewPager) findViewById(R.id.mViewPager);
		mHeadlayout.setTitle("炫册");

	}

	public void loaddata() {
		view_1 = Mxp.getView(getActivity(), null);
		view_2 = Zhaopian.getView(getActivity(), null);
		view_3 = Taili.getView(getActivity(), null);
		views.add(view_2);
		views.add(view_3);
		views.add(view_1);
		mMPagerAdapter = new MyViewPagerAdapter(views);
		mViewPager.setAdapter(mMPagerAdapter);
	}
}