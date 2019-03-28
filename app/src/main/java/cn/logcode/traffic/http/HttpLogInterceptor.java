package cn.logcode.traffic.http;

import android.util.Log;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class HttpLogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n=method= "+request.method()+"\r\n");
        sb.append("=url= "+request.url().url()+"\r\n");

        Headers headers = request.headers();
        sb.append("==headers==\r\n");
        for (int i=0;i<headers.size();i++){
                sb.append("="+headers.name(i)+":"+headers.value(i)+"\r\n");
        }
        RequestBody body = request.body();
        if(null != body){
            Buffer buffer = new Buffer();
            body.writeTo(buffer);

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = body.contentType();

            if (null != contentType) {
                charset = contentType.charset();

                if (null == charset) {
                    charset = Charset.forName("UTF-8");
                }
            }

            if (isPlaintext(buffer)) {
                sb.append(buffer.readString(charset));
                if (null != contentType) {
                    sb.append("		(Content-Type = ").append(contentType.toString()).append(", ")
                            .append(body.contentLength()).append("-byte body");
                }
            } else {
                if (null != contentType) {
                    sb.append("		(Content-Type = ").append(contentType.toString())
                            .append(", binary").append(body.contentLength()).append("-byte body omitted");
                }
            }
            
        }
        else{
            sb.append("=Request Body [ Body is Null ]");
        }

        Response response = chain.proceed(request);

        //响应数据
        ResponseBody responseBody = response.body();

        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();
        Charset charset = Charset.defaultCharset();
         MediaType contentType = body.contentType();
        if (null != contentType) {
            charset = contentType.charset(charset);
        }
        String bodyString = buffer.clone().readString(charset);
        Log.e("HttpLog", String.format(Locale.getDefault(), "请求数据：%s \n响应数据：[ %s ]",sb.toString(),bodyString));


        return response;
    }


    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            e.printStackTrace();
            return false;
        }
    }

}
