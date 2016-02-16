//
//  FrgTailiYulan
//
//  Created by Administrator on 2015-10-14 15:05:34
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

import java.util.ArrayList;
import java.util.List;

import okio.ByteString;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

import com.flipview.FlipView;
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
import com.work.xuance.ada.AdaTailiYulan;
import com.work.xuance.ada.AdaZhaopianYlChanggui;
import com.work.xuance.model.FileFather;
import com.work.xuance.model.Model2Son;
import com.work.xuance.model.FileFather.FileSon;

public class FrgTailiYulan extends BaseFrg {

	public FlipView flipView;
	public List<FileSon> mFileSons = new ArrayList<FileFather.FileSon>();
	public AdaTailiYulan mAdaTailiYulan;
	public String id = "";
	public List<Model2Son> mModel2Sons = new ArrayList<Model2Son>();
	public String color = "1";
	public boolean isCan = false;

	@SuppressWarnings("unchecked")
	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_taili_yulan);
		id = getActivity().getIntent().getStringExtra("id");
		Helper.toast("图片上传中...", getContext());
		initView();
		loaddata();
	}

	@Override
	public void disposeMsg(int type, Object obj) {
		switch (type) {
		case 0:
			dingGou();
			break;
		case 010:
			FrgTailiYulan.this.finish();
			break;
		case 2:
			color = obj.toString();
			mAdaTailiYulan.setColor(color);
			break;
		}
	}

	private void initView() {
		flipView = (FlipView) findViewById(R.id.flipView);
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
							.getPath()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				MFile mMFile = new MFile(imgFile, mFileSons.get(i)
						.getPath().split("/")[mFileSons.get(i)
						.getPath().split("/").length - 1]);
				mModel.file.add(mMFile);
			}
			ApisFactory.getApiMUploadFile().load(getActivity(),
					FrgTailiYulan.this, "MUploadFile1", mModel);
		} else {
			isCan = true;
			ApisFactory.getApiMOrderDetail().load(getContext(),
					FrgTailiYulan.this, "MOrderDetail", id);
		}
	}

	public void MUploadFile1(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		for (int i = 0; i < mMRet.msg.split(",").length; i++) {
			mFileSons.get(i).setImg(mMRet.msg.split(",")[i]);
		}
		isCan = true;
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

	public void update() {
		mModel2Sons.clear();
		for (int i = 0; i < mFileSons.size(); i++) {
			mModel2Sons.add(new Model2Son(null, mFileSons.get(i)));
		}
		mModel2Sons.add(new Model2Son(null, null));
		mAdaTailiYulan = new AdaTailiYulan(getContext(), mModel2Sons, color);
		flipView.setAdapter(mAdaTailiYulan);
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
			public void onClick(View v) {
				dingGou();
			}
		});
	}

	public void dingGou() {
		if (isCan) {
			MPhotoList mMPhotoList = new MPhotoList();
			for (int i = 0; i < mFileSons.size(); i++) {
				MPhoto mMPhoto = new MPhoto(i, mFileSons.get(i).img,
						mFileSons.get(i).getTitle(), mFileSons.get(i)
								.getSubtitle());
				mMPhotoList.photos.add(mMPhoto);
			}
			color = mMPhotoList.color;
			Helper.startActivity(getActivity(), FrgXiadan.class,
					TitleAct.class, "type", 2, "mMPhotoList", mMPhotoList);
		} else {
			Helper.toast("图片上传，请稍后...", getContext());
		}

	}
}