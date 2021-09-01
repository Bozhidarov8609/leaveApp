package com.example.leaveapp.model.view;

import com.example.leaveapp.model.entity.Leave;
import com.example.leaveapp.model.entity.Role;

import java.time.LocalDate;
import java.util.List;

public class UserProfileViewModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String jobPosition;
    private String dateOfAppointment;
    private int totalDaysLeave;
    private List<Leave> leave;

    public UserProfileViewModel() {
    }

    public Long getId() {
        return id;
    }

    public UserProfileViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfileViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }




    public String getEmail() {
        return email;
    }

    public UserProfileViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public UserProfileViewModel setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
        return this;
    }

    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    public UserProfileViewModel setDateOfAppointment(String dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
        return this;
    }

    public int getTotalDaysLeave() {
        return totalDaysLeave;
    }

    public UserProfileViewModel setTotalDaysLeave(int totalDaysLeave) {
        this.totalDaysLeave = totalDaysLeave;
        return this;
    }

    public List<Leave> getLeave() {
        return leave;
    }

    public UserProfileViewModel setLeave(List<Leave> leave) {
        this.leave = leave;
        return this;
    }
}
