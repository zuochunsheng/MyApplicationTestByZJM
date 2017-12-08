package com.example.edz;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.example.edz.util.LogUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;



/*
对于Android调用JS代码的方法有2种：
1. 通过WebView的loadUrl（）-----带参数 ，不带参数
2. 通过WebView的evaluateJavascript（）-----有回调

对于JS调用Android代码的方法有3种：
1. 通过WebView的addJavascriptInterface（）进行对象映射
2. 通过 WebViewClient 的shouldOverrideUrlLoading ()方法回调拦截 url
3. 通过 WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt（）方法回调拦截JS对话框alert()、confirm()、prompt（） 消息


http://blog.csdn.net/carson_ho/article/details/64904691

 */

public class JavaJsActivity extends Activity {

    private static final String TAG = "tag";
    private WebView mWebView;
   // String h5 = "http://www.huijindai123.com/hjdh5/help_center/index.html";

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
        mWebView.addJavascriptInterface(new AndroidtoJs(), "androidAndjs");//AndroidtoJS类对象映射到js的test对象
        //添加客户端支持
        mWebView.setWebChromeClient(new WebChromeClient());
        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
        mWebView.loadUrl("file:///android_asset/javascript.html");
       // mWebView.loadUrl(h5);


        // 复写WebViewClient类的shouldOverrideUrlLoading方法
        mWebView.setWebViewClient(new WebViewClient() {
              @Override
              public boolean shouldOverrideUrlLoading(WebView view, String url) {

                  // 步骤2：根据协议的参数，判断是否是所需要的url
                  // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                  //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）

                  Uri uri = Uri.parse(url);
                  // 如果url的协议 = 预先约定的 js 协议
                  // 就解析往下解析参数
                  if (uri.getScheme().equals("js")) {

                      // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                      // 所以拦截url,下面JS开始调用Android需要的方法
                      if (uri.getAuthority().equals("webview")) {

                          //  步骤3：
                          // 执行JS所需要调用的逻辑
                          Log.e("tag", "JS调用了Android的方法--shouldOverrideUrlLoading");
                          // 可以在协议上带有参数并传递到Android上
                          HashMap<String, String> params = new HashMap<>();
                          //Set<String> collection = uri.getQueryParameterNames();
                          String arg1 = uri.getQueryParameter("arg1");
                          String query = uri.getQuery();
                          //LogUtil.e(collection);//["arg1","arg2"]
                          LogUtil.e(query);//arg1=111&arg2=222
                          LogUtil.e(arg1);//111
                      }

                      return true;
                  }
                  return super.shouldOverrideUrlLoading(view, url);
              }
          }
        );


        //无效
        mWebView.setWebChromeClient(new WebChromeClient() {
                // 拦截输入框(原理同方式2)
                // 参数message:代表promt（）的内容（不是url）
                // 参数result:代表输入框的返回值
                @Override
                public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                    // 根据协议的参数，判断是否是所需要的url(原理同方式2)
                    // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                    //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）

                    Uri uri = Uri.parse(url);
                    // 如果url的协议 = 预先约定的 js 协议
                    // 就解析往下解析参数
                    if ( uri.getScheme().equals("js")) {

                        // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                        // 所以拦截url,下面JS开始调用Android需要的方法
                        if (uri.getAuthority().equals("webview")) {

                            //
                            // 执行JS所需要调用的逻辑
                            //System.out.println("js调用了Android的方法");
                            LogUtil.e("js调用了Android的方法");
                            // 可以在协议上带有参数并传递到Android上
                            HashMap<String, String> params = new HashMap<>();
                            Set<String> collection = uri.getQueryParameterNames();

                            //参数result:代表消息框的返回值(输入值)
                            result.confirm("js调用了Android的方法成功啦");
                        }
                        return true;
                    }
                    return super.onJsPrompt(view, url, message, defaultValue, result);
                }

// 通过alert()和confirm()拦截的原理相同，此处不作过多讲述

                // 拦截JS的警告框
                @Override
                public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                    return super.onJsAlert(view, url, message, result);
                }

                // 拦截JS的确认框
                @Override
                public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                    return super.onJsConfirm(view, url, message, result);
                }
            }
        );




        // 没什么用
        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
//        mWebView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
//                AlertDialog.Builder b = new AlertDialog.Builder(JavaJsActivity.this);
//                b.setTitle("Alert");
//                b.setMessage(message);
//                b.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        result.confirm();
//                    }
//                });
//                b.setCancelable(false);
//                b.create().show();
//                return true;
//            }
//
//        });


    }

    // 在Android 中调用js代码
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void sendInfoToJs(View view) {
        final String msg = ((EditText) findViewById(R.id.input_et)).getText().toString();
        Log.e(TAG, "sendInfoToJs: ");

        // 可行
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                //android 调 js
//                // mWebView.loadUrl("javascript:avc()");
//                //Log.e("tag", "run: -------安卓 调用js");
//
//                final boolean isLogin = false;
//                final String token = "1233";
//                mWebView.loadUrl("javascript:huiqucanshu('" + isLogin + "','" + token + "')");
//            }
//        });

        // 可行
        // 必须另开线程进行JS方法调用(否则无法调用)
//        mWebView.post(new Runnable() {
//            @Override
//            public void run() {
//
//                // 注意调用的JS方法名要对应上
//                mWebView.loadUrl("javascript:avc()");
//            }
//        });



       /* 优点：该方法比第一种方法效率更高、使用更简洁。

        因为该方法的执行不会使页面刷新，而第一种方法（loadUrl ）的执行则会。
        Android 4.4 后才可使用*/
        mWebView.evaluateJavascript("javascript:avcCalljs()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                //此处为 js 返回的结果
                Log.e(TAG, "js 回调返回的结果 = " + s);
            }
        });


    }


    // js 调用 Android
//下面这种方式 存在严重的漏洞问题 ,可以有返回值
// 继承自Object类
    public class AndroidtoJs extends Object {

        // 定义JS需要调用的方法
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        public void hello(String msg) {
            Log.e("tag", "JS调用了Android的hello方法");
        }

        @JavascriptInterface
        public String hello() {
            Log.e("tag", "JS调用了Android的hello方法 有返回值");
            return "Android的hello方法 有返回值";
        }

        @JavascriptInterface
        public String hello2() {
            Log.e("tag", "JS调用了Android的hello方法 有返回值  2");

            //  String data = "{\"token\":\"token\",\"invitecode\":\"invitecode\"}";

            String token = "token值";
            String invitecode = "invitecode值";
            String data = "{\"token\":\"" + token + "\",\"invitecode\":\"" + invitecode + "\"}";

            Log.e("tag", data);
            return data;
        }


        //有 问题
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
        public void prodectAction(String info) {

            Log.e("tag", "web");
            Log.e("tag", info);

            try {
                JSONObject jsonObject = new JSONObject(info);

                String productId = jsonObject.getString("id");
                int b = jsonObject.getInt("b");
                Log.e("tag", productId);
                Log.e("tag", b + "");

                //getProductata();
            } catch (Exception e) {


            }

        }

        //跳转主页
        @JavascriptInterface
        public void popToRoot() {

            Log.e("web", "popToRoot");


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



    // 快捷键 练习
    void foo(String s){
           //s.isEmpty().i

    }
    void foo(ArrayList<String> list){
        //list.f

    }

}
