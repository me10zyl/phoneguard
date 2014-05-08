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
	  private static final int[] ICONS = new int[] {R.drawable.greenhook, R.drawable.purpleandroid, R.drawable.bluemisc, 
          R.drawable.purplecomputer, R.drawable.purplecomputer, R.drawable.purplecomputer, R.drawable.greenplugin, 
          R.drawable.graytools, R.drawable.bluesettings};
	  
	  private static ImageView imageView;
	  private static TextView textView;
	  
	  private Context context;
	  private LayoutInflater inflater ;
	  private SharedPreferences sharedPreferences;
	 public MainUIAdapter(Context context) {
		// TODO Auto-generated constructor stub
		 this.context = context;
		 inflater = LayoutInflater.from(this.context);
		 sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
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
		View view = inflater.inflate(R.layout.main_item, null);
        imageView = (ImageView) view.findViewById(R.id.iv_main_icon); 
        textView = (TextView) view.findViewById(R.id.tv_main_name);
        imageView.setImageResource(ICONS[position]);
        textView.setText(NAMES[position]);
        if(position == 0)
        {
                String name = sharedPreferences.getString("lostName", "");
                if(!name.equals(""))
                {
                        textView.setText(name);
                }
        }
        return view;
	}

}
