package com.ironhack.security.models;

import com.ironhack.security.enums.Level;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Spell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String name;
    @Enumerated(EnumType.STRING)
    private Level level;

    public Spell() {
    }

    public Spell(Integer id, String name, Level level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
