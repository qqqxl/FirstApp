package com.example.firstapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class RecordAdapter extends FragmentPagerAdapter {
    List<Fragment>fragmentList;
    String[]titles={"支出","收入"};//联动
    public RecordAdapter(@NonNull FragmentManager fm,List<Fragment>fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {

        return fragmentList.size();
    }
    @NonNull
    @Override
    public CharSequence getPageTitle(int position){

        return  titles[position];
    }
}
