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
    private String startPoint;
    private String endPoint;
    private int carType;
    private int userId;
}
