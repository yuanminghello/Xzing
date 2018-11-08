package com.test.yuan.yuanminglei.precenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.test.yuan.yuanminglei.MainActivity;
import com.test.yuan.yuanminglei.bean.User;
import com.test.yuan.yuanminglei.net.Net;

/**
 * date:2018/11/08
 * author:袁明磊(123)
 * function:
 */
public class LoginUser {
    String name;
    String pass;
    User user;
    public LoginUser(LoginUserInterface loginUserInterface) {
        mLoginUserInterface = loginUserInterface;
        user = new User();
    }

    public boolean submit(String name1,String pass1){
        name=name1;
        pass=pass1;
        if (TextUtils.isEmpty(name1)&&TextUtils.isEmpty(pass1)) {

            return false;
        }else {
            return true;
        }

    }
String url = "http://apis.juhe.cn/cook/query?key=5fe1d350dc0779e3bfe2e4fb0c27709a&menu=%E8%A5%BF%E7%BA%A2%E6%9F%BF&rn=10&pn=3";
    public void commit(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                Net net = new Net(url,name,pass);
                if(net.getRequest()){
                    mLoginUserInterface.success();
                }else {
                    mLoginUserInterface.fail();
                }
            }
        }).start();

    }

    public interface LoginUserInterface{
        void success();
        void fail();
    }
    public LoginUserInterface mLoginUserInterface;

//    public void setLoginUserInterface(LoginUserInterface loginUserInterface) {
//        mLoginUserInterface = loginUserInterface;
//    }
}
