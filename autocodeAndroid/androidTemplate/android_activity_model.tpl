//
//  {{classname}}
//
//  Created by {{creater}} on {{time}}
//  Copyright (c) {{creater}} All rights reserved.


/**
   {{mark}}
*/

package {{package}};

import android.os.Bundle;
{{imports}}

import {{R}};

public class {{classname}} extends BaseAct{

{{viewsdeaclear}}

 	@Override
    protected void create(Bundle savedInstanceState) {
        setContentView({{layout}});
        initView();
        loaddata();
    }
    
    private void initView() {
{{viewsfind}}
{{clikcsListener}}
    }
    
    
    
     public void loaddata(){
{{set}}
    }
    
{{clicks}}
}
