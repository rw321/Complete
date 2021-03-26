package com.example.complete.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.complete.R;
import com.example.complete.base.BaseActivity;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class OKHttpActivity extends BaseActivity {
    TextView tvContent;
    ImageView ivContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_okhttp;
    }

    @Override
    protected void initData() {
        super.initData();
        setTitle("OkHttp");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i) {
            //
            case R.id.one:
                asynGetRequest();
                break;
            //
            case R.id.two:
                syncGetRequest();
                break;
            //
            case R.id.three:
                postCommitString();
                break;
            //
            case R.id.four:
                postCommitStream();
                break;
            //
            case R.id.five:
                postCommitFile();
                break;
            //
            case R.id.six:
                postCommitForm();
                break;
            //
            case R.id.seven:
                downloadImage();
                break;
            //
            case R.id.eight:
                commitMultPartBody();
                break;

        }
        return true;
    }

    /**
     * 异步GET请求
     */
    private void asynGetRequest() {

        String url = "http://60.205.243.97:8088/cjapp/app/yngpHomePage.htm";
        OkHttpClient client = new OkHttpClient.Builder().build();
        final Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("exception = " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvContent.setText("response = " + content);
                    }
                });
            }
        });
    }

    /**
     * 同步GET请求
     */
    private void syncGetRequest() {
        String url = "http://60.205.243.97:8088/cjapp/app/yngpHomePage.htm";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Response response = call.execute();
                    String content = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvContent.setText(content);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * post请求提交String字符串
     */
    private void postCommitString() {
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        String content = "西塞山前白鹭飞,桃花流水鳜鱼肥,青箬笠,绿蓑衣,斜风细雨不须归.";
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(mediaType , content))
                .build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Headers headers = response.headers();
                for (int index = 0; index < headers.size(); index++) {
                    System.out.println("name = " + headers.name(index) + " ,value = " + headers.value(index));
                }
                System.out.println("response = " + response.body().string());
            }
        });
    }

    /**
     * post请求提交stream
     */
    private void postCommitStream() {
        RequestBody requestBody = new RequestBody() {

            @Nullable
            @Override
            public MediaType contentType() {
                return MediaType.parse("text/x-markdown; charset=utf-8");
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("闭上眼脑海里都是你,睁开眼,眼睛里都是你");
            }
        };
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("content = " + response.body().string());
            }
        });
    }

    /**
     * post请求提交文件
     */
    private void postCommitFile() {
        MediaType mediaType = MediaType.parse("text/x-maskdown; charset=utf-8");
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(mediaType , new File("")))
                .build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("response = " + response.body().string());
            }
        });
    }

    /**
     * post请求提交表单
     */
    private void postCommitForm() {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("name" , "Alex")
                .add("sex" , "1")
                .build();

        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("content = " + response.body().string());
            }
        });

    }

    /**
     * 下载图片
     */
    private void downloadImage() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://avatar.csdn.net/B/0/1/1_new_one_object.jpg")
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivContent.setImageBitmap(bitmap);
                    }
                });

            }
        });

    }

    /**
     * 提交表单 既有字段也有文件
     */
    private void commitMultPartBody() {
        File file = new File("file's name");
        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name" , "Alex")
                .addFormDataPart("age" , "28")
                .addFormDataPart("file" , file.getName(),RequestBody.create(MediaType.parse("file/*") , file))
                .build();

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, OKHttpActivity.class);
        context.startActivity(intent);
    }
}
