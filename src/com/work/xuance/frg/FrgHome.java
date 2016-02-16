//
//  FrgHome
//
//  Created by Administrator on 2015-10-02 21:04:22
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.Toast;

import com.framewidget.newMenu.SlidingFragment;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.IndexAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MAccount;
import com.udows.common.proto.MFile;
import com.udows.common.proto.MFileList;
import com.work.xuance.F;
import com.work.xuance.R;

public class FrgHome extends BaseFrg {

	public FragmentManager fragmentManager;
	public SlidingFragment mSlidingFragment;

	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_home);
		Frame.UpdateSelf(getContext(), true);
		initView();
		loaddata();
	}

	@Override
	public void disposeMsg(int type, Object obj) {
		switch (type) {
		case 0:
			ApisFactory.getApiMLogin().load(getContext(), FrgHome.this,
					"MLogin", null, null, "android");
			break;
		}
	}

	public void MLogin(Son s) {
		MAccount mMAccount = (MAccount) s.getBuild();
		F.Login(mMAccount.id, mMAccount.verify);
	}

	private void initView() {
		mSlidingFragment = new SlidingFragment(this);
		fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		mSlidingFragment = new SlidingFragment(this);
		fragmentTransaction.replace(R.id.mFrameLayout, mSlidingFragment);
		fragmentTransaction.commit();
		mSlidingFragment.addContentView(new FrgDingzhi(), "定制",
				R.drawable.bt_dz_n);
		mSlidingFragment.addContentView(new FrgZh(), "账户", R.drawable.bt_zh_n);
		mSlidingFragment.addContentView(new FrgXinxi(), "信息",
				R.drawable.bt_xx_n);
		mSlidingFragment.setMode(0);
	}

	public void loaddata() {
	}

}