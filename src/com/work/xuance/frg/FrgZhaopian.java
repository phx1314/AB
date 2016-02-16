//
//  FrgZhaopian
//
//  Created by Administrator on 2015-10-06 10:34:24
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

import java.util.ArrayList;
import java.util.List;

import okio.ByteString;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flipview.FlipView;
import com.flipview.OverFlipMode;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MFile;
import com.udows.common.proto.MFileList;
import com.udows.common.proto.MPhoto;
import com.udows.common.proto.MPhotoList;
import com.udows.common.proto.MRet;
import com.work.xuance.F;
import com.work.xuance.R;
import com.work.xuance.act.TitleActDf;
import com.work.xuance.ada.AdaZhaopianYlChanggui;
import com.work.xuance.model.FileFather;
import com.work.xuance.model.Model2Son;
import com.work.xuance.model.FileFather.FileSon;

public class FrgZhaopian extends BaseFrg {

	public FlipView flipView;
	public LinearLayout clk_left;
	public LinearLayout clk_right;
	public TextView mTextView_left;
	public TextView mTextView_right;
	public LinearLayout mLinearLayout_bottom;
	public List<FileSon> mFileSons = new ArrayList<FileFather.FileSon>();
	public AdaZhaopianYlChanggui mAdaZhaopianYlChanggui;
	public List<Model2Son> mModel2Sons = new ArrayList<Model2Son>();
	public int type = 0;
	public String id = "";
	public String color = "1";
	public boolean isCan = false;
	public String isxiugai= "";
	@SuppressWarnings("unchecked")
	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_zhaopian);
		id = getActivity().getIntent().getStringExtra("id");
		isxiugai = getActivity().getIntent().getStringExtra("isxiugai");
		Helper.toast("图片上传中...", getContext());
		initView();
		loaddata();
	}

	public void MOrderDetail(Son s) {
		MPhotoList mMPhotoList = (MPhotoList) s.getBuild();
		for (int i = 0; i < mMPhotoList.photos.size(); i++) {
			FileSon mFileSon = new FileSon(mMPhotoList.photos.get(i).img, i,
					false, i + "", "");
			mFileSon.setTitle(mMPhotoList.photos.get(i).title);
			mFileSon.setSubtitle(mMPhotoList.photos.get(i).subtitle);
			mFileSons.add(mFileSon);
		}
		color = mMPhotoList.color;
		update();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void disposeMsg(int type, Object obj) {
		switch (type) {
		case 0:
			mFileSons = (List<FileSon>) obj;
			update();
			break;
		case 1:
			dingGou();
			break;
		case 010:
			FrgZhaopian.this.finish();
			break;
		case 2:
			color = obj.toString();
			mAdaZhaopianYlChanggui.setColor(color);
			break;
		}
	}

	private void initView() {
		flipView = (FlipView) findViewById(R.id.flipView);
		flipView.peakNext(false);
		flipView.setOverFlipMode(OverFlipMode.GLOW);
		mTextView_left = (TextView) findViewById(R.id.mTextView_left);
		mTextView_right = (TextView) findViewById(R.id.mTextView_right);
		clk_left = (LinearLayout) findViewById(R.id.clk_left);
		clk_right = (LinearLayout) findViewById(R.id.clk_right);
		mLinearLayout_bottom = (LinearLayout) findViewById(R.id.mLinearLayout_bottom);

		clk_left.setOnClickListener(com.mdx.framework.utility.Helper
				.delayClickLitener(this));
		clk_right.setOnClickListener(com.mdx.framework.utility.Helper
				.delayClickLitener(this));
	}

	public void update() {
		mModel2Sons.clear();
		mModel2Sons.add(new Model2Son(null, mFileSons.get(0)));
		mModel2Sons.add(new Model2Son(null, null));
		mModel2Sons.add(new Model2Son(null, mFileSons.get(1)));
		for (int i = 2; i < mFileSons.size() - 1; i++) {
			if (i % 2 == 0) {
				mModel2Sons.add(new Model2Son(mFileSons.get(i), mFileSons
						.get(i + 1)));
			}
		}
		mModel2Sons
				.add(new Model2Son(mFileSons.get(mFileSons.size() - 1), null));
		mModel2Sons.add(new Model2Son(null, null));
		mModel2Sons.add(new Model2Son(null, null));
		if (mAdaZhaopianYlChanggui == null) {
			mAdaZhaopianYlChanggui = new AdaZhaopianYlChanggui(getContext(),
					mModel2Sons, color,mFileSons.size()-1,isxiugai);
			flipView.setAdapter(mAdaZhaopianYlChanggui);
		} else {
			mAdaZhaopianYlChanggui.clear();
			mAdaZhaopianYlChanggui.AddAll(mModel2Sons);
		}

	}

	@SuppressWarnings("unchecked")
	public void loaddata() {
		if (TextUtils.isEmpty(id)) {
			mFileSons = (List<FileSon>) getActivity().getIntent()
					.getSerializableExtra("data");
			update();
			MFileList mModel = new MFileList();
			mModel.file.clear();
			for (int i = 0; i < mFileSons.size(); i++) {
				ByteString imgFile = null;
				try {
					imgFile = ByteString.of(F.bitmap2Byte(mFileSons.get(i)
							.getPath(),600));
				} catch (Exception e) {
					e.printStackTrace();
				}
				MFile mMFile = new MFile(imgFile,
						mFileSons.get(i).getPath().split("/")[mFileSons.get(i)
								.getPath().split("/").length - 1]);
				mModel.file.add(mMFile);
			}
			ApisFactory.getApiMUploadFile().load(getActivity(),
					FrgZhaopian.this, "MUploadFile1", mModel);
		} else {
			isCan = true;
			ApisFactory.getApiMOrderDetail().load(getContext(),
					FrgZhaopian.this, "MOrderDetail", id);
		}
	}

	public void MUploadFile1(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		for (int i = 0; i < mMRet.msg.split(",").length; i++) {
			mFileSons.get(i).setImg(mMRet.msg.split(",")[i]);
		}
		isCan = true;
	}

	@Override
	public void onClick(android.view.View v) {
		if (R.id.clk_left == v.getId() || R.id.mTextView_left == v.getId()) {
			mLinearLayout_bottom.setVisibility(View.GONE);
			mHeadlayout.mTextView_right
					.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			mHeadlayout.setRText("保存");
			type = 1;
			mAdaZhaopianYlChanggui.setType(type);
		} else if (R.id.clk_right == v.getId()
				|| R.id.mTextView_right == v.getId()) {
			if (isCan) {
				Helper.startActivity(getContext(), FrgShunxuTiaozheng.class,
						TitleActDf.class, "data", mFileSons);
			} else {
				Helper.toast("图片上传中，请稍后...", getContext());
			}
		}
	}

	@Override
	public void setActionBar(ActionBar actionBar, Context context) {
		super.setActionBar(actionBar, context);
		mHeadlayout.setTitle("预览");
		mHeadlayout.setRText("订购");
		mHeadlayout.mTextView_right.setCompoundDrawablesWithIntrinsicBounds(
				R.drawable.bt_dinggou_n, 0, 0, 0);
		mHeadlayout.setRightOnclicker(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (type == 0) {
					dingGou();
				} else {
					mHeadlayout.mTextView_right
							.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.bt_dinggou_n, 0, 0, 0);
					mLinearLayout_bottom.setVisibility(View.VISIBLE);
					mHeadlayout.setRText("订购");
					type = 0;
					for (int i = 0; i < mFileSons.size(); i++) {
						mFileSons.get(i).setTitle(
								mFileSons.get(i).getTitle_beifen());
						mFileSons.get(i).setSubtitle(
								mFileSons.get(i).getSubtitle_beifen());
					}
					mAdaZhaopianYlChanggui.setType(type);
				}
			}
		});
	}

	private void dingGou() {
		if (isCan) {
			MPhotoList mMPhotoList = new MPhotoList();
			for (int i = 0; i < mFileSons.size(); i++) {
				MPhoto mMPhoto = new MPhoto(i, mFileSons.get(i).img, mFileSons
						.get(i).getTitle(), mFileSons.get(i).getSubtitle());
				mMPhotoList.photos.add(mMPhoto);
			}
			mMPhotoList.color = color;
			Helper.startActivity(getActivity(), FrgXiadan.class,
					TitleAct.class, "type", 1, "mMPhotoList", mMPhotoList);
		} else {
			Helper.toast("图片上传，请稍后...", getContext());
		}
	}
}