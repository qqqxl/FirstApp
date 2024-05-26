package com.example.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RateActivity extends AppCompatActivity {
    private static final String TAG = "Rate";

    TextView show;
    private float dollarRate = 0.1f;
    private float euroRate = 0.05f;
    private float wonRate = 500f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rate);
        show = findViewById(R.id.rmb_show);

        //加载之前设定的汇率数据<-sharepreferences
        SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        dollarRate=sharedPreferences.getFloat("dollar_rate",0.1f);
        euroRate=sharedPreferences.getFloat("euro_rate",0.2f);
        wonRate=sharedPreferences.getFloat("won_rate",5f);
        Log.i(TAG,"onCreat:get from sp dollarRate="+dollarRate);
        Log.i(TAG,"onCreat:get from sp euroRate="+euroRate);
        Log.i(TAG,"onCreat:get from sp wonRate="+wonRate);
    }

    public void click(View btn) {
        //获取输入数据；
        EditText input = findViewById(R.id.rmb);
        String inpStr = input.getText().toString();
        try {
            float rmb = Float.parseFloat(inpStr);
            float result = 0.0f;//进行计算

            if (btn.getId() == R.id.btn_dollar) {

                result = rmb * dollarRate;
            } else if (btn.getId() == R.id.btn_euro) {

                result = rmb * euroRate;
            } else if (btn.getId() == R.id.btn_won) {

                result = rmb * wonRate;
            }
            //显示输出
            show.setText(String.valueOf(result));
        } catch (NumberFormatException ex) {
            Toast.makeText(this, "请输入正确数据", Toast.LENGTH_LONG).show();
        }
    }

    public void clickOpen(View btn) {
        openConfigActivity();
    }

    private void openConfigActivity() {
        //打开新的窗口
        Intent config = new Intent(this, ConfigActivity.class);
        //传递参数；
        config.putExtra("dollar_rate_key", dollarRate);
        config.putExtra("euro_rate_key", euroRate);
        config.putExtra("won_rate_key", wonRate);

        Log.i(TAG, "clickOpen:dollarRate=" + dollarRate);
        Log.i(TAG, "clickOpen:euroRate=" + euroRate);
        Log.i(TAG, "clickOpen:wonRate=" + wonRate);

        //startActivity(config);
        startActivityForResult(config, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 3 && resultCode == 6) {
            //汇率修改页面返回的数据，说明data中包含bundle对象
            Bundle bdl2 = data.getExtras();
            //拆分放入的数据
            dollarRate = bdl2.getFloat("dollar_key2", 0f);
            euroRate = bdl2.getFloat("euro_key2", 0f);
            wonRate = bdl2.getFloat("won_key2", 0f);

            Log.i(TAG, "onActivityResult: dollarRate=" + dollarRate);
            Log.i(TAG, "onActivityResult: euroRate=" + euroRate);
            Log.i(TAG, "onActivityResult: wonRate=" + wonRate);

            //保存新的数据到——>sharepreferences
            SharedPreferences sp=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor= sp.edit();
            editor.putFloat("dollar_rate",dollarRate);
            editor.putFloat("euro_rate",euroRate);
            editor.putFloat("won_rate",wonRate);
            editor.apply();
            Log.i(TAG,"onActivityResult: save to sp");
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;//说明当前页面具有菜单功能
    }

   @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId()==R.id.menu_set){
          openConfigActivity();
        }
        return super.onOptionsItemSelected(item);
    }

}

