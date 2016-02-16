//
//  AdaMxpLast
//
//  Created by Administrator on 2015-10-24 13:19:53
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.ada;

import java.util.List;

import com.mdx.framework.adapter.MAdapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.work.xuance.item.MxpLast;

public class AdaMxpLast extends MAdapter<String>{

   public AdaMxpLast(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = MxpLast.getView(getContext(), parent);;
        }
        MxpLast mMxpLast=(MxpLast) convertView.getTag();
//        mMxpLast.set(item);
        return convertView;
    }
}
