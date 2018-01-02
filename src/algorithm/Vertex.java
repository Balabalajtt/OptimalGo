package algorithm;

import data.model.City;
import data.model.Route;
import data.model.TotalPlan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 江婷婷 on 2018/1/1.
 */
public class Vertex {
    private City city;
    private Map<City, List<TotalPlan>> zhuancheng = new HashMap<>();//目的地

    public Vertex(City city) {
        this.city = city;
        initCityMap();
    }


    private void initCityMap() {
        for (Route r : city.getRouteList()) {
            List<Route> routes = new ArrayList<>();
            routes.add(r);
            TotalPlan totalPlan = new TotalPlan(routes);
            List<TotalPlan> totalPlanList = new ArrayList<>();
            totalPlanList.add(totalPlan);
            zhuancheng.put(r.getEndStation(), totalPlanList);
        }
    }

    public City getCity() {
        return city;
    }

    public Map<City, List<TotalPlan>> getZhuancheng() {
        return zhuancheng;
    }

    public List<TotalPlan> getCityTotalPlan(City city) {
        return zhuancheng.get(city);
    }

    public void addZhuancheng(City city, TotalPlan totalPlan) {
        this.zhuancheng.get(city).add(totalPlan);

    }

}
