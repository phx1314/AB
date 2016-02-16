//
//  AdaDd
//
//  Created by Administrator on 2015-10-03 15:26:11
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.ada;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.udows.common.proto.MOrderMini;
import com.work.xuance.act.TitleActDf;
import com.work.xuance.frg.FrgDingdanDetail;
import com.work.xuance.frg.FrgPay;
import com.work.xuance.frg.FrgXuanzezhaopian;
import com.work.xuance.frg.FrgZhaopian;
import com.work.xuance.item.Dd;

public class AdaDd extends MAdapter<MOrderMini> {
	public int state;

	public AdaDd(Context context, List<MOrderMini> list, int state) {
		super(context, list);
		this.state = state;
	}

	public void updateState(int state) {
		this.state = state;
		this.notifyDataSetChanged();
	}

	@Override
	public View getview(int position, View convertView, ViewGroup parent) {
		final MOrderMini item = get(position);
		if (convertView == null) {
			convertView = Dd.getView(getContext(), parent);
		}
		Dd mDd = (Dd) convertView.getTag();
		mDd.set(item, position, state);
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Helper.startActivity(getContext(), FrgDingdanDetail.class,
						TitleAct.class, "id", item.id);
			}
		});
		return convertView;
	}
}
