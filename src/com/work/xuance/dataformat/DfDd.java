//
//  DfDd
//
//  Created by Administrator on 2015-10-03 15:26:11
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.dataformat;

import android.content.Context;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;
import com.udows.common.proto.MOrderList;
import com.work.xuance.ada.AdaDd;

public class DfDd implements DataFormat {
	int size = 1;
	public int state = 0;

	@Override
	public boolean hasNext() {
		return size >= 10;
	}

	@Override
	public MAdapter<?> getAdapter(Context context, Son son, int page) {
		size = ((MOrderList) son.getBuild()).order.size();
		return new AdaDd(context, ((MOrderList) son.getBuild()).order, state);
	}

	public void updateState(int state) {
		this.state = state;
	}

	@Override
	public String[][] getPageNext() {
		return null;
	}

	@Override
	public void reload() {

	}
}
