package com.example.leaveapp.model.binding;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import java.time.LocalDate;

public class UserRegisterBindingModel {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
     private String jobPosition;
    private String dateOfAppointment;
    private int totalDaysLeave;

    public UserRegisterBindingModel() {
    }
    @Length(min = 3, max = 15, message = "First name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    @Length(min = 3, max = 15, message = "Last name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getLastName() {
        return lastName;
    }

    public UserRegisterBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    @Length(min = 3, max = 15, message = "Username length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
    @Email
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public UserRegisterBindingModel setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
        return this;
    }

    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    public UserRegisterBindingModel setDateOfAppointment(String dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
        return this;
    }

    @Min(0)
    public int getTotalDaysLeave() {
        return totalDaysLeave;
    }

    public UserRegisterBindingModel setTotalDaysLeave(int totalDaysLeave) {
        this.totalDaysLeave = totalDaysLeave;
        return this;
    }
}
