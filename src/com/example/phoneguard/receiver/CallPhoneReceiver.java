package com.example.phoneguard.receiver;

import com.example.phoneguard.LostProtectedActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CallPhoneReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String outPhoneNumber = this.getResultData();
		if(outPhoneNumber.equals("1314"))
		{
			Intent intent2 = new Intent(context,LostProtectedActivity.class);
			intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);/*�������Ҫ�����û����һ�䣬�Ǿͻᱨ����һ������Ϊ��������һ��Receiver��������һ��activity�ģ���activity�����������Ƿŵ�һ��ջ����ģ�
            ��Receiver����û���Ǹ�ջ����������Ҫ����������һ��activity���Ǿͱ���Ҫָ�����д�����*/
			context.startActivity(intent2);
			setResultData(null);////���д����ǰѹ㲥����������Ϊnull�������Ͳ���Ѹո��Ǹ����벦���ȥ����ֻ���������ǵ�activity
			
		}
	}
	
}
