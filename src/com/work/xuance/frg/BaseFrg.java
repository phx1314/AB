//
//  BaseFrg
//
//  Created by Administrator on 2015-10-02 21:04:23
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.mdx.framework.activity.MFragment;
import com.mdx.framework.widget.ActionBar;
import com.work.xuance.R;
import com.work.xuance.item.Headlayout;

public abstract class BaseFrg extends MFragment implements View.OnClickListener {
	public Headlayout mHeadlayout;

	@Override
	public void onClick(View v) {

	}

	@Override
	protected void initcreate(Bundle savedInstanceState) {
		super.initcreate(savedInstanceState);
		LoadingShow = true;
	}

	@Override
	public void setActionBar(ActionBar actionBar, Context context) {
		mHeadlayout = new Headlayout(context);
		mHeadlayout.setLeftBackground(R.drawable.bt_back_n);
		mHeadlayout.setGoBack(getActivity());
		actionBar.addView(mHeadlayout);
	}
}
