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

import {{R}};

{{imports}}


public class {{classname}} extends BaseFrg{

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