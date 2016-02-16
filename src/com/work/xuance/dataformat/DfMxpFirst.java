//
//  DfMxpFirst
//
//  Created by Administrator on 2015-10-24 13:19:53
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.dataformat;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;

import android.content.Context;

import com.work.xuance.ada.AdaMxpFirst;

public class DfMxpFirst implements DataFormat{
    int size = 1;

	@Override
	public boolean hasNext() {
		return size >= Integer.MAX_VALUE;
	}

	@Override
	public MAdapter<?> getAdapter(Context context, Son son, int page) {
		return new AdaMxpFirst(context,null);
	}

	@Override
	public String[][] getPageNext() {
		return null;
	}

	@Override
	public void reload() {

	}
}
