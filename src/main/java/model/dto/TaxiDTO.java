package model.dto;

import lombok.*;
import model.dao.entity.CarType;
import model.dao.entity.Taxi;
import model.dao.entity.TaxiStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class TaxiDTO extends Taxi {
    private TaxiStatus taxiStatus;
    private CarType carType;
}
