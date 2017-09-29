package org.zhudou.semioehelper;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_main);
        setTitle("semioe 健康一体机使用教程");

        //血氧
        Button button_SaO2=(Button)findViewById(R.id.button_SaO2);
        button_SaO2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                ComponentName component = new ComponentName(MainActivity.this, VideoActivity.class);
                intent.setComponent(component);
                intent.putExtra("from","MainActivity");
                intent.putExtra("name","SaO2");
                startActivity(intent);
            }
        });

        //血糖
        Button button_GLU=(Button)findViewById(R.id.button_GLU);
        button_GLU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                ComponentName component = new ComponentName(MainActivity.this, VideoActivity.class);
                intent.setComponent(component);
                intent.putExtra("from","MainActivity");
                intent.putExtra("name","GLU");
                startActivity(intent);
            }
        });

        //血压
        Button button_BP=(Button)findViewById(R.id.button_BP);
        button_BP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                ComponentName component = new ComponentName(MainActivity.this, VideoActivity.class);
                intent.setComponent(component);
                intent.putExtra("from","MainActivity");
                intent.putExtra("name","BP");
                startActivity(intent);
            }
        });



        //同步数据
        Button button_SYNC=(Button)findViewById(R.id.button_SYNC);
        button_SYNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                ComponentName component = new ComponentName(MainActivity.this, VideoActivity.class);
                intent.setComponent(component);
                intent.putExtra("from","MainActivity");
                intent.putExtra("name","SYNC");
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK)
            return true;//不执行父类点击事件
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }
}
