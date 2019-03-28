package cn.logcode.traffic.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtils {
    static OkHttpClient client = new OkHttpClient
            .Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(5,TimeUnit.SECONDS)
            .writeTimeout(5,TimeUnit.SECONDS)
            .addInterceptor(new HttpLogInterceptor())
            .build();
    static Gson gson = new GsonBuilder().serializeNulls().create();
}
