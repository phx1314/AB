//
//  AdaBottomImg
//
//  Created by Administrator on 2015-10-04 14:37:02
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.ada;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.work.xuance.F;
import com.work.xuance.instance.goReturn;
import com.work.xuance.item.BottomImg;
import com.work.xuance.item.ImgshowDialog;
import com.work.xuance.model.FileFather.FileSon;
import com.work.xuance.view.MAdapter;

public class AdaBottomImg extends MAdapter<FileSon> {

	public AdaBottomImg(Context context, List<FileSon> list) {
		super(context, list);
	}

	@Override
	public View getview(final int position, View convertView, ViewGroup parent) {
		FileSon item = get(position);
		if (convertView == null) {
			convertView = BottomImg.getView(getContext(), parent);
		}
		BottomImg mBottomImg = (BottomImg) convertView.getTag();
		item.setDiji(position);
		mBottomImg.set(item);
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				List<String> lists = new ArrayList<String>();
				lists.add("ASSETS:b.png");
				lists.add("ASSETS:b.png");
				lists.add("ASSETS:b.png");
				// PhotoShow mPhotoShow=new PhotoShow(getContext(), lists);
				// mPhotoShow.show();
				final View view = ImgshowDialog.getView(getContext(), null);
				F.showImgDialog(getContext(), view, new goReturn() {
					@Override
					public void go2Object(Object obj) {
						((ImgshowDialog) view.getTag()).set(obj, getList(),
								position);
					}
				});
			}
		});
		return convertView;
	}
}
