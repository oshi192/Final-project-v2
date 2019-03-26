package model.dao.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Discount {
    private int id;
    private String text;
    private String text_uk;
    private LocalDate startTime;
    private LocalDate endTime;
    private boolean isSumWithUserDisc;
    private int authorId;
    private int percent;
    public boolean getIsSumWithUserDisc(){
        return isSumWithUserDisc;
    }
}
