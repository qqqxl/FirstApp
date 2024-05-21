package com.example.firstapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScorceActivity extends AppCompatActivity {

    TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scorce);

        score=(TextView) findViewById(R.id.score);
    }
    public  void  btnAdd1(View btn){
          showScore(1);
    }
    public  void  btnAdd2(View btn){
        showScore(2);
    }
    public  void  btnAdd3(View btn){
        showScore(3);
    }
    public  void  btnReset(View btn){
        score.setText("0" );
    }
    private void showScore(int inc){
        Log.i("show","inc="+inc);
        String oldScore =(String) score.getText();
        int newScore=Integer.parseInt(oldScore)+ inc;
       score.setText("" + newScore);
    }
}