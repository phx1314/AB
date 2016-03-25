//
//  FrgLoading
//
//  Created by Administrator on 2015-10-03 15:29:15
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.mdx.framework.activity.IndexAct;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MAccount;
import com.udows.common.proto.MPhotoList;
import com.udows.common.proto.MRet;
import com.work.xuance.F;
import com.work.xuance.R;

public class FrgLoading extends BaseFrg {
	private final static int CWJ_HEAP_SIZE = 6 * 1024 * 1024;
	public ProgressDialog mProgressDialog;
	public Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Helper.startActivity(getContext(), FrgHome.class, IndexAct.class);
			FrgLoading.this.finish();
		}

	};

	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_loading);
		mProgressDialog = new ProgressDialog(getContext());
		mProgressDialog.setMessage("图片更新中，请稍后...");
		LoadingShow = false;
		initView();
		loaddata();
	}

	private void initView() {

	}

	public void loaddata() {
		ApisFactory.getApiMSingleSysParams().load(getContext(),
				FrgLoading.this, "MSingleSysParams1", "1001");
	}

	public void MSingleSysParams1(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		F.photo_price = mMRet.msg;
		ApisFactory.getApiMSingleSysParams().load(getContext(),
				FrgLoading.this, "MSingleSysParams2", "1002");
	}

	public void MSingleSysParams2(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		F.taili_price = mMRet.msg;
		ApisFactory.getApiMSingleSysParams().load(getContext(),
				FrgLoading.this, "MSingleSysParams3", "1003");
	}

	public void MSingleSysParams3(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		F.mxp_price = mMRet.msg;
		ApisFactory.getApiMLogin().load(getContext(), FrgLoading.this,
				"MLogin", null, null, "android");
	}

	public void MTemplate(Son s) {
		F.mMPhotoList = (MPhotoList) s.getBuild();
		mProgressDialog.show();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < F.mMPhotoList.photos.size(); i++) {
						F.saveImg(getContext(),
								BaseConfig.getUri() + "/download.do?id="
										+ F.mMPhotoList.photos.get(i).img,
								F.mMPhotoList.photos.get(i).img);
					}
					mHandler.sendEmptyMessage(0);
				} catch (Exception e) {
				}
			}
		}).start();
	}

	public void MLogin(Son s) {
		MAccount mMAccount = (MAccount) s.getBuild();
		F.Login(mMAccount.id, mMAccount.verify);
		ApisFactory.getApiMTemplate().load(getContext(), FrgLoading.this,
				"MTemplate");
	}
}