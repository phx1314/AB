//
//  MimgAda
//
//  Created by Administrator on 2015-10-02 21:04:23
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.debug;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.widget.MImageView;

public class MimgAda extends MAdapter<String>{

 	public MimgAda(Context context) {
        super(context, getsList());
    }
    
    public static List<String> getsList(){
        ArrayList<String> list=new ArrayList<String>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        return list;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=new MImageView(getContext());
        }
        ((MImageView)convertView).setObj("ASSETS:b.png");
        ((MImageView)convertView).setScaleType(ScaleType.FIT_XY);
        return convertView;
    }
}