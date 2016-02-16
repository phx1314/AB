//
//  AdaImgShow
//
//  Created by Administrator on 2015-10-04 14:37:02
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.ada;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.work.xuance.item.ImgShow;
import com.work.xuance.model.FileFather.FileSon;

public class AdaImgShow extends MAdapter<FileSon> {

	public AdaImgShow(Context context, List<FileSon> list) {
		super(context, list);
	}

	@Override
	public View getview(int position, View convertView, ViewGroup parent) {
		FileSon item = get(position);
		if (convertView == null) {
			convertView = ImgShow.getView(getContext(), parent);
		}
		ImgShow mImgShow = (ImgShow) convertView.getTag();
		mImgShow.set(item);
		return convertView;
	}
}
