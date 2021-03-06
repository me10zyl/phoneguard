package com.example.phoneguard.update;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

public class UpdateInfoParser {

	public static UpdateInfo getUpdateInfo(InputStream is) throws XmlPullParserException, IOException  {
		// TODO Auto-generated method stub
		UpdateInfo info = new UpdateInfo();
		XmlPullParser xmlPullParser = Xml.newPullParser();
		xmlPullParser.setInput(is, "utf-8");
		int type = xmlPullParser.getEventType();
		while (type != XmlPullParser.END_DOCUMENT) {
			switch (type) {
			case XmlPullParser.START_TAG:
				if (xmlPullParser.getName().equals("version")) {
					info.setVersion(xmlPullParser.nextText());
				} else if (xmlPullParser.getName().equals("description")) {
					info.setDescription(xmlPullParser.nextText());
				} else if (xmlPullParser.getName().equals("apkurl")) {
					info.setUrl(xmlPullParser.nextText());
				}
				break;
			default:
				break;
			}
			type = xmlPullParser.next();
		}
		Log.e("security",info.toString());
		return info;
	}

}
