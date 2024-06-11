package com.example.firstapp.frag_record;

import static java.security.AccessController.getContext;

import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firstapp.R;
import com.example.firstapp.db.AcountBean;
import com.example.firstapp.db.DBManager;
import com.example.firstapp.db.TypeBean;
import com.example.firstapp.utils.BeiZhuDialog;
import com.example.firstapp.utils.SelectTime;
import com.example.firstapp.utils.keyBoardUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class IncomeFragment extends Fragment implements View.OnClickListener {

    KeyboardView keyboardView;
    EditText money;
    ImageView recordIv;
    TextView record_type,beizhu, timeTv;
    GridView record_gv;
    List<TypeBean>typeList;
    TypeBaseAdapter adapter;
    AcountBean acountBean;//插入记账信息

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        acountBean = new AcountBean();
        acountBean.setTypename("其他");
        acountBean.setYesId(R.drawable.baseline_view_list_24);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_outcome, container, false);
        initView(view);
        setInitTime();//时间
        loadDataToGV();
        setGVListner();//点击事件
        return  view;
    }

    private void setGVListner() {
        record_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.mSelectPos=position;
                adapter.notifyDataSetChanged();//提示绘制发生变化
                TypeBean typeBean=typeList.get(position);
                String typename = typeBean.getTypename();
                record_type.setText(typename);
                acountBean.setTypename(typename);
                int yesId = typeBean.getYesId();
                recordIv.setImageResource(yesId);
                acountBean.setYesId(yesId);
            }
        });
    }

    private void setInitTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String tm = sdf.format(date);
        timeTv.setText(tm);
        acountBean.setTime(tm);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        acountBean.setYear(year);
        acountBean.setMonth(month);
        acountBean.setDay(day);

    }

    void initView(View view) {
        keyboardView = view.findViewById(R.id.record_keyboard);
        money = view.findViewById(R.id.frag_record_money);
        recordIv = view.findViewById(R.id.frag_record_iv);
        record_type = view.findViewById(R.id.frag_record_type);
        beizhu = view.findViewById(R.id.record_beizhu);
        timeTv = view.findViewById(R.id.recoerd_time);
        record_gv = view.findViewById(R.id.record_gv);
        beizhu.setOnClickListener(this);
        timeTv.setOnClickListener(this);
        //软键盘显示出来
        keyBoardUtils boardUtils = new keyBoardUtils(keyboardView, money);
        boardUtils.setKeyboardView();
        //点击事件
        boardUtils.setOnEnsureListener(new keyBoardUtils.onEnsureListener() {
            @Override
            public void onEnsure() {
                //获取money
                String moneystr = money.getText().toString();
                if (TextUtils.isEmpty(moneystr) || moneystr.equals("0")) {
                    getActivity().finish();
                    return;
                }
                float moneyy = Float.parseFloat(moneystr);
                acountBean.setMoney(moneyy);
                //获取记录信息，保持在数据库中
                acountBean.setKind(1);
                DBManager.insertToAcounttb(acountBean);
                //返回上一级界面
                getActivity().finish();

            }
        });
    }


    public void loadDataToGV() {
        typeList=new ArrayList<>();
        adapter = new TypeBaseAdapter(getContext(), typeList);
        record_gv.setAdapter(adapter);
        List<TypeBean> inlist= DBManager.getTypeList(1);
        typeList.addAll(inlist);
        adapter.notifyDataSetChanged();
        record_type.setText("其他");
        recordIv.setImageResource(R.drawable.baseline_view_list_24);

    }



    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.recoerd_time){
            showTime();
        }else if (v.getId()==R.id.record_beizhu){
            showBZDialog();
        }
    }

    private void showBZDialog() {
        BeiZhuDialog dialog = new BeiZhuDialog(getContext());
        dialog.show();
        dialog.setDialogSize();

        dialog.setOnEnsureListener(new BeiZhuDialog.onEnsureListener() {
            @Override
            public void onEnsure() {
                String msg = dialog.getEditText();
                if (!TextUtils.isEmpty(msg)){
                    beizhu.setText(msg);
                    acountBean.setBeizhu(msg);
                }
                dialog.cancel();

            }
        });

    }

    private void showTime() {
        SelectTime selectTime = new SelectTime(getContext());
        selectTime.show();
        //设置监听器
        selectTime.setOnEnsureListener(new SelectTime.OnEnsureListener() {
            @Override
            public void onEnsure(String time, int year, int month, int day) {
                timeTv.setText(time);
                acountBean.setYear(year);
                acountBean.setMonth(month);
                acountBean.setDay(day);
            }
        });
    }
}