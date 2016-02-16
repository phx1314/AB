//
//  CardZhaopianShy
//
//  Created by Administrator on 2015-10-06 10:34:24
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.work.xuance.item.ZhaopianShy;
import com.work.xuance.model.FileFather.FileSon;

public class CardZhaopianShy extends Card<FileSon>{
	public FileSon item;
	
	public CardZhaopianShy(FileSon mFileSon) {
        this.type = 8019;
    	this.item = mFileSon;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ZhaopianShy.getView(context, null);;
        }
        ZhaopianShy mZhaopianShy=(ZhaopianShy) contentView.getTag();
//        mZhaopianShy.set(item);
        return contentView;
    }
    
    

}


