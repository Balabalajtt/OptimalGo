package data.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by 江婷婷 on 2017/12/30.
 * 总行程
 */
public class TotalPlan implements Serializable {
    private List<Route> routeList;

    private float totalPrice = 0;//总价

    private Date startTime;
    private Date endTime;
    private long duration = 0;//总用时

    private int transferNumber = 0;//转站数

    public TotalPlan(List<Route> routeList) {
        this.routeList = routeList;
        init();
    }

    private void init() {
        for (Route route : routeList) {
            totalPrice = route.getPrices()[0];
        }
        startTime = routeList.get(0).getStartTime();
        int e = routeList.size();
        endTime = routeList.get(e - 1).getStartTime();
        duration = endTime.getTime() - startTime.getTime();
        transferNumber = routeList.size() - 1;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public long getDuration() {
        return duration;
    }

    public int getTransferNumber() {
        return transferNumber;
    }

}
