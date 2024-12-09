package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CityDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        Bundle bundle = getIntent().getExtras();
        int cityImg = bundle.getInt("cityImg");
        String cityTiele = bundle.getString("cityTitle");
        String cityText = bundle.getString("cityText");

        ImageView ivDetailIcon = findViewById(R.id.iv_detail_icon);
        TextView tvDetailTitle = findViewById(R.id.tv_detail_title);
        TextView tvDetailText = findViewById(R.id.tv_detail_text);
        ivDetailIcon.setImageResource(cityImg);
        tvDetailTitle.setText(cityTiele);
        tvDetailText.setText(cityText);


    }
}