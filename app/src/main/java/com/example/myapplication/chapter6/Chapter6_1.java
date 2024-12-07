package com.example.myapplication.chapter6;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Chapter6_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter61);

        String[] cityArray = {"北京", "广州", "杭州"};
        int[] iconArray = {R.drawable.beijing, R.drawable.guangzhou, R.drawable.hangzhou};

        // 使用 Java 8 的 Stream API 来构建 listArray
        List<Map<String, Object>> listArray = new ArrayList<>();
        IntStream.range(0, cityArray.length).forEach(i -> {
            Map<String, Object> mapArray = new HashMap<>();
            mapArray.put("icon", iconArray[i]);
            mapArray.put("text", cityArray[i]);
            listArray.add(mapArray);
        });

        SimpleAdapter adapter = new SimpleAdapter(this, listArray, R.layout.template_arary_city,
                new String[]{"icon", "text"}, new int[]{R.id.iv_icon, R.id.tv_text});

        Spinner spCity = findViewById(R.id.sp_city);
        spCity.setPrompt("请选择城市：");
        spCity.setSelection(0);
        spCity.setAdapter(adapter);

        // 使用匿名内部类实现 OnItemSelectedListener
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Chapter6_1.this, "您选择了第" + position + "项", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 可选：在未选择任何项时执行的操作
            }
        });
    }
}
