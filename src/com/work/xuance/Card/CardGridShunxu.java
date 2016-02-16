//
//  CardGridShunxu
//
//  Created by Administrator on 2015-10-06 18:20:40
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.GridShunxu;

public class CardGridShunxu extends Card<String> {
	public String item;

	public CardGridShunxu() {
        this.type = 8004;
	}

	@Override
	public View getView(Context context, View contentView) {
		if (contentView == null) {
			contentView = GridShunxu.getView(context, null);
			;
		}
		GridShunxu mGridShunxu = (GridShunxu) contentView.getTag();
		// mGridShunxu.set(item);
		return contentView;
	}

}
