//
//  CardMxpLast
//
//  Created by Administrator on 2015-10-24 13:19:53
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.MxpLast;

public class CardMxpLast extends Card<String>{
	public String item;
	
	public CardMxpLast() {
    	this.type = 8012;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = MxpLast.getView(context, null);
        }
        MxpLast mMxpLast=(MxpLast) contentView.getTag();
//        mMxpLast.set(item);
        return contentView;
    }
    
    

}


