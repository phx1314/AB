//
//  FrgXinxi
//
//  Created by Administrator on 2015-10-03 17:59:53
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

import android.os.Bundle;

import com.mdx.framework.server.api.Son;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MRet;
import com.work.xuance.F;
import com.work.xuance.R;
import com.work.xuance.item.Headlayout;

import android.widget.TextView;

public class FrgXinxi extends BaseFrg {

	public Headlayout mHeadlayout;
	public TextView mTextView_verson;
	public TextView mTextView_phone;
	public TextView mTextView_email;
	public TextView mTextView_weixin;

	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_xinxi);
		initView();
		loaddata();
	}

	private void initView() {
		mHeadlayout = (Headlayout) findViewById(R.id.mHeadlayout);
		mTextView_verson = (TextView) findViewById(R.id.mTextView_verson);
		mTextView_phone = (TextView) findViewById(R.id.mTextView_phone);
		mTextView_email = (TextView) findViewById(R.id.mTextView_email);
		mTextView_weixin = (TextView) findViewById(R.id.mTextView_weixin);
		mHeadlayout.setTitle("信息");

	}

	public void loaddata() {
		ApisFactory.getApiMSingleSysParams().load(getContext(), FrgXinxi.this,
				"MSingleSysParams1", "1008");
		ApisFactory.getApiMSingleSysParams().load(getContext(), FrgXinxi.this,
				"MSingleSysParams2", "1009");
		ApisFactory.getApiMSingleSysParams().load(getContext(), FrgXinxi.this,
				"MSingleSysParams3", "1010");
	}

	public void MSingleSysParams1(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		mTextView_phone.setText("电话       "+ mMRet.msg);
	}
	public void MSingleSysParams2(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		mTextView_email.setText("邮箱       "+ mMRet.msg);
	}
	public void MSingleSysParams3(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		mTextView_weixin.setText("微信       "+ mMRet.msg);
	}
}