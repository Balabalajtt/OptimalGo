package data.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 江婷婷 on 2017/12/30.
 * 片段航班或火车行程
 */

public class Route implements Serializable {
    private float price;//价钱
    private City startStation;
    private City endStation;

    private long startTime;//只存天小时分钟      9时
    private long endTime;//存天小时分钟     一天12时
    private Transport transport;

    /**
     * private 写方法判断传入参数成立不成立
     */
    public Route(City startStation, City endStation, long startTime, long endTime, float price) {
        this.startStation = startStation;
        this.endStation = endStation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        startStation.addRoute(this);//添加到起始城市
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public float getPrices() {
        return price;
    }

    public void setPrices(float price) {
        this.price = price;
    }

    public City getStartStation() {
        return startStation;
    }

    public void setStartStation(City startStation) {
        this.startStation = startStation;
    }

    public City getEndStation() {
        return endStation;
    }

    public void setEndStation(City endStation) {
        this.endStation = endStation;
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
}
