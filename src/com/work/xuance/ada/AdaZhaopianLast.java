//
//  AdaZhaopianLast
//
//  Created by Administrator on 2015-10-06 10:34:24
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.ada;

import java.util.List;

import com.mdx.framework.adapter.MAdapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.work.xuance.item.ZhaopianLast;

public class AdaZhaopianLast extends MAdapter<String>{

   public AdaZhaopianLast(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = ZhaopianLast.getView(getContext(), parent);;
        }
        ZhaopianLast mZhaopianLast=(ZhaopianLast) convertView.getTag();
        mZhaopianLast.set(item);
        return convertView;
    }
}
