package cn.logcode.traffic.http;
import android.os.Handler;
import android.util.Log;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Map;
import cn.logcode.traffic.App;
import cn.logcode.traffic.config.AppConfig;
import cn.logcode.traffic.utils.Utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//请求基类
public abstract class BaseRequest<T> extends OkhttpUtils {
    protected Handler handler = new Handler();

    protected MediaType type = MediaType.parse("application/json; charset=utf-8");

    // 返回样例 "transportservice/type/jason/action/GetCarAccountBalance.do"
    public abstract String action();

    public String getJsonContent(){
        return null;
    }

    //请求体表单
    public Map<String, String> getFormParams() {
        return null;
    }

    public abstract void request(RequestListener listener);

    protected void postBodyRequest(Class<?> cls, RequestListener listener) {
        if(!Utils.isNetWork(App.INSTANCE.getApplicationContext())){
             return;
        }
        String content = "{}";
        if(null != getJsonContent()){
            content = getJsonContent();
        }
        Log.i("=content=", content);
        RequestBody requestBody = RequestBody.create(type, content);
        Request request = new Request
                .Builder()
                .post(requestBody)
                .url(AppConfig.BASE_URL + action())
                .build();
        Log.i("*****", "postBodyRequest: "+AppConfig.BASE_URL + action());
//        http://10.35.70.117:8080/stu/save.do
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(()->listener.failed(e,e.getMessage()));

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String str = response.body().string();
                try {
                    JSONObject obj = new JSONObject(str);
                    Log.i("******** ", "onResponse: "+str);
                    if(obj.getInt("code") == 200){
                        handler.post(() -> {
                            T bean = null;
                            try {
                                bean = gson.fromJson(String.valueOf(obj.getJSONObject("data")), (Class<T>) cls);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(null == bean){
                                try {
                                    bean = (T) cls.newInstance();
                                } catch (InstantiationException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                            listener.success(bean);
                            Log.i("==bean==", bean.toString());
                        });
                    }else{
                        handler.post(()-> {
                            try {
                                listener.failed(new Exception(),obj.getString("message"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    protected void postBodyRequest(TypeToken typeToken, RequestListener listener) {
        String content = "{}";
        if(null != getJsonContent()){
            content = getJsonContent();
        }
        Log.i("=content=", content);
        RequestBody requestBody = RequestBody.create(type, content);
        Request request = new Request
                .Builder()
                .post(requestBody)
                .url(AppConfig.BASE_URL + action())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.failed(e,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String str = response.body().string();
                try {
                    JSONObject obj = new JSONObject(str);
                    if(obj.getInt("code") == 200){
                        handler.post(() -> {
                            T bean = null;
                            try {
                                bean = gson.fromJson(String.valueOf(obj.getJSONArray("data")),typeToken.getType());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            listener.success(bean);
                            Log.i("==bean==", bean.toString());
                        });
                    }else{
                        listener.failed(new Exception(),obj.getString("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
