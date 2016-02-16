package com.work.xuance.frg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Thumbnails;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.mdx.framework.dialog.PhotoShow;
import com.work.xuance.R;

@SuppressLint("HandlerLeak")
public class ImageViewFileListActivity extends BaseFrg implements
		OnItemClickListener {

	private ArrayList<String> imagePathList = null;

	private ArrayList<String> bitMapList = null;

	private HashMap<String, String> imageMap = new HashMap<String, String>();

	@Override
	protected void create(Bundle savedInstanceState) {
		setContentView(R.layout.frg_loading);
		initView();
	}

	private void initView() {
		imagePathList = new ArrayList<String>();
		bitMapList = new ArrayList<String>();
		// new Thread(new GetImageFilePathThread()).start();
		// loader = new AsyncImageListLoader(this);
		// loader.execute(Executors.newCachedThreadPool());
		getPhotoThumbnail();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	}

	Handler handler = new Handler() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void handleMessage(Message msg) {
			HashMap<String, String> map = (HashMap<String, String>) msg.obj;
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				// System.out.println("没有暂停");
				Map.Entry entry = (Map.Entry) it.next();
				String filePath = (String) entry.getKey();
				String bitmapPath = (String) entry.getValue();
				Log.i(filePath, bitmapPath);
				// 把图片添加到适配器里面，以便调整图片的属性
				// adapter.addData(filePath, bitmapPath);
				// adapter.notifyDataSetChanged();
			}
			map.clear();
			super.handleMessage(msg);
		}
	};

	private class GetImageFilePathThread implements Runnable {
		@Override
		public void run() {
			File file = new File(Environment.getExternalStorageDirectory() + "");
			getFileList(file);
		}

	}

	public static List<GalleryEntity> queryGallery(Activity context) {
		List<GalleryEntity> galleryList = new ArrayList<GalleryEntity>();
		ContentResolver cr = context.getContentResolver();
		String[] columns = { Images.Media._ID, Images.Media.DATA,
				Images.Media.BUCKET_ID, Images.Media.BUCKET_DISPLAY_NAME,
				"COUNT(1) AS count" };
		String selection = "0==0) GROUP BY (" + Images.Media.BUCKET_ID;
		String sortOrder = Images.Media.DATE_MODIFIED;
		Cursor cur = cr.query(Images.Media.EXTERNAL_CONTENT_URI, columns,
				selection, null, sortOrder);
		if (cur.moveToFirst()) {

			int id_column = cur.getColumnIndex(Images.Media._ID);
			int image_id_column = cur.getColumnIndex(Images.Media.DATA);
			int bucket_id_column = cur.getColumnIndex(Images.Media.BUCKET_ID);
			int bucket_name_column = cur
					.getColumnIndex(Images.Media.BUCKET_DISPLAY_NAME);
			int count_column = cur.getColumnIndex("count");

			do {
				// Get the field values
				int id = cur.getInt(id_column);
				String image_path = cur.getString(image_id_column);
				int bucket_id = cur.getInt(bucket_id_column);
				String bucket_name = cur.getString(bucket_name_column);
				int count = cur.getInt(count_column);
				// Do something with the values.
				GalleryEntity gallery = new GalleryEntity();
				// gallery.setId(id);
				// gallery.setPath(image_path);
				// gallery.setGallery_id(bucket_id);
				// gallery.setGallery_name(bucket_name);
				// gallery.setCount(count);
				Log.i(bucket_name, count + "");
				galleryList.add(gallery);
			} while (cur.moveToNext());
		}
		return galleryList;
	}

	private void getPhotoThumbnail() {
		// 查询的列
		String[] projection = new String[] { MediaStore.Images.Media._ID,
				MediaStore.Images.Media.BUCKET_ID, // 直接包含该图片文件的文件夹ID，防止在不同下的文件夹重名
				MediaStore.Images.Media.BUCKET_DISPLAY_NAME, // 直接包含该图片文件的文件夹名
				MediaStore.Images.Media.DISPLAY_NAME, // 图片文件名
				MediaStore.Images.Media.DATA, // 图片绝对路径
				"count(" + MediaStore.Images.Media._ID + ")"// 统计当前文件夹下共有多少张图片
		};
		// 这种写法是为了进行分组查询，详情可参考http://yelinsen.iteye.com/blog/836935
		String selection = " 0==0) group by bucket_display_name --(";

		ContentResolver cr = getActivity().getContentResolver();
		Cursor cursor = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				projection, selection, null, "");
//		PhotoFolder pf = null;
		while (cursor.moveToNext()) {
			String folderId = cursor.getString(cursor
					.getColumnIndex(MediaStore.Images.Media.BUCKET_ID));
			String folder = cursor
					.getString(cursor
							.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
			long fileId = cursor.getLong(cursor
					.getColumnIndex(MediaStore.Images.Media._ID));
			String finaName = cursor.getString(cursor
					.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
			String path = cursor.getString(cursor
					.getColumnIndex(MediaStore.Images.Media.DATA));
			int count = cursor.getInt(5);// 该文件夹下一共有多少张图片
			BitmapFactory.Options options = new BitmapFactory.Options();
			// Thumbnails.getThumbnail(cr, fileId, Thumbnails.MICRO_KIND,
			// options)获取指定图片缩略片
//			pf = new PhotoFolder(folderId, folder, count,
//					Thumbnails.getThumbnail(cr, fileId, Thumbnails.MICRO_KIND,
//							options));
//			list.add(pf);
			Log.i(finaName, count+"");
		}
		if (null != cursor && !cursor.isClosed()) {
			cursor.close();
		}
	}

	private void getFileList(File file) {
		File[] files = file.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isFile()) {
					if (".png".equals(getFileEx(f))) {
						if (!imageMap.containsKey(file.getAbsolutePath())) {
							imageMap.put(file.getAbsolutePath(),
									f.getAbsolutePath());
							HashMap<String, String> temp = new HashMap<String, String>();
							temp.put(file.getAbsolutePath(),
									f.getAbsolutePath());
							Message msg = handler.obtainMessage();
							msg.obj = temp;
							handler.sendMessage(msg);
						}
					}

					if (".jpg".equals(getFileEx(f))) {
						if (!imageMap.containsKey(file.getAbsolutePath())) {

							imageMap.put(file.getAbsolutePath(),
									f.getAbsolutePath());
							HashMap<String, String> temp = new HashMap<String, String>();
							temp.put(file.getAbsolutePath(),
									f.getAbsolutePath());
							Message msg = handler.obtainMessage();
							msg.obj = temp;
							handler.sendMessage(msg);
						}
					}
				} else {
					getFileList(f);
				}
			}
		}
	}

	public String getFileEx(File file) {
		String fileName = file.getName();
		int index = fileName.indexOf('.');
		if (index != -1) {
			int length = fileName.length();
			String str = fileName.substring(index, length);
			return str;
		}
		return "";
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

			System.out.println("---------------------One------------");

		} else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

			System.out.println("---------------Two------------");

		}
	}

}