package com.work.xuance.model;

import java.io.Serializable;

import com.work.xuance.model.FileFather.FileSon;

public class ModelData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FileSon mFileSon;
	private int position_father;
	private int position_son;

	public FileSon getmFileSon() {
		return mFileSon;
	}

	public void setmFileSon(FileSon mFileSon) {
		this.mFileSon = mFileSon;
	}

	public int getPosition_father() {
		return position_father;
	}

	public void setPosition_father(int position_father) {
		this.position_father = position_father;
	}

	public int getPosition_son() {
		return position_son;
	}

	public void setPosition_son(int position_son) {
		this.position_son = position_son;
	}

	public ModelData(FileSon mFileSon, int position_father, int position_son) {
		super();
		this.mFileSon = mFileSon;
		this.position_father = position_father;
		this.position_son = position_son;
	}

}
