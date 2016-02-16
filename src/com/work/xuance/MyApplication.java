package com.work.xuance;

import android.app.Application;

import com.mdx.framework.Frame;

public class MyApplication extends  Application {

	@Override
	public void onCreate() {
		// Thread.setDefaultUncaughtExceptionHandler(new
		// UncaughtExceptionHandler() {
		// @Override
		// public void uncaughtException(Thread thread, Throwable ex) {
		// ex.printStackTrace();
		// System.exit(1);
		// }
		// });
		Frame.init(getApplicationContext());
		F.init();
		super.onCreate();
	}
}
