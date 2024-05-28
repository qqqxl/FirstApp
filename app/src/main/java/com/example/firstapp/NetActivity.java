package com.example.firstapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;

public class NetActivity extends AppCompatActivity implements Runnable{

    private static final String TAG = "Net";
    Handler handler;
    TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_net);
        show = findViewById(R.id.net_show);

        handler=new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                //处理返回
                if (msg.what==5){
                    String str=(String) msg.obj;
                    //Log.i(TAG,"handleMessage:str="+ str);
                   show.setText(str);
                }
                super.handleMessage(msg);
            }
        };

        Log.i(TAG,"onCreat: start Thread");
        Thread t=new Thread(this);
        t.start();//this.run()

//        Thread t2=new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//          t2.start();
//          Thread t3=new Thread(()->{
//              //执行方法
//          });
//          t3.start();
    }
    public void onClick(View btn){
        Log.i(TAG,"onCreat: start Thread");
        Thread t=new Thread(this);
        t.start();//this.run()

    }

    @Override
    public void run() {
        Log.i(TAG,"run: 子线程run()......");
        //获取网络数据
        URI url=null;
        String html="";
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
                Log.i(TAG,"run:td1="+td1.text()+"->"+td2.text());
                //Log.i(TAG,"run:td1="+td1.html()+"->"+td2.html());
                html +=(td1.text()+"=>"+td2.text()+"\n");
            }
            //doc.select("body > div > div.wrap > div.money-box.money-box1");
            //doc.select("#app > ul > li:nth-child(6) > div.today-box-item.li-one");
            Element td=doc.select("body > div > div.wrap > div.money-box.money-box1").first();
            Log.i(TAG,"run: 美元: "+td.text());
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();


        }
        //发送消息
        Message msg=handler.obtainMessage(5,html);
        handler.sendMessage(msg);
    }
    private String inputStream2String(InputStream inputStream)throws IOException{
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out =new StringBuilder();
        Reader in=new InputStreamReader(inputStream,"gb2312");
        while(true){
            int rsz=in.read(buffer,0,buffer.length);
            if(rsz<0)
                break;
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }
}