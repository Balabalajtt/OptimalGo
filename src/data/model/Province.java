package data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 江婷婷 on 2017/12/30.
 * 省份描述
 */
public class Province implements Serializable {
    private String provinceName;
    private List<City> cityList;

    public Province(String provinceName, List<City> cityList) {
        this.provinceName = provinceName;
        if (cityList != null) {
            this.cityList = cityList;
        } else {
            this.cityList = new ArrayList<>();
        }
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
