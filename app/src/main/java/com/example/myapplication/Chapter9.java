package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.adapter.GoodsPageAdapter;
import com.example.myapplication.baen.Goods;

import java.util.List;

public class Chapter9 extends AppCompatActivity {

    private List<Goods>list = Goods.getGoodsList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chapter9);
        ViewPager vpPhone = findViewById(R.id.vp_phone);
        GoodsPageAdapter goodsPageAdapter = new GoodsPageAdapter(this, list);
        vpPhone.setAdapter(goodsPageAdapter);
        vpPhone.setCurrentItem(0);
        vpPhone.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(Chapter9.this, "您选择了第" + position + "项", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}