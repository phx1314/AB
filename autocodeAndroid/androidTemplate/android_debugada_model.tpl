//
//  {{classname}}
//
//  Created by {{creater}} on {{time}}
//  Copyright (c) {{creater}} All rights reserved.


/**
   {{mark}}
*/

package {{package}};

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import com.mdx.framework.adapter.Card;
import com.mdx.framework.adapter.CardAdapter;
import android.view.View;
import android.widget.TextView;


public class {{classname}} extends  CardAdapter {
    
    /**
     * [构造简要说明]
     * 
     * @param context
     * @param list
     */
    public DebugAda(Context context, List<Card<?>> list) {
        super(context, getDefaultList());
    }
    
    public DebugAda(Context context) {
        super(context, getDefaultList());
    }
    
    public static List<Card<?>> getDefaultList() {
        ArrayList<Card<?>> list = new ArrayList<Card<?>>();
               
        {{CardsString}}
        
        return list;
    }
    
   	public static class CardNameView extends Card<String>{
        public String item;
        public CardNameView(String item) {
            this.type = 7999;
            this.item=item;
        }


        @Override
        public View getView(Context context, View contentView) {
            if (contentView == null) {
                contentView = new TextView(context, null);
                contentView.setPadding(30, 15, 30, 15);
                contentView.setBackgroundColor(0x33000000);
            }
            TextView mMain=(TextView) contentView;
         
            mMain.setText(item);
            return contentView;
        }
    }
 
}