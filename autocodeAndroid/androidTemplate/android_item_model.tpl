//
//  {{classname}}
//
//  Created by {{creater}} on {{time}}
//  Copyright (c) {{creater}} All rights reserved.


/**
   {{mark}}
*/

package {{package}};

import {{R}};

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

{{imports}}


public class {{classname}} extends BaseItem{
{{viewsdeaclear}}

	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate({{layout}},null);
	     convertView.setTag( new {{classname}}(convertView));
	     return convertView;
	}

	public {{classname}}(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
{{viewsfind}}
{{clikcsListener}}
    }
    
    public void set(String item){
{{set}}
    }
    
{{clicks}}    

}