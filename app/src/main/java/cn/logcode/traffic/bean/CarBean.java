package cn.logcode.traffic.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Planet on 2018/4/27.
 */
@DatabaseTable(tableName = "carbean")
public class CarBean  {
    public CarBean() {
    }
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "aftermonet")
    private int aftermonet;
    @DatabaseField(columnName = "money")
    private int money;
    @DatabaseField(columnName = "carId")
    private int carId;
    @DatabaseField(columnName = "username")
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAftermonet() {
        return aftermonet;
    }

    public void setAftermonet(int aftermonet) {
        this.aftermonet = aftermonet;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @DatabaseField(columnName = "date")
    private Date date;
}
