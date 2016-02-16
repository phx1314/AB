//
//  CardTailiShy
//
//  Created by Administrator on 2015-10-14 16:25:27
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.TailiShy;

public class CardTailiShy extends Card<String>{
	public String item;
	
	public CardTailiShy() {
        this.type = 8015;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = TailiShy.getView(context, null);;
        }
        TailiShy mTailiShy=(TailiShy) contentView.getTag();
//        mTailiShy.set(item);
        return contentView;
    }
    
    

}


