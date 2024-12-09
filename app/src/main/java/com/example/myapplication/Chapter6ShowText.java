package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Chapter6ShowText extends AppCompatActivity {

    private TextView tvShowText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter6_show_text);
        tvShowText = findViewById(R.id.tv_show_text);
        String result = getIntent().getStringExtra("result");
        tvShowText.setText(result);

    }
}