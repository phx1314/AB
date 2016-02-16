//
//  AdaZhaopian
//
//  Created by Administrator on 2015-10-03 15:26:12
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.ada;

import java.util.List;

import com.mdx.framework.adapter.MAdapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.work.xuance.item.Zhaopian;

public class AdaZhaopian extends MAdapter<String>{

   public AdaZhaopian(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = Zhaopian.getView(getContext(), parent);;
        }
        Zhaopian mZhaopian=(Zhaopian) convertView.getTag();
        mZhaopian.set(item);
        return convertView;
    }
}
