package com.work.xuance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

public class FileFather implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int count;
	private String name;
	private String first_img;
	private List<FileSon> mFileSons = new ArrayList<FileFather.FileSon>();

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirst_img() {
		return first_img;
	}

	public void setFirst_img(String first_img) {
		this.first_img = first_img;
	}

	public List<FileSon> getmFileSons() {
		return mFileSons;
	}

	public void setmFileSons(List<FileSon> mFileSons) {
		this.mFileSons = mFileSons;
	}

	public static class FileSon implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public String img = "";
		private String imageId = "";
		private String title = "";
		private String subtitle = "";
		private String title_beifen = "";
		private String subtitle_beifen = "";
		private String path = "";
		private int diji;
		private boolean isChoose = false;

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getTitle_beifen() {
			return title_beifen;
		}

		public void setTitle_beifen(String title_beifen) {
			this.title_beifen = title_beifen;
		}

		public String getSubtitle_beifen() {
			return subtitle_beifen;
		}

		public void setSubtitle_beifen(String subtitle_beifen) {
			this.subtitle_beifen = subtitle_beifen;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getSubtitle() {
			return subtitle;
		}

		public void setSubtitle(String subtitle) {
			this.subtitle = subtitle;
		}

		public String getImageId() {
			return imageId;
		}

		public void setImageId(String imageId) {
			this.imageId = imageId;
		}

		public boolean isChoose() {
			return isChoose;
		}

		public void setChoose(boolean isChoose) {
			this.isChoose = isChoose;
		}

		public String getImg() {
			if (!TextUtils.isEmpty(path)) {
				return "File:" + path;
			}
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public int getDiji() {
			return diji;
		}

		public void setDiji(int diji) {
			this.diji = diji;
		}

		public FileSon(String img, int diji, boolean isChoose, String imageId,
				String path) {
			super();
			this.img = img;
			this.diji = diji;
			this.isChoose = isChoose;
			this.imageId = imageId;
			this.path = path;
		}

	}

}
