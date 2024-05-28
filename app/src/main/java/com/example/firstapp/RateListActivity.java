package com.example.firstapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class RateListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //准备数据
        String[] data={"hello","android","studio","listview"};
        List<String> list_data=new ArrayList<String>(100);
        for(int i=1;i<=100;i++){
            list_data.add("Item"+i);
        }
        //构造适配器
        ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_data);

        //绑定
        setListAdapter(adapter);
    }
}