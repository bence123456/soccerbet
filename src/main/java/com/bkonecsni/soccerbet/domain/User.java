package com.bkonecsni.soccerbet.domain;

import javax.persistence.*;

@Entity
@Table(name = "user", catalog = "soccerbet")
public class User {

    @Id
    private String id;

    private String name;

    private int points;

    public User() {}

    public User(String id, String name, int points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", points=" + points +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
