package cn.logcode.traffic.http.request;

import org.json.JSONException;
import org.json.JSONObject;

import cn.logcode.traffic.bean.CarsBean;
import cn.logcode.traffic.http.BaseRequest;
import cn.logcode.traffic.http.RequestListener;

/**
 * Created by Planet on 2018/4/27.
 */

public class CarSelectRequest extends BaseRequest<CarsBean> {
    int userId;

    public CarSelectRequest(int userId){
        this.userId=userId;
    }

    @Override
    public String action() {
        return "query/carUserQuery";
    }

    @Override
    public String getJsonContent() {
        JSONObject obj=new JSONObject();
        try {
            obj.put("userId",userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }

    @Override
    public void request(RequestListener listener) {
        postBodyRequest(CarsBean.class,listener);
    }
}
