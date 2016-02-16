//
//  CardZhaopian
//
//  Created by Administrator on 2015-10-03 15:26:12
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.Zhaopian;

public class CardZhaopian extends Card<String>{
	public String item;
	
	public CardZhaopian() {
        this.type = 8017;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = Zhaopian.getView(context, null);;
        }
        Zhaopian mZhaopian=(Zhaopian) contentView.getTag();
        mZhaopian.set(item);
        return contentView;
    }
    
    

}


