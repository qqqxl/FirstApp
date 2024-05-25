package com.example.firstapp;

import android.content.Intent;
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
        startActivityForResult(config,3);
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
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

//    private void startActivity(Intent config, int i) {
//    }
//
//
//    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data){
//                if(requestCode==6 && resultCode==2){
//                    Bundle bdl2 = data.getExtras();
//                    dollarRate = bdl2.getFloat("dollar_key2", 0f);
//                    euroRate = bdl2.getFloat("euro_key2", 0f);
//                    wonRate = bdl2.getFloat("won_key2", 0f);
//
//                    Log.i(TAG, "onActivityResult: dollarRate=" + dollarRate);
//                    Log.i(TAG, "onActivityResult: euroRate=" + euroRate);
//                    Log.i(TAG, "onActivityResult: wonRate=" + wonRate);
//                }
//
//                super.onActivityResult(requestCode, resultCode, data);
//            }
//
//            private void myopen(){
//
//            }
//
//            public boolean onCreateOptionsMenu(Menu menu){
//                getMenuInflater().inflate(R.menu.mymenu, menu);
//                return true;
//            }
//
//            public boolean onOptionsItemSelected(@NonNull MenuItem item){
//                if(item.getItemId()==R.id.btn_config){
//
//                }
//                return super.onOptionsItemSelected(item);
//        }
//    }
