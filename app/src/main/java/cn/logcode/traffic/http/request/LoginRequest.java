package cn.logcode.traffic.http.request;

import org.json.JSONException;
import org.json.JSONObject;

import cn.logcode.traffic.bean.UserBean;
import cn.logcode.traffic.http.BaseRequest;
import cn.logcode.traffic.http.RequestListener;

public class LoginRequest extends BaseRequest<UserBean> {

    String username;
    String userpass;

    public LoginRequest(String username, String userpass) {
        this.username = username;
        this.userpass = userpass;
    }

    @Override
    public String action() {
        return "save.do";
    }

    @Override
    public String getJsonContent() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("username",username)
                    .put("userpass",userpass);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return obj.toString();
    }

    @Override
    public void request(RequestListener listener) {

        postBodyRequest(UserBean.class,listener);
    }
}
