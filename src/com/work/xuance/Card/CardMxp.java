//
//  CardMxp
//
//  Created by Administrator on 2015-10-03 15:26:11
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.Mxp;

public class CardMxp extends Card<String>{
	public String item;
	
	public CardMxp() {
        this.type = 8009;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = Mxp.getView(context, null);;
        }
        Mxp mMxp=(Mxp) contentView.getTag();
        mMxp.set(item);
        return contentView;
    }
    
    

}


