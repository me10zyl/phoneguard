package com.example.phoneguard;

import java.io.File;
import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import com.example.phoneguard.download.DownloadTask;
import com.example.phoneguard.update.UpdateInfo;
import com.example.phoneguard.update.UpdateInfoService;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends ActionBarActivity {

	private static final int ConnectError = 0;
	protected static final int SHOW_UPDATE_DIALOG = 1;
	private static final int INSTALL_FAILED = 2;
	private UpdateInfo updateInfo;
	private CharSequence version;
	private ProgressDialog progressDialog;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (msg.what == ConnectError) {
				Toast.makeText(SplashActivity.this, "获取版本信息错误，请稍后再试",
						Toast.LENGTH_SHORT).show();
				turnToMainUI();
			}
			if(msg.what == SHOW_UPDATE_DIALOG) {
				showUpdateDialog();
			}
			if(msg.what == INSTALL_FAILED)
			{
				Toast.makeText(SplashActivity.this, "获取更新信息失败", Toast.LENGTH_SHORT).show();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		TextView tv_version = (TextView) findViewById(R.id.label_version);
		version = getVersion();
		tv_version.setText("version:" + version);
		LinearLayout ll = (LinearLayout) findViewById(R.id.layout_splash);
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation.setDuration(2000);
		ll.startAnimation(alphaAnimation);
		
		progressDialog = new ProgressDialog(this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMessage("正在下载...");
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				if (isNeedToUpdate()) {
					Message message = new Message();
					message.what = SplashActivity.this.SHOW_UPDATE_DIALOG;
					handler.sendMessage(message);
					Log.e("security", "需要更新");
				}
			}
		}).start();
//		turnToMainUI();
	}
	/**
	 * 显示是否更新对话框
	 */
	private void showUpdateDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("需要更新 ");
		builder.setMessage(updateInfo.getDescription());
		builder.setCancelable(false);
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
				{
					 File dir = new File(Environment.getExternalStorageDirectory(), "/mobilephonesafeguard/update");
                     if(!dir.exists())
                     {
                             dir.mkdirs();
                     }
                     String apkPath = Environment.getExternalStorageDirectory() + "/mobilephonesafeguard/update/mobilephonesafeguard.apk";
                     UpdateTask task = new UpdateTask(updateInfo.getUrl(), apkPath);
                     progressDialog.show();
                     new Thread(task).start();
				}else {
					Toast.makeText(SplashActivity.this, "SD卡不可用，请插入SD卡", Toast.LENGTH_SHORT).show();
					turnToMainUI();
				}
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				turnToMainUI();
			}
		});
		builder.create().show();
	};
	/**
	 * 与服务器比较最新文件版本
	 * @return 是否需要更新
	 */
	private boolean isNeedToUpdate() {
		// TODO Auto-generated method stub
		try {
			updateInfo = new UpdateInfoService(this)
					.getUpdateInfo(R.string.serverUrl);// 获取xml中string 标签name =
														// serverurl中的值；
			String v = updateInfo.getVersion();
			Log.e("sercurity", v);
			if (v.equals(version)) {
				// System.out.println("不用更新");
				return false;
			} else {
				// System.out.println("需要更新");
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("security", e.getMessage());
			Message message = new Message();
			message.what = SplashActivity.this.ConnectError;
			handler.sendMessage(message);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private void turnToMainUI() {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	/**
	 * 获取当前APP的版本
	 * @return
	 */
	private CharSequence getVersion() {
		try {
			PackageManager pm = this.getPackageManager();
			PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "版本号未知";
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_splash,
					container, false);
			return rootView;
		}
	}
	/**
	 * 安装新下载的文件
	 * @param file 安装的文件路径
	 */
	private void install(File file) {
		// TODO Auto-generated method stub
		 Intent intent = new Intent();
         intent.setAction(Intent.ACTION_VIEW);
         if(file == null)
         {
        	 Message msg = new Message();
        	 msg.what = this.INSTALL_FAILED;
        	 handler.sendMessage(msg);
        	 finish();
        	 return;
         }
         intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
         finish();
         startActivity(intent);
	}
	/**
	 * 下载的线程
	 * @author ZyL
	 *
	 */
	class UpdateTask implements Runnable
	{
		private String url;
		private String apkPath;
		public UpdateTask(String url, String apkPath) {
			// TODO Auto-generated constructor stub
			this.url = url;
			this.apkPath = apkPath;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			File file = DownloadTask.getFile(url, apkPath, progressDialog);//下载
			progressDialog.dismiss();
			install(file);
		}
		
		
	}

}
