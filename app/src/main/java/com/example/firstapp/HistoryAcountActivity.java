package com.example.firstapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firstapp.adapter.AcountAdapter;
import com.example.firstapp.db.AcountBean;
import com.example.firstapp.db.DBManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoryAcountActivity extends AppCompatActivity {

    int year,month;
    ListView list;
    List<AcountBean>mDatas;
    AcountAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history_acount);
        initView();
        initTime();
        loadData();
    }
    private void initView() {
        list=findViewById(R.id.his_list);
        mDatas=new ArrayList<>();
        adapter = new AcountAdapter(this, mDatas);
        list.setAdapter(adapter);
    }
    private void loadData() {
        List<AcountBean> list = DBManager.getOneMonthAccounnt(year,month);
        mDatas.addAll(list);
        adapter.notifyDataSetChanged();
    }
    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH)+1;
    }
}