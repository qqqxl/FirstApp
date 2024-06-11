package com.example.firstapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.firstapp.adapter.RecordAdapter;
import com.example.firstapp.frag_record.OutcomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import com.example.firstapp.frag_record.IncomeFragment;

public class RecordActivity extends AppCompatActivity {

    TabLayout layout;
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_record);
        layout =findViewById(R.id.record_tabs);
        pager =findViewById(R.id.recordi_vp);
        initPager();

    }

    private void initPager() {
        List<Fragment>fragmentList=new ArrayList<>();
        OutcomeFragment outcomeFrag = new OutcomeFragment();
        IncomeFragment incomeFrag=new IncomeFragment();
        fragmentList.add(outcomeFrag);
        fragmentList.add(incomeFrag);
        RecordAdapter pagerAdapter = new RecordAdapter(getSupportFragmentManager(), fragmentList);
        pager.setAdapter(pagerAdapter);
        layout.setupWithViewPager(pager);
    }

    public  void  oonClick(View view){
           if (view.getId()==R.id.record_iv_back){
               finish();
           }
    }
}