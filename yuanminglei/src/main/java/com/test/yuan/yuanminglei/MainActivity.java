package com.test.yuan.yuanminglei;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.yuan.yuanminglei.bean.User;
import com.test.yuan.yuanminglei.net.Net;
import com.test.yuan.yuanminglei.precenter.LoginUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginUser.LoginUserInterface {

    private TextView et1;
    private EditText username;
    private TextView et2;
    private EditText password;
    private Button login;
    private ProgressDialog mDialog;
    private LoginUser mLoginUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        et1 = (TextView) findViewById(R.id.et1);
        username = (EditText) findViewById(R.id.username);
        et2 = (TextView) findViewById(R.id.et2);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(this);
        initprocess();

        mLoginUser = new LoginUser(this);
    }

    private void initprocess() {
        mDialog = new ProgressDialog(this);
    }

    //
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                final String usernameString = username.getText().toString().trim();
                final String passwordString = password.getText().toString().trim();

               // final User user = new User();
                boolean info = mLoginUser.submit(usernameString,passwordString);

                if(info){
                    mDialog.show();

                    mLoginUser.commit();
                }else {
                    Toast.makeText(this, "请输入帐号,密码", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void success() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mDialog.dismiss();
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
        }
        });
    }

    @Override
    public void fail() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDialog.dismiss();

                Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
 //防止内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginUser=null;
    }
}
