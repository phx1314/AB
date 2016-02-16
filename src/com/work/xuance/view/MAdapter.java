package com.work.xuance.view;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.mdx.framework.adapter.MAdapterView;
import com.mdx.framework.adapter.MBaseAdapter;

@SuppressLint("UseSparseArrays")
public abstract class MAdapter<T> extends MBaseAdapter {
	private Context context;

	protected HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();

	protected int groupCount = 1;

	private LayoutInflater layoutInflater;

	private ArrayList<T> list = new ArrayList<T>();

	private OnNotifyChangedListener<T> onNotifyChangedListener;

	private int resoure = 0;

	public MAdapter(Context context, List<T> list, int Resoure) {
		this.context = context;
		layoutInflater = LayoutInflater.from(this.context);
		this.list.addAll(list);
		this.resoure = Resoure;
	}

	public void AddAll(List<T> list) {
		this.list.addAll(list);
		resetGroup();
		this.notifyDataSetChanged();
	}

	public void AddAll(MAdapter<?> list) {
		for (int i = 0; i < list.getCount(); i++) {
			@SuppressWarnings("unchecked")
			T item = (T) list.get(i);
			this.list.add(item);
			if (list.params != null) {
				this.params.putAll(list.params);
			}
		}
		resetGroup();
		this.notifyDataSetChanged();
	}

	public void AddAllOnBegin(MAdapter<?> list) {
		for (int i = list.getCount() - 1; i >= 0; i--) {
			@SuppressWarnings("unchecked")
			T item = (T) list.get(i);
			this.list.add(0, item);
			if (list.params != null) {
				this.params.putAll(list.params);
			}
		}
		resetGroup();
		this.notifyDataSetChanged();
	}

	@SuppressWarnings("unchecked")
	public void add(Object item) {
		this.getList().add((T) item);
		resetGroup();
		this.notifyDataSetChanged();
	}

	@SuppressWarnings("unchecked")
	public void add(int ind, Object item) {
		this.getList().add(ind, (T) item);
		resetGroup();
		this.notifyDataSetChanged();
	}

	public void remove(Object item) {
		this.getList().remove(item);
		this.notifyDataSetChanged();
	}

	public void remove(int posion) {
		this.getList().remove(posion);
		this.notifyDataSetChanged();
	}

	public void clear() {
		this.getList().clear();
		this.nowAnimiCurry = 0;
		this.notifyDataSetChanged();
	}

	public void clearNoChange() {
		this.getList().clear();
		this.nowAnimiCurry = 0;
	}

	public MAdapter(Context context, List<T> list) {
		this.context = context;
		layoutInflater = LayoutInflater.from(this.context);
		this.list.addAll(list);
	}

	public int getCount() {
		return list.size();
	}

	public T getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return 99900000 + position;
	}

	public View createView() {
		return createView(this.resoure);
	}

	public View createView(int resoure) {
		if (resoure == 0) {
			return null;
		}
		View view = layoutInflater.inflate(resoure, null);
		if (view instanceof MAdapterView) {
			MAdapterView mv = (MAdapterView) view;
			mv.init();
		} else {
			try {
				Method met = view.getClass().getMethod("init");
				met.invoke(view);
			} catch (Exception e) {
			}
		}
		return view;
	}

	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		if (onNotifyChangedListener != null) {
			onNotifyChangedListener.onNotifyChanged(this);
		}
	}

	public void resetGroup() {

	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	public LayoutInflater getLayoutInflater() {
		return layoutInflater;
	}

	public List<T> getList() {
		return list;
	}

	public int getResoure() {
		return resoure;
	}

	public T get(int ind) {
		return list.get(ind);
	}

	public void setOnNotifyChangedListener(
			OnNotifyChangedListener<T> onNotifyChangedListener) {
		this.onNotifyChangedListener = onNotifyChangedListener;
	}

	public interface OnNotifyChangedListener<T> {
		public void onNotifyChanged(MAdapter<T> adapter);
	}
}
