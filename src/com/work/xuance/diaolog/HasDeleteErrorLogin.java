package com.work.xuance.diaolog;

import android.content.Context;
import android.widget.Toast;

import com.mdx.framework.Frame;
import com.mdx.framework.prompt.ErrorPrompt;
import com.mdx.framework.server.api.ErrorMsg;

public class HasDeleteErrorLogin implements ErrorPrompt {
	private ErrorMsg errorMsg;

	private Context context;

	public HasDeleteErrorLogin(Context context) {
		this.context = context;
	}

	public void setMsg(ErrorMsg em) {
		this.errorMsg = em;
	}

	public void show() {
		Toast.makeText(context, errorMsg.value, Toast.LENGTH_LONG).show();
		Frame.HANDLES.sentAll("FrgHome", 0, null);
	}

	public void dismiss() {
		// dialog.dismiss();
	}
}
