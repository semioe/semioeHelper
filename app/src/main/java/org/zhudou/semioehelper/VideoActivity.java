package org.zhudou.semioehelper;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class VideoActivity extends AppCompatActivity {
    VideoView video=null;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(VideoActivity.this)
                            //.setIcon(R.drawable.ic_launcher)
                            .setTitle("播放已经结束")
                            .setMessage("是否重新播放一遍？")
                            .setPositiveButton("重新播放", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    video.start();
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("返回菜单", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    Intent intent=new Intent();
                                    ComponentName component = new ComponentName(VideoActivity.this, MainActivity.class);
                                    intent.setComponent(component);
                                    intent.putExtra("from","VideoActivity");
                                    startActivity(intent);
                                    dialog.dismiss();
                                }
                            });
                    builder1.create().show();
                    //
                    break;
                case 2:
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(VideoActivity.this)
                            //.setIcon(R.drawable.ic_launcher)
                            .setTitle("系统提示")
                            .setMessage("确定退出播放器？")
                            .setPositiveButton("不退出", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("返回菜单", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                    Intent intent=new Intent();
                                    ComponentName component = new ComponentName(VideoActivity.this, MainActivity.class);
                                    intent.setComponent(component);
                                    intent.putExtra("from","VideoActivity");
                                    startActivity(intent);
                                    dialog.dismiss();
                                }
                            });
                    builder2.create().show();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_video);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        setTitle("semioe 健康一体机使用教程");
        video = (VideoView) findViewById(R.id.video_player);
        MediaController mediaController = new MediaController(this);
        Uri uri = null;

        //

        //

        if (name.equals("SaO2")) {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.testing_sa02);
            setTitle("semioe 健康一体机使用教程之如何测量血氧");
        }
        if (name.equals("GLU")) {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.testing_glu);
            setTitle("semioe 健康一体机使用教程之如何测量血糖");
        }
        if (name.equals("BP")) {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.testing_bp);
            setTitle("semioe 健康一体机使用教程之如何测量血压");
        }
        if (name.equals("SYNC")) {
            uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.testing_sync);
            setTitle("semioe 健康一体机使用教程之如何同步数据");
        }
        /*
        switch (name){
            case "SaO2":{
                uri = Uri.parse("android.resource://" + getPackageName() + "/"+ R.raw.testing_sa02);
                /*
                File file=new File("/mnt/sdcard/通话录音/1.mp4");
                if(file.exists()){
                    //VideoView与MediaController进行关联
                    video.setVideoPath(file.getAbsolutePath());
                }
                setTitle("semioe 健康一体机使用教程之如何测量血氧");
            }
            case "GLU":{
                uri = Uri.parse("android.resource://" + getPackageName() + "/"+ R.raw.testing_glu);
                setTitle("semioe 健康一体机使用教程之如何测量血糖");
            }
            case "BP":{
                uri = Uri.parse("android.resource://" + getPackageName() + "/"+ R.raw.testing_bp);
                setTitle("semioe 健康一体机使用教程之如何测量血压");
            }
            case "SYNC":{
                uri = Uri.parse("android.resource://" + getPackageName() + "/"+ R.raw.testing_sync);
                setTitle("semioe 健康一体机使用教程之如何同步数据");
            }
        }*/

        video.setVideoURI(uri);
        video.setMediaController(mediaController);
        mediaController.setMediaPlayer(video);
        //让VideiView获取焦点
        video.requestFocus();
        video.start();
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //播放结束后的动作
                /*
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //更新UI
                        //

                    }
                });*/
                //Toast.makeText(getBaseContext(), "end", Toast.LENGTH_LONG).show();
                Message message = mHandler.obtainMessage();
                message.what = 1;
                message.sendToTarget();
                //
            }
        });
        /*
        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(!video.isPlaying()){

                }
            }
        })).start();*/
    }
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            Message message = mHandler.obtainMessage();
            message.what = 2;
            message.sendToTarget();
        }
        return false;//不执行父类点击事件
        //return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }
}
