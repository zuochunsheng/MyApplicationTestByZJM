package com.example.edz.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;

import com.example.edz.R;
import com.github.barteksc.pdfviewer.PDFView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/*
  pdf
  http://blog.csdn.net/u010046908/article/details/53008310  本地



   1 预览 网上pdf  先下载 再加载
   2 加载本地PDF
 */
public class ProtocolActivity extends Activity {

    private PDFView pdfView;

    String pdfUrl = "http://file.chmsp.com.cn/colligate/file/00100000224821.pdf" ;

    private ProgressDialog dialogProgress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol);

        pdfView = (PDFView) findViewById(R.id.pdfView);


       /*
        String  pdfName = Environment.getExternalStorageDirectory() + "/download";
        //testPic1.pdf为文件下载后的命名
        File file = new File(pdfName, "testPic1.pdf");

        */

        dialogProgress = ProgressDialog.show(this, null, "pdf正在加载，请稍后...");
        downFile(pdfUrl);


       //另外一个 本地方法 用法  可行
       /* pdfView.fromAsset("00100000224821.pdf")
                .defaultPage(1)
                //.onPageChange(ProtocolActivity.this)
                .swipeVertical(true)
                .showMinimap(false)
                .enableAnnotationRendering(true)
                //.onLoad(ProtocolActivity.this)
                .load();
*/




    }


    private void downFile(final String urlString) {

        new Thread() {
            @Override
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(urlString);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    long length = entity.getContentLength();
                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;

                    String  savePath = getExternalCacheDir().getAbsolutePath();

                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdir();
                    }

                    final String saveFileName = savePath + "/zjm_protocol.pdf";
                    File apkFile = new File(saveFileName);
                    if (is != null) {
                        fileOutputStream = new FileOutputStream(apkFile);

                        byte[] buf = new byte[1024];
                        int ch;
                        int count = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            count += ch;
                            if (length > 0) {
                            }
                        }
                    }

                    fileOutputStream.flush();
                    fileOutputStream.close();

                    dialogProgress.dismiss();
                    //2 显示pdf

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            displayFromFile(new File(saveFileName));
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }

    private void displayFromFile( File file ) {
        Uri uri = Uri.fromFile(file);
        pdfView.fromUri(uri)
                .defaultPage(1)
                .enableDoubletap(true)
                //.onDraw(this)
                //.onPageChange(this)
                .enableAnnotationRendering(true)
                //.onLoad(this)
                .enableDoubletap(true)
                .swipeVertical(true)
                .load();
    }


}
