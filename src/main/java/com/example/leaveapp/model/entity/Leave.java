package com.example.leaveapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Table(name = "leaveOfEmployee")
public class Leave extends BaseEntity {

    private LocalDate startDay;
    private LocalDate endDay;
    private int days;
    private boolean isApproved;
    private User user;

    public Leave() {
    }
@Column(nullable = false)
@FutureOrPresent(message = "Leave cannot begin in the past")
    public LocalDate getStartDay() {
        return startDay;
    }

    public Leave setStartDay(LocalDate startDay) {
        this.startDay = startDay;
        return this;
    }
    @Column(nullable = false)
    @FutureOrPresent(message = "Leave cannot end in the past")
    public LocalDate getEndDay() {
        return endDay;
    }

    public Leave setEndDay(LocalDate endDay) {
        this.endDay = endDay;
        return this;
    }
@Column(nullable = false)
@Min(0)
    public int getDays() {
        return days;
    }

    public Leave setDays(int days) {
        this.days = days;
        return this;
    }
@Column()
    public boolean isApproved() {
        return isApproved;
    }

    public Leave setApproved(boolean approved) {
        isApproved = approved;
        return this;
    }
@ManyToOne
    public User getUser() {
        return user;
    }

    public Leave setUser(User user) {
        this.user = user;
        return this;
    }
}
