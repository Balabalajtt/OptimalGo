import data.model.*;
import data.savedata.ProvinceData;
import data.savedata.TransportData;
import ui.AdminMenu;
import ui.Query;
import ui.admin.*;
import util.Algorithm;
import util.DateUtil;
import util.FileUtil;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 江婷婷 on 2017/12/30.
 */
public class Test {
    public static void main(String[] args) {
//        ProvinceData.addProvince(new Province("安徽", null));
//        ProvinceData.addCity(ProvinceData.getProvince("安徽"), new City("安庆"));
//        ProvinceData.addCity(ProvinceData.getProvince("安徽"), new City("合肥"));
//        ProvinceData.addCity(ProvinceData.getProvince("安徽"), new City("芜湖"));
//////        print();
//        List<City> list = new ArrayList<>();
//        list.add(new City("西安"));
//        list.add(new City("宝鸡"));
//        ProvinceData.addProvince(new Province("陕西", list));

        FileUtil.readTransportData();
        FileUtil.readProvinceData();
        FileUtil.readAccountData();
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
//                ProvinceData.getCity("安庆"),date11, date15));
//        l.add(new Route(new float[]{25, 720, 1110}, ProvinceData.getCity("西安"),
//                ProvinceData.getCity("宝鸡"),date11, date12));
//        l.add(new Route(new float[]{405, 720, 1110}, ProvinceData.getCity("宝鸡"),
//                ProvinceData.getCity("合肥") ,date14, date15));
//        l.add(new Route(new float[]{400, 720, 1110}, ProvinceData.getCity("宝鸡"),
//                ProvinceData.getCity("芜湖"),date13, date14));
//        l.add(new Route(new float[]{80, 720, 1110}, ProvinceData.getCity("芜湖"),
//                ProvinceData.getCity("合肥"),date15, date16));
//        l.add(new Route(new float[]{405, 720, 1110}, ProvinceData.getCity("合肥"),
//                ProvinceData.getCity("宝鸡"),date16, date17));
//        l.add(new Route(new float[]{415, 720, 1110}, ProvinceData.getCity("宝鸡"),
//                ProvinceData.getCity("安庆"),date18, date19));
//        l.add(new Route(new float[]{70, 720, 1110}, ProvinceData.getCity("合肥"),
//                ProvinceData.getCity("安庆"),date12, date13));
//        l.add(new Route(new float[]{60, 720, 1110}, ProvinceData.getCity("芜湖"),
//                ProvinceData.getCity("安庆"),date17, date18));
//        TransportData.addTransport(new Transport("K705", 1, true, l));
//
//        List<Route> routes = new ArrayList<>();
//        routes.add(new Route(new float[]{700, 720, 1110}, ProvinceData.getCity("西安"),
//                ProvinceData.getCity("安庆"),date11, date13));
//        routes.add(new Route(new float[]{100, 720, 1110}, ProvinceData.getCity("西安"),
//                ProvinceData.getCity("宝鸡"),date12, DateUtil.getDate(2012, 11, 31, 22, 29)));
//        TransportData.addTransport(new Transport("G888", 1, true, routes));
//        AlgorithmData.initZhuanchengOneData(ProvinceData.getCity("西安"));
//
//        FileUtil.writeTransportData();
//        FileUtil.writeProvinceData();

//        new AdminMenu("江婷婷");
//        print();
//////
//        System.out.println("================");
//        List<TotalPlan> plans = Algorithm.sortPlansByMoney(ProvinceData.getCity("合肥"), ProvinceData.getCity("西安"), DateUtil.getDate(2018, 1, 5, 0, 0));
//        for (TotalPlan p : plans) {
//            for (Route r : p.getRouteList()) {
//                System.out.print(r.getTransport().getId() + " " + r.getStartStation().getCityName() + "-" + r.getEndStation().getCityName() + "  ");
//                r.printString();
//
//            }
//            System.out.println("  |" + p.getTransferNumber() + "转乘 " + p.getTotalPrice() + "总价 " + DateUtil.transferHour(p.getDuration()) + "耗时 ");
//        }

//        System.out.println(ProvinceData.getCity("安庆").getRouteList().size());

        new Query();

//        new AdminMenu("江婷婷");
//        new AddTransport();

//        new AddCity();

//        new AddAdmin();
//        new ListAdmin();
//        new DeleteCity();
//        new DeleteTransport();
//        new ModifyTransport();

        print();
        for (Province p : ProvinceData.provinces) {
            System.out.print(p.getProvinceName() + " | ");
            for (City c: p.getCityList()) {
                System.out.print(c.getCityName() + " ");
                for (Route r : c.getRouteList()) {
                    System.out.println(r.getTransport().getId() + "  " + r.getPrice());
                }
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
//            for (Date d : t.getDispatchDate()) {
//                System.out.println(DateUtil.transfer(d));
//            }
            for (Route r : t.getRoutes()) {
                System.out.println(r.getStartStation().getCityName() + "-" + r.getEndStation().getCityName() + "  "
                + DateUtil.transferDay(r.getStartTime()) + " " + DateUtil.transferDay(r.getEndTime()) + " " + r.getPrice());
            }
        }
    }
}
