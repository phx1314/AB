package com.work.xuance.model;

import java.io.Serializable;

import com.work.xuance.model.FileFather.FileSon;

public class Model2Son implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FileSon mFileSon1;
	private FileSon mFileSon2;

	public FileSon getmFileSon1() {
		return mFileSon1;
	}

	public void setmFileSon1(FileSon mFileSon1) {
		this.mFileSon1 = mFileSon1;
	}

	public FileSon getmFileSon2() {
		return mFileSon2;
	}

	public void setmFileSon2(FileSon mFileSon2) {
		this.mFileSon2 = mFileSon2;
	}

	public Model2Son(FileSon mFileSon1, FileSon mFileSon2) {
		super();
		this.mFileSon1 = mFileSon1;
		this.mFileSon2 = mFileSon2;
	}

}
