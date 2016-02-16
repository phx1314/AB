//
//  AdaMxp
//
//  Created by Administrator on 2015-10-03 15:26:11
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.ada;

import java.util.List;

import com.mdx.framework.adapter.MAdapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.work.xuance.item.Mxp;

public class AdaMxp extends MAdapter<String>{

   public AdaMxp(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = Mxp.getView(getContext(), parent);;
        }
        Mxp mMxp=(Mxp) convertView.getTag();
        mMxp.set(item);
        return convertView;
    }
}
