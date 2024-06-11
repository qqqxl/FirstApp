package com.example.firstapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.adapter.AcountAdapter;
import com.example.firstapp.db.AcountBean;
import com.example.firstapp.db.DBManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainPageActivity extends AppCompatActivity implements View.OnClickListener {
    ListView todayList;
    List<AcountBean> mDatas;

    int year, month, day;
    AcountAdapter adapter;
    View headView;
    TextView input, output, todayCon;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_page);
        initTime();

        todayList = findViewById(R.id.main_lv);
        addHead();
        mDatas = new ArrayList<>();
        adapter = new AcountAdapter(this, mDatas);
        todayList.setAdapter(adapter);
    }
    private void addHead() {
        headView = getLayoutInflater().inflate(R.layout.item_mainlv_top, null);
        todayList.addHeaderView(headView);
        output = headView.findViewById(R.id.main_top_out);
        input = headView.findViewById(R.id.main_top_in);
        todayCon = headView.findViewById(R.id.main_top_tvday);
        headView.setOnClickListener(this);

    }

    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDDData();
        setSumText();
    }

    private void setSumText() {
        float dayOutData = DBManager.getDayData(year, month, day, 0);
        float dayInData = DBManager.getDayData(year, month, day, 1);
        String dayData = "今日支出: $" + dayOutData + "收入: $" + dayInData;
        todayCon.setText(dayData);
        float monthOut = DBManager.getSumMonthData(year, month, 0);
        float monthIn = DBManager.getSumMonthData(year, month, 1);
        output.setText("$ " + monthOut);
        input.setText("$" + monthIn);
    }

    private void loadDDData() {
        List<AcountBean> list = DBManager.getOneDayAccount(year, month, day);
        mDatas.clear();
        mDatas.addAll(list);
        adapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.main_btn_edit) {
            Intent intent = new Intent(this, RecordActivity.class);
            startActivity(intent);
        }
    }

    public void onHistoryClick(View btn) {
        if (btn.getId() == R.id.main_btn_acount) {
            Intent intentHis = new Intent(this, HistoryAcountActivity.class);
            startActivity(intentHis);
        }
    }
}



