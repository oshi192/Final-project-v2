package model.dao.entity;

public class Taxi {

    private int id;
    private TaxiStatus taxiStatus;
    private CarType carType;

    public Taxi() {
    }

    public Taxi(int id, TaxiStatus taxiStatus, CarType carType) {
        this.id = id;
        this.taxiStatus = taxiStatus;
        this.carType = carType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaxiStatus getStatus() {
        return taxiStatus;
    }

    public void setStatus(TaxiStatus status) {
        this.taxiStatus = status;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
