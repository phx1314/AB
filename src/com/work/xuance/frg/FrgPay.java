//
//  FrgPay
//
//  Created by Administrator on 2015-10-03 15:29:15
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.frg;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import net.sourceforge.simcpux.Constants;
import net.sourceforge.simcpux.MD5;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.mdx.framework.Frame;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MOrderMini;
import com.udows.common.proto.MRet;
import com.udows.shoppingcar.data.AlixId;
import com.udows.shoppingcar.data.BaseHelper;
import com.udows.shoppingcar.data.Rsa;
import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;
import com.work.xuance.F;
import com.work.xuance.R;

public class FrgPay extends BaseFrg implements Callback, Runnable,
		OnKeyListener {

	public TextView mTextView_name;
	public TextView mTextView_price;
	public TextView mTextView_yunfei;
	public TextView mTextView_heji;
	public CheckBox mCheckBox;
	public EditText mEditText_1;
	public EditText mEditText_2;
	public EditText mEditText_3;
	public EditText mEditText_4;
	public EditText mEditText_5;
	public EditText mEditText_6;
	public EditText mEditText_7;
	public EditText mEditText_8;
	public EditText mEditText_9;
	public EditText mEditText_10;
	public TextView mTextView_youhuijia;
	public TextView mTextView_shifu;
	public TextView mTextView_time;
	public TextView mTextView_pay;
	public LinearLayout mLinearLayout_youhui;
	private static final int RQF_PAY = 1;
	// public String[] data = { "支付宝", "银联", "微信" };
	public String[] data = { "支付宝", "微信" };
	private String orderid;
	/*****************************************************************
	 * mMode参数解释： "00" - 启动银联正式环境 "01" - 连接银联测试环境
	 *****************************************************************/
	private final String mMode = "01";
	private Handler mHandler1 = null;
	private ProgressDialog mLoadingDialog = null;
	// 微信支付
	private PayReq req;
	private IWXAPI msgApi;
	private String id;
	private String code = "", a = "", b = "", c = "", d = "", e = "", f = "",
			g = "", h = "", i = "", j = "";
	private MOrderMini mMOrderMini;
	private double yh_price;

	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_pay);
		id = getActivity().getIntent().getStringExtra("id");
		initView();
		loaddata();
	}

	@Override
	public void disposeMsg(int type, Object obj) {
		switch (type) {
		case 0:
			FrgPay.this.finish();
			Frame.HANDLES.sentAll("FrgZh", 010, "");
			break;
		}
	}

	private void initView() {
		mLinearLayout_youhui = (LinearLayout) findViewById(R.id.mLinearLayout_youhui);
		mTextView_name = (TextView) findViewById(R.id.mTextView_name);
		mTextView_price = (TextView) findViewById(R.id.mTextView_price);
		mTextView_yunfei = (TextView) findViewById(R.id.mTextView_yunfei);
		mTextView_heji = (TextView) findViewById(R.id.mTextView_heji);
		mCheckBox = (CheckBox) findViewById(R.id.mCheckBox);
		mEditText_1 = (EditText) findViewById(R.id.mEditText_1);
		mEditText_2 = (EditText) findViewById(R.id.mEditText_2);
		mEditText_3 = (EditText) findViewById(R.id.mEditText_3);
		mEditText_4 = (EditText) findViewById(R.id.mEditText_4);
		mEditText_5 = (EditText) findViewById(R.id.mEditText_5);
		mEditText_6 = (EditText) findViewById(R.id.mEditText_6);
		mEditText_7 = (EditText) findViewById(R.id.mEditText_7);
		mEditText_8 = (EditText) findViewById(R.id.mEditText_8);
		mEditText_9 = (EditText) findViewById(R.id.mEditText_9);
		mEditText_10 = (EditText) findViewById(R.id.mEditText_10);
		mTextView_youhuijia = (TextView) findViewById(R.id.mTextView_youhuijia);
		mTextView_shifu = (TextView) findViewById(R.id.mTextView_shifu);
		mTextView_time = (TextView) findViewById(R.id.mTextView_time);
		mTextView_pay = (TextView) findViewById(R.id.mTextView_pay);
		msgApi = WXAPIFactory.createWXAPI(getActivity(), null);
		req = new PayReq();
		mHandler1 = new Handler(this);
		mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					mLinearLayout_youhui.setVisibility(View.VISIBLE);
					mEditText_1.requestFocus();
					reSetPrice();
				} else {
					mLinearLayout_youhui.setVisibility(View.GONE);
					mTextView_shifu.setText(mMOrderMini.totalPrice);
				}
			}
		});
		mEditText_1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mEditText_1.getText().toString().length() > 0) {
					mEditText_2.requestFocus();
					getCodePrice();
				}
			}
		});
		mEditText_2.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mEditText_2.getText().toString().length() > 0) {
					mEditText_3.requestFocus();
					getCodePrice();
				}
			}
		});
		mEditText_3.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mEditText_3.getText().toString().length() > 0) {
					mEditText_4.requestFocus();
					getCodePrice();
				}
			}
		});
		mEditText_4.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mEditText_4.getText().toString().length() > 0) {
					mEditText_5.requestFocus();
					getCodePrice();
				}
			}
		});
		mEditText_5.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mEditText_5.getText().toString().length() > 0) {
					mEditText_6.requestFocus();
					getCodePrice();
				}
			}
		});
		mEditText_6.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mEditText_6.getText().toString().length() > 0) {
					mEditText_7.requestFocus();
					getCodePrice();
				}
			}
		});
		mEditText_7.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mEditText_7.getText().toString().length() > 0) {
					mEditText_8.requestFocus();
					getCodePrice();
				}
			}
		});
		mEditText_8.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mEditText_8.getText().toString().length() > 0) {

					mEditText_9.requestFocus();
					getCodePrice();
				}
			}
		});
		mEditText_9.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (mEditText_9.getText().toString().length() > 0) {

					mEditText_10.requestFocus();
					getCodePrice();
				}
			}
		});
		mEditText_10.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				getCodePrice();
			}
		});
		mEditText_1.setOnKeyListener(this);
		mEditText_2.setOnKeyListener(this);
		mEditText_3.setOnKeyListener(this);
		mEditText_4.setOnKeyListener(this);
		mEditText_5.setOnKeyListener(this);
		mEditText_6.setOnKeyListener(this);
		mEditText_7.setOnKeyListener(this);
		mEditText_8.setOnKeyListener(this);
		mEditText_9.setOnKeyListener(this);
		mEditText_10.setOnKeyListener(this);
	}

	protected void getCodePrice() {
		a = mEditText_1.getText().toString().trim();
		b = mEditText_2.getText().toString().trim();
		c = mEditText_3.getText().toString().trim();
		d = mEditText_4.getText().toString().trim();
		e = mEditText_5.getText().toString().trim();
		f = mEditText_6.getText().toString().trim();
		g = mEditText_7.getText().toString().trim();
		h = mEditText_8.getText().toString().trim();
		i = mEditText_9.getText().toString().trim();
		j = mEditText_10.getText().toString().trim();
		code = a + b + c + d + e + f + g + h + i + j;
		if (code.length() == 10) {
			F.closeSoftKey(getActivity());
			ApisFactory.getApiMCouponCode().load(getContext(), FrgPay.this,
					"MCouponCode", code, mMOrderMini.totalPrice);
		}
	}

	public void MCouponCode(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		if (mMRet.code == 0) {
			yh_price = Double.valueOf(mMRet.msg);
			mTextView_youhuijia.setText(Html.fromHtml("优惠"
					+ "<font color=#18A80D>" + "￥" + yh_price + " </font>"));
		} else {
			yh_price = 0;
			Helper.toast("优惠券不可用", getContext());
		}
		reSetPrice();
	}

	public void reSetPrice() {
		if (yh_price >= Double.valueOf(mMOrderMini.totalPrice)) {
			mTextView_shifu.setText("0");
		} else {
			mTextView_shifu.setText(F.go2Wei((Double
					.valueOf(mMOrderMini.totalPrice) - yh_price)));
		}
	}

	public void loaddata() {
		mTextView_pay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Double.valueOf(mTextView_shifu.getText().toString()) > 0) {
					new AlertDialog.Builder(getContext())
							.setTitle("请选择支付方式")
							.setItems(data,
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											switch (which) {
											case 0:
												ApisFactory
														.getApiMToPay()
														.load(getContext(),
																FrgPay.this,
																"MToPay2",
																(double) 1,
																id,
																mCheckBox
																		.isChecked() == true ? code
																		: null);
												break;
											// case 1:
											// ApisFactory
											// .getApiMToPay()
											// .load(getContext(),
											// FrgPay.this,
											// "MToPay3",
											// (double) 2,
											// id,
											// mCheckBox
											// .isChecked() == true ? code
											// : null);
											// break;
											case 1:
												ApisFactory
														.getApiMToPay()
														.load(getContext(),
																FrgPay.this,
																"MToPay4",
																(double) 3,
																id,
																mCheckBox
																		.isChecked() == true ? code
																		: null);
												break;
											}
											dialog.dismiss();
										}
									}).show();
				} else {
					ApisFactory.getApiMToPay().load(getContext(), FrgPay.this,
							"MToPay1", (double) 1, id, code);
				}
			}
		});
		ApisFactory.getApiMOrderInfo().load(getContext(), FrgPay.this,
				"MOrderInfo", id);
	}

	public void MToPay1(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		if (mMRet.code == 1) {
			ApisFactory.getApiMFinishPay().load(getContext(), FrgPay.this,
					"MFinishPay", id);
		}
	}

	public void MToPay3(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		if (mMRet.code == 0) {
			orderid = mMRet.msg;
			mLoadingDialog = ProgressDialog.show(getActivity(), // context
					"", // title
					"正在努力的获取中,请稍候...", // message
					true); // 进度是否是不确定的，这只和创建进度条有关
			/*************************************************
			 * 步骤1：从网络开始,获取交易流水号即TN
			 ************************************************/
			new Thread(FrgPay.this).start();
		}
	}

	public void MToPay2(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		if (mMRet.code == 0) {
			orderid = mMRet.msg;
			pay(mTextView_shifu.getText().toString().trim());
		}
	}

	public void MToPay4(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		if (mMRet.code == 0) {
			orderid = mMRet.msg;
			genPayReq();
		}
	}

	public void MFinishPay(Son s) {
		Helper.toast("支付成功", getContext());
		Frame.HANDLES.sentAll("FrgZh", 010, "");
		FrgPay.this.finish();
	}

	public void MOrderInfo(Son s) {
		mMOrderMini = (MOrderMini) s.getBuild();
		mTextView_yunfei.setText("￥" + mMOrderMini.expressPrice);
		mTextView_heji.setText("￥" + mMOrderMini.totalPrice);
		mTextView_shifu.setText(mMOrderMini.totalPrice);
		switch (mMOrderMini.type) {
		case 1:
			mTextView_name.setText(mMOrderMini.total + "*相册");
			break;
		case 2:
			mTextView_name.setText(mMOrderMini.total + "*台历");
			break;
		case 3:
			mTextView_name.setText(mMOrderMini.total + "*明信片");
			break;
		}
		mTextView_price.setText("￥" + mMOrderMini.price);
		ApisFactory.getApiMSingleSysParams().load(getContext(), FrgPay.this,
				"MSingleSysParams", "1004");
	}

	@SuppressLint("SimpleDateFormat")
	public void MSingleSysParams(Son s) {
		MRet mMRet = (MRet) s.getBuild();
		java.util.Calendar Cal = java.util.Calendar.getInstance();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		try {
			Cal.setTime(ConverToDate(mMOrderMini.time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Cal.add(Calendar.DAY_OF_MONTH, +Integer.valueOf(mMRet.msg));
		System.out.println("date:" + format.format(Cal.getTime()));
		mTextView_time.setText("预计发货日期： " + format.format(Cal.getTime()));
	}

	// 把字符串转为日期
	@SuppressLint("SimpleDateFormat")
	public static Date ConverToDate(String strDate) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.parse(strDate);
	}

	@Override
	public void setActionBar(ActionBar actionBar, Context context) {
		super.setActionBar(actionBar, context);
		mHeadlayout.setTitle("支付");
	}

	/**
	 * the OnCancelListener for lephone platform. lephone系统使用到的取消dialog监听
	 */

	public static class AlixOnCancelListener implements
			DialogInterface.OnCancelListener {
		Activity mcontext;

		public AlixOnCancelListener(Activity context) {
			mcontext = context;
		}

		public void onCancel(DialogInterface dialog) {
			mcontext.onKeyDown(KeyEvent.KEYCODE_BACK, null);
		}
	}

	/**
	 * get the selected order info for pay. 获取商品订单信息
	 * 
	 * @param position
	 *            商品在列表中的位置
	 * @return
	 */
	String getNewOrderInfo(String orderprice) {
		StringBuilder sb = new StringBuilder();
		sb.append("partner=\"");
		sb.append(F.partnerId);
		sb.append("\"&out_trade_no=\"");
		sb.append(orderid); // 订单号 原数据getOutTradeNo()
		sb.append("\"&subject=\"");
		sb.append("来" + getResources().getString(R.string.app_name) + "的商品");// sProducts[position].subject
		sb.append("\"&body=\"");
		sb.append("看不见的body");// sProducts[position].body
		sb.append("\"&total_fee=\"");
		// 价格
		sb.append(Double.valueOf(orderprice));// sProducts[position].price.replace("一口价:",
		// "")
		sb.append("\"&notify_url=\"");

		// 网址需要做URL编码
		sb.append(URLEncoder.encode(F.notifyUrl));// "http://diedie.webok.net/alipaynotify"
		sb.append("\"&service=\"mobile.securitypay.pay");
		sb.append("\"&_input_charset=\"UTF-8");
		sb.append("\"&return_url=\"");
		sb.append(URLEncoder.encode("http://m.alipay.com"));
		sb.append("\"&payment_type=\"1");
		sb.append("\"&seller_id=\"");
		sb.append(F.sellerId);

		// 如果show_url值为空，可不传
		// sb.append("\"&show_url=\"");
		sb.append("\"&it_b_pay=\"1m");
		sb.append("\"");

		return new String(sb);
	}

	private void pay(String money) {
		// 根据订单信息开始进行支付
		try {
			Log.i("ExternalPartner", "onItemClick");
			String info = getNewOrderInfo(money);
			String sign = Rsa.sign(info, F.rsaPrivate);
			sign = URLEncoder.encode(sign, "utf8");
			info += "&sign=\"" + sign + "\"&" + getSignType();
			Log.i("ExternalPartner", "start pay");
			// start the pay.
			Log.i("info", "info = " + info);

			final String orderInfo = info;
			new Thread() {
				public void run() {
					PayTask alipay = new PayTask(getActivity());
					// AliPay alipay = new AliPay(getActivity(), mHandler);
					String result = alipay.pay(orderInfo);
					Message msg = new Message();
					msg.what = 1;
					msg.obj = result;
					mHandler.sendMessage(msg);
				}
			}.start();

		} catch (Exception ex) {
			ex.printStackTrace();
			Toast.makeText(getContext(), R.string.remote_call_failed,
					Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 * @return
	 */

	private String getSignType() {
		return "sign_type=\"RSA\"";
	}

	// the handler use to receive the pay result.
	// 这里接收支付结果，支付宝手机端同步通知
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				String strRet = (String) msg.obj;
				Log.e("info", strRet); // strRet范例：resultStatus={9000};memo={};result={partner="2088201564809153"&seller="2088201564809153"&out_trade_no="050917083121576"&subject="123456"&body="2010新款NIKE 耐克902第三代板鞋 耐克男女鞋 386201 白红"&total_fee="0.01"&notify_url="http://notify.java.jpxx.org/index.jsp"&success="true"&sign_type="RSA"&sign="d9pdkfy75G997NiPS1yZoYNCmtRbdOP0usZIMmKCCMVqbSG1P44ohvqMYRztrB6ErgEecIiPj9UldV5nSy9CrBVjV54rBGoT6VSUF/ufjJeCSuL510JwaRpHtRPeURS1LXnSrbwtdkDOktXubQKnIMg2W0PreT1mRXDSaeEECzc="}
				switch (msg.what) {
				case AlixId.RQF_PAY: {
					BaseHelper.log("info", strRet);
					// 处理交易结果
					try {
						// 获取交易状态码，具体状态代码请参看文档
						String tradeStatus = "resultStatus={";
						int imemoStart = strRet.indexOf("resultStatus=");
						imemoStart += tradeStatus.length();
						int imemoEnd = strRet.indexOf("};memo=");
						tradeStatus = strRet.substring(imemoStart, imemoEnd);
						if (tradeStatus.equals("9000")) {// 判断交易状态码，只有9000表示交易成功
							Toast.makeText(getContext(), "支付成功",
									Toast.LENGTH_SHORT).show();
							FrgPay.this.finish();
							Frame.HANDLES.sentAll("FrgZh", 010, "");
						} else
							Toast.makeText(getContext(), "支付失败",
									Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						e.printStackTrace();
						BaseHelper.showDialog(getActivity(), "提示", strRet,
								R.drawable.infoicon);
					}
				}
					break;
				}
				super.handleMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	// 银联支付
	@Override
	public boolean handleMessage(Message msg) {
		if (mLoadingDialog.isShowing()) {
			mLoadingDialog.dismiss();
		}

		String tn = "";
		if (msg.obj == null || ((String) msg.obj).length() == 0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("错误提示");
			builder.setMessage("网络连接失败,请重试!");
			builder.setNegativeButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			builder.create().show();
		} else {
			tn = (String) msg.obj;
			/*************************************************
			 * 步骤2：通过银联工具类启动支付插件
			 ************************************************/
			UPPayAssistEx.startPayByJAR(getActivity(), PayActivity.class, null,
					null, tn, mMode);
		}

		return false;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		/*************************************************
		 * 步骤3：处理银联手机支付控件返回的支付结果
		 ************************************************/
		if (data == null) {
			return;
		}

		String msg = "";
		/*
		 * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
		 */
		final String str = data.getExtras().getString("pay_result");
		if (str.equalsIgnoreCase("success")) {
			msg = "支付成功！";
		} else if (str.equalsIgnoreCase("fail")) {
			msg = "支付失败！";
		} else if (str.equalsIgnoreCase("cancel")) {
			msg = "用户取消了支付";
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("支付结果通知");
		builder.setMessage(msg);
		builder.setInverseBackgroundForced(true);
		// builder.setCustomTitle();
		builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (str.equalsIgnoreCase("success")) {
					FrgPay.this.finish();
					Frame.HANDLES.sentAll("FrgZh", 010, "");
				}

				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	@Override
	public void run() {
		String tn = orderid;
		Message msg = mHandler1.obtainMessage();
		msg.obj = tn;
		mHandler1.sendMessage(msg);
	}

	/**
	 * 微信支付调用 生成签名
	 * 
	 * @author Avery
	 */
	private void genPayReq() {
		req.appId = Constants.APP_ID;
		req.partnerId = Constants.MCH_ID;
		req.prepayId = orderid;
		req.packageValue = "Sign=WXPay";
		req.nonceStr = genNonceStr();
		req.timeStamp = String.valueOf(genTimeStamp());

		List<NameValuePair> signParams = new LinkedList<NameValuePair>();
		signParams.add(new BasicNameValuePair("appid", req.appId));
		signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
		signParams.add(new BasicNameValuePair("package", req.packageValue));
		signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
		signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
		signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));

		req.sign = genAppSign(signParams);
		Log.e("signParams", signParams.toString());
		msgApi.registerApp(Constants.APP_ID);
		msgApi.sendReq(req);
	}

	private String genNonceStr() {
		Random random = new Random();
		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000))
				.getBytes());
	}

	private long genTimeStamp() {
		return System.currentTimeMillis() / 1000;
	}

	private String genAppSign(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append(Constants.API_KEY);

		String appSign = MD5.getMessageDigest(sb.toString().getBytes())
				.toUpperCase();
		Log.e("orion", appSign);
		return appSign;
	}

	@Override
	public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
		switch (arg0.getId()) {
		case R.id.mEditText_2:
			if (arg1 == KeyEvent.KEYCODE_DEL) {
				if (mEditText_2.getText().toString().length() <= 0)
					mEditText_1.requestFocus();
			}
			break;
		case R.id.mEditText_3:
			if (arg1 == KeyEvent.KEYCODE_DEL) {
				if (mEditText_3.getText().toString().length() <= 0)
					mEditText_2.requestFocus();
			}
			break;
		case R.id.mEditText_4:
			if (arg1 == KeyEvent.KEYCODE_DEL) {
				if (mEditText_4.getText().toString().length() <= 0)
					mEditText_3.requestFocus();
			}
			break;
		case R.id.mEditText_5:
			if (arg1 == KeyEvent.KEYCODE_DEL) {
				if (mEditText_5.getText().toString().length() <= 0)
					mEditText_4.requestFocus();
			}
			break;
		case R.id.mEditText_6:
			if (arg1 == KeyEvent.KEYCODE_DEL) {
				if (mEditText_6.getText().toString().length() <= 0)
					mEditText_5.requestFocus();
			}
			break;
		case R.id.mEditText_7:
			if (arg1 == KeyEvent.KEYCODE_DEL) {
				if (mEditText_7.getText().toString().length() <= 0)
					mEditText_6.requestFocus();
			}
			break;
		case R.id.mEditText_8:
			if (arg1 == KeyEvent.KEYCODE_DEL) {
				if (mEditText_8.getText().toString().length() <= 0)
					mEditText_7.requestFocus();
			}
			break;
		case R.id.mEditText_9:
			if (arg1 == KeyEvent.KEYCODE_DEL) {
				if (mEditText_9.getText().toString().length() <= 0)
					mEditText_8.requestFocus();
			}
			break;
		case R.id.mEditText_10:
			if (arg1 == KeyEvent.KEYCODE_DEL) {
				if (mEditText_10.getText().toString().length() <= 0)
					mEditText_9.requestFocus();
			}
			break;
		}
		return false;
	}

}