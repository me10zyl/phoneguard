package com.example.phoneguard.adapter;

import com.example.phoneguard.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainUIAdapter extends BaseAdapter{

	private static final String[] NAMES = new String[] {"手机防盗", "通讯卫士", "软件管理", "流量管理", "任务管理", "手机杀毒", 
        "系统优化", "高级工具", "设置中心"};
	  private static final int[] ICONS = new int[] {R.drawable.bluemisc, R.drawable.coners_background, R.drawable.graytools, 
          R.drawable.greenhook, R.drawable.greenplugin, R.drawable.ic_launcher, R.drawable.purpleandroid, 
          R.drawable.purplecomputer, R.drawable.bluesettings};
	  
	  private static ImageView imageView;
	  private static TextView textView;
	  
	  private Context context;
	  private LayoutInflater inflater ;
	  
	 public MainUIAdapter(Context context) {
		// TODO Auto-generated constructor stub
		 this.context = context;
		 inflater = LayoutInflater.from(this.context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return NAMES.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
