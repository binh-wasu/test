package com.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnGenericMotionListener;
import android.view.View.OnHoverListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.View.OnTouchListener;
import android.widget.MediaController;
import android.widget.VideoView;

@SuppressLint({ "NewApi", "ClickableViewAccessibility" })
public class VideoPlayerActivity extends Activity implements
		OnPreparedListener,
		OnCompletionListener,
		OnKeyListener,
		OnFocusChangeListener,
		OnErrorListener,
		OnClickListener,
		OnLongClickListener,
		OnTouchListener,
		OnDragListener,
		OnGenericMotionListener,
		OnHoverListener,
		OnInfoListener,
		OnSystemUiVisibilityChangeListener {
	
			private final String tag = VideoPlayerActivity.class.getSimpleName();
			
			boolean isok = false;	
			public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;
			VideoView videoView;		
			Handler handler = new Handler();		
			public int flag = 0;
						
			@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				flag = View.SYSTEM_UI_FLAG_VISIBLE | View.SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
				this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);
				setContentView(R.layout.activity_video_player);
				this.init();
	}
			
	protected void init() {
		videoView = (VideoView) this.findViewById(R.id.videoView);
		videoView.setOnPreparedListener(this);
		videoView.setOnCompletionListener(this);
		videoView.setOnErrorListener(this);
		videoView.setOnFocusChangeListener(this);
		videoView.setOnKeyListener(this);
		videoView.setOnClickListener(this);
		videoView.setOnLongClickListener(this);
		videoView.setOnTouchListener(this);
		videoView.setOnDragListener(this);
		videoView.setOnGenericMotionListener(this);
		videoView.setOnHoverListener(this);
		videoView.setOnClickListener(this);
		videoView.setOnSystemUiVisibilityChangeListener(this);
		videoView.setVideoURI(Uri.parse(Constant.playUrl));
		videoView.setMediaController(new MediaController(this));
		videoView.requestFocus();
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		Log.i(tag, "onPrepared");
		Log.i(tag, "videoView.getDuration() = "+videoView.getDuration());
		videoView.start();
		Log.i(tag, "start()");
		// videoView1.seekTo(200);
		handler.postDelayed(r, 0);
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		Log.i(tag, "onCompletion");
		// this.finish();
		videoView.stopPlayback();
		new AlertDialog.Builder(this)
		.setTitle("提示")
		.setMessage("播放完成!")
		.setPositiveButton("退出", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		})
		.setNegativeButton("重播", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				init();				
			}
		})
		.create().show();	
	}

	Runnable r = new Runnable() {
		@Override
		public void run() {
			Log.i(tag, "getCurrentPosition() = "+videoView.getCurrentPosition());
			if (!isok && videoView.getDuration() > 10) {
				isok = true;  
				}
			handler.postDelayed(r, 1000);
		}
	};

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
//		Log.i(tag, "onKey = " + keyCode);
//		Toast.makeText(this, "keycode：" + keyCode, Toast.LENGTH_SHORT).show();
		return false;
	}

	@Override
	public void onSystemUiVisibilityChange(int visibility) {
		Log.i(tag, "onSystemUiVisibilityChange");
	}
	
	@Override
	public boolean onInfo(MediaPlayer arg0, int arg1, int arg2) {
		Log.i(tag, "onInfo");
		return false;
	}
	
	@Override
	public boolean onHover(View v, MotionEvent event) {
		Log.i(tag, "onHover");
		return false;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.i(tag, "onKeyDown = " + keyCode );
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		Log.i(tag, "onKeyLongPress = " + keyCode );
		return super.onKeyLongPress(keyCode, event);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		Log.i(tag, "onKeyUp = " + keyCode );
		if(keyCode == 4){
			exitApplication();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	
	private void exitApplication() {
		videoView.pause();
		Log.i(tag, "pause()");
		new AlertDialog.Builder(this)
				.setTitle("提示")
				.setMessage("是否退出播放？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,int which) {
						videoView.stopPlayback();
						Log.i(tag, "stopPlayback()");
						android.os.Process.killProcess(android.os.Process.myPid());
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,int which) {
						videoView.start();
						Log.i(tag, "start()");
					}
				}).show();
	}
	
	@Override
	public boolean onGenericMotion(View v, MotionEvent event) {
		Log.i(tag, "onGenericMotion");
		return false;
	}
	
	@Override
	public boolean onDrag(View v, DragEvent event) {
		Log.i(tag, "onDrag");
		return false;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.i(tag, "onTouch");
		return false;
	}
	
	@Override
	public boolean onLongClick(View v) {
		Log.i(tag, "onLongClick");
		return false;
	}
	
	@Override
	public void onClick(View v) {
		Log.i(tag, "onClick");
	}
	
	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		Log.i(tag, "onError = " +String.valueOf(what));	
		new AlertDialog.Builder(this)
		.setTitle("提示")
		.setMessage("播放器异常，错误码：    "+String.valueOf(what))
		.setPositiveButton("退出", 
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						android.os.Process.killProcess(android.os.Process.myPid());
						}
			}
		)
		.setNegativeButton("重试", 
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						init();
						}
					}
		)
		.create().show();		
		return true;
	}
	
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		Log.i(tag, "onFocusChange");
	}
}