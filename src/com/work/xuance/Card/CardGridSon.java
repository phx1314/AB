//
//  CardGridSon
//
//  Created by Administrator on 2015-10-04 14:37:02
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.GridSon;

public class CardGridSon extends Card<String>{
	public String item;
	
	public CardGridSon() {
        this.type = 8005;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = GridSon.getView(context, null);;
        }
        GridSon mGridSon=(GridSon) contentView.getTag();
//        mGridSon.set(item);
        return contentView;
    }
    
    

}


