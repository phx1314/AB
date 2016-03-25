//
//  FrgXiadan
//
//  Created by Administrator on 2015-10-03 15:26:11
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.MActivityActionbar;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.dialog.DataSelectDialog;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.commons.AddressChoose;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MImageView;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MFileList;
import com.udows.common.proto.MPhotoList;
import com.udows.common.proto.MRet;
import com.work.xuance.F;
import com.work.xuance.R;

public class FrgXiadan extends BaseFrg implements
		com.mdx.framework.dialog.DataSelectDialog.OnSelected {

	public MImageView mMImageView;
	public MImageView mMImageView_taili;
	public MImageView mMImageView_mxp;
	public ImageView mImageView_jian;
	public ImageView mImageView_line_top;
	public ImageView mImageView_line_bottom;
	public ImageView mImageView_jia;
	public EditText mTextView_num;
	public TextView mTextView_allprice;
	public TextView mTextView_sub;
	public EditText mEditText_name;
	public EditText mEditText_phone;
	public EditText mEditText_address;
	public EditText mEditText_xiangxidizhi;
	public EditText mEditText_remark;
	public LinearLayout mLinearLayout_taili_bg;
	public LinearLayout mLinearLayout_bg;
	public LinearLayout mLinearLayout_mxp_bg;
	public String price = "";
	public int type = 1;
	public MPhotoList mMPhotoList;
	public MFileList mModel;

	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_xiadan);
		((MActivityActionbar) getActivity()).setSwipeBackEnable(false);
		type = getActivity().getIntent().getIntExtra("type", 1);
		mMPhotoList = (MPhotoList) getActivity().getIntent()
				.getSerializableExtra("mMPhotoList");
		initView();
		loaddata();
	}

	private void initView() {
		mMImageView = (MImageView) findViewById(R.id.mMImageView);
		mMImageView_taili = (MImageView) findViewById(R.id.mMImageView_taili);
		mMImageView_mxp = (MImageView) findViewById(R.id.mMImageView_mxp);
		mImageView_jian = (ImageView) findViewById(R.id.mImageView_jian);
		mImageView_line_top = (ImageView) findViewById(R.id.mImageView_line_top);
		mImageView_line_bottom = (ImageView) findViewById(R.id.mImageView_line_bottom);
		mImageView_jia = (ImageView) findViewById(R.id.mImageView_jia);
		mTextView_num = (EditText) findViewById(R.id.mTextView_num);
		mTextView_allprice = (TextView) findViewById(R.id.mTextView_allprice);
		mLinearLayout_taili_bg = (LinearLayout) findViewById(R.id.mLinearLayout_taili_bg);
		mLinearLayout_mxp_bg = (LinearLayout) findViewById(R.id.mLinearLayout_mxp_bg);
		mLinearLayout_bg = (LinearLayout) findViewById(R.id.mLinearLayout_bg);
		mTextView_sub = (TextView) findViewById(R.id.mTextView_sub);
		mEditText_name = (EditText) findViewById(R.id.mEditText_name);
		mEditText_phone = (EditText) findViewById(R.id.mEditText_phone);
		mEditText_address = (EditText) findViewById(R.id.mEditText_address);
		mEditText_xiangxidizhi = (EditText) findViewById(R.id.mEditText_xiangxidizhi);
		mEditText_remark = (EditText) findViewById(R.id.mEditText_remark);
		mImageView_jia.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextView_num.setText((Integer.valueOf(mTextView_num.getText()
						.toString()) + 1) + "");
				mTextView_allprice.setText(F.go2Wei(Double
						.valueOf(mTextView_num.getText().toString())
						* Double.valueOf(price)));
			}
		});
		mImageView_jian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Double.valueOf(mTextView_num.getText().toString()) > 1) {
					mTextView_num.setText((Integer.valueOf(mTextView_num
							.getText().toString()) - 1) + "");
					mTextView_allprice.setText(F.go2Wei(Double
							.valueOf(mTextView_num.getText().toString())
							* Double.valueOf(price)));

				}
			}
		});
		mTextView_sub.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mEditText_name.getText().toString().trim().equals("")) {
					Helper.toast("请输入收货姓名", getContext());
				} else if (mEditText_phone.getText().toString().trim()
						.equals("")) {
					Helper.toast("请输入手机号码", getContext());
				} else if (mEditText_phone.getText().toString().trim().length() != 11) {
					Helper.toast("请输入正确手机号码格式", getContext());
				} else if (mEditText_address.getText().toString().trim()
						.equals("")) {
					Helper.toast("请选择所在地区", getContext());
				} else if (mEditText_xiangxidizhi.getText().toString().trim()
						.equals("")) {
					Helper.toast("请输入详细地址", getContext());
				} else {
					Log.i("color", mMPhotoList.color + "");
					ApisFactory.getApiMPostPhotoOrder().load(
							getContext(),
							FrgXiadan.this,
							"MPostPhotoOrder",
							Double.valueOf(mTextView_num.getText().toString()),
							(double) type,
							mEditText_name.getText().toString().trim(),
							mEditText_phone.getText().toString().trim(),
							mEditText_address.getText().toString().trim()
									+ mEditText_xiangxidizhi.getText()
											.toString().trim(),
							mEditText_remark.getText().toString().trim(),
							mMPhotoList);
				}
			}
		});
		mEditText_address.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AddressChoose data1 = new AddressChoose();
				DataSelectDialog addressdialog = new DataSelectDialog(
						getActivity(), data1);
				addressdialog.setOnSelected(FrgXiadan.this);
				addressdialog.show();
			}
		});
	}

	public void MPostPhotoOrder(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		Helper.toast("下单成功", getContext());
		FrgXiadan.this.finish();
		Frame.HANDLES
				.sentAll(
						"FrgZh,FrgZhaopian,FrgXuanzezhaopian,FrgXuanzezhaopianSon,FrgTailiYulan,FrgMxpZs",
						010, null);
		Helper.startActivity(getContext(), FrgPay.class, TitleAct.class, "id",
				mMRet.msg);
	}

	public void loaddata() {
		switch (type) {
		case 1:
			mLinearLayout_bg.setVisibility(View.VISIBLE);
			mLinearLayout_taili_bg.setVisibility(View.GONE);
			mLinearLayout_mxp_bg.setVisibility(View.GONE);
			price = F.photo_price;
			break;
		case 2:
			mLinearLayout_bg.setVisibility(View.GONE);
			mLinearLayout_taili_bg.setVisibility(View.VISIBLE);
			mLinearLayout_mxp_bg.setVisibility(View.GONE);
			price = F.taili_price;
			break;
		case 3:
			mLinearLayout_bg.setVisibility(View.GONE);
			mLinearLayout_taili_bg.setVisibility(View.GONE);
			mLinearLayout_mxp_bg.setVisibility(View.VISIBLE);
			price = F.mxp_price;
			break;
		}
		mTextView_allprice.setText(price);
		mMImageView.setObj(mMPhotoList.photos.get(0).img);
		mMImageView_taili.setObj(mMPhotoList.photos.get(0).img);
		mMImageView_mxp.setObj(mMPhotoList.photos.get(0).img);
	}

	@Override
	public void onSelected(Dialog dia, String first, String second,
			String threed) {
		mEditText_address.setText(first + second + threed);
	}

	@Override
	public void setActionBar(ActionBar actionBar, Context context) {
		super.setActionBar(actionBar, context);
		mHeadlayout.setTitle("下单");
	}
}