package model.dao.entity;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Discount {
    private int id;
    private String text;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isSumWithUserDisc;
}
