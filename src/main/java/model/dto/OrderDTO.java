package model.dto;

import lombok.*;
import model.dao.entity.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderDTO extends Order {
    private CarType carType;
    private User user;
    private Taxi taxi;
    private CityDistance cityDistance;
}
