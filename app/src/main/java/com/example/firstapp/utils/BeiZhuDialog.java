package com.example.firstapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.firstapp.R;

public class BeiZhuDialog extends Dialog implements View.OnClickListener {
    EditText et;
    Button btn_ensure,btn_cancel;
    onEnsureListener onEnsureListener;


    //设定回调接口的方法；
    public void setOnEnsureListener(BeiZhuDialog.onEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public BeiZhuDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_beizhu);//设置对话框显示布局
        et = findViewById(R.id.beizhu_et);
        btn_cancel=findViewById(R.id.beizhu_btn_cancel);
        btn_ensure=findViewById(R.id.beizhu_btn_ensure);
        btn_cancel.setOnClickListener(this);
        btn_ensure.setOnClickListener(this);
    }
    public interface onEnsureListener{
        public void onEnsure();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.beizhu_btn_cancel){
                cancel();
        }else if (v.getId()==R.id.beizhu_btn_ensure){
              if (onEnsureListener!=null){
                  onEnsureListener.onEnsure();
              }
        }

    }
   // 获取输入数据的方法
    public String getEditText(){
        return et.getText().toString().trim();
    }
    //设置窗口尺寸和屏幕一样
    public void setDialogSize(){
        //获取当前窗口对象
        Window window = getWindow();
        //获取参数
        WindowManager.LayoutParams aa = window.getAttributes();
        Display dd = window.getWindowManager().getDefaultDisplay();
        aa.width=(int) (dd.getWidth());
        aa.gravity= Gravity.BOTTOM;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(aa);


    }
}
