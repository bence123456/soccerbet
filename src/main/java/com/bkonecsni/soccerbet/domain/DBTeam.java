package com.bkonecsni.soccerbet.domain;

import javax.persistence.*;

@Entity
@Table(name = "team", catalog = "soccerbet")
public class DBTeam {

    @Id
    private Long id;

    private String name;

    public DBTeam(){}

    public DBTeam(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DBTeam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
