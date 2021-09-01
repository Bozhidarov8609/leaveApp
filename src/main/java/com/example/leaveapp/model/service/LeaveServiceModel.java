package com.example.leaveapp.model.service;

import com.example.leaveapp.model.entity.User;

import java.time.LocalDate;

public class LeaveServiceModel extends BaseEntityServiceModel {
    private String startDay;
    private String endDay;
    private int days;
    private boolean isApproved;
    private UserServiceModel user;

    public LeaveServiceModel() {
    }

    public String getStartDay() {
        return startDay;
    }

    public LeaveServiceModel setStartDay(String startDay) {
        this.startDay = startDay;
        return this;
    }

    public String getEndDay() {
        return endDay;
    }

    public LeaveServiceModel setEndDay(String endDay) {
        this.endDay = endDay;
        return this;
    }

    public int getDays() {
        return days;
    }

    public LeaveServiceModel setDays(int days) {
        this.days = days;
        return this;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public LeaveServiceModel setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public LeaveServiceModel setUser(UserServiceModel user) {
        this.user = user;
        return this;
    }
}
