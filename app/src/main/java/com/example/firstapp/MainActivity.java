package com.example.firstapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static  final  String  TAG="main";
    EditText input1;
    EditText input2;
    TextView output1;
    TextView output2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        EditText input1 = findViewById(R.id.inp1);
        EditText input2 = findViewById(R.id.inp2);
        //获取用户输入数据
        String sg = input1.getText().toString();
        String tz = input2.getText().toString();

        output1 = findViewById(R.id.out);
        output1.setText("你的BMI是: ");

        System.out.println("this is system.out");
        Log.i("tag1", "这里是输入信息");
    }

    public void myClick(View v){
        Log.i(TAG, "myClick: 111111");
        Log.i(TAG, "myClick: ");

        //获取用户输入
        input1 = findViewById(R.id.inp1);
        input2 = findViewById(R.id.inp2);
        String sg = input1.getText().toString();
        String tz = input2.getText().toString();

        //计算过程
        double result = Double.parseDouble(tz)/Double.parseDouble(sg)/Double.parseDouble(sg);

        String s;
        if(result<18.5) s = "偏瘦，建议补充营养";
        else if (result>=25) s = "肥胖，建议控制饮食，增加运动";
        else s = "正常，请继续保持";

        //显示到控件中
        output1 = findViewById(R.id.out);
        output1.setText("你的BMI是: " + result);

        output2 = findViewById(R.id.suggestions);
        output2.setText(s);

    }
}