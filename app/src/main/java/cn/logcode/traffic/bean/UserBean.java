package cn.logcode.traffic.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public class UserBean {

    /**
     * userstatus : 1
     * phone : 1516615616
     * userpass : 123456
     * username : user0312
     */
    @DatabaseField(columnName = "userstatus",dataType = DataType.INTEGER)
    public int userstatus;
    @DatabaseField(columnName = "phone")
    public String phone;
    @DatabaseField(columnName = "userpass")
    public String userpass;
    @DatabaseField(columnName = "username")
    public String username;
    @DatabaseField(generatedId = true)
    public int userId;

    @Override
    public String toString() {
        return "UserBean{" +
                "userstatus=" + userstatus +
                ", phone='" + phone + '\'' +
                ", userpass='" + userpass + '\'' +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                '}';
    }
}
