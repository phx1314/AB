//
//  AdaGridShunxu
//
//  Created by Administrator on 2015-10-06 18:20:40
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.ada;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.work.xuance.item.GridShunxu;
import com.work.xuance.model.FileFather.FileSon;

public class AdaGridShunxu extends MAdapter<FileSon> {

	public AdaGridShunxu(Context context, List<FileSon> list) {
		super(context, list);
	}

	@Override
	public View getview(int position, View convertView, ViewGroup parent) {
		FileSon item = get(position);
		if (convertView == null) {
			convertView = GridShunxu.getView(getContext(), parent);
		}
		GridShunxu mGridShunxu = (GridShunxu) convertView.getTag();
		mGridShunxu.set(item, position, getCount());
		return convertView;
	}
}
