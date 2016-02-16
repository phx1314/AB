//
//  CardGridFather
//
//  Created by Administrator on 2015-10-04 14:37:02
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.GridFather;

public class CardGridFather extends Card<String>{
	public String item;
	
	public CardGridFather() {
        this.type = 8003;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = GridFather.getView(context, null);;
        }
        GridFather mGridFather=(GridFather) contentView.getTag();
//        mGridFather.set(item);
        return contentView;
    }
    
    

}


