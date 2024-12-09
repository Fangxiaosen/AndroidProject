package com.example.myapplication.chapter4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DetailActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.HouseBaseAdapter;
import com.example.myapplication.baen.House;

import java.util.List;

public class Chapter4_3 extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<House> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter4_3);
        list = House.getHouseDate();
        HouseBaseAdapter houseBaseAdapter = new HouseBaseAdapter(this, list, R.layout.trmplate_list_house);
        ListView lvHouse = findViewById(R.id.lv_house);
        lvHouse.setAdapter(houseBaseAdapter);
        lvHouse.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("houseImg", list.get(position).houseImg);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}