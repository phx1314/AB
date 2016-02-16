//
//  CardTaili
//
//  Created by Administrator on 2015-10-03 15:26:12
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.Taili;

public class CardTaili extends Card<String>{
	public String item;
	
	public CardTaili() {
        this.type = 8013;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = Taili.getView(context, null);;
        }
        Taili mTaili=(Taili) contentView.getTag();
        mTaili.set(item);
        return contentView;
    }
    
    

}


