package com.example.phoneguard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phoneguard.adapter.MainUIAdapter;

public class MainActivity extends ActionBarActivity implements
		OnItemClickListener {
	private GridView gridView;
	private MainUIAdapter adapter;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		sharedPreferences = getSharedPreferences("config", this.MODE_PRIVATE);
		gridView = (GridView) findViewById(R.id.gv_main);
		adapter = new MainUIAdapter(this);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(this);
		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, final View view,
					int position, long id) {
				  if(position == 0)        //�������Ϊ��������ǵ��ֻ������ˣ��û�һ������һ���ֻ������������϶�����ж�����ǵĳ���ģ������������ֻ��������item���棬������һ���������Ĺ���
                  {
                          AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                          builder.setTitle("����");
                          builder.setMessage("������Ҫ���Ե�����");
                          final EditText et = new EditText(MainActivity.this);
                          et.setHint("������");
                          builder.setView(et);
                          builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                          {
                                  @Override
                                  public void onClick(DialogInterface dialog, int which)
                                  {
                                          String name = et.getText().toString();
                                          if(name.equals(""))
                                          {
                                                  Toast.makeText(MainActivity.this, "�������ݲ���Ϊ��", Toast.LENGTH_SHORT).show();
                                          }
                                          else
                                          {
                                                  Editor editor = sharedPreferences.edit();
                                                  editor.putString("lostName", name);
                                                  editor.commit();
//                                                  TextView tv = (TextView) view.findViewById(R.id.tv_main_name);
//                                                  tv.setText(name);
                                                  adapter.notifyDataSetChanged();
                                          }
                                  }
                          });
                          builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
                          {
                                  @Override
                                  public void onClick(DialogInterface dialog, int which)
                                  {
                                          // TODO Auto-generated method stub
                                          
                                  }
                          });
                          builder.create().show();
                  }
				return false;
			}

		});
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0: // �ֻ�����
			Intent intent = new Intent();
			intent.setClass(this, LostProtectedActivity.class);
			startActivity(intent);
			break;
		case 1: // ͨѶ��ʿ
			break;
		case 2: // �������
			break;
		case 3: // ��������
			break;
		case 4: // �������
			break;
		case 5: // �ֻ�ɱ��
			break;
		case 6: // ϵͳ�Ż�
			break;
		case 7: // �߼�����
			break;
		case 8: // ��������
			break;
		default:
			break;
		}
	}

}
