package org.example.model;

import java.time.LocalDateTime;

public class Indicators {
    private int id;
    private int name_id;
    private double value;
    private LocalDateTime date;
    private int user_id;

    public Indicators(int id, int name_id, double value, LocalDateTime date, int user_id) {
        this.id = id;
        this.name_id = name_id;
        this.value = value;
        this.date = date;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName_id() {
        return name_id;
    }

    public void setName_id(int name_id) {
        this.name_id = name_id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
