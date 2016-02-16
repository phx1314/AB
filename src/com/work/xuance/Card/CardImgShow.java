//
//  CardImgShow
//
//  Created by Administrator on 2015-10-04 14:37:02
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.ImgShow;

public class CardImgShow extends Card<String>{
	public String item;
	
	public CardImgShow() {
        this.type = 8008;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ImgShow.getView(context, null);;
        }
        ImgShow mImgShow=(ImgShow) contentView.getTag();
//        mImgShow.set(item);
        return contentView;
    }
    
    

}


