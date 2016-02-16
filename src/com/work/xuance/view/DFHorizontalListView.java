package com.work.xuance.view;

import android.content.Context;
import android.util.AttributeSet;

import com.mdx.framework.widget.HorizontalListView;

public class DFHorizontalListView extends HorizontalListView {

	public DFHorizontalListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DFHorizontalListView(Context context) {
		super(context);
	}

	@Override
	public void setSelection(int position) {
		int positionX = position * this.getWidth();
		int maxWidth = this.getChildCount() * this.getWidth();
		if (positionX <= 0) {
			positionX = 0;
		}
		if (positionX > maxWidth) {
			positionX = maxWidth;
		}
		scrollTo(positionX);
	}

}
