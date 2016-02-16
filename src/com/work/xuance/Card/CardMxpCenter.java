//
//  CardMxpCenter
//
//  Created by Administrator on 2015-10-24 13:19:53
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.MxpCenter;

public class CardMxpCenter extends Card<String>{
	public String item;
	
	public CardMxpCenter() {
    	this.type = 8010;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = MxpCenter.getView(context, null);;
        }
        MxpCenter mMxpCenter=(MxpCenter) contentView.getTag();
//        mMxpCenter.set(item);
        return contentView;
    }
    
    

}


