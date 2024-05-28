package com.example.firstapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;

public class Mytask implements Runnable{
    private static final String TAG = "Rate";
    Handler handler;

    public Mytask(Handler handler) {
        this.handler = handler;
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

}
