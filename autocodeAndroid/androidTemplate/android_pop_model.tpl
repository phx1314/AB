//
//  {{classname}}
//
//  Created by {{creater}} on {{time}}
//  Copyright (c) {{creater}} All rights reserved.


/**
   {{mark}}
*/

package {{package}};

import com.mdx.framework.adapter.MAdapter;
{{imports}}

public class {{classname}} extends MAdapter<String>{

   public {{classname}}(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = new {{itemView}}(getContext());
        }
        {{itemView}} view=({{itemView}}) convertView;
        tv.set(item);
        return convertView;
    }
}