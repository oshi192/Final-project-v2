package model.dao.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CityDistance {
    private int id;
    private int distanceKm;
    private int toCityId;
    private int fromCityId;
}
