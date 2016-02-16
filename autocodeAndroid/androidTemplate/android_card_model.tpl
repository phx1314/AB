//
//  {{classname}}
//
//  Created by {{creater}} on {{time}}
//  Copyright (c) {{creater}} All rights reserved.


/**
   {{mark}}
*/

package {{package}};

import android.content.Context;
import com.mdx.framework.adapter.Card;
{{imports}}

public class {{classname}} extends Card<String>{
	public String item;
	
	public {{classname}}() {
    	this.type = {{cardtype}};
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = {{itemView}}.getView(context, null);;
        }
        {{itemView}} m{{itemView}}=({{itemView}}) contentView.getTag();
        m{{itemView}}.set(item);
        return contentView;
    }
    
{{clicks}}    

}


