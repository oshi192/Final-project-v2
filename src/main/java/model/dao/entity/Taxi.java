package model.dao.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Taxi {

    private int id;
    private int idtaxiStatus;
    private int idcarType;
    private String descryption;

}
