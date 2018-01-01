import data.model.City;
import data.model.Province;
import data.model.Route;
import data.model.Transport;
import data.savedata.ProvinceData;
import data.savedata.TransportData;
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
//        ProvinceData.addCity(ProvinceData.getProvince("安徽"), new City("安庆"));
//        ProvinceData.addCity(ProvinceData.getProvince("安徽"), new City("合肥"));
//        print();
//        ProvinceData.addCity(ProvinceData.getProvince("安徽"), new City("芜湖"));
//        print();
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
//        TransportData.deleteTransport("江婷婷航空666");
//        List<Route> l = new ArrayList<>();
//        l.add(new Route(new float[]{425, 720, 1110}, ProvinceData.getCity("咸阳"),
//                ProvinceData.getCity("安庆"), new Date(), DateUtil.getDate(2017, 12, 31, 18, 29)));
//        TransportData.addTransport(new Transport("江婷婷航空666", 1, true, l));
//        FileUtil.writeTransportData();
        print();
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
