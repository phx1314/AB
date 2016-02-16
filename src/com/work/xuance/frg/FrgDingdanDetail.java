//
//  FrgDingdanDetail
//
//  Created by Administrator on 2015-10-24 13:19:53
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MImageView;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MOrderMini;
import com.work.xuance.R;
import com.work.xuance.act.TitleActDf;

public class FrgDingdanDetail extends BaseFrg {

	public TextView mTextView_name;
	public TextView mTextView_dingdan_hao;
	public LinearLayout mLinearLayout_bg;
	public MImageView mMImageView;
	public LinearLayout mLinearLayout_taili_bg;
	public LinearLayout mLinearLayout_mxp_bg;
	public MImageView mMImageView_taili;
	public MImageView mMImageView_mxp;
	public TextView mTextView_ren;
	public TextView mTextView_price;
	public TextView mTextView_phone;
	public TextView mTextView_time;
	public TextView mTextView_dizhi;
	public TextView mTextView_danjia;
	public TextView mTextView_youhui;
	public TextView mTextView_num;
	public TextView mTextView_jine;
	public TextView mTextView_state;
	public TextView mTextView_dingdanjine;
	public TextView mTextView_shifu;
	public TextView mTextView_remark;
	public TextView mTextView_fukuan;
	public String id = "";
	public MOrderMini mMOrderMini;

	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_dingdan_detail);
		id = getActivity().getIntent().getStringExtra("id");
		initView();
		loaddata();
	}

	private void initView() {
		mTextView_name = (TextView) findViewById(R.id.mTextView_name);
		mTextView_dingdan_hao = (TextView) findViewById(R.id.mTextView_dingdan_hao);
		mLinearLayout_bg = (LinearLayout) findViewById(R.id.mLinearLayout_bg);
		mLinearLayout_mxp_bg = (LinearLayout) findViewById(R.id.mLinearLayout_mxp_bg);
		mMImageView = (MImageView) findViewById(R.id.mMImageView);
		mMImageView_mxp = (MImageView) findViewById(R.id.mMImageView_mxp);
		mLinearLayout_taili_bg = (LinearLayout) findViewById(R.id.mLinearLayout_taili_bg);
		mMImageView_taili = (MImageView) findViewById(R.id.mMImageView_taili);
		mTextView_ren = (TextView) findViewById(R.id.mTextView_ren);
		mTextView_price = (TextView) findViewById(R.id.mTextView_price);
		mTextView_phone = (TextView) findViewById(R.id.mTextView_phone);
		mTextView_time = (TextView) findViewById(R.id.mTextView_time);
		mTextView_dizhi = (TextView) findViewById(R.id.mTextView_dizhi);
		mTextView_danjia = (TextView) findViewById(R.id.mTextView_danjia);
		mTextView_youhui = (TextView) findViewById(R.id.mTextView_youhui);
		mTextView_num = (TextView) findViewById(R.id.mTextView_num);
		mTextView_jine = (TextView) findViewById(R.id.mTextView_jine);
		mTextView_state = (TextView) findViewById(R.id.mTextView_state);
		mTextView_dingdanjine = (TextView) findViewById(R.id.mTextView_dingdanjine);
		mTextView_shifu = (TextView) findViewById(R.id.mTextView_shifu);
		mTextView_remark = (TextView) findViewById(R.id.mTextView_remark);
		mTextView_fukuan = (TextView) findViewById(R.id.mTextView_fukuan);
		mTextView_fukuan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Helper.startActivity(getContext(), FrgPay.class,
						TitleAct.class, "id", id);
			}
		});
	}

	public void loaddata() {
		ApisFactory.getApiMOrderInfo().load(getContext(),
				FrgDingdanDetail.this, "MOrderInfo", id);
	}

	public void MOrderInfo(Son s) {
		mMOrderMini = (MOrderMini) s.getBuild();
		mTextView_ren.setText("收件人：" + mMOrderMini.address.name);
		mTextView_price.setText("￥" + mMOrderMini.price);
		mTextView_phone.setText("联系电话：" + mMOrderMini.address.phone);
		mTextView_dizhi.setText("收货地址：" + mMOrderMini.address.address);
		mTextView_danjia.setText("单价：" + mMOrderMini.price + "元/本");
		mTextView_num.setText("数量：" + mMOrderMini.total);
		if (TextUtils.isEmpty(mMOrderMini.couponInfo)) {
			mTextView_youhui.setVisibility(View.GONE);
			mTextView_jine.setVisibility(View.GONE);
		} else {
			mTextView_youhui.setVisibility(View.VISIBLE);
			mTextView_jine.setVisibility(View.VISIBLE);
			mTextView_youhui.setText("优惠券：" + mMOrderMini.payCode);
			mTextView_jine.setText("优惠金额：" + mMOrderMini.couponInfo + "元");
		}
		if (mMOrderMini.state == 1) {
			mTextView_state.setText("订单状态：待付款");
		} else {
			mTextView_state.setText("订单状态：已付款");
		}
		mTextView_dingdanjine
				.setText("订单金额（含运费）：￥"
						+ (Double.valueOf(mMOrderMini.price)
								* mMOrderMini.total + mMOrderMini.expressPrice));
		mTextView_shifu.setText("实付：￥" + mMOrderMini.totalPrice);
		mTextView_remark.setText("备注：" + mMOrderMini.info);
		switch (mMOrderMini.type) {
		case 1:
			mTextView_name.setText(mMOrderMini.total + "*相册");
			mLinearLayout_bg.setVisibility(View.VISIBLE);
			mLinearLayout_taili_bg.setVisibility(View.GONE);
			mLinearLayout_mxp_bg.setVisibility(View.GONE);
			break;
		case 2:
			mLinearLayout_bg.setVisibility(View.GONE);
			mLinearLayout_taili_bg.setVisibility(View.VISIBLE);
			mLinearLayout_mxp_bg.setVisibility(View.GONE);
			mTextView_name.setText(mMOrderMini.total + "*台历");
			break;
		case 3:
			mLinearLayout_bg.setVisibility(View.GONE);
			mLinearLayout_taili_bg.setVisibility(View.GONE);
			mLinearLayout_mxp_bg.setVisibility(View.VISIBLE);
			mTextView_name.setText(mMOrderMini.total + "*明信片");
			break;
		}
		mMImageView.setObj(mMOrderMini.img);
		mMImageView_taili.setObj(mMOrderMini.img);
		mMImageView_mxp.setObj(mMOrderMini.img);
	}

	@SuppressLint("NewApi")
	@Override
	public void setActionBar(ActionBar actionBar, Context context) {
		super.setActionBar(actionBar, context);
		mHeadlayout.setTitle("订单详情");
		mHeadlayout.setRText("预览");
		mHeadlayout.mTextView_right
				.setCompoundDrawablesRelativeWithIntrinsicBounds(
						R.drawable.ic_yulan, 0, 0, 0);
		mHeadlayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (mMOrderMini.type) {
				case 1:
					Helper.startActivity(getContext(), FrgZhaopian.class,
							TitleActDf.class, "id", id);
					break;
				case 2:
					Helper.startActivity(getContext(), FrgTailiYulan.class,
							TitleAct.class, "id", id);
					break;
				case 3:
					Helper.startActivity(getContext(), FrgMxpZs.class,
							TitleActDf.class, "id", id);
					break;
				}
			}
		});
	}

}