package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.utils.ViewUtil;

public class Chapter5 extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener {

    private String sexStr = "";  // 性别
    private String hobbyStr = "";  // 爱好
    private String rememberStr = "关闭";  // 记住密码开关状态
    private TextView tvResult;

    // 控件声明
    private CheckBox cbFootball, cbBasketball, cbRap, cbDance;
    private Button btRegister, btRegister2;
    private RadioGroup rgSex;
    private EditText etUsername, etPassword;
    private Switch switchRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // 启用EdgeToEdge模式
        setContentView(R.layout.activity_chapter5);

        bindViews();
        bindListeners();

        etPassword.setOnFocusChangeListener(this);
    }

    // 绑定控件
    private void bindViews() {
        cbFootball = findViewById(R.id.cb_football);
        cbBasketball = findViewById(R.id.cb_basketball);
        cbRap = findViewById(R.id.cb_rap);
        cbDance = findViewById(R.id.cb_dance);
        rgSex = findViewById(R.id.rg_sex);
        btRegister = findViewById(R.id.bt_register);
        btRegister2 = findViewById(R.id.bt_register2);
        switchRemember = findViewById(R.id.switch_remember);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
    }

    // 绑定监听器
    private void bindListeners() {
        btRegister.setOnClickListener(this);
        btRegister2.setOnClickListener(this);
        rgSex.setOnCheckedChangeListener(this);
        switchRemember.setOnCheckedChangeListener(this);
        cbFootball.setOnCheckedChangeListener(this);
        cbBasketball.setOnCheckedChangeListener(this);
        cbRap.setOnCheckedChangeListener(this);
        cbDance.setOnCheckedChangeListener(this);
        etUsername.addTextChangedListener(new HideTextWatch(etUsername, 11));
        etPassword.addTextChangedListener(new HideTextWatch(etPassword, 6));
    }

    // 点击事件处理
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_register) {
            showAlertDialog();
        } else if (v.getId() == R.id.bt_register2) {
            showPopupWindow();
            String result = getResultText();
            startNextActivity(result);
        }
    }

    // 弹出AlertDialog
    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("表单结果")
                .setMessage("您的性别为：" + sexStr + "\n您的爱好为：" + hobbyStr + "\n您" + rememberStr + "记住密码!")
                .setPositiveButton("信息正确！", (dialogInterface, i) -> Toast.makeText(this, "恭喜您表单填写正确！", Toast.LENGTH_LONG).show())
                .setNegativeButton("信息错误!", (dialogInterface, i) -> Toast.makeText(this, "表单填写错误！", Toast.LENGTH_LONG).show())
                .show();
    }

    // 弹出PopupWindow
    private void showPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_window_template, null);
        tvResult = contentView.findViewById(R.id.tv_result);
        tvResult.setText(getResultText());

        PopupWindow popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    // 获取表单结果文本
    private String getResultText() {
        return "你的用户名：" + etUsername.getText().toString() + "\n你的密码：" + etPassword.getText().toString()
                + "\n您的性别为：" + sexStr + "\n您的爱好为：" + hobbyStr + "\n您" + rememberStr + "记住密码!";
    }

    // 跳转到下一个活动
    private void startNextActivity(String result) {
        Intent intent = new Intent(this, Chapter6ShowText.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }

    // 复选框和开关的选择事件
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.switch_remember) {
            rememberStr = isChecked ? "打开" : "关闭";
        } else {
            handleHobbyChange(buttonView, isChecked);
        }
    }

    // 处理爱好选择
    private void handleHobbyChange(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            hobbyStr = hobbyStr.isEmpty() ? buttonView.getText().toString() : hobbyStr + " | " + buttonView.getText();
        } else {
            hobbyStr = hobbyStr.replace(buttonView.getText(), "").replace(" | ", "").trim();
        }
    }

    // 处理性别选择事件
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        sexStr = ((RadioButton) findViewById(checkedId)).getText().toString();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.et_password && hasFocus && (etUsername.getText().length() < 11)) {
            Toast.makeText(this, "用户名必须满足11位", Toast.LENGTH_SHORT).show();
            etUsername.requestFocus();
        }
    }

    // 自定义TextWatcher
    public class HideTextWatch implements TextWatcher {

        private final EditText editText;
        private final int maxLength;

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
            if (editable.length() == maxLength) {
                ViewUtil.hideOneInputMethod(Chapter5.this, editText);
            }
        }
    }
}
