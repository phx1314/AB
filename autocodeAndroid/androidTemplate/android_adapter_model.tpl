//
//  {{classname}}
//
//  Created by {{creater}} on {{time}}
//  Copyright (c) {{creater}} All rights reserved.


/**
   {{mark}}
*/

package {{package}};

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
{{imports}}

public class {{classname}} extends MAdapter<String>{

   public {{classname}}(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = {{itemView}}.getView(getContext(), parent);;
        }
        {{itemView}} m{{itemView}}=({{itemView}}) convertView.getTag();
        m{{itemView}}.set(item);
        return convertView;
    }
}
