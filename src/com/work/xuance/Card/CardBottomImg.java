//
//  CardBottomImg
//
//  Created by Administrator on 2015-10-04 14:37:02
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.BottomImg;

public class CardBottomImg extends Card<String>{
	public String item;
	
	public CardBottomImg() {
        this.type = 8001;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = BottomImg.getView(context, null);;
        }
        BottomImg mBottomImg=(BottomImg) contentView.getTag();
//        mBottomImg.set(item);
        return contentView;
    }
    
    

}


