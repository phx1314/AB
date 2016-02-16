//
//  FrgZh
//
//  Created by Administrator on 2015-10-03 17:59:53
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mdx.framework.widget.MPageListView;
import com.udows.common.proto.ApisFactory;
import com.work.xuance.R;
import com.work.xuance.ada.AdaDd;
import com.work.xuance.dataformat.DfDd;
import com.work.xuance.item.Headlayout;

public class FrgZh extends BaseFrg {

	public Headlayout mHeadlayout;
	public MPageListView mMPageListView;

	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_zh);
		initView();
		loaddata();
	}

	@Override
	public void disposeMsg(int type, Object obj) {
		switch (type) {
		case 010:
			mMPageListView.reload();
			break;
		}
	}

	private void initView() {
		mHeadlayout = (Headlayout) findViewById(R.id.mHeadlayout);
		mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
		mHeadlayout.setTitle("账户");
		mHeadlayout.setRText("编辑");
		mHeadlayout.setRightOnclicker(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mHeadlayout.mTextView_right.getText().toString()
						.equals("编辑")) {
					mHeadlayout.setRText("完成");
					((DfDd) mMPageListView.getDataFormat()).updateState(1);
					((AdaDd) mMPageListView.getListAdapter()).updateState(1);
				} else {
					mHeadlayout.setRText("编辑");
					((DfDd) mMPageListView.getDataFormat()).updateState(0);
					((AdaDd) mMPageListView.getListAdapter()).updateState(0);
				}
			}
		});
	}

	public void loaddata() {
		mMPageListView.setApiUpdate(ApisFactory.getApiMOrderList().set());
		mMPageListView.setDataFormat(new DfDd());
		mMPageListView.pullLoad();
	}

}