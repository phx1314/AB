//
//  AdaTailiYulan
//
//  Created by Administrator on 2015-10-14 16:25:27
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.ada;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.work.xuance.item.TailiLast;
import com.work.xuance.item.TailiShy;
import com.work.xuance.item.TailiYulan;
import com.work.xuance.item.ZhaopianLast;
import com.work.xuance.model.Model2Son;

public class AdaTailiYulan extends MAdapter<Model2Son> {
	public String color;

	public AdaTailiYulan(Context context, List<Model2Son> list,String color) {
		super(context, list);
		this.color=color;
	}

	@Override
	public View getview(int position, View convertView, ViewGroup parent) {
		Model2Son item = get(position);
		if (position == 0) {
			convertView = TailiShy.getView(getContext(), parent);
			TailiShy mZhaopianLast = (TailiShy) convertView.getTag();
			mZhaopianLast.set(item,color);
		} else if (position == getCount() - 1) {
			convertView = TailiLast.getView(getContext(), parent);
			TailiLast mZhaopianLast = (TailiLast) convertView.getTag();
			mZhaopianLast.set("");
		} else {
			convertView = TailiYulan.getView(getContext(), parent);
			TailiYulan mZhaopianLast = (TailiYulan) convertView.getTag();
			mZhaopianLast.set(item, position);
		}
		return convertView;
	}

	public void setColor(String color) {
		this.color = color;
		this.notifyDataSetChanged();
	}
}
