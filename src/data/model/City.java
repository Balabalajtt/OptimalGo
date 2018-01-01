package data.model;


import java.io.Serializable;

/**
 * Created by 江婷婷 on 2017/12/30.
 * 城市描述
 */

public class City implements Serializable {
    private String cityName;

    public City(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
