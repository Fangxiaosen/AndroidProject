package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.CityBaseAdapter;
import com.example.myapplication.adapter.CityRecyclerViewAdapter;
import com.example.myapplication.baen.City;

import java.util.Arrays;

public class Chapter8 extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter8);

        City[] cityList = City.getCityList();
        CityBaseAdapter cityBaseAdapter = new CityBaseAdapter(this, Arrays.asList(cityList), R.layout.template_list_city);
        ListView lvCity = findViewById(R.id.lv_city);
        RecyclerView rvCity = findViewById(R.id.rv_city);
        rvCity.setLayoutManager(new LinearLayoutManager(this));

        rvCity.setAdapter(new CityRecyclerViewAdapter(Arrays.asList(cityList), this));
        lvCity.setAdapter(cityBaseAdapter);

        lvCity.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, CityDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("cityImg", cityList[position].img);
            bundle.putString("cityTitle", cityList[position].title);
            bundle.putString("cityText", cityList[position].text);
            intent.putExtras(bundle);
            startActivity(intent);
        });

    }
}