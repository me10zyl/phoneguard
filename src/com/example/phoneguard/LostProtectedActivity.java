package com.example.phoneguard;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.utils.MD5Encoder;

public class LostProtectedActivity extends Activity implements OnClickListener {
	private Dialog dialog;
	private EditText password;
	private EditText confirmPassword;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
		if (isSetPassword()) {
			showLoginDialog();
		} else {
			showFirstDialog();
		}
	}

	private void showFirstDialog() {
		// TODO Auto-generated method stub
		dialog = new Dialog(this, R.style.MyDialog);
		// View ÿ��Ҫʹ�õ�ʱ��Ҫ����һ��
		View view = View.inflate(this, R.layout.first_dialog, null);
		password = (EditText) view
				.findViewById(R.id.et_protected_first_password);
		confirmPassword = (EditText) view.findViewById(R.id.et_protected_confirm_password);
		Button yes = (Button) view.findViewById(R.id.bt_protected_first_yes);
		Button no = (Button) view.findViewById(R.id.bt_protected_first_no);
		yes.setOnClickListener(this);
		no.setOnClickListener(this);
		dialog.setContentView(view);
		dialog.show();
	}

	private void showLoginDialog() {
		// TODO Auto-generated method stub
		dialog = new Dialog(this, R.style.AppTheme);
		View view = View.inflate(this, R.layout.login_dialog, null);
		password = (EditText) view.findViewById(R.id.et_protected_password);
		Button yes = (Button) view.findViewById(R.id.bt_protected_login_yes);
		Button cancel = (Button) view.findViewById(R.id.bt_protected_login_no);
		yes.setOnClickListener(this);
		cancel.setOnClickListener(this);
		dialog.setContentView(view);
		dialog.show();
	}

	private boolean isSetPassword() {
		 String pwd = sharedPreferences.getString("password", "");
         if(pwd.equals("") || pwd == null)
         {
                 return false;
         }
         return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 switch(v.getId())
         {
                 case R.id.bt_protected_first_yes : 
                         String fp = password.getText().toString().trim();
                         String cp = confirmPassword.getText().toString().trim();
                         if(fp.equals("") || cp.equals(""))
                         {
                                 Toast.makeText(this, "���벻��Ϊ��", Toast.LENGTH_SHORT).show();
                                 return;
                         }
                         else 
                         {
                                 if(fp.equals(cp))
                                 {
                                         Editor editor = sharedPreferences.edit();
                                         editor.putString("password", MD5Encoder.encode(fp));
                                         editor.commit();
                                 }
                                 else
                                 {
                                         Toast.makeText(this, "�������벻��ͬ", Toast.LENGTH_SHORT).show();
                                         return;
                                 }
                         }
                         dialog.dismiss();
                         break;
                         
                 case R.id.bt_protected_first_no : 
                         dialog.dismiss();
                         finish();
                         break;
                         
                 case R.id.bt_protected_login_yes : 
                         String pwd = password.getText().toString().toString();
                         if(pwd.equals(""))
                         {
                                 Toast.makeText(this, "����������", Toast.LENGTH_SHORT).show();
                         }
                         else
                         {
                                 String str = sharedPreferences.getString("password", "");
                                 if(MD5Encoder.encode(pwd).equals(str))
                                 {
                                         dialog.dismiss();
                                 }
                                 else
                                 {
                                         Toast.makeText(this, "�������", Toast.LENGTH_SHORT).show();
                                 }
                         }
                         break;
                         
                 case R.id.bt_protected_login_no : 
                         dialog.dismiss();
                         finish();
                         break;
                         
                 default : 
                         break;
         }
	}

}
