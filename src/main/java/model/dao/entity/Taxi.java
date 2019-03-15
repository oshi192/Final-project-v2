package model.dao.entity;

public class Taxi {
    public enum Status{
        FREE, ON_THE_WAY,PAUSE
    }
    public enum CarType{
        STANDARD, COMFORT, BUSINESS, WAGON, MINIBUS
    }


    private int id;
    private Status status;
    private CarType carType;

    public Taxi() {
    }

    public Taxi(int id, Status status, CarType carType) {
        this.id = id;
        this.status = status;
        this.carType = carType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
