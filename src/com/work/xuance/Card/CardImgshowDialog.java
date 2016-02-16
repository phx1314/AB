//
//  CardImgshowDialog
//
//  Created by Administrator on 2015-10-04 14:37:02
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.Card;

import android.content.Context;

import com.mdx.framework.adapter.Card;

import android.view.View;

import com.work.xuance.item.ImgshowDialog;

public class CardImgshowDialog extends Card<String>{
	public String item;
	
	public CardImgshowDialog() {
        this.type = 8007;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = ImgshowDialog.getView(context, null);;
        }
        ImgshowDialog mImgshowDialog=(ImgshowDialog) contentView.getTag();
//        mImgshowDialog.set(item);
        return contentView;
    }
    
    

}


