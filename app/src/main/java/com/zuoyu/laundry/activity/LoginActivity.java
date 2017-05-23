package com.zuoyu.laundry.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zuoyu.laundry.R;
import com.zuoyu.laundry.base.BaseActivity;
import com.zuoyu.laundry.utils.IntentUtil;
import com.zuoyu.laundry.utils.ToastUtil;
import com.zuoyu.laundry.utils.ToolUtil;
import com.zuoyu.laundry.widget.ClearEditText;

import static com.zuoyu.laundry.R.id.tv_forget;

/**
 * <pre>
 * Function：登录页面
 *
 * Created by JoannChen on 2017/5/16 19:07
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class LoginActivity extends BaseActivity {

    // 手机号／密码输入框
    private EditText usernameEdit, passwordEdit;

    // 登录按钮
    private Button loginBtn;

    @Override
    public int setLayout() {
        return R.layout.login_main;
    }

    @Override
    public void initTitle() {
        titleManage.hideTitle();
    }

    @Override
    public void initView() {

        // 账号输入框
        usernameEdit = (ClearEditText) findViewById(R.id.et_username);
        usernameEdit.addTextChangedListener(watcher);

        // 密码输入框
        passwordEdit = (ClearEditText) findViewById(R.id.et_password);
        passwordEdit.addTextChangedListener(watcher);

        // 登录按钮
        loginBtn = (Button) findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(this);
        loginBtn.setEnabled(false);

        // 忘记密码
        TextView forgerText = (TextView) findViewById(tv_forget);
        forgerText.setOnClickListener(this);
    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                parseLogin();
                break;
            case R.id.tv_forget:
                IntentUtil.start(activity, ForgetActivity.class, false);
                break;
        }
    }

    /**
     * 监听文本变化
     */
    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

            if (passwordEdit.hasFocus()) {
                if (start < 6) {
                    ToastUtil.show(getString(R.string.please_input_password_length));
                }
            }

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

            // 交互：当手机号和验证码均不为空时，且手机号长度为11位，登录按钮高亮显示
            if (usernameEdit.getText().length() == 0
                    || passwordEdit.getText().length() < 6) {
                ToolUtil.setBtnClick(loginBtn, false);
            } else {
                ToolUtil.setBtnClick(loginBtn, true);
            }

        }


        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    /**
     * 【解析】登录
     */
    private void parseLogin() {

    }

}
