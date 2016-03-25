package com.work.xuance;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.mdx.framework.Frame;
import com.mdx.framework.config.ApiConfig;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.server.api.ErrorMsg;
import com.mdx.framework.server.api.OnApiInitListener;
import com.mdx.framework.utility.Device;
import com.udows.common.proto.MPhotoList;
import com.work.xuance.instance.goReturn;
import com.work.xuance.model.FileFather;
import com.work.xuance.model.FileFather.FileSon;

public class F {
	public static String Verify = "", UserId = "", Phone = "", Password = "",
			photo_price = "", taili_price = "", mxp_price = "",
			pub_name = "AB日历素材";
	public static MPhotoList mMPhotoList;
	public static String DEVICEID = "",
			HEADURL = "",
			SHAREURL = "",
			DIMAGEURL = "",
			partnerId = "2088021951865016",
			sellerId = "411796754@qq.com",
			rsaPrivate = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANRCcd0ZW14wGf5XK1P4wMiA8YStaIRK/z3ECJc4weJlxKutjsrYdodlXwOD9KLjSLAdVrUUC8tGwV4PkWxx5lj8Zx/ksdlumGTsTaA8tn93ZdSTqkdcGZ2V74+RVgJ6Xgg+meqau9hYlxiArMy0Eww/NCM84SFoZpe1axe9Z+Q9AgMBAAECgYEAmo3nKv8TsHmF89S1D+fJODXrppg9ylZxlB/xwWrqgldn5TnzB3CmneAKVxz2PMUSYGp8gE2gaNuUYVEkqrok5sJE/r/fcGFVcRualPXUebpZYPMXF2chxqgW8ZVW53lvSPC0WPuMXSVf26x8NpMn0DV8woVUWvPub95NmMfKJWECQQD6peU07PzGRRPCtNP8wu+xpaLnqVgnBLX0/JULi892gD2OrHVA/FuOvJ0PQloQEoVUrbZm/5m3SVty//YjJdcXAkEA2Mq1XDgivKMLM7+XgKmeMuo8KZ8c92dRPMsvgI0lPKb5VovFLUHdmZZ++1oJf1EQb8x3Nc55LzJqf6G6A9FzywJBAMn+DaREZEUogA9GiRxGwMIqWQlWuNrg+f5P25Ftf6jhGjqzkt7nQKXcLLqSX2BafKbMQ5Xn4KoL7S3VRzf2E8sCQQCormkY/TDCGBbSSAQo/1FximIPfJd8RS6nn+0xGCHeJImjdpOQWEyFgd0B1BfJsZiZdL1Y53PRbmgBMSRcbi+rAkAp/rIy5kcMKhEKvEZbxLW7IqY41vhiNiK/xWz4tLTu5Gmoj8ZL6BMWQCaAcAjxuNNAsCD3/g5XhgcPjgGnAUct",
			rsaAlipayPublic = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUQnHdGVteMBn+VytT+MDIgPGErWiESv89xAiXOMHiZcSrrY7K2HaHZV8Dg/Si40iwHVa1FAvLRsFeD5FsceZY/Gcf5LHZbphk7E2gPLZ/d2XUk6pHXBmdle+PkVYCel4IPpnqmrvYWJcYgKzMtBMMPzQjPOEhaGaXtWsXvWfkPQIDAQAB",
			notifyUrl = "http://112.74.112.237/xc/mobilePayNotify.do",
			mobile = "";

