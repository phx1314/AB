//
//  CardTailiYulan
//
//  Created by Administrator on 2015-10-14 16:25:27
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.TailiYulan;

public class CardTailiYulan extends Card<String>{
	public String item;
	
	public CardTailiYulan() {
        this.type = 8016;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = TailiYulan.getView(context, null);;
        }
        TailiYulan mTailiYulan=(TailiYulan) contentView.getTag();
//        mTailiYulan.set(item);
        return contentView;
    }
    
    

}


