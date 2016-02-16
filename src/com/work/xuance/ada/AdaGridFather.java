//
//  AdaGridFather
//
//  Created by Administrator on 2015-10-04 14:37:02
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
import com.work.xuance.frg.FrgXuanzezhaopianSon;
import com.work.xuance.item.GridFather;
import com.work.xuance.model.FileFather;

public class AdaGridFather extends MAdapter<FileFather> {
	public int size;

	public AdaGridFather(Context context, List<FileFather> list, int size) {
		super(context, list);
		this.size = size;
	}

	@Override
	public View getview(final int position, View convertView, ViewGroup parent) {
		final FileFather item = get(position);
		if (convertView == null) {
			convertView = GridFather.getView(getContext(), parent);
		}
		GridFather mGridFather = (GridFather) convertView.getTag();
		mGridFather.set(item);
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Helper.startActivity(getContext(), FrgXuanzezhaopianSon.class,
						TitleAct.class, "title", item.getName(), "data",
						item.getmFileSons(), "size", size,"position_father",position);
			}
		});
		return convertView;
	}
}
