package com.work.xuance.item;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.work.xuance.F;
import com.work.xuance.R;

/**
 * 顶部 [功能详细描述]
 * 
 * @author Administrator
 * @version [2013-11-14 下午4:42:38]
 */
public class Headlayout extends LinearLayout {
	public ImageButton btn_left, btn_right, btn_right_2;

	public TextView tv_title;
	public TextView mTextView_right;
	public ImageView mImageView;
	public RelativeLayout mRelativeLayout;

	public Headlayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Headlayout(Context context) {
		super(context);
		init();
	}

	@SuppressLint("NewApi")
	public void init() {
		LayoutInflater f = LayoutInflater.from(getContext());
		View v = f.inflate(R.layout.topbar, this);
		btn_left = (ImageButton) findViewById(R.id.btn_left);
		btn_right = (ImageButton) findViewById(R.id.btn_right);
		btn_right_2 = (ImageButton) findViewById(R.id.btn_right_2);
		tv_title = (TextView) findViewById(R.id.tv_title);
		mTextView_right = (TextView) findViewById(R.id.mTextView_right);
		mImageView = (ImageView) findViewById(R.id.mImageView);
		mRelativeLayout = (RelativeLayout) findViewById(R.id.mRelativeLayout);
		mRelativeLayout.setAlpha(0.9f);
	}

	public TextView getmTextView_right() {
		return mTextView_right;
	}

	public void setmTextView_right(TextView mTextView_right) {
		this.mTextView_right = mTextView_right;
	}

	public RelativeLayout getmRelativeLayout() {
		return mRelativeLayout;
	}

	public void setmRelativeLayout(RelativeLayout mRelativeLayout) {
		this.mRelativeLayout = mRelativeLayout;
	}

	public TextView getTv_title() {
		return tv_title;
	}

	public void setTv_title(TextView tv_title) {
		this.tv_title = tv_title;
	}

	public void setRText(CharSequence s) {
		mTextView_right.setText(s);
		mTextView_right.setVisibility(View.VISIBLE);
	}

	/**
	 * 返回
	 * 
	 * @author Administrator
	 * @Title: goBack
	 * @Description: TODO
	 * @param @param act
	 * @return void
	 * @throws
	 */
	public void goBack(final Activity act) {
		findViewById(R.id.mLinearLayout_back).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						act.finish();
					}
				});
		findViewById(R.id.btn_left).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				act.finish();
				F.closeSoftKey(act);
			}
		});
	}

	/**
	 * 设置左边按钮监听
	 * 
	 * @author Administrator
	 * @Title: leftOnclicker
	 * @Description: TODO
	 * @param @param c
	 * @return void
	 * @throws
	 */
	public void setLeftOnclicker(OnClickListener c) {
		btn_left.setOnClickListener(c);
		findViewById(R.id.mLinearLayout_back).setOnClickListener(c);
	}

	/**
	 * 设置右边按钮监听
	 * 
	 * @author Administrator
	 * @Title: setRightOnclicker
	 * @Description: TODO
	 * @param @param c
	 * @return void
	 * @throws
	 */
	public void setRightOnclicker(OnClickListener c) {
		btn_right.setOnClickListener(c);
		mTextView_right.setOnClickListener(c);
	}

	public void setRightEnable(boolean isEnable) {
		btn_right.setEnabled(isEnable);
		mTextView_right.setEnabled(isEnable);
	}

	public void setRight2Onclicker(OnClickListener c) {
		btn_right_2.setOnClickListener(c);
	}

	/**
	 * 设置标题
	 * 
	 * @author Administrator
	 * @Title: setTitle
	 * @Description: TODO
	 * @param @param s
	 * @return void
	 * @throws
	 */
	public void setTitle(String s) {
		tv_title.setText(s);
	}

	/**
	 * 设置res
	 * 
	 * @author Administrator
	 * @Title: setTitle
	 * @Description: TODO
	 * @param @param s
	 * @return void
	 * @throws
	 */
	public void setTitleRes(int s) {
		tv_title.setVisibility(View.GONE);
		mImageView.setVisibility(View.VISIBLE);
		mImageView.setBackgroundResource(s);

	}

	/**
	 * 设置左边按钮隐藏
	 * 
	 * @author Administrator
	 * @Title: setLeftGone
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	public void setLeftGone() {
		btn_left.setVisibility(View.GONE);
	}

	/**
	 * 设置左边按钮显示
	 * 
	 * @author Administrator
	 * @Title: setLeftGone
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	public void setLeftShow() {
		btn_left.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置右边按钮隐藏
	 * 
	 * @author Administrator
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	public void setRightGone() {
		btn_right.setVisibility(View.GONE);
	}

	/**
	 * 设置右边按钮显示
	 * 
	 * @author Administrator
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	public void setRightShow() {
		btn_right.setVisibility(View.VISIBLE);
	}

	public void setRight2Show() {
		btn_right_2.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置左边的按钮的背景
	 * 
	 * @author Administrator
	 * @Title: setLeftBc
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	public void setLeftBackground(int res) {
		btn_left.setImageResource(res);
		setLeftShow();
	}

	/**
	 * 设置右边的按钮的背景
	 * 
	 * @author Administrator
	 * @Title: setRightBacgroud
	 * @Description: TODO
	 * @param @param res
	 * @return void
	 * @throws
	 */
	public void setRightBacgroud(int res) {
		btn_right.setImageResource(res);
		setRightShow();
	}

	public void setRight2Bacgroud(int res) {
		btn_right_2.setImageResource(res);
		setRight2Show();
	}

	/**
	 * 返回
	 * 
	 * @author Administrator
	 * @Title: setGoBack
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	public void setGoBack(final Activity act) {
		findViewById(R.id.mLinearLayout_back).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						act.finish();
						F.closeSoftKey(act);
					}
				});
		findViewById(R.id.btn_left).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				act.finish();
				F.closeSoftKey(act);
			}
		});
	}

	public void setBg(int resid) {
		mRelativeLayout.setBackgroundResource(resid);
	}
}
