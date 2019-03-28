package cn.logcode.traffic.utils.network;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkhttpApi {
    private static final String path1 = "lamplist";
    private static final String path2 = "gongjiao";


    public static void Illumination(MyCall myCall) {
        RequestBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.easy-mock.com/mock/5c949f1bf1327e51cff2c041/example/Illumination")
                .post(body)
                .build();
        NetworkApi.getNetworkApi().request(request, myCall);
    }

 public static void weather(MyCall myCall) {
        RequestBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/weather")
                .post(body)
                .build();
        NetworkApi.getNetworkApi().request(request, myCall);
    }





    public static void pm(MyCall myCall) {
        RequestBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.easy-mock.com/mock/5c949f1bf1327e51cff2c041/example/pm")
                .post(body)
                .build();
        NetworkApi.getNetworkApi().request(request, myCall);
    }
    public static void road(MyCall myCall) {
        RequestBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.easy-mock.com/mock/5c949f1bf1327e51cff2c041/example/road")
                .post(body)
                .build();
        NetworkApi.getNetworkApi().request(request, myCall);
    }
    public static void co2(MyCall myCall) {
        RequestBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.easy-mock.com/mock/5c949f1bf1327e51cff2c041/example/co")
                .post(body)
                .build();
        NetworkApi.getNetworkApi().request(request, myCall);
    }
    public static void humidity(MyCall myCall) {
        RequestBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.easy-mock.com/mock/5c949f1bf1327e51cff2c041/example/humidity")
                .post(body)
                .build();
        NetworkApi.getNetworkApi().request(request, myCall);
    }
    public static void temperature(MyCall myCall) {
        RequestBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.easy-mock.com/mock/5c949f1bf1327e51cff2c041/example/temperature")
                .post(body)
                .build();
        NetworkApi.getNetworkApi().request(request, myCall);
    }



    public static void senseapi(MyCall myCall) {
        RequestBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.easy-mock.com/mock/5c949f1bf1327e51cff2c041/example/test")
                .post(body)
                .build();
        NetworkApi.getNetworkApi().request(request, myCall);
    }


}
