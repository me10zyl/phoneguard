package com.example.phoneguard.update;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.util.Log;

public class UpdateInfoService {
	private Context context;

	public UpdateInfoService(Context context) {
		super();
		this.context = context;
	}
	public UpdateInfo getUpdateInfo(int urlId) throws IOException,XmlPullParserException
	{
		String path = context.getResources().getString(urlId);
		URL url = new URL(path);
		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
		httpURLConnection.setConnectTimeout(5000);
		httpURLConnection.setRequestMethod("GET");
		InputStream is = httpURLConnection.getInputStream();
		return UpdateInfoParser.getUpdateInfo(is);//½âÎöxml
	}
}
