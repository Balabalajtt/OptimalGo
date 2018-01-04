package data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 江婷婷 on 2017/12/30.
 * 总行程
 */
public class TotalPlan implements Serializable {
    private List<Route> routeList;

    private float totalPrice = 0;//总价

    private long startTime;
    private long endTime;
    private long duration = 0;//总用时

    private int transferNumber = 0;//转站数

    public TotalPlan(List<Route> routeList) {
        this.routeList = routeList;
        init();
    }

    private void init() {
        String id = routeList.get(0).getTransport().getId();
        transferNumber = 0;
        for (Route route : routeList) {
            if (!route.getTransport().getId().equals(id)) {
                transferNumber++;
                id = route.getTransport().getId();
            }
            totalPrice += route.getPrice();
        }
        startTime = routeList.get(0).getStartTime() + routeList.get(0).getStartDate().getTime();
        int e = routeList.size();
        endTime = routeList.get(e - 1).getEndTime() + routeList.get(e - 1).getStartDate().getTime();
        duration = endTime - startTime;


    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setTransferNumber(int transferNumber) {
        this.transferNumber = transferNumber;
    }

    public long getDuration() {
        return duration;
    }

    public int getTransferNumber() {
        return transferNumber;
    }

}
