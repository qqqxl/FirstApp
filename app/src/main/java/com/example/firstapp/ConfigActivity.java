package com.example.firstapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfigActivity extends AppCompatActivity {
    private static final String TAG = "Rate";
    private EditText dollarEdit;
    private EditText euroEdit;
    private EditText wonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_config);

        //接收参数
        Intent intent = getIntent();
        float dollar2 = intent.getFloatExtra("dollar_rate_key", 1.1f);
        float euro2 = intent.getFloatExtra("euro_rate_key", 2.2f);
        float won2 = intent.getFloatExtra("won_rate_key", 3.3f);

        Log.i(TAG, "onCreat:dollar2" + dollar2);
        Log.i(TAG, "onCreat:euro2" + euro2);
        Log.i(TAG, "onCreat:won2" + won2);


        //把数据放入页面控件里，供用户修改
        dollarEdit = findViewById(R.id.dollar_edit);
        euroEdit = findViewById(R.id.euro_edit);
        wonEdit = findViewById(R.id.won_edit);

        dollarEdit.setText(String.valueOf(dollar2));
        euroEdit.setText(String.valueOf(euro2));
        wonEdit.setText(String.valueOf(won2));
    }

    public void save(View btn) {
        //保存数据，并返回到调用页面
        float new_dollar = Float.parseFloat(dollarEdit.getText().toString());
        float new_euro = Float.parseFloat(euroEdit.getText().toString());
        float new_won = Float.parseFloat(wonEdit.getText().toString());

        Log.i(TAG, "save: dollar=" + new_dollar);
        Log.i(TAG, "save: euro=" + new_euro);
        Log.i(TAG, "save: won=" + new_won);

        //带回数据到调用页面RateActivity
        Intent retIntent = getIntent();
        Bundle bdl = new Bundle();
        bdl.putFloat("dollar_key2", new_dollar);
        bdl.putFloat("euro_key2", new_euro);
        bdl.putFloat("won_key2", new_won);
        retIntent.putExtras(bdl);

        setResult(6, retIntent);

        finish();
    }
}