//
//  AdaImg
//
//  Created by Administrator on 2015-10-06 18:20:40
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.ada;

import java.util.List;

import com.mdx.framework.adapter.MAdapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.work.xuance.item.Img;

public class AdaImg extends MAdapter<String>{

   public AdaImg(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = Img.getView(getContext(), parent);;
        }
        Img mImg=(Img) convertView.getTag();
        mImg.set(item);
        return convertView;
    }
}
