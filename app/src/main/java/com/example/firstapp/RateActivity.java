package com.example.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;

public class RateActivity extends AppCompatActivity  {
    private static final String TAG = "Rate";

    TextView show;
    Handler handler;
    private float dollarRate = 0.1f;
    private float euroRate = 0.05f;
    private float wonRate = 500f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        handler=new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                //处理返回
                if (msg.what==5){
                    //String str=(String) msg.obj;
                    //Log.i(TAG,"handleMessage:str="+ str);
                    //show.setText(str);
                    Bundle bundle=(Bundle) msg.obj;
                    dollarRate=bundle.getFloat("dollar");
                    euroRate=bundle.getFloat("euro");
                    wonRate=bundle.getFloat("won");
                    Toast.makeText(RateActivity.this, "汇率更新完毕", Toast.LENGTH_SHORT).show();
                    Log.i(TAG,"handleMessage:dollarRate"+dollarRate);
                    Log.i(TAG,"handleMessage:euroRate"+euroRate);
                    Log.i(TAG,"handleMessage:wonRate"+wonRate);
                }
                super.handleMessage(msg);
            }
        };
        //启动线程
        Thread t=new Thread(new Mytask(handler));
        t.start();//this.run()
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
    @Override
    public void run() {
        Log.i(TAG,"run: 子线程run()......");
        //获取网络数据
        URI url=null;
        String html="";
        Bundle retbundle = new Bundle();
        try{
          /*  url = new URL("https://jwc.swufe.edu.cn/info/1025/15961.htm");
            HttpURLConnection http =(HttpURLConnection) url.openConnection();
            InputStream in=http.getInputStream();


             html=inputStream2String(in);
            Log.i(TAG,"run:html="+html);*/
            Document doc= Jsoup.connect("https://www.huilvzaixian.com/").get();
            Element table= doc.getElementsByTag("table").first();
            Elements rows =table.getElementsByTag("tr");
            rows.remove(0);
            for(Element row : rows) {
                // Log.i(TAG, "run:row=" + row);
                Elements tds = row.getElementsByTag("td");
                Element td1 = tds.first();
                Element td2 = tds.get(4);
                String str1=td1.text().trim();
                String str2=td2.text().trim();

                Log.i(TAG,"run:td1="+str1+"->"+str2);
                //Log.i(TAG,"run:td1="+td1.html()+"->"+td2.html());
                html +=(str1+"=>"+str2+"\n");

                //提取三个汇率值;
                if(str1.equals("美元")){
                    retbundle.putFloat("dollar",100f/Float.parseFloat(str2));
                }else  if(str1.equals("欧元")){
                    retbundle.putFloat("euro",100f/Float.parseFloat(str2));
                }else  if(str1.equals("韩国元")){
                    retbundle.putFloat("won",100f/Float.parseFloat(str2));
                }
            }
            //doc.select("body > div > div.wrap > div.money-box.money-box1");
            //doc.select("#app > ul > li:nth-child(6) > div.today-box-item.li-one");
            //Element td=doc.select("body > div > div.wrap > div.money-box.money-box1").first();
            //Log.i(TAG,"run: 美元: "+td.text());
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();


        }
        //发送消息
        Message msg=handler.obtainMessage(5,retbundle);
        handler.sendMessage(msg);
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

