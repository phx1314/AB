//
//  ImgshowDialog
//
//  Created by Administrator on 2015-10-04 14:37:02
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.item;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framewidget.MyViewPagerAdapter;
import com.mdx.framework.Frame;
import com.mdx.framework.widget.MViewPager;
import com.work.xuance.R;
import com.work.xuance.model.ModelData;
import com.work.xuance.model.FileFather.FileSon;

public class ImgshowDialog extends BaseItem implements OnPageChangeListener {
	public MViewPager mViewPager;
	public TextView mTextView_num;
	public TextView mTextView_del;
	public TextView mTextView_cancel;
	public Dialog mDialog;
	public List<FileSon> list;
	public int position;
	public int position_father;
	public MyViewPagerAdapter mMyViewPagerAdapter;

	@SuppressLint("InflateParams")
	public static View getView(Context context, ViewGroup parent) {
		LayoutInflater flater = LayoutInflater.from(context);
		View convertView = flater.inflate(R.layout.item_imgshow_dialog, null);
		convertView.setTag(new ImgshowDialog(convertView));
		return convertView;
	}

	public ImgshowDialog(View view) {
		this.contentview = view;
		this.context = contentview.getContext();
		initView();
	}

	private void initView() {
		this.contentview.setTag(this);
		mViewPager = (MViewPager) contentview.findViewById(R.id.mViewPager);
		mTextView_num = (TextView) contentview.findViewById(R.id.mTextView_num);
		mTextView_del = (TextView) contentview.findViewById(R.id.mTextView_del);
		mTextView_cancel = (TextView) contentview
				.findViewById(R.id.mTextView_cancel);

		mTextView_del.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Frame.HANDLES.sentAll("FrgXuanzezhaopian", 1, new ModelData(
						list.get(mViewPager.getCurrentItem()), position_father,
						mViewPager.getCurrentItem()));
				Frame.HANDLES.sentAll("FrgXuanzezhaopianSon", 0,
						list.get(mViewPager.getCurrentItem()));

				list.remove(mViewPager.getCurrentItem());
				int ri = mViewPager.getCurrentItem() - 1;
				updata();
				if (ri < 0) {
					ri = 0;
					mViewPager.setCurrentItem(ri);
				} else {
					mViewPager.setCurrentItem(ri);
				}
				mTextView_num.setText((ri + 1) + "/" + list.size());
				if (list.size() == 0) {
					mDialog.dismiss();
				}
			}
		});
		mTextView_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mDialog.dismiss();
			}
		});
		mViewPager.setOnPageChangeListener(this);
	}

	public void set(Object item, List<FileSon> list, int position) {
		this.mDialog = (Dialog) item;
		this.list = list;
		this.position = position;
		mTextView_num.setText((position + 1) + "/" + list.size());
		updata();
		mViewPager.setCurrentItem(position);
	}

	public void updata() {
		List<View> mViews = new ArrayList<View>();
		for (int i = 0; i < list.size(); i++) {
			View view = ImgShow.getView(context, null);
			mViews.add(view);
			((ImgShow) view.getTag()).set(list.get(i));
		}
		mMyViewPagerAdapter = new MyViewPagerAdapter(mViews);
		mViewPager.setAdapter(mMyViewPagerAdapter);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		mTextView_num.setText((arg0 + 1) + "/" + list.size());
	}
}