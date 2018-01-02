package util;

import data.model.City;
import data.model.Province;
import data.model.Route;
import data.model.TotalPlan;
import data.savedata.ProvinceData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 江婷婷 on 2018/1/2.
 */
public class Algorithm {

    public static List<TotalPlan> plans = new ArrayList<>();
    public static List<City> cityList;
    public static boolean[] isRead;

    /**
     * 罗列城市到城市的所有可行方案
     * @param startCity
     * @param endCity
     * @return
     */
    public static List<TotalPlan> allPlans(City startCity, City endCity) {
        plans.clear();
        init();
        List<Route> routes = new ArrayList<>();//相当于一个TotalPlan
        dfs(startCity, endCity, null, routes);
        return plans;

    }

    private static void dfs(City startCity, City endCity, Route lastRoute, List<Route> routes) {
        for (Route r : startCity.getRouteList()) {
            //当上一个Route为空或时间点和这个Route不交叉
            if (lastRoute == null || r.getStartTime().after(lastRoute.getEndTime())) {
                routes.add(r);
//                System.out.println(startCity.getCityName());
                isRead[cityList.indexOf(startCity)] = true;

                City nextCity = r.getEndStation();
                //下一个城市没读过
                if (!isRead[cityList.indexOf(nextCity)]) {
                    if (nextCity.getCityName().equals(endCity.getCityName())) {
                        List<Route> rs = new ArrayList<>();
                        rs.addAll(routes);//复制routes
                        plans.add(new TotalPlan(rs));//添加一个完成好的TotalPlan
                        routes.remove(routes.size() - 1);
                    } else {
//                        isRead[cityList.indexOf(nextCity)] = true;
                        dfs(nextCity, endCity, r, routes);
                    }
                } else { //下一个城市读过了
                    routes.remove(routes.size() - 1);
                }
            }

        }
        if (routes.size() > 0) {
            routes.remove(routes.size() - 1);
            isRead[cityList.indexOf(startCity)] = false;
        }
    }



    private static void init() {
        cityList = new ArrayList<>();
        for (Province p : ProvinceData.provinces) {
            cityList.addAll(p.getCityList());
        }
        isRead = new boolean[cityList.size()];
        for (int i = 0; i < isRead.length; i++) {
            isRead[i] = false;
        }
    }


}
