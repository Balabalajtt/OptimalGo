import data.model.*;
import data.savedata.AlgorithmData;
import data.savedata.ProvinceData;
import data.savedata.TransportData;
import util.Algorithm;
import util.DateUtil;
import util.FileUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 江婷婷 on 2017/12/30.
 */
public class Test {
    public static void main(String[] args) {
//        Date date = new Date();
//        System.out.println(DateUtil.transfer(date));
//        System.out.println(DateUtil.transferDay(172860000));
//        ProvinceData.addProvince(new Province("安徽", null));
//        ProvinceData.addCity(ProvinceData.getProvince("安徽"), new City("安庆"));
//        ProvinceData.addCity(ProvinceData.getProvince("安徽"), new City("合肥"));
//        ProvinceData.addCity(ProvinceData.getProvince("安徽"), new City("芜湖"));
////        print();
//        List<City> list = new ArrayList<>();
//        list.add(new City("西安"));
//        list.add(new City("宝鸡"));
//        ProvinceData.addProvince(new Province("陕西", list));
//        print();
//        list = new ArrayList<>();
//        list.add(new City("咸阳"));//list添加后自动变
//        System.out.println(ProvinceData.addCity(ProvinceData.getProvince("陕西"), list));
//        print();
////        ProvinceData.deleteCity("陕西", "咸阳");
//        print();
//        FileUtil.writeProvinceData();
//        System.out.println("======================");
//        print();
//        System.out.println("======================");
//        ProvinceData.provinces.clear();
//        System.out.println("======================");
//        ProvinceData.provinces = (List<Province>) FileUtil.readProvinceData();
//        print();

        FileUtil.readTransportData();
        FileUtil.readProvinceData();
//        print();
//        TransportData.deleteTransport();

//        Date date11 = DateUtil.getDate(2011, 12, 31, 18, 29);
//        Date date12 = DateUtil.getDate(2012, 12, 31, 18, 29);
//        Date date13 = DateUtil.getDate(2013, 12, 31, 18, 29);
//        Date date14 = DateUtil.getDate(2014, 12, 31, 18, 29);
//        Date date15 = DateUtil.getDate(2015, 12, 31, 18, 29);
//        Date date16 = DateUtil.getDate(2016, 12, 31, 18, 29);
//        Date date17 = DateUtil.getDate(2017, 12, 31, 18, 29);
//        Date date18 = DateUtil.getDate(2018, 12, 31, 18, 29);
//        Date date19 = DateUtil.getDate(2019, 12, 31, 18, 29);
//
//
//        List<Route> l = new ArrayList<>();
//        l.add(new Route(new float[]{425, 720, 1110}, ProvinceData.getCity("西安"),
//                ProvinceData.getCity("安庆"),date11, date13));
//        l.add(new Route(new float[]{425, 720, 1110}, ProvinceData.getCity("西安"),
//                ProvinceData.getCity("宝鸡"),date11, date12));
//        l.add(new Route(new float[]{425, 720, 1110}, ProvinceData.getCity("宝鸡"),
//                ProvinceData.getCity("合肥") ,date14, date15));
//        l.add(new Route(new float[]{425, 720, 1110}, ProvinceData.getCity("宝鸡"),
//                ProvinceData.getCity("芜湖"),date13, date14));
//        l.add(new Route(new float[]{425, 720, 1110}, ProvinceData.getCity("芜湖"),
//                ProvinceData.getCity("合肥"),date15, date16));
//        l.add(new Route(new float[]{425, 720, 1110}, ProvinceData.getCity("合肥"),
//                ProvinceData.getCity("宝鸡"),date16, date17));
//        l.add(new Route(new float[]{425, 720, 1110}, ProvinceData.getCity("宝鸡"),
//                ProvinceData.getCity("安庆"),date18, date19));
//        l.add(new Route(new float[]{425, 720, 1110}, ProvinceData.getCity("合肥"),
//                ProvinceData.getCity("安庆"),date12, date13));
//        l.add(new Route(new float[]{425, 720, 1110}, ProvinceData.getCity("芜湖"),
//                ProvinceData.getCity("安庆"),date17, date18));
//
//        TransportData.addTransport(new Transport("K666", 2, true, l));
//        AlgorithmData.initZhuanchengOneData(ProvinceData.getCity("西安"));
//
//        FileUtil.writeTransportData();
//        FileUtil.writeProvinceData();
        print();


        List<TotalPlan> plans = Algorithm.allPlans(ProvinceData.getCity("西安"), ProvinceData.getCity("安庆"));
        for (TotalPlan p : plans) {
            for (Route r : p.getRouteList()) {
                System.out.print(" " + r.getStartStation().getCityName() + "-" + r.getEndStation().getCityName() + "");
            }
            System.out.println();
        }

    }

    public static void print() {
        if (ProvinceData.provinces.size() == 0) {
            System.out.println("无省份");
        }
        for (Province p : ProvinceData.provinces) {
            System.out.print(p.getProvinceName() + " | ");
            for (City c: p.getCityList()) {
                System.out.print(c.getCityName() + " ");
            }
            System.out.println();
        }
        if (TransportData.transports.size() == 0) {
            System.out.println("无工具");
        }
        for (Transport t : TransportData.transports) {
            System.out.println(t.getId());
            for (Route r : t.getRoutes()) {
                System.out.println(r.getStartStation().getCityName() + "-" + r.getEndStation().getCityName()
                        + ":" + DateUtil.transfer(r.getStartTime())
                        + "-" + DateUtil.transfer(r.getEndTime()));
            }
        }
    }
}
