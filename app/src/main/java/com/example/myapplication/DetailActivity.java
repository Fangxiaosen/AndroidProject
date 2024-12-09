package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        int houseImg = bundle.getInt("houseImg");
        ImageView ivHouseImg = findViewById(R.id.iv_house_img);
        ivHouseImg.setImageResource(houseImg);

    }
}