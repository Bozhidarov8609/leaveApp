package com.example.leaveapp.model.service;


import com.example.leaveapp.model.entity.Leave;
import com.example.leaveapp.model.entity.Role;

import java.time.LocalDate;
import java.util.List;

public class UserServiceModel extends BaseEntityServiceModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Role role;
    private String jobPosition;
    private String dateOfAppointment;
    private int totalDaysLeave;
    private List<Leave> leave;

    public UserServiceModel() {
    }



    public String getFirstName() {
        return firstName;
    }

    public UserServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserServiceModel setRole(Role role) {
        this.role = role;
        return this;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public UserServiceModel setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
        return this;
    }

    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    public UserServiceModel setDateOfAppointment(String dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
        return this;
    }

    public int getTotalDaysLeave() {
        return totalDaysLeave;
    }

    public UserServiceModel setTotalDaysLeave(int totalDaysLeave) {
        this.totalDaysLeave = totalDaysLeave;
        return this;
    }

    public List<Leave> getLeave() {
        return leave;
    }

    public UserServiceModel setLeave(List<Leave> leave) {
        this.leave = leave;
        return this;
    }
}
