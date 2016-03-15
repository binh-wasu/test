package com.test;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;

@SuppressLint("UseSparseArrays") public class Constant {
	
	public static Context context;
	
	public static String playUrl = "http://chyd-wsvod.wasu.tv/data11/ott/344/2014-09/22/1411356408554_579762/playlist.m3u8";
	
	public static Map<Integer, String> errorCodeMap;
	static {
		errorCodeMap = new HashMap<Integer, String>();

		errorCodeMap.put(-1001, "ERROR_NOT_CONNECTED");
		errorCodeMap.put(-1002, "ERROR_UNKNOWN_HOST");
		errorCodeMap.put(-1003, "ERROR_CANNOT_CONNECT");
		errorCodeMap.put(-1004, "ERROR_IO");
		errorCodeMap.put(-1005, "ERROR_CONNECTION_LOST");
		errorCodeMap.put(-1006, "");
		errorCodeMap.put(-1007, "ERROR_MALFORMED");
		errorCodeMap.put(-1008, "ERROR_OUT_OF_RANGE");
		errorCodeMap.put(-1009, "ERROR_BUFFER_TOO_SMALL");
		errorCodeMap.put(-1010, "ERROR_UNSUPPORTED");
		errorCodeMap.put(-1011, "ERROR_END_OF_STREAM");
		errorCodeMap.put(-1012, "INFO_FORMAT_CHANGED");
		errorCodeMap.put(-1013, "INFO_DISCONTINUITY");
		
	}

}
