//
//  AdaImgshowDialog
//
//  Created by Administrator on 2015-10-04 14:37:02
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.work.xuance.ada;

import java.util.List;

import com.mdx.framework.adapter.MAdapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.work.xuance.item.ImgshowDialog;

public class AdaImgshowDialog extends MAdapter<String>{

   public AdaImgshowDialog(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = ImgshowDialog.getView(getContext(), parent);;
        }
        ImgshowDialog mImgshowDialog=(ImgshowDialog) convertView.getTag();
//        mImgshowDialog.set(item);
        return convertView;
    }
}
