package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class chapter3 extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private Button btChapter3_1;
    private Button btChapter3_2;
    private Button btChapter3_3;
    private Button btChapter3_4;
    private Button btAble;
    private Button btDisable;
    private Button btAbleTest;
    private TextView tvBtnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_chapter3);
        super.onCreate(savedInstanceState);
        btChapter3_1 = findViewById(R.id.bt_chapter3_1);
        btChapter3_2 = findViewById(R.id.bt_chapter3_2);
        btChapter3_3 = findViewById(R.id.bt_chapter3_3);
        btChapter3_4 = findViewById(R.id.bt_chapter3_4);
        //获取控件
        btAble = findViewById(R.id.bt_able);
        btDisable = findViewById(R.id.bt_disable);
        btAbleTest = findViewById(R.id.bt_able_test);
        tvBtnTest = findViewById(R.id.tv_btn_test);
        //修改布局文件中按钮的文本
        btAble.setText("启用测试按钮");
        btDisable.setText("禁用测试按钮");
        btAbleTest.setText("测试按钮");
        tvBtnTest.setText("这里查看测试按钮的点击结果");

        //绑定监听事件
        btAble.setOnClickListener(this);
        btDisable.setOnClickListener(this);
        btDisable.setOnLongClickListener(this);

        btChapter3_1.setText("实训3_1/2");
        btChapter3_2.setText("实训3_3");
        btChapter3_2.setText("实训3_4");
        btChapter3_1.setOnClickListener(v -> {
            Toast.makeText(this, "实训3_1/2", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(chapter3.this, Chapter3_1.class);
            startActivity(intent);
        });
        btChapter3_2.setOnClickListener(v -> {
            Toast.makeText(this, "实训3_3", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(chapter3.this, Chapter3_2.class);
            startActivity(intent);
        });
        btChapter3_3.setOnClickListener(v -> {
            Toast.makeText(this, "正在开发", Toast.LENGTH_SHORT).show();

        });
        btChapter3_4.setOnClickListener(v -> {
            Toast.makeText(this, "实训3_4", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(chapter3.this, Chapter3_4.class);
            startActivity(intent);
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_able) {
            btAbleTest.setBackgroundColor(getColor(R.color.colorPrimary));
            btAbleTest.setEnabled(true);
            tvBtnTest.setText("测试按钮已启用");
        }
        if (v.getId() == R.id.bt_disable) {
            btAbleTest.setBackgroundColor(getColor(R.color.gray));
            btAbleTest.setEnabled(false);
            tvBtnTest.setText("单击测试按钮已禁用");
        }

    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.bt_disable) {
            Toast.makeText(getApplicationContext(), "长按按钮", Toast.LENGTH_LONG).show();
            btAbleTest.setBackgroundColor(getColor(R.color.gray));
            btAbleTest.setEnabled(false);
            tvBtnTest.setText("长按测试按钮已禁用");
            return true;
        }
        return false;
    }
}