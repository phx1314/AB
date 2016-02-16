//
//  Dd
//
//  Created by Administrator on 2015-10-03 15:26:11
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.MActivityActionbar;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MOrderMini;
import com.work.xuance.F;
import com.work.xuance.R;
import com.work.xuance.act.TitleActDf;
import com.work.xuance.frg.FrgPay;
import com.work.xuance.frg.FrgTailiYulan;
import com.work.xuance.frg.FrgZhaopian;

public class Dd extends BaseItem {
	public LinearLayout mLinearLayout_zhanghu;
	public TextView mTextView_liubai;
	public LinearLayout mLinearLayout_lan;
	public LinearLayout mLinearLayout_bg;
	public LinearLayout mLinearLayout_taili_bg;
	public LinearLayout mLinearLayout_mxp_bg;
	public TextView mTextView_ddbianhao;
	public TextView mTextView_dd_state;
	public MImageView mMImageView;
	public MImageView mMImageView_taili;
	public MImageView mMImageView_mxp;
	public ImageView mImageView_fu;
	public TextView mTextView_username;
	public TextView mTextView_price;
	public TextView mTextView_time;
	public TextView mTextView_yh;
	public TextView mTextView_shiji;
	public TextView mTextView_fukuan;
	public TextView mTextView_yulan;
	public TextView mTextView_name;
	public ImageView mImageView_del;
	public MOrderMini item;

	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_dd, null);
		convertView.setTag(new Dd(convertView));
		return convertView;
	}

	public Dd(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		((MActivityActionbar) context).LoadingShow = true;
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		mLinearLayout_zhanghu = (LinearLayout) contentview
				.findViewById(R.id.mLinearLayout_zhanghu);
		mImageView_fu = (ImageView) contentview
				.findViewById(R.id.mImageView_fu);
		mLinearLayout_bg = (LinearLayout) contentview
				.findViewById(R.id.mLinearLayout_bg);
		mLinearLayout_mxp_bg = (LinearLayout) contentview
				.findViewById(R.id.mLinearLayout_mxp_bg);
		mLinearLayout_taili_bg = (LinearLayout) contentview
				.findViewById(R.id.mLinearLayout_taili_bg);
		mImageView_del = (ImageView) contentview
				.findViewById(R.id.mImageView_del);
		mTextView_liubai = (TextView) contentview
				.findViewById(R.id.mTextView_liubai);
		mTextView_yulan = (TextView) contentview
				.findViewById(R.id.mTextView_yulan);
		mTextView_username = (TextView) contentview
				.findViewById(R.id.mTextView_username);
		mLinearLayout_lan = (LinearLayout) contentview
				.findViewById(R.id.mLinearLayout_lan);
		mTextView_ddbianhao = (TextView) contentview
				.findViewById(R.id.mTextView_ddbianhao);
		mTextView_dd_state = (TextView) contentview
				.findViewById(R.id.mTextView_dd_state);
		mMImageView = (MImageView) contentview.findViewById(R.id.mMImageView);
		mMImageView_taili = (MImageView) contentview
				.findViewById(R.id.mMImageView_taili);
		mMImageView_mxp = (MImageView) contentview
				.findViewById(R.id.mMImageView_mxp);
		mTextView_name = (TextView) contentview
				.findViewById(R.id.mTextView_name);
		mTextView_price = (TextView) contentview
				.findViewById(R.id.mTextView_price);
		mTextView_time = (TextView) contentview
				.findViewById(R.id.mTextView_time);
		mTextView_yh = (TextView) contentview.findViewById(R.id.mTextView_yh);
		mTextView_shiji = (TextView) contentview
				.findViewById(R.id.mTextView_shiji);
		mTextView_fukuan = (TextView) contentview
				.findViewById(R.id.mTextView_fukuan);
		mTextView_fukuan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Helper.startActivity(context, FrgPay.class, TitleAct.class,
						"id", item.id);
			}
		});
		mImageView_del.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ApisFactory.getApiMUpdateOrder().load(context, Dd.this,
						"MUpdateOrder", (double) 3, item.id, "");
			}
		});
		mTextView_yulan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (item.type == 1) {
					Helper.startActivity(context, FrgZhaopian.class,
							TitleActDf.class, "id", item.id);
				} else if (item.type == 2) {
					Helper.startActivity(context, FrgTailiYulan.class,
							TitleAct.class, "id", item.id);
				}
			}
		});
	}

	public void MUpdateOrder(Son s) {
		Helper.toast("删除成功", context);
		Frame.HANDLES.sentAll("FrgZh", 010, null);
	}

	public void set(MOrderMini item, int position, int state) {
		this.item = item;
		if (position == 0) {
			mLinearLayout_zhanghu.setVisibility(View.VISIBLE);
			mTextView_liubai.setVisibility(View.GONE);
		} else {
			mLinearLayout_zhanghu.setVisibility(View.GONE);
			mTextView_liubai.setVisibility(View.VISIBLE);
		}
		if (state == 0) {
			mImageView_del.setVisibility(View.GONE);
		} else {
			mImageView_del.setVisibility(View.VISIBLE);
		}
		mTextView_username
				.setText("用户：" + F.UserId.substring(0, 6) + "，欢迎您...");
		mTextView_ddbianhao.setText("订单编号：" + item.code);
		switch (item.state) {
		case -1:
			mTextView_dd_state.setText("取消");
			break;
		case 1:
			mTextView_dd_state.setText("未付款");
			mImageView_fu.setVisibility(View.VISIBLE);
			mTextView_fukuan.setVisibility(View.VISIBLE);
			break;
		case 2:
			mTextView_dd_state.setText("已付款");
			mImageView_fu.setVisibility(View.GONE);
			mTextView_fukuan.setVisibility(View.GONE);
			break;
		case 3:
			mTextView_dd_state.setText("已发货");
			mImageView_fu.setVisibility(View.GONE);
			mTextView_fukuan.setVisibility(View.GONE);
			break;
		case 4:
			mTextView_dd_state.setText("交易完成");
			mImageView_fu.setVisibility(View.GONE);
			mTextView_fukuan.setVisibility(View.GONE);
			break;
		case 5:
			mTextView_dd_state.setText("已评价");
			mImageView_fu.setVisibility(View.GONE);
			mTextView_fukuan.setVisibility(View.GONE);
			break;
		case 6:
			mTextView_dd_state.setText("退款中");
			mImageView_fu.setVisibility(View.GONE);
			mTextView_fukuan.setVisibility(View.GONE);
			break;
		case 7:
			mTextView_dd_state.setText("退款成功");
			mImageView_fu.setVisibility(View.GONE);
			mTextView_fukuan.setVisibility(View.GONE);
			break;
		case 8:
			mTextView_dd_state.setText("退款失败");
			mImageView_fu.setVisibility(View.GONE);
			mTextView_fukuan.setVisibility(View.GONE);
			break;
		}
		switch (item.type) {
		case 1:
			mTextView_name.setText("相册*" + item.total);
			mLinearLayout_bg.setVisibility(View.VISIBLE);
			mLinearLayout_taili_bg.setVisibility(View.GONE);
			mLinearLayout_mxp_bg.setVisibility(View.GONE);
			break;
		case 2:
			mTextView_name.setText("台历*" + item.total);
			mLinearLayout_bg.setVisibility(View.GONE);
			mLinearLayout_taili_bg.setVisibility(View.VISIBLE);
			mLinearLayout_mxp_bg.setVisibility(View.GONE);
			break;
		case 3:
			mTextView_name.setText("明信片*" + item.total);
			mLinearLayout_bg.setVisibility(View.GONE);
			mLinearLayout_taili_bg.setVisibility(View.GONE);
			mLinearLayout_mxp_bg.setVisibility(View.VISIBLE);
			break;
		}
		mTextView_price.setText("￥" + item.price);
		mTextView_time.setText("下单时间：" + item.time);
		if (!TextUtils.isEmpty(item.couponInfo)) {
			mTextView_yh.setText("优惠  ￥ " + item.couponInfo);
		} else {
			mTextView_yh.setVisibility(View.INVISIBLE);
		}
		mTextView_shiji.setText("实付款：￥" + item.totalPrice);
		mMImageView.setObj(item.img);
		mMImageView_taili.setObj(item.img);
		mMImageView_mxp.setObj(item.img);
	}

}