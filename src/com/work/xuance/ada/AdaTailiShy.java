//
//  AdaTailiShy
//
//  Created by Administrator on 2015-10-14 16:25:27
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.ada;

import java.util.List;

import com.mdx.framework.adapter.MAdapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.work.xuance.item.TailiShy;

public class AdaTailiShy extends MAdapter<String>{

   public AdaTailiShy(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = TailiShy.getView(getContext(), parent);;
        }
        TailiShy mTailiShy=(TailiShy) convertView.getTag();
//        mTailiShy.set(item);
        return convertView;
    }
}
