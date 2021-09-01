package com.example.leaveapp.model.binding;

import javax.validation.constraints.FutureOrPresent;

public class LeaveRequestBindingModel {

    private String startDay;
    private String endDay;

    public LeaveRequestBindingModel() {
    }

    public String getStartDay() {
        return startDay;
    }

    public LeaveRequestBindingModel setStartDay(String startDay) {
        this.startDay = startDay;
        return this;
    }

    public String getEndDay() {
        return endDay;
    }

    public LeaveRequestBindingModel setEndDay(String endDay) {
        this.endDay = endDay;
        return this;
    }
}
