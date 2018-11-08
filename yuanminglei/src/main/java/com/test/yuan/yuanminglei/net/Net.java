package com.test.yuan.yuanminglei.net;


import com.google.gson.Gson;
import com.test.yuan.yuanminglei.bean.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * date:2018/11/08
 * author:袁明磊(123)
 * function:
 */
public class Net {
    String name;
    String pass;
    String urll;
    private User mJson;

    public Net(String url, String name1, String pass1) {
        this.urll=url;
        name=name1;
        pass=pass1;
    }

    public boolean getRequest(){
        String result = null;
        URL url = null;
        try {
            url = new URL(urll);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            int responseCode = urlConnection.getResponseCode();
            if(responseCode==200){
               result = String2string(urlConnection.getInputStream());
                Gson gson = new Gson();
                mJson = gson.fromJson(result, User.class);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

       if (mJson.getResultcode().equals(name)&&mJson.getError_code().equals(pass)) {
            return true;
       }else {
            return false;
       }


   }

       private String String2string(InputStream inputStream) {
           StringBuffer buffer = new StringBuffer();
           BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
           try {
               for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
                   buffer.append(temp);
               }
           } catch (IOException e) {
               e.printStackTrace();
           } finally {
               try {
                   reader.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           return buffer.toString();
       }
}
