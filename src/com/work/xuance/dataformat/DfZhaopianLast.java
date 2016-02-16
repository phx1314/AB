//
//  DfZhaopianLast
//
//  Created by Administrator on 2015-10-06 10:34:24
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.dataformat;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;

import android.content.Context;

import com.work.xuance.ada.AdaZhaopianLast;

public class DfZhaopianLast implements DataFormat{
    int size = 1;

	@Override
	public boolean hasNext() {
		return size >= Integer.MAX_VALUE;
	}

	@Override
	public MAdapter<?> getAdapter(Context context, Son son, int page) {
		return new AdaZhaopianLast(context,null);
	}

	@Override
	public String[][] getPageNext() {
		return null;
	}

	@Override
	public void reload() {

	}
}
