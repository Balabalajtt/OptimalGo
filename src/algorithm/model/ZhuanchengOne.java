package algorithm.model;

import data.model.City;
import data.model.Province;
import data.model.Route;
import data.model.TotalPlan;
import data.savedata.ProvinceData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 以一个城市未起点，到达所有城市的转乘数据
 * Created by 江婷婷 on 2018/1/2.
 */
public class ZhuanchengOne {
    private Map<City, Data> oneCityData;//终点站和相关数据
    private City startCity;

//    public ZhuanchengOne(City startCity) {
//        oneCityData = new HashMap<>();
//        for (Province p : ProvinceData.provinces) {
//            for (City c : p.getCityList()) {
//                oneCityData.put(c, new Data());
//            }
//        }
//        this.startCity = startCity;
//        oneCityData.get(startCity).pathLength = 0;
////        oneCityData.get(startCity).isKnow = true;
//        oneCityData.get(startCity).path = new ArrayList<>();
//        wuquan();
//
//        for(City c : oneCityData.keySet()) {
//            System.out.println(getStartCity().getCityName() + "到" + c.getCityName() + ":" + oneCityData.get(c).pathLength + oneCityData.get(c).isKnow);
//            for (Route r : oneCityData.get(c).path) {
//                System.out.print(r.getEndStation().getCityName() + "");
//            }
//            System.out.println();
//        }
//
//    }

//    private void wuquan() {
//        for (int i = 0; i < oneCityData.size(); i++) {
//            for (City c : oneCityData.keySet()) {
//                if (!oneCityData.get(c).isKnow && oneCityData.get(c).pathLength == i) {
//                    oneCityData.get(c).isKnow = true;
//                    for (Route r : c.getRouteList()) {
////                        System.out.println("111");
//
//                        if (oneCityData.get(r.getEndStation()).pathLength == Integer.MAX_VALUE) {
//                            oneCityData.get(r.getEndStation()).pathLength = i + 1;
//                            List<Route> routes = new ArrayList<>();
//                            for (Route rr : oneCityData.get(c).getPath()) {
//                                routes.add(rr);
//                            }
//                            routes.add(r);
//                            oneCityData.get(r.getEndStation()).path = routes;
//                        }
//                    }
//                }
//            }
//        }
//    }

    public Map<City, Data> getOneCityData() {
        return oneCityData;
    }

    public City getStartCity() {
        return startCity;
    }

    public class Data {
        private List<TotalPlan> path;//路线
        private int pathLength;//转乘数
        private boolean isKnow;

        public Data() {
            path = new ArrayList<>();
            pathLength = Integer.MAX_VALUE;
            isKnow = false;
        }

        public List<TotalPlan> getPath() {
            return path;
        }

        public boolean isKnow() {
            return isKnow;
        }

        public void setKnow(boolean know) {
            isKnow = know;
        }

        /**
         * 填充路线
         * 修改长度
         * @param path
         */
        public void setPath(List<TotalPlan> path) {
            this.path = path;
            for (TotalPlan t : path) {
                if (t.getRouteList().size() < pathLength) {
                    pathLength = t.getRouteList().size();
                }
            }
        }

        public int getPathLength() {
            return pathLength;
        }
    }

}