	public static void init() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(Frame.CONTEXT);
		if (sp.contains("verify")) {
			Verify = sp.getString("verify", "");
			UserId = sp.getString("userid", "");
		}
		// Device.getId()
		ApiConfig.setAutoApiInitParams(new OnApiInitListener() {
			@Override
			public String[][] onApiInitListener(Object... objs) {
				return new String[][] { { "appid", BaseConfig.getAppid() },
						{ "deviceid", Device.getId() }, { "userid", F.UserId },
						{ "verify", F.Verify } };
			}
		});
	}

	public static void toLogin(Context context, ErrorMsg em) {
		// Helper.startActivity(context, FrgLogin.class, TitleAct.class);
	}

	public static void Login(String userid, String verify) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(Frame.CONTEXT);
		sp.edit().putString("verify", verify).putString("userid", userid)
				.commit();
		F.UserId = userid;
		F.Verify = verify;
	}

	// kfc 1
	// / 关闭软件盘
	public static void closeSoftKey(Activity act) {
		InputMethodManager localInputMethodManager = (InputMethodManager) act
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		IBinder localIBinder = act.getWindow().getDecorView().getWindowToken();
		localInputMethodManager.hideSoftInputFromWindow(localIBinder, 2);
	}

	// kfc 1
	// / 弹出软件盘
	public static void tanChuSoftKey(Activity act) {
		InputMethodManager imm = (InputMethodManager) act
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInputFromInputMethod(act.getWindow().getDecorView()
				.getWindowToken(), 0);
	}

	// public byte[] Bitmap2Bytes(String picpathcrop) {
	// Bitmap bm = BitmapRead.decodeSampledBitmapFromFile(picpathcrop, 50, 50);
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
	// return baos.toByteArray();
	// }

	public static byte[] bitmap2Byte(String picpathcrop) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		com.mdx.framework.utility.BitmapRead.decodeSampledBitmapFromFile(
				picpathcrop, 1024, 0).compress(Bitmap.CompressFormat.JPEG, 80,
				out);
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return out.toByteArray();
	}

	public static byte[] bitmap2Byte(String picpathcrop, int size) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		com.mdx.framework.utility.BitmapRead.decodeSampledBitmapFromFile(
				picpathcrop, size, 0).compress(Bitmap.CompressFormat.JPEG, 70,
				out);
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return out.toByteArray();
	}

	public static String go2Wei(double f) {
		return String.format("%.2f", f);
	}

	public static List<FileFather> getFileList(Context context) {
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
		ContentResolver cr = context.getContentResolver();
		Cursor cursor = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				projection, selection, null, "");
		List<FileFather> mFileFathers = new ArrayList<FileFather>();
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
			FileFather mFileFather = new FileFather();
			mFileFather.setFirst_img("File:" + path);
			mFileFather.setName(folder);
			mFileFather.setCount(count);
			addFile(mFileFather, context, folder);
			mFileFathers.add(mFileFather);

		}
		if (null != cursor && !cursor.isClosed()) {
			cursor.close();
		}
		return mFileFathers;
	}

	private static void addFile(FileFather mFileFather, Context context,
			String name) {
		String[] projection = { Media._ID, Media.DATA, Media.ORIENTATION };
		Cursor cursor = context.getContentResolver().query(
				Media.EXTERNAL_CONTENT_URI, projection,
				Media.BUCKET_DISPLAY_NAME + " = '" + name + "'", null,
				Media._ID + " desc");
		if (cursor.moveToFirst()) {
			do {
				int imageId = cursor.getInt(cursor.getColumnIndex(Media._ID));
				String imagePath = cursor.getString(cursor
						.getColumnIndex(Media.DATA));
				int orientation = cursor.getInt(cursor
						.getColumnIndex(Media.ORIENTATION));
				FileSon mFileSon = new FileSon("File:" + imagePath, 0, false,
						imageId + "", imagePath);
				mFileFather.getmFileSons().add(mFileSon);
			} while (cursor.moveToNext());
		}
	}

	public static void showImgDialog(Context context, View view,
			goReturn mgoReturn) {
		final Dialog mDialog = new Dialog(context, R.style.full_dialog);
		mDialog.setContentView(view);
		WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
		// lp.width = (int) ((MActivityActionbar) context).getWindowManager()
		// .getDefaultDisplay().getWidth();// 设置宽度
		// lp.height = (int) ((MActivityActionbar) context).getWindowManager()
		// .getDefaultDisplay().getHeight(); // 高度设置为屏幕的0.6
		// lp.gravity = Gravity.CENTER;
		// mDialog.getWindow().setAttributes(lp);
		mDialog.show();
		// mDialog.setCanceledOnTouchOutside(true);
		mgoReturn.go2Object(mDialog);
	}

	public static List<String> getList() {
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			data.add("");
		}
		return data;
	}

	public static void saveImg(final Context context, final String url,
			String name) {
		if (ExistSDCard()) {
			File destDir = new File(Environment.getExternalStorageDirectory()
					+ "/" + pub_name);
			if (!destDir.exists()) {
				destDir.mkdirs();
			}
			final File f = new File(Environment.getExternalStorageDirectory()
					+ "/" + pub_name + "/" + name + ".png");
			new Thread() {
				@Override
				public void run() {
					Bitmap bitmap = null;
					try {
						URL pictureUrl = new URL(url);
						HttpURLConnection con = (HttpURLConnection) pictureUrl
								.openConnection();
						InputStream in = con.getInputStream();
						bitmap = BitmapFactory.decodeStream(in);
						in.close();
						f.createNewFile();
						FileOutputStream fOut = null;
						fOut = new FileOutputStream(f);
						bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
						fOut.close();
						fOut.flush();
					} catch (Exception e) {
						e.printStackTrace();
						return;
					}
				}
			}.start();
		} else {
		}
	}

	public static void fileSuoFang(String w, String h) {
		// File filepath = new File(CoreConstants.FILE_PATH + "/"
		// + item.getFilePath());
		// int width = 0, height = 0;
		// if (w != null && w.length() > 0) {
		// width = Integer.valueOf(w);
		// }
		// if (h != null && h.length() > 0) {
		// height = Integer.valueOf(h);
		// }
		// if (width != 0 && item.getFileMinitype().startsWith("image")) {
		// // 检查目录
		// File tempDirectory = new File(CoreConstants.FILE_PATH + "/temp/");
		// if (!tempDirectory.isDirectory()) {
		// tempDirectory.mkdirs();
		// }
		//
		// String tf = CoreConstants.FILE_PATH + "/temp/" + item.getFileMd5()
		// + "_" + width + "x" + height + ".jpg";
		// File tempfile = new File(tf);
		// if (tempfile.exists()) {
		// filepath = tempfile;
		// } else {
		// if (height == 0) {
		// BufferedImage buf = ImageIO.read(filepath);
		// if (buf.getWidth() > width) {
		// ImageUtils.Compress(filepath, tempfile, width);
		// filepath = tempfile;
		// }
		// } else {
		// ImageUtils.Compress(filepath, tempfile, width, height);
		// filepath = tempfile;
		// }
		// }
		// }

	}

	public static boolean ExistSDCard() {

		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else
			return false;
	}
}
