package com.goldenrealestate.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String taskDescription;

    @ManyToOne
    Building building;

    @ManyToOne
    Employee employee;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String task) {
        this.taskDescription = task;
    }

    @Override
    public String toString() {
        return "Task{" +
                "building=" + building +
                ", employee=" + employee +
                ", taskDescription='" + taskDescription + '\'' +
                '}';
    }

}
