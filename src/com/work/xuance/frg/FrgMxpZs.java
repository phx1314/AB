//
//  FrgMxpZs
//
//  Created by Administrator on 2015-10-24 13:19:53
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

import java.util.ArrayList;
import java.util.List;

import okio.ByteString;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

import com.framewidget.MyViewPagerAdapter;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MViewPager;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MFile;
import com.udows.common.proto.MFileList;
import com.udows.common.proto.MPhoto;
import com.udows.common.proto.MPhotoList;
import com.udows.common.proto.MRet;
import com.work.xuance.F;
import com.work.xuance.R;
import com.work.xuance.item.MxpCenter;
import com.work.xuance.item.MxpFirst;
import com.work.xuance.item.MxpLast;
import com.work.xuance.model.FileFather;
import com.work.xuance.model.FileFather.FileSon;

public class FrgMxpZs extends BaseFrg {
	public String id = "";
	public MViewPager mViewPager;
	public int type = 0;
	public List<FileSon> mFileSons = new ArrayList<FileFather.FileSon>();
	public List<View> mviews = new ArrayList<View>();
	public String color = "1";
	public MPhotoList mMPhotoList;
	public boolean isCan = false;
	public Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			mProgressDialog.dismiss();
			MFileList mModel = (MFileList) msg.obj;
			ApisFactory.getApiMUploadFile().load(getActivity(), FrgMxpZs.this,
					"MUploadFile1", mModel);
		}
	};
	public ProgressDialog mProgressDialog;

	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_mxp_zs);
		id = getActivity().getIntent().getStringExtra("id");
		initView();
		loaddata();
	}

	private void initView() {
		mViewPager = (MViewPager) findViewById(R.id.mViewPager);
		mProgressDialog = new ProgressDialog(getContext());
		mProgressDialog.setMessage("图片上传中...");
	}

	@Override
	public void disposeMsg(int type, Object obj) {
		switch (type) {
		case 0:
			dingGou();
			break;
		case 1:
			color = obj.toString();
			break;
		case 010:
			FrgMxpZs.this.finish();
			break;
		case 2:
			mHeadlayout.mTextView_right
					.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			mHeadlayout.setRText("保存");
			type = 1;
			for (int i = 0; i < mviews.size(); i++) {
				View view = mviews.get(i);
				if (view.getTag() instanceof MxpFirst) {
					((MxpFirst) view.getTag()).setIsEdit(true);
				}
				if (view.getTag() instanceof MxpCenter) {
					((MxpCenter) view.getTag()).setIsEdit(true);
				}
			}
			break;
		}
	}

	public void loaddata() {
		if (TextUtils.isEmpty(id)) {
			mProgressDialog.show();
			mFileSons = (List<FileSon>) getActivity().getIntent()
					.getSerializableExtra("data");
			update();
			new Thread(new Runnable() {
				@Override
				public void run() {
					MFileList mModel = new MFileList();
					mModel.file.clear();
					for (int i = 0; i < mFileSons.size(); i++) {
						ByteString imgFile = null;
						try {
							imgFile = ByteString.of(F.bitmap2Byte(mFileSons
									.get(i).getPath()));
						} catch (Exception e) {
							e.printStackTrace();
						}
						MFile mMFile = new MFile(imgFile, mFileSons.get(i)
								.getPath().split("/")[mFileSons.get(i)
								.getPath().split("/").length - 1]);
						mMFile.fileName = "1.png";
						mModel.file.add(mMFile);
					}
					Message mMessage = new Message();
					mMessage.obj = mModel;
					mHandler.sendMessage(mMessage);
				}
			}).start();
		} else {
			isCan = true;
			ApisFactory.getApiMOrderDetail().load(getContext(), FrgMxpZs.this,
					"MOrderDetail", id);
		}

	}

	public void MOrderDetail(Son s) {
		mMPhotoList = (MPhotoList) s.getBuild();
		for (int i = 0; i < mMPhotoList.photos.size(); i++) {
			FileSon mFileSon = new FileSon(mMPhotoList.photos.get(i).img, i,
					false, i + "", "");
			mFileSon.setTitle(mMPhotoList.photos.get(i).title);
			mFileSon.setSubtitle(mMPhotoList.photos.get(i).subtitle);
			mFileSons.add(mFileSon);
		}
		mMPhotoList.color = color;
		update();
	}

	private void update() {
		for (int i = 0; i < mFileSons.size(); i++) {
			View view = null;
			if (i == 0) {
				view = MxpFirst.getView(getContext(), null);
				((MxpFirst) view.getTag()).set(mFileSons.get(i));
			} else {
				view = MxpCenter.getView(getContext(), null);
				((MxpCenter) view.getTag()).set(mFileSons.get(i));
			}
			mviews.add(view);
		}
		mviews.add(MxpLast.getView(getContext(), null));
		((MxpLast) mviews.get(mviews.size() - 1).getTag()).set(mMPhotoList);
		mViewPager.setAdapter(new MyViewPagerAdapter(mviews));
	}

	public void MUploadFile1(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		for (int i = 0; i < mMRet.msg.split(",").length; i++) {
			mFileSons.get(i).setImg(mMRet.msg.split(",")[i]);
		}
		isCan = true;
		Helper.toast("图片上传中成功", getContext());
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
					mHeadlayout.setRText("订购");
					type = 0;
					for (int i = 0; i < mFileSons.size(); i++) {
						mFileSons.get(i).setTitle(
								mFileSons.get(i).getTitle_beifen());
					}
					for (int i = 0; i < mviews.size(); i++) {
						View view = mviews.get(i);
						if (view.getTag() instanceof MxpFirst) {
							((MxpFirst) view.getTag()).setIsEdit(false);
						}
						if (view.getTag() instanceof MxpCenter) {
							((MxpCenter) view.getTag()).setIsEdit(false);
						}
					}
				}
			}
		});
	}

	public void dingGou() {
		if (isCan) {
			MPhotoList mMPhotoList = new MPhotoList();
			for (int i = 0; i < mFileSons.size(); i++) {
				MPhoto mMPhoto = new MPhoto(i, mFileSons.get(i).img, mFileSons
						.get(i).getTitle(), mFileSons.get(i).getSubtitle());
				mMPhotoList.photos.add(mMPhoto);
			}
			mMPhotoList.color = color;
			Helper.startActivity(getActivity(), FrgXiadan.class,
					TitleAct.class, "type", 3, "mMPhotoList", mMPhotoList);
		} else {
			Helper.toast("图片上传，请稍后...", getContext());
		}
	}
}