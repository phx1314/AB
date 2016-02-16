package com.work.xuance.ada;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.work.xuance.R;
import com.mdx.framework.adapter.MPagerAdapter;
import com.mdx.framework.broadcast.BIntent;
import com.mdx.framework.broadcast.BReceiver;
import com.mdx.framework.widget.MImageView;
import com.mdx.framework.widget.MImageView.OnImageLoaded;
import com.mdx.framework.widget.viewpagerindicator.IconPagerAdapter;

public class PhotoAdapter extends MPagerAdapter<String> implements
		IconPagerAdapter {
	public PhotoAdapter(Context context, List<String> list) {
		super(context, list);
	}

	@Override
	public int getIconResId(int index) {
		return 0;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater f = LayoutInflater.from(getContext());
			convertView = f.inflate(R.layout.photoshow_item, null);
			holder.img = (MImageView) convertView
					.findViewById(R.id.photoshow_imageview);
			holder.process = (ProgressBar) convertView
					.findViewById(R.id.photoshow_processbar);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.process.setVisibility(View.VISIBLE);
		final ViewHolder hold = holder;
		holder.img.setOnImageLoaded(new OnImageLoaded() {

			@Override
			public void onImageLoaded(Object obj, Drawable drawable, int size,
					int length) {
				hold.process.setVisibility(View.GONE);
			}
		});
		holder.img.setScaleAble(true);
		holder.img.setObj(get(position));
		holder.img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		return convertView;
	}

	public class Rec extends BReceiver {
		/**
		 * 
		 * [鏋勯�绠�璇存槑]
		 * 
		 * @param action
		 * @param id
		 * @param obj
		 * @param context
		 */
		public Rec(String action, Object id, Object obj, Context context) {
			super(action, id, obj, context);
		}

		@Override
		public void onReceive(Context context, BIntent intent) {
		}

	}

	public final class ViewHolder {
		public MImageView img;

		public ProgressBar process;
	}
}