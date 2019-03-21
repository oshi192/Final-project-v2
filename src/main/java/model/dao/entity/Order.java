package model.dao.entity;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Order {
    private int id;
    private LocalDateTime time;
    private String comment;
    private int cityDistanceId;
    private int carTypeId;
    private int userId;
    private int taxiId;
    private int orderStatusId;

}
