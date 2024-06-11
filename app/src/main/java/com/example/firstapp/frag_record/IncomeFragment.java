package com.example.firstapp.frag_record;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.firstapp.R;
import com.example.firstapp.db.DBManager;
import com.example.firstapp.db.TypeBean;

import java.util.List;


public class IncomeFragment extends BaseRecordFragment {

    @Override
    public void loadDataToGV() {
        super.loadDataToGV();
        //获取当前数据源
        List<TypeBean> inlist= DBManager.getTypeList(1);
        typeList.addAll(inlist);
        adapter.notifyDataSetChanged();
        record_type.setText("其他");
        recordIv.setImageResource(R.drawable.baseline_view_list_24);

    }

    @Override
    public void saveAcountTODB() {
        acountBean.setKind(1);
        DBManager.insertToAcounttb(acountBean);

    }


}