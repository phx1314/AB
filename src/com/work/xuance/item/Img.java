//
//  Img
//
//  Created by Administrator on 2015-10-06 18:20:40
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.item;

import com.work.xuance.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.view.View;
import com.mdx.framework.widget.MImageView;



public class Img extends BaseItem{
    public MImageView mImageView_1;
    public MImageView mImageView;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_img,null);
	     convertView.setTag( new Img(convertView));
	     return convertView;
	}

	public Img(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
        mImageView_1=(MImageView)contentview.findViewById(R.id.mImageView_1);
        mImageView=(MImageView)contentview.findViewById(R.id.mImageView);


    }
    
    public void set(String item){

        mImageView_1.setObj("ASSETS:b.png");
        mImageView.setObj("ASSETS:b.png");
    }
    
    

}