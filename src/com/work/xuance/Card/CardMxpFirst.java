//
//  CardMxpFirst
//
//  Created by Administrator on 2015-10-24 13:19:53
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.MxpFirst;

public class CardMxpFirst extends Card<String>{
	public String item;
	
	public CardMxpFirst() {
    	this.type = 8011;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = MxpFirst.getView(context, null);;
        }
        MxpFirst mMxpFirst=(MxpFirst) contentView.getTag();
//        mMxpFirst.set(item);
        return contentView;
    }
    
    

}


