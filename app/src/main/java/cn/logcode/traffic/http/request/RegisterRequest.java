package cn.logcode.traffic.http.request;

import org.json.JSONException;
import org.json.JSONObject;

import cn.logcode.traffic.bean.NullBean;
import cn.logcode.traffic.http.BaseRequest;
import cn.logcode.traffic.http.RequestListener;

/**
 * Created by 24327 on 2018/4/27.
 */

public class RegisterRequest extends BaseRequest<NullBean> {

    private String userName;
    private String userPass;
    private String phoneNum;

    public RegisterRequest(String userName, String userPass, String phoneNum) {
        this.userName = userName;
        this.userPass = userPass;
        this.phoneNum = phoneNum;
    }

    @Override
    public String action() {
        return "user/userRegister";
    }

    @Override
    public String getJsonContent() {
        JSONObject object = new JSONObject();
        try {
            object.put("username", userName)
                    .put("userpass", userPass)
                    .put("phone", phoneNum);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @Override
    public void request(RequestListener listener) {
        postBodyRequest(NullBean.class,listener);
    }
}
