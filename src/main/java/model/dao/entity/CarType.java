package model.dao.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CarType {
    private int id;
    private String carTypeName;
    public int priceCityKm;
    public int priceOverTheCityKm;
    public int priceWaitingTimeMinute;
    public int priceWaitingTimeFree;

}
