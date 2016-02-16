//
//  AdaZhaopianYlChanggui
//
//  Created by Administrator on 2015-10-06 10:34:24
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.ada;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.work.xuance.item.ZhaopianLast;
import com.work.xuance.item.ZhaopianShy;
import com.work.xuance.item.ZhaopianYlChanggui;
import com.work.xuance.model.Model2Son;

public class AdaZhaopianYlChanggui extends MAdapter<Model2Son> {
	public int type = 0;
	public String color;
	public String isxiugai;
	public int yema;

	public AdaZhaopianYlChanggui(Context context, List<Model2Son> list,
			String color, int yema, String isxiugai) {
		super(context, list);
		this.color = color;
		this.yema = yema;
		this.isxiugai = isxiugai;
	}

	public void setType(int type) {
		this.type = type;
		AdaZhaopianYlChanggui.this.notifyDataSetChanged();
	}

	public void setColor(String color) {
		this.color = color;
		AdaZhaopianYlChanggui.this.notifyDataSetChanged();
	}

	@Override
	public View getview(int position, View convertView, ViewGroup parent) {
		Model2Son item = get(position);
		if (position == 0) {
			convertView = ZhaopianShy.getView(getContext(), parent);
			ZhaopianShy mZhaopianShy = (ZhaopianShy) convertView.getTag();
			mZhaopianShy.set(item, type, color);
		} else if (position == getCount() - 1) {
			convertView = ZhaopianLast.getView(getContext(), parent);
			ZhaopianLast mZhaopianLast = (ZhaopianLast) convertView.getTag();
			mZhaopianLast.set("");
		} else {
			convertView = ZhaopianYlChanggui.getView(getContext(), parent);
			ZhaopianYlChanggui mZhaopianYlChanggui = (ZhaopianYlChanggui) convertView
					.getTag();
			if (position == 1) {
				mZhaopianYlChanggui.set(0, null, null, type, position, yema,
						isxiugai);
			} else if (position == 2) {
				mZhaopianYlChanggui.set(1, null, item.getmFileSon2(), type,
						position, yema, isxiugai);
			} else if (position == getCount() - 3) {
				mZhaopianYlChanggui.set(2, item.getmFileSon1(), null, type,
						position, yema, isxiugai);
			} else if (position == getCount() - 2) {
				mZhaopianYlChanggui.set(3, null, null, type, position, yema,
						isxiugai);
			} else {
				mZhaopianYlChanggui
						.set(4, item.getmFileSon1(), item.getmFileSon2(), type,
								position - 2, yema, isxiugai);
			}

		}
		return convertView;
	}
}
