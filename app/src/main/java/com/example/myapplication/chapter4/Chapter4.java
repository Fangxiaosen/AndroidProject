package com.example.myapplication.chapter4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Chapter4 extends AppCompatActivity implements View.OnClickListener {


    private Button bt_chapter4_1;
    private Button bt_chapter4_2;
    private Button bt_chapter4_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter4);
        bt_chapter4_1 = findViewById(R.id.bt_chapter4_1);
        bt_chapter4_2 = findViewById(R.id.bt_chapter4_2);
        bt_chapter4_3 = findViewById(R.id.bt_chapter4_3);
        bt_chapter4_1.setText("实例4_1");
        bt_chapter4_2.setText("实例4_2");
        bt_chapter4_3.setText("实例4_3");
        bt_chapter4_1.setOnClickListener(this);
        bt_chapter4_2.setOnClickListener(this);
        bt_chapter4_3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        
        if (v.getId() == R.id.bt_chapter4_1) {
            Intent intent = new Intent(Chapter4.this, Chapter4_1.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.bt_chapter4_2) {
            Intent intent = new Intent(Chapter4.this, Chapter4_2.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.bt_chapter4_3) {
            Intent intent = new Intent(Chapter4.this, Chapter4_3.class);
            startActivity(intent);
        }
    }
}