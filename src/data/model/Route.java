package data.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 江婷婷 on 2017/12/30.
 * 片段航班或火车行程
 */

public class Route implements Serializable {
    private float[] prices;//座位描述 价钱

    private City startStation;
    private City endStation;

    private Date startTime;
    private Date endTime;

    /**
     * private 写方法判断传入参数成立不成立
     */
    public Route(float[] prices, City startStation, City endStation, Date startTime, Date endTime) {
        this.prices = prices;
        this.startStation = startStation;
        this.endStation = endStation;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public float[] getPrices() {
        return prices;
    }

    public void setPrices(float[] prices) {
        this.prices = prices;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
