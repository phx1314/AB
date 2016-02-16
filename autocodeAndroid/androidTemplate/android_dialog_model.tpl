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
import android.os.Bundle;

{{imports}}

import {{R}};

public class {{classname}} extends BaseDia {

{{viewsdeaclear}}

    public {{classname}}(Context context) {
        super(context, com.mdx.framework.R.style.Dialog);
        loaddata();
    }

 	@Override
    protected void create(Bundle savedInstanceState) {
        setContentView({{layout}});
        initView();
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