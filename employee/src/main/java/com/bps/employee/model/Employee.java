package com.bps.employee.model;

import java.util.Date;

public class Employee {
    private Integer id;
    private Integer Reward;
    private Date updated;

    public Employee() {
    }

    public Employee(Integer surveyorId) {
        this.id = surveyorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReward() {
        return Reward;
    }

    public void setReward(Integer reward) {
        Reward = reward;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
