package data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 交通工具对象
 * Created by 江婷婷 on 2017/12/31.
 */
public class Transport implements Serializable {
    private String id;//航班或车次
    private int type;//1:飞机 2:火车
//    private boolean status;//正常运行 停运
    private List<Route> routes;//排表
    private List<Date>  dispatchDate;//发车或发航班日期 只存年月日 在此基础上加排表小时

    /**
     * 创建一个车次或是一个航班
     * @param id 列次名 航班名
     * @param type 1:飞机  2:火车
     * @param routes 行程
     * @param dispatchDate 发车或发航班日期
     */
    public Transport(String id, int type, List<Route> routes, List<Date> dispatchDate) {
        this.id = id;
        this.type = type;
//        this.status = status;

        this.dispatchDate = dispatchDate;
        this.routes = routes;
        for (Route r : routes) {
            r.setTransport(this);
        }

    }


    public List<Date> getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(List<Date> dispatchDate) {
        this.dispatchDate = dispatchDate;
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

//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
