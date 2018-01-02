package data.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 江婷婷 on 2017/12/30.
 * 城市描述
 */

public class City implements Serializable {
    private String cityName;

    private List<Route> routeList = new ArrayList<>();//以此为起点的

    public City(String cityName) {
        this.cityName = cityName;
    }

    public void addRoute(Route route) {
        routeList.add(route);
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }
}
