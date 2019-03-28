package cn.logcode.traffic.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class DBSense {
    public DBSense() {
    }

    public DBSense( int temperature, int humidity, int co2, int lightIntensity, int pm2_5, int address) {

        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
        LightIntensity = lightIntensity;
        this.pm2_5 = pm2_5;
        this.address = address;
    }

    @DatabaseField(generatedId = true)

    int id;
    @DatabaseField
    int temperature;
    @DatabaseField
    int humidity;
    @DatabaseField
    int co2;
    @DatabaseField
    int LightIntensity;
    @DatabaseField
    int pm2_5;
    @DatabaseField
    int address;

}
