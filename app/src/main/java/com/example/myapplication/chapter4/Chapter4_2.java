package com.example.myapplication.chapter4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Chapter4_2 extends AppCompatActivity {

    private Button bt_chapter4_2_visable;
    private ProgressBar donw_load_dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter4_2);
        bt_chapter4_2_visable = findViewById(R.id.bt_chapter4_2_visable);
        donw_load_dp = findViewById(R.id.donw_load_dp);
        bt_chapter4_2_visable.setOnClickListener(v -> {
            donw_load_dp.setVisibility(View.VISIBLE);
        });
    }
}