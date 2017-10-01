package com.example.edz;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;

import org.json.JSONObject;

import javax.security.auth.login.LoginException;

public class JavaJsActivity extends Activity {

    private static final String TAG = "tag";
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_js);
        mWebView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = mWebView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);


        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        mWebView.addJavascriptInterface(new AndroidtoJs(), "test");//AndroidtoJS类对象映射到js的test对象
       //添加客户端支持
        mWebView.setWebChromeClient(new WebChromeClient());
        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
        mWebView.loadUrl("file:///android_asset/javascript.html");





    }

    //在java中调用js代码
    public void sendInfoToJs(View view) {
        final String msg = ((EditText) findViewById(R.id.input_et)).getText().toString();
        Log.e(TAG, "sendInfoToJs: ");

       new Handler().post(new Runnable() {
            public void run() {
                //android 调 js
               // mWebView.loadUrl("javascript:avc()");
                //Log.e("tag", "run: -------安卓 调用js");

                final boolean isLogin = false;
                final String token = "1233";
                mWebView.loadUrl("javascript:huiqucanshu('"+isLogin+"','"+token+"')");
            }
        });


    }

    // 继承自Object类
    public class AndroidtoJs extends Object {

        // 定义JS需要调用的方法
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        public void hello(String msg) {
            Log.e("tag","JS调用了Android的hello方法");
        }
       /* @JavascriptInterface
        public void prodectAction(JSONObject jsonObject) {

            Log.e("tag","web");
            Log.e("tag",jsonObject.toString());

            try{

                String   productId = jsonObject.getString("id");
                Log.e("tag",productId);

                //getProductata();
            }catch (Exception e){


            }

        }*/

        @JavascriptInterface
        public void prodectAction(String  info) {

            Log.e("tag","web");
            Log.e("tag",info);

            try{
                JSONObject jsonObject = new JSONObject(info);

                String   productId = jsonObject.getString("id");
                int   b = jsonObject.getInt("b");
                Log.e("tag",productId);
                Log.e("tag",b+"");

                //getProductata();
            }catch (Exception e){


            }

        }

    }

    /*@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        new Handler().post(new Runnable() {
            public void run() {
                //android 调 js
                // mWebView.loadUrl("javascript:avc()");
                //Log.e("tag", "run: -------安卓 调用js");

                final boolean isLogin = false;
                final String token = "1233";
                mWebView.loadUrl("javascript:huiqucanshu('"+isLogin+"','"+token+"')");
            }
        });


    }*/
}
