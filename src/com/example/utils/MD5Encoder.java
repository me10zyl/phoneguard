package com.example.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException; 

	public class MD5Encoder
	{
	        public static String encode(String pwd)
	        {
	                try
	                {
	                        MessageDigest messageDigest = MessageDigest.getInstance("MD5");//�õ�MD5���ܵĶ���
	                        byte[] bytes = messageDigest.digest(pwd.getBytes());//����һ�����ܺ���ֽ�����
	                        StringBuffer sb = new StringBuffer();
	                        String tmp;
	                        for(int i = 0; i < bytes.length; i++)
	                        {
	                                tmp = Integer.toHexString(0xff & bytes[i]);//���ֽ�ת��Ϊ16���Ƶ��ַ���
	                                if(tmp.length() == 1)        //�������ַ�����ֻ��һ���ַ�����Ҫ��0
	                                {
	                                        sb.append("0" + tmp);
	                                }
	                                else
	                                {
	                                        sb.append(tmp);
	                                }
	                        }
	                        return sb.toString();
	                }
	                catch (NoSuchAlgorithmException e)
	                {
	                        throw new RuntimeException("û����������㷨" + e);
	                }
	        }

	}

