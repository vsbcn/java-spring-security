package com.ironhack.security.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ironhack.security.enums.House;
import com.ironhack.security.enums.Wing;

import javax.persistence.*;

@Entity
public class HouseAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private House house;
    @Enumerated(EnumType.STRING)
    private Wing wing;
    private Integer assignedBed;
    @OneToOne(mappedBy = "houseAssignment")
    @JsonBackReference
    private Student student;

    public HouseAssignment() {
    }

    public HouseAssignment(House house, Wing wing, Integer assignedBed) {
        this.house = house;
        this.wing = wing;
        this.assignedBed = assignedBed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Wing getWing() {
        return wing;
    }

    public void setWing(Wing wing) {
        this.wing = wing;
    }

    public Integer getAssignedBed() {
        return assignedBed;
    }

    public void setAssignedBed(Integer assignedBed) {
        this.assignedBed = assignedBed;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "HouseAssignment{" +
                "id=" + id +
                ", house=" + house +
                ", wing=" + wing +
                ", assignedBed=" + assignedBed +
                ", student=" + student +
                '}';
    }
}
