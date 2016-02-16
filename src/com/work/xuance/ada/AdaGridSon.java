//
//  AdaGridSon
//
//  Created by Administrator on 2015-10-04 14:37:02
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.work.xuance.ada;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.mdx.framework.Frame;
import com.mdx.framework.utility.Helper;
import com.work.xuance.frg.FrgXuanzezhaopian;
import com.work.xuance.item.GridSon;
import com.work.xuance.model.ModelData;
import com.work.xuance.model.FileFather.FileSon;
import com.work.xuance.view.MAdapter;

public class AdaGridSon extends MAdapter<FileSon> {
	public int position_father;
	public int size;

	public AdaGridSon(Context context, List<FileSon> list, int position_father,
			int size) {
		super(context, list);
		this.position_father = position_father;
		this.size = size;
	}

	@Override
	public View getview(final int position, View convertView, ViewGroup parent) {
		final FileSon item = get(position);
		if (convertView == null) {
			convertView = GridSon.getView(getContext(), parent);
		}
		GridSon mGridSon = (GridSon) convertView.getTag();
		mGridSon.set(item);
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (!item.isChoose()) {
					if (FrgXuanzezhaopian.mFileSons.size() >= size) {
						Helper.toast("图片已满", getContext());
					} else if (size == 13) {
						android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
						options.inJustDecodeBounds = true;
						BitmapFactory.decodeFile(item.getPath(), options);
						if (options.outHeight > options.outWidth) {
							AlertDialog exitDialog = new AlertDialog.Builder(
									getContext())
									.setMessage("该图片高度大于宽度,您还要继续选择吗?")
									.setTitle("提示")
									.setPositiveButton("是",
											new AlertDialog.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int which) {
													item.setDiji(FrgXuanzezhaopian.mFileSons
															.size());
													item.setChoose(!item
															.isChoose());
													Frame.HANDLES
															.sentAll(
																	"FrgXuanzezhaopian",
																	0,
																	new ModelData(
																			item,
																			position_father,
																			position));
													Frame.HANDLES
															.sentAll(
																	"FrgXuanzezhaopianSon",
																	0, null);
													AdaGridSon.this
															.notifyDataSetChanged();
												}
											}).setNegativeButton("否", null)
									.create();
							exitDialog.show();
						} else {
							item.setDiji(FrgXuanzezhaopian.mFileSons.size());
							item.setChoose(!item.isChoose());
							Frame.HANDLES.sentAll("FrgXuanzezhaopian", 0,
									new ModelData(item, position_father,
											position));
							Frame.HANDLES.sentAll("FrgXuanzezhaopianSon", 0,
									null);
							AdaGridSon.this.notifyDataSetChanged();
						}
					} else {
						item.setDiji(FrgXuanzezhaopian.mFileSons.size());
						item.setChoose(!item.isChoose());
						Frame.HANDLES.sentAll("FrgXuanzezhaopian", 0,
								new ModelData(item, position_father, position));
						Frame.HANDLES.sentAll("FrgXuanzezhaopianSon", 0, null);
						AdaGridSon.this.notifyDataSetChanged();
					}
				} else {
					Frame.HANDLES.sentAll("FrgXuanzezhaopian", 1,
							new ModelData(item, position_father, position));
					Frame.HANDLES.sentAll("FrgXuanzezhaopianSon", 0, item);
				}

			}
		});
		return convertView;
	}
}
