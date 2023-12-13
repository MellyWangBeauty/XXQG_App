package com.example.xxqg;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View.OnClickListener;
import android.content.pm.ActivityInfo;
import android.widget.RelativeLayout;
import android.content.SharedPreferences;
import android.widget.RatingBar;

public class DetailActivity extends Activity implements OnClickListener{
    private SurfaceView surfaceView;
    private RatingBar ratingBar;
    private float savedRating;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;
    private Button play, pause, stop;
    private int curPosition;
    private RelativeLayout mParent;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //      获取从 MainActivity 传递过来的数据
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("url");
            String title = extras.getString("title");

            // 在 DetailActivity 中找到对应的视图
            TextView detailTitleTextView = findViewById(R.id.detailTitleTextView);
            // 设置数据到对应的视图中
            detailTitleTextView.setText(title);
        }

        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // 保存评分值到 SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putFloat("rating", rating);
                editor.apply();
            }});
        // 从 SharedPreferences 中读取上次保存的评分值
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        savedRating = sharedPreferences.getFloat("rating", 0.0f);
        // 设置上次保存的评分值到 RatingBar
        ratingBar.setRating(savedRating);

        surfaceView = findViewById(R.id.surfaceView1);
        // 创建SurfaceHolder对象
        surfaceHolder = surfaceView.getHolder();

        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.stop);
        pause = (Button) findViewById(R.id.pause);
        mParent = findViewById(R.id.test_parent_play);
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        pause.setOnClickListener(this);
    }

    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        // 保存评分值到 SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("rating", rating);
        editor.apply();
    }

    //改变视频的尺寸自适应。
    public void changeVideoSize() {
        int videoWidth = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();

        int surfaceWidth = surfaceView.getWidth();
        int surfaceHeight = surfaceView.getHeight();

        //根据视频尺寸去计算->视频可以在sufaceView中放大的最大倍数。
        float max;
        if (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //竖屏模式下按视频宽度计算放大倍数值
            max = Math.max((float) videoWidth / (float) surfaceWidth, (float) videoHeight / (float) surfaceHeight);
        } else {
            //横屏模式下按视频高度计算放大倍数值
            max = Math.max(((float) videoWidth / (float) surfaceHeight), (float) videoHeight / (float) surfaceWidth);
        }

        //视频宽高分别/最大倍数值 计算出放大后的视频尺寸
        videoWidth = (int) Math.ceil((float) videoWidth / max);
        videoHeight = (int) Math.ceil((float) videoHeight / max);

        //无法直接设置视频尺寸，将计算出的视频尺寸设置到surfaceView 让视频自动填充。
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(videoWidth, videoHeight);
        params.addRule(RelativeLayout.CENTER_VERTICAL, mParent.getId());
        surfaceView.setLayoutParams(params);
    }

    @Override
    public void onClick(View arg0) {
        switch (getResources().getResourceEntryName(arg0.getId())) {
            case "play": {
                if (mediaPlayer != null && !mediaPlayer.isPlaying()) {  // 视频不正在播放时
                    // 继续播放
                    mediaPlayer.start();
                } else {
                    if (mediaPlayer != null) {
                        mediaPlayer.release();
                    }
                    // 创建MediaPlayer
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    // 设置mediaPlayer在surfaceHolder中播放
                    mediaPlayer.setDisplay(surfaceHolder);
                    try {
                        // 设置要播放的视频
                        mediaPlayer.setDataSource(url);
                        // 准备视频
                        mediaPlayer.prepareAsync();
                        // 设置准备完成监听器
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                // 播放视频
                                mediaPlayer.start();
                            }
                        });
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case "pause":
                // 暂停并保存当前播放进度
                if(mediaPlayer != null) {
                    mediaPlayer.pause();
                    curPosition = mediaPlayer.getCurrentPosition();
                }
                break;
            case "stop":
                // 停止播放
                if(mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
        }
    }
}

