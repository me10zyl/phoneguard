package com.example.phoneguard.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


import android.app.ProgressDialog;
import android.util.Log;

public class DownloadTask {
	public static File getFile(String url,String filePath,ProgressDialog progressDialog)
	{
		
		try {
			URL url2 = new URL(url);
			HttpURLConnection urlConnection = (HttpURLConnection) url2.openConnection();
			urlConnection.setConnectTimeout(5000);
			urlConnection.setRequestMethod("GET");
			InputStream iStream = urlConnection.getInputStream();
			progressDialog.setMax(urlConnection.getContentLength());
			File file = new File(filePath);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			int process = 0;
			while ((len = iStream.read(buffer))!=-1) {
				fos.write(buffer, 0, len);
				process += len;
				progressDialog.setProgress(process);
			}
			fos.flush();
			fos.close();
			iStream.close();
			return file;
			
		} catch (IOException e) {
			Log.e("security", e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
