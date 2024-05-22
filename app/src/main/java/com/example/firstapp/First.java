package com.example.firstapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class First extends AppCompatActivity implements View.OnClickListener {

    TextView out;
    EditText inp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);

        out = (TextView)findViewById(R.id.textView3);
        inp =(EditText) findViewById(R.id.editTextText);

        Button btn=(Button) findViewById(R.id.btn1);
        //btn.setOnClickListener(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click","onclick.....");
                String str = inp.getText().toString();
                out.setText("Hello"+str);
            }
        });

    }

    @Override
    public void onClick(View v) {
          Log.i("click","onclick.....");

       // TextView tv = (TextView)findViewById(R.id.textView3);

       // EditText inp =(EditText) findViewById(R.id.editTextText);
        String str = inp.getText().toString();
        out.setText("Welcome "+str);
    }
}