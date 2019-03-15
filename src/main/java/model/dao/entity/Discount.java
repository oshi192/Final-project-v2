package model.dao.entity;

import java.time.LocalDateTime;

public class Discount {
    private int id;
    private String text;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isSumWithUserDisc;

    public Discount() {
    }

    public Discount(int id, String text, LocalDateTime startTime, LocalDateTime endTime, boolean isSumWithUserDisc) {
        this.id = id;
        this.text = text;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isSumWithUserDisc = isSumWithUserDisc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isSumWithUserDisc() {
        return isSumWithUserDisc;
    }

    public void setSumWithUserDisc(boolean sumWithUserDisc) {
        isSumWithUserDisc = sumWithUserDisc;
    }
}
