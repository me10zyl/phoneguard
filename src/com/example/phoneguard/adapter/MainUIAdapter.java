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

	private static final String[] NAMES = new String[] {"�ֻ�����", "ͨѶ��ʿ", "�������", "��������", "�������", "�ֻ�ɱ��", 
        "ϵͳ�Ż�", "�߼�����", "��������"};
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
