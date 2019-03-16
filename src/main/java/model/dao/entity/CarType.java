package model.dao.entity;

public class CarType {
    private int id;
    private String name;
    public int priceCityKm;
    public int priceOverTheCityKm;
    public int priceWaitingTimeMinute;
    public int priceWaitingTimeFree;

    public CarType() {
    }

    public CarType(int id, String name, int priceCityKm, int priceOverTheCityKm, int priceWaitingTimeMinute, int priceWaitingTimeFree) {
        this.id = id;
        this.name = name;
        this.priceCityKm = priceCityKm;
        this.priceOverTheCityKm = priceOverTheCityKm;
        this.priceWaitingTimeMinute = priceWaitingTimeMinute;
        this.priceWaitingTimeFree = priceWaitingTimeFree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriceCityKm() {
        return priceCityKm;
    }

    public void setPriceCityKm(int priceCityKm) {
        this.priceCityKm = priceCityKm;
    }

    public int getPriceOverTheCityKm() {
        return priceOverTheCityKm;
    }

    public void setPriceOverTheCityKm(int priceOverTheCityKm) {
        this.priceOverTheCityKm = priceOverTheCityKm;
    }

    public int getPriceWaitingTimeMinute() {
        return priceWaitingTimeMinute;
    }

    public void setPriceWaitingTimeMinute(int priceWaitingTimeMinute) {
        this.priceWaitingTimeMinute = priceWaitingTimeMinute;
    }

    public int getPriceWaitingTimeFree() {
        return priceWaitingTimeFree;
    }

    public void setPriceWaitingTimeFree(int priceWaitingTimeFree) {
        this.priceWaitingTimeFree = priceWaitingTimeFree;
    }
}
