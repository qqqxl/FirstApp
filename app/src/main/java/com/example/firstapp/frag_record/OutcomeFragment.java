package com.example.firstapp.frag_record;

import com.example.firstapp.R;
import com.example.firstapp.db.DBManager;
import com.example.firstapp.db.TypeBean;

import java.util.List;

public class OutcomeFragment extends BaseRecordFragment{
    @Override
    public void loadDataToGV() {
        super.loadDataToGV();
        List<TypeBean> outlist=DBManager.getTypeList(0);
        typeList.addAll(outlist);
        adapter.notifyDataSetChanged();
        record_type.setText("其他");
        recordIv.setImageResource(R.drawable.baseline_view_list_24);


    }

    @Override
    public void saveAcountTODB() {
        acountBean.setKind(0);
        DBManager.insertToAcounttb(acountBean);
    }


}
