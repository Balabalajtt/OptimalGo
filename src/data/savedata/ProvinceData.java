package data.savedata;

import data.model.City;
import data.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 江婷婷 on 2017/12/30.
 * 所有省市数据
 */
public class ProvinceData {

    public static List<Province> provinces = new ArrayList<>();

    public static Province getProvince(String provinceName) {
        for (Province p : provinces) {
            if (provinceName.equals(p.getProvinceName())) {
                return p;
            }
        }
        return null;
    }

    public static City getCity(String cityName) {
        for (Province p : provinces) {
            for (City c : p.getCityList()) {
                if (c.getCityName().equals(cityName)) {
                    return c;
                }
            }
        }
        return null;
    }

    public static City getCity(String provinceName, String cityName) {
        for (Province p : provinces) {
            if (p.getProvinceName().equals(provinceName)) {
                for (City c : p.getCityList()) {
                    if (c.getCityName().equals(cityName)) {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    public static City getCity(Province province, String cityName) {
        for (City c : province.getCityList()) {
            if (c.getCityName().equals(cityName)) {
                return c;
            }
        }
        return null;
    }

        /**
         * 向省份数据列表中添加新省份
         * @param province 需要添加的省份
         * @return true:添加成功 false:添加失败
         */
    public static boolean addProvince(Province province) {
        if (!provinceExist(province)) {
            provinces.add(province);
            return true;
        }
        return false;
    }

    /**
     * 向省份中添加城市 只有当省份存在 城市不存在时才添加成功
     * @param province 省份
     * @param city 城市
     * @return 0:添加成功 1:省份不存在 2:城市存在
     */
    public static int addCity(Province province, City city) {
        int rtn = 0;
        if (!provinceExist(province)) {
           rtn = 1;
       } else if(cityExist(province, city)){
           rtn = 2;
       } else {
           province.getCityList().add(city);
       }
       return rtn;
    }

    /**
     * 只要不重复 就添加  省不存在的话先加省
     * @param provinceName
     * @param cityName
     */
    public static void addCityAlways(String provinceName, String cityName) {
        if (!provinceExist(new Province(provinceName, null))) {
            List<City> cities = new ArrayList<>();
            cities.add(new City(cityName));
            addProvince(new Province(provinceName, cities));
            return;
        }
        if (!cityExist(getProvince(provinceName), new City(cityName))) {
            addCity(getProvince(provinceName), new City(cityName));
        }
    }

    public static int addCity(Province province, List<City> cities) {
        int count = 0;
        for (City city : cities) {
            if (addCity(province, city) == 0) {
                count++;
                System.out.println(count);
            }
        }
        return count;
    }

    /**
     * 根据省份名删除省份
     * @param provinceName 省份名
     * @return true:删除成功 false:删除失败
     */
    public static boolean deleteProvince (String provinceName) {
        for (Province p : provinces) {
            if (p.getProvinceName().equals(provinceName)) {
                provinces.remove(p);
                return true;
            }
        }
        return false;

    }

    /**
     * 根据省份名城市名删除城市
     * @param provinceName 省份名
     * @param cityName 城市名
     * @return 0:删除成功 1:省份不存在 2:城市不存在
     */
    public static int deleteCity (String provinceName, String cityName) {
        Province province = getProvince(provinceName);
        if (province == null) {
            return 1;
        }
        City city = getCity(province, cityName);
        if (city == null) {
            return 2;
        }
        province.getCityList().remove(city);
        return 0;
    }

    private static boolean provinceExist(Province province) {
        boolean rtn = false;
        for (Province p : provinces) {
            if(province.getProvinceName().equals(p.getProvinceName())) {
                rtn = true;
                break;
            }
        }
        return rtn;
    }

    private static boolean cityExist(Province province, City city) {
        for (City c : province.getCityList()) {
            if (c.getCityName().equals(city.getCityName())) {
                return true;
            }
        }
        return false;
    }

}
