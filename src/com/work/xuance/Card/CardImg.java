//
//  CardImg
//
//  Created by Administrator on 2015-10-06 18:20:40
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.Img;

public class CardImg extends Card<String>{
	public String item;
	
	public CardImg() {
        this.type = 8006;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = Img.getView(context, null);;
        }
        Img mImg=(Img) contentView.getTag();
        mImg.set(item);
        return contentView;
    }
    
    

}


