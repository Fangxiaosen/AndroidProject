package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.utils.ViewUtil;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Chapter6 是一个继承自 AppCompatActivity 的活动类，用于启动另一个活动并接收返回的数据。
 * 该类包含了一个按钮，用户点击后可以启动另一个活动（Chapter6_1），
 * 并将一些数据通过 Intent 传递给下一个活动。返回后会显示接收到的返回数据。
 */
public class Chapter6 extends AppCompatActivity implements View.OnFocusChangeListener {

    // 定义页面中的控件
    private TextView tvResultData;
    private TextView tvRequestData;
    private Button btnStartActivity;
    private ActivityResultLauncher<Intent> register;


    /**
     * 当活动创建时调用，绑定视图并设置监听器。
     *
     * @param savedInstanceState 如果活动被重新初始化时用于恢复数据的 Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter6);

        // 绑定布局中的控件
        bindViews();

        // 绑定控件的监听器
        bindListeners();

        // 注册用于接收启动的 Activity 返回结果的启动器
        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Intent intent = result.getData();
                Bundle bundle = intent.getExtras();
                String responseStr = bundle.getString("respData");
                tvResultData.setText(responseStr);
            }
        });
    }

    /**
     * 绑定布局文件中的视图控件。
     */
    private void bindViews() {
        tvRequestData = findViewById(R.id.tv_request_data);
        btnStartActivity = findViewById(R.id.btn_start_activity);
        tvResultData = findViewById(R.id.tv_result_data);
    }

    /**
     * 获取当前本地时间，并格式化为 HH:mm:ss 格式。
     *
     * @return 格式化后的当前时间字符串
     */
    private String getLocalTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter);
    }

    /**
     * 绑定按钮的点击事件监听器，用于启动另一个活动并传递数据。
     */
    private void bindListeners() {
        btnStartActivity.setOnClickListener(v -> {
            Intent intent = new Intent(Chapter6.this, Chapter6_1.class);
            Bundle bundle = new Bundle();
            String showMsg = "你吃饭了吗？要不要找我一起吃饭？";
            String format = String.format("收到的请求消息为：\n请求时间为%s\n请求内容为：%s", getLocalTime(), showMsg);
            bundle.putString("data", format);
            bundle.putInt("number", 1999);
            intent.putExtras(bundle);
            register.launch(intent);
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }


    private class HideTextWatch implements TextWatcher {

        private EditText editText;
        private int maxLength;

        public HideTextWatch(EditText editText, int maxLength) {
            this.editText = editText;
            this.maxLength = maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            String editableString = editable.toString();
            if (editableString.length() == 6 && maxLength == 6 || editableString.length() == 11 && maxLength == 11) {
                ViewUtil.hideOneInputMethod(Chapter6.this, editText);
            }
        }
    }
}
