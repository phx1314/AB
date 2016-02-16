//
//  CardZhaopianLast
//
//  Created by Administrator on 2015-10-06 10:34:24
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.ZhaopianLast;

public class CardZhaopianLast extends Card<String>{
	public String item;
	
	public CardZhaopianLast() {
        this.type = 8018;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ZhaopianLast.getView(context, null);;
        }
        ZhaopianLast mZhaopianLast=(ZhaopianLast) contentView.getTag();
        mZhaopianLast.set(item);
        return contentView;
    }
    
    

}


