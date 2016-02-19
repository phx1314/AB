package com.work.xuance.wxapi;

import net.sourceforge.simcpux.Constants;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.mdx.framework.Frame;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.work.xuance.R;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pay_result);
		api = WXAPIFactory.createWXAPI(WXPayEntryActivity.this,
				Constants.APP_ID);
		api.handleIntent(getIntent(), WXPayEntryActivity.this);
	}

	// @Override
	// protected void onNewIntent(Intent intent) {
	// super.onNewIntent(intent);
	// setIntent(intent);
	// api.handleIntent(intent, this);
	// }

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			switch (resp.errCode) {
			case BaseResp.ErrCode.ERR_OK:
				Toast.makeText(WXPayEntryActivity.this, "付款成功，去订单中查看。",
						Toast.LENGTH_SHORT).show();
				Frame.HANDLES.sentAll("FrgPay", 0, "");
				this.finish();
				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				Toast.makeText(WXPayEntryActivity.this, "取消支付",
						Toast.LENGTH_SHORT).show();
				this.finish();
				break;
			default:
				Toast.makeText(WXPayEntryActivity.this, "支付失败，请重试",
						Toast.LENGTH_SHORT).show();
				this.finish();
				break;
			}
		}
		// Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);
		// if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
		// AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setTitle(R.string.app_tip);
		// builder.setMessage(getString(R.string.pay_result_callback_msg,
		// resp.errStr + ";code=" + String.valueOf(resp.errCode)));
		// builder.show();
		// }
	}
}