package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Chapter6_1 是一个用于接收 Chapter6 传递数据并返回应答数据的活动类。
 * 该类通过 Intent 接收传递过来的数据，并在界面上展示给用户。
 * 用户可以输入应答数据，并将应答结果通过 Intent 发送回上一活动。
 */
public class Chapter6_1 extends AppCompatActivity {

    private TextView tvGetDataShow;
    private Button btnResponseData;
    private TextView tvResponseData;

    /**
     * 当活动创建时调用，绑定视图并设置监听器。
     * @param savedInstanceState 如果活动被重新初始化时用于恢复数据的 Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter6_1);

        bindViews();

        // 获取 Intent 中的传递数据
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String data = bundle.getString("data");
        int number = bundle.getInt("number");

        // 在 TextView 中显示接收到的数据
        tvGetDataShow.setText(data + "，数字：" + number);

        // 为按钮设置点击事件，传递返回的数据
        btnResponseData.setOnClickListener(v -> {
            Intent intent = new Intent();
            Bundle bundle1 = new Bundle();
            String format = String.format("收到的返回消息为：\n应答时间为%s\n应答内容为：%s", getLocalTime(), tvResponseData.getText().toString());
            bundle1.putString("respData", format);
            intent.putExtras(bundle1);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    /**
     * 获取当前本地时间，并格式化为 HH:mm:ss 格式。
     * @return 格式化后的当前时间字符串
     */
    private String getLocalTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter);
    }

    /**
     * 绑定布局文件中的视图控件。
     */
    private void bindViews() {
        tvGetDataShow = findViewById(R.id.tv_get_data_show);
        btnResponseData = findViewById(R.id.btn_response_data);
        tvResponseData = findViewById(R.id.tv_response_data);
    }
}
