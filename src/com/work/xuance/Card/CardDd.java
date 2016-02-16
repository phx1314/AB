//
//  CardDd
//
//  Created by Administrator on 2015-10-03 15:26:11
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.Dd;

public class CardDd extends Card<String> {
	public String item;

	public CardDd() {
        this.type = 8002;
	}

	@Override
	public View getView(Context context, View contentView) {
		if (contentView == null) {
			contentView = Dd.getView(context, null);
		}
		Dd mDd = (Dd) contentView.getTag();
		// mDd.set(item);
		return contentView;
	}

}
