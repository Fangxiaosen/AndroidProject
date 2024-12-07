package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.chapter4.Chapter4;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_chapter3;
    private Button bt_chapter4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        bt_chapter3 = findViewById(R.id.bt_chapter3);
        bt_chapter4 = findViewById(R.id.bt_chapter4);
        bt_chapter3.setOnClickListener(this);
        bt_chapter4.setOnClickListener(this);
        bt_chapter3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, chapter3.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_chapter3) {
            Intent intent = new Intent(MainActivity.this, chapter3.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.bt_chapter4) {
            Intent intent = new Intent(MainActivity.this, Chapter4.class);
            startActivity(intent);
        }
    }
}