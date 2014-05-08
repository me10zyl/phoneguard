package com.example.phoneguard;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LostProtectedActivity extends Activity implements OnClickListener{
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
		}else {
			showFirstDialog();  
		}
	}
	private void showFirstDialog() {
		// TODO Auto-generated method stub
	}
	
	
	private void showLoginDialog() {
		// TODO Auto-generated method stub
		
	}
	private boolean isSetPassword() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}

}
