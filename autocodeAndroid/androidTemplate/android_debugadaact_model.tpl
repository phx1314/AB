//
//  {{classname}}
//
//  Created by {{creater}} on {{time}}
//  Copyright (c) {{creater}} All rights reserved.


/**
   {{mark}}
*/

package {{package}};

import android.app.ListActivity;
import android.os.Bundle;


public class {{classname}} extends  ListActivity {
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DebugAda debugdad=new DebugAda(this);
        setListAdapter(debugdad);
    }
    
}

