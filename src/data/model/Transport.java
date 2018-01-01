package data.model;

import java.io.Serializable;
import java.util.List;

/**
 * 交通工具对象
 * Created by 江婷婷 on 2017/12/31.
 */
public class Transport implements Serializable {
    private String id;//航班或车次
    private int type;//1:飞机 2:火车
    private boolean status;//正常运行 停运
    private List<Route> routes;//所有排表

    public Transport(String id, int type, boolean status, List<Route> routes) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.routes = routes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
