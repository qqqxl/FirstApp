package com.example.firstapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.firstapp.R;

import java.util.Calendar;

//弹出时间
public class SelectTime extends Dialog implements View.OnClickListener {

    DatePicker selectDate;
    Button ensureBtn,cancelBtn;

    public interface OnEnsureListener{
        public void  onEnsure(String time,int year,int month,int day);
    }
    OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public SelectTime(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_time);
        selectDate=findViewById(R.id.time_dialog);
        ensureBtn=findViewById(R.id.time_ensure);
        cancelBtn=findViewById(R.id.time_cancel);
        ensureBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        hideDate();
    }

    @Override
    public void onClick(View v) {
             if (v.getId()==R.id.time_cancel){
                 cancel();
             }else  if (v.getId()==R.id.time_ensure){
                 int year=selectDate.getYear();
                 int month=selectDate.getMonth();
                 int day=selectDate.getDayOfMonth();
                 String timeFor=year+"年"+month+"月"+day+"日 ";
             if (onEnsureListener!=null){
                     onEnsureListener.onEnsure(timeFor,year,month,day);
                 }
                 cancel();

             }
    }

    //隐藏头布局
    private  void  hideDate(){
        ViewGroup rootView=(ViewGroup) selectDate.getChildAt(0);
        if (rootView==null){
            return;
        }
        View header = rootView.getChildAt(0);
        if (header==null){
            return;
        }
        int headerId=getContext().getResources().getIdentifier("day_picker_selector_layout","id","android");
       if (headerId==header.getId()){
           header.setVisibility(View.GONE);
           ViewGroup.LayoutParams layoutParams=rootView.getLayoutParams();
           layoutParams.width=ViewGroup.LayoutParams.WRAP_CONTENT;
           rootView.setLayoutParams(layoutParams);

           ViewGroup animator=(ViewGroup) rootView.getChildAt(1);
           ViewGroup.LayoutParams layoutParamsAni=animator.getLayoutParams();
           layoutParamsAni.width=ViewGroup.LayoutParams.WRAP_CONTENT;
           animator.setLayoutParams(layoutParamsAni);

           View child = animator.getChildAt(0);
           ViewGroup.LayoutParams layoutParamsChild=child.getLayoutParams();
           layoutParamsChild.width=ViewGroup.LayoutParams.WRAP_CONTENT;
           child.setLayoutParams(layoutParamsChild);
           return;
       }
    }
}
