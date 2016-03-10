package com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class Dispatch extends Activity{
	
	private final String tag = Dispatch.class.getSimpleName();
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(tag, "Dispatch start");
		super.onCreate(savedInstanceState);		
		setTheme(android.R.style.Theme_NoDisplay);
		parseIntent();	
		finish();
	}

	private void parseIntent() {
		Intent i = getIntent();
		if (i == null || TextUtils.isEmpty(i.getAction())){
			Log.i(tag, "调用参数错误");
			Toast.makeText(this, "调用参数错误", Toast.LENGTH_LONG).show();
			return;
		}
		if (!i.getAction().equalsIgnoreCase("com.test.player")){
			Log.i(tag, "调用参数错误");
			Toast.makeText(this, "调用参数错误", Toast.LENGTH_LONG).show();
			return;
		}
		
		Constant.playUrl = (i.getStringExtra("playUrl") != null && i.getStringExtra("playUrl") != "") ? i.getStringExtra("playUrl") : "" ;
		Log.i(tag, "playUrl = " + Constant.playUrl);
		
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setClass(getBaseContext(), VideoPlayerActivity.class);
		startActivity(intent);
	}

}
