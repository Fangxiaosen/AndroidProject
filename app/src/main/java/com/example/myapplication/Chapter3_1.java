package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.utils.Utils;

public class Chapter3_1 extends AppCompatActivity {

    private TextView tvHello;
    private Button ac3_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter3_1);
        tvHello = findViewById(R.id.tvHello);
        ac3_btn = findViewById(R.id.ac3_btn);
        ac3_btn.setText("点击折行java逻辑代码");
        ac3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Chapter3_1.this, "执行java逻辑代码的修改", Toast.LENGTH_SHORT);
                //java文件中引用字符串常量“hello”，显示文本内容
                tvHello.setText(R.string.hello);
                //调用setTextSize()方法，指定文本大小
                tvHello.setTextSize(20);
                //java文件中引用定义好的颜色常量，使之改变颜色
                tvHello.setTextColor(getColor(R.color.colorPrimary));
                //java修改视图的宽高属性，使之按照设置好的宽高展示
                ViewGroup.LayoutParams layoutParams = tvHello.getLayoutParams();
                layoutParams.width = Utils.px2dip(Chapter3_1.this, 600);
                tvHello.setLayoutParams(layoutParams);
            }
        });
    }
}