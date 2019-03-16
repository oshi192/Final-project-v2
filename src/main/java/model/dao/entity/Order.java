package model.dao.entity;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private LocalDateTime time;
    private String startPoint;
    private String endPoint;
    private CarType carType;
    private int userId;

    public Order() {
    }

    public Order(int id, LocalDateTime time, String startPoint, String endPoint, CarType carType, int userId) {
        this.id = id;
        this.time = time;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.carType = carType;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
