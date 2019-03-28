package cn.logcode.traffic.http.request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import cn.logcode.traffic.bean.CarBean;
import cn.logcode.traffic.http.BaseRequest;
import cn.logcode.traffic.http.RequestListener;

/**
 * Created by Planet on 2018/4/27.
 */

public class CarRechargeRequest extends BaseRequest<CarBean> {
    private int carID;
    private int balance;


    public CarRechargeRequest(int carID,int balance){
        this.carID=carID;
        this.balance=balance;
    }
    @Override
    public String action() {
        return "recharge/carRecharge";
    }

    @Override
    public String getJsonContent() {
        JSONObject object=new JSONObject();
        try {
            object.put("carId",carID)
                    .put("balance",balance);
            Log.i("======",balance+"balance");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public void request(RequestListener listener) {
        postBodyRequest(CarBean.class,listener);
    }
}
