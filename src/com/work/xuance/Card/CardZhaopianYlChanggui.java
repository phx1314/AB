//
//  CardZhaopianYlChanggui
//
//  Created by Administrator on 2015-10-06 10:34:24
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.Card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.work.xuance.item.ZhaopianYlChanggui;
import com.work.xuance.model.FileFather.FileSon;

public class CardZhaopianYlChanggui extends Card<FileSon> {
	public FileSon item_left;
	public FileSon item_right;
	public int i;

	public CardZhaopianYlChanggui(int i, FileSon item_left, FileSon item_right) {
        this.type = 8020;
		this.item_left = item_left;
		this.item_right = item_right;
		this.i = i;
	}

	@Override
	public View getView(Context context, View contentView) {
		if (contentView == null) {
			contentView = ZhaopianYlChanggui.getView(context, null);
		}
		ZhaopianYlChanggui mZhaopianYlChanggui = (ZhaopianYlChanggui) contentView
				.getTag();
//		mZhaopianYlChanggui.set(i,item_left,item_right);
		return contentView;
	}

}
