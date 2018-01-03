package data.savedata;

import data.model.Route;
import data.model.Transport;

import java.util.ArrayList;
import java.util.List;

import static util.DateUtil.hasTimeConflict;

/**
 * Created by 江婷婷 on 2017/12/30.
 * 所有交通工具数据
 */
public class TransportData {
    public static List<Transport> transports = new ArrayList<>();

    public static boolean addTransport(Transport transport) {
        if (transportExist(transport)) {
            return false;
        } else {
            transports.add(transport);
            return true;
        }
    }

    public static boolean deleteTransport(String transportName) {
        Transport t = getTransport(transportName);
        if (t == null) {
            return false;
        }
        transports.remove(t);
        return true;

    }

    /**
     * 添加新行程
     * @param transport
     * @param route
     * @return 0:添加成功 1:Transport不存在 2:行程冲突
     */
    public static int addRoute(Transport transport, Route route) {
        if (!transportExist(transport)) {
            return 1;
        }
        for (Route r : transport.getRoutes()) {
            if (hasRouteConflict(r, route)) {
                return 2;
            }
        }
        route.setTransport(transport);
        transport.getRoutes().add(route);
        return 0;
    }

    public static boolean deleteRoute(Transport transport, int index) {
        if (!transportExist(transport) || index >= transport.getRoutes().size() || index < 0) {
            return false;
        }
        transport.getRoutes().remove(index);
        return true;
    }

    public static boolean hasRouteConflict(Route r1, Route r2) {
        return  hasTimeConflict(r1.getStartTime(), r1.getEndTime(), r2.getStartTime(), r2.getStartTime());
    }


    public static Transport getTransport(String transportName) {
        for (Transport t : transports) {
            if (t.getId().equals(transportName)) {
                return t;
            }
        }
        return null;
    }

    private static boolean transportExist(Transport transport) {
        for (Transport t : transports) {
            if (t.getId().equals(transport.getId()) && t.getType() == transport.getType()) {
                return true;
            }
        }
        return false;
    }



}
