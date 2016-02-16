//
//  {{classname}}
//
//  Created by {{creater}} on {{time}}
//  Copyright (c) {{creater}} All rights reserved.


/**
   {{mark}}
*/

package {{package}};

import android.view.View;
import com.mdx.framework.prompt.MDialog;
import android.content.Context;

public abstract class BaseDia  extends MDialog implements View.OnClickListener{


	public BaseDia(Context context) {
		super(context);
	}

	public BaseDia(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	public BaseDia(Context context, int theme) {
		super(context, theme);
	}

	@Override
	public void onClick(View v) {
		
	} 

}
