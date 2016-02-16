//
//  CardTailiLast
//
//  Created by Administrator on 2015-10-14 16:25:27
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.TailiLast;

public class CardTailiLast extends Card<String>{
	public String item;
	
	public CardTailiLast() {
        this.type = 8014;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = TailiLast.getView(context, null);;
        }
        TailiLast mTailiLast=(TailiLast) contentView.getTag();
        mTailiLast.set(item);
        return contentView;
    }
    
    

}


