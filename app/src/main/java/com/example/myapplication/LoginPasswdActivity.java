package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.baen.UserInfo;
import com.example.myapplication.utils.ViewUtil;

import java.util.Locale;
import java.util.Random;

/**
 * 登录界面活动类，支持密码登录和验证码登录两种模式。
 */
public class LoginPasswdActivity
        extends AppCompatActivity
        implements
        RadioGroup.OnCheckedChangeListener, // 用于监听单选按钮组的切换
        View.OnFocusChangeListener,         // 用于监听控件焦点变化
        View.OnClickListener,               // 用于监听按钮点击事件
        CompoundButton.OnCheckedChangeListener { // 用于监听复选框的选中状态

    // 控件声明
    private RadioGroup rgLogin; // 单选按钮组，用于切换登录模式
    private TextView tvPassword; // 显示密码或验证码的提示文本
    private EditText etPhone; // 输入手机号
    private EditText etPassword; // 输入密码或验证码
    private Button btnForget; // 忘记密码或获取验证码按钮
    private CheckBox ckRemember; // 记住密码的复选框
    private Button btnLogin; // 登录按钮

    // 登录相关变量
    private String verifyCode; // 生成的验证码
    private boolean remember = false; // 记住密码标志
    private UserDBHelper dbHelper; // 数据库操作帮助类

    // 定义登录模式的枚举
    private enum LoginMode {
        PASSWORD,   // 密码登录
        VERIFY_CODE // 验证码登录
    }

    private LoginMode currentMode = LoginMode.PASSWORD; // 默认登录模式为密码登录

    @Override
    protected void onResume() {
        super.onResume();
        dbHelper = UserDBHelper.getInstance(this, 1); // 获取数据库实例
        dbHelper.openWriteLink(); // 打开数据库的写入链接

        // 如果手机号已输入，尝试从数据库获取对应的密码
        String phoneStr = etPhone.getText().toString();
        UserInfo userInfo = dbHelper.queryByPhone(phoneStr);
        if (userInfo != null) {
            etPassword.setText(userInfo.password); // 自动填充密码
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        dbHelper.closeLink(); // 关闭数据库连接，避免资源泄漏
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_passwd); // 设置布局文件

        initViews(); // 初始化控件
        setupListeners(); // 设置监听器
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        rgLogin = findViewById(R.id.rg_login);
        tvPassword = findViewById(R.id.tv_password);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        btnForget = findViewById(R.id.btn_forget);
        ckRemember = findViewById(R.id.ck_remember);
        btnLogin = findViewById(R.id.btn_login);
    }

    /**
     * 设置控件的监听器
     */
    private void setupListeners() {
        rgLogin.setOnCheckedChangeListener(this); // 单选按钮组切换监听
        etPhone.addTextChangedListener(new HideTextWatcher(etPhone, 11)); // 手机号输入框监听
        etPassword.addTextChangedListener(new HideTextWatcher(etPassword, 6)); // 密码/验证码输入框监听
        btnForget.setOnClickListener(this); // 忘记密码按钮点击监听
        btnLogin.setOnClickListener(this); // 登录按钮点击监听
        ckRemember.setOnCheckedChangeListener(this); // 记住密码复选框监听
    }

    /**
     * 监听登录模式的切换
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // 根据选中的按钮切换登录模式
        currentMode = (checkedId == R.id.rb_password) ? LoginMode.PASSWORD : LoginMode.VERIFY_CODE;
        updateUIForMode(); // 更新界面
    }

    /**
     * 根据当前登录模式更新界面
     */
    private void updateUIForMode() {
        if (currentMode == LoginMode.PASSWORD) {
            tvPassword.setText("密码登录");
            etPassword.setHint("请输入密码");
            btnForget.setText("忘记密码");
            ckRemember.setVisibility(View.VISIBLE);
        } else {
            tvPassword.setText("验证码:");
            etPassword.setHint("请输入验证码");
            btnForget.setText("获取验证码");
            ckRemember.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 监听控件焦点变化
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.et_password && hasFocus && etPhone.getText().toString().length() < 11) {
            Toast.makeText(this, "手机号码必须为11位", Toast.LENGTH_SHORT).show();
            etPhone.requestFocus(); // 如果手机号不足11位，将焦点重新设置到手机号输入框
        }
    }

    /**
     * 按钮点击事件处理
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_forget) {
            handleForgetAction(); // 忘记密码或获取验证码
        } else if (v.getId() == R.id.btn_login) {
            handleLoginAction(); // 登录操作
        }
    }

    /**
     * 忘记密码或获取验证码的处理逻辑
     */
    private void handleForgetAction() {
        if (currentMode == LoginMode.PASSWORD) {
            Toast.makeText(this, "密码登录", Toast.LENGTH_SHORT).show();
        } else {
            // 生成随机验证码并显示
            verifyCode = String.format(Locale.ROOT, "%06d", new Random().nextInt(1000000));
            showAlert("验证码是：" + verifyCode);
        }
    }

    /**
     * 登录操作处理
     */
    private void handleLoginAction() {
        String input = etPassword.getText().toString(); // 获取用户输入
        if (currentMode == LoginMode.PASSWORD) {
            processPasswordLogin(input); // 处理密码登录
        } else {
            processVerifyCodeLogin(input); // 处理验证码登录
        }
    }

    /**
     * 处理密码登录
     */
    private void processPasswordLogin(String inputPassword) {
        // 默认密码
        String password = "123456";
        if (inputPassword.equals(password)) {
            loginSuccess();
            if (remember) saveUserInfo(); // 如果记住密码，保存用户信息
        } else {
            Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 处理验证码登录
     */
    private void processVerifyCodeLogin(String inputCode) {
        if (inputCode.equals(verifyCode)) {
            loginSuccess();
        } else {
            Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 登录成功处理
     */
    private void loginSuccess() {
        Toast.makeText(this, "恭喜你登录成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class)); // 跳转到主界面
    }

    /**
     * 保存用户信息到数据库
     */
    private void saveUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.phone = etPhone.getText().toString();
        userInfo.password = etPassword.getText().toString();
        dbHelper.insert(userInfo);
    }

    /**
     * 显示提示框
     */
    private void showAlert(String message) {
        new AlertDialog.Builder(this)
                .setTitle("验证码")
                .setMessage(message)
                .setPositiveButton("确定", null)
                .create()
                .show();
    }

    /**
     * 记住密码复选框状态变化
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        remember = isChecked; // 更新记住密码标志
    }

    /**
     * 自定义文本监听器类，用于隐藏输入法
     */
    private static class HideTextWatcher implements TextWatcher {
        private final EditText editText;
        private final int maxLength;

        public HideTextWatcher(EditText editText, int maxLength) {
            this.editText = editText;
            this.maxLength = maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == maxLength) {
                ViewUtil.hideOneInputMethod(editText.getContext(), editText); // 隐藏输入法
            }
        }
    }
}
