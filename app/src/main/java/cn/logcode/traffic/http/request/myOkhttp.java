package cn.logcode.traffic.http.request;


import android.os.Handler;
import android.os.Looper;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class myOkhttp {

    private static Handler handler =  new Handler(Looper.getMainLooper());



    public static void myokhttp(String url,String json,myRequestListener listener){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.failed(e,e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.success(json);
                    }
                });
            }
        });
    }
}
