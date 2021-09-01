package com.example.leaveapp.model.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Role role;
    private String jobPosition;
    private LocalDate dateOfAppointment;
    private int totalDaysLeave;
    private List<Leave> leave;

    public User() {
    }

    @Column(nullable = false)
    @Length(min = 3, max = 15, message = "First name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(nullable = false)
    @Length(min = 3, max = 15, message = "Last name length must be between 3 and 20 characters (inclusive 3 and 20).")

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Column(nullable = false, unique = true)
    @Length(min = 3, max = 15, message = "Username length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(nullable = false, unique = true)
    @Email
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @ManyToOne
    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    @Column(nullable = false)
    public String getJobPosition() {
        return jobPosition;
    }

    public User setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
        return this;
    }

    @Column()
    @DateTimeFormat(fallbackPatterns = "d/m/Y")
    @FutureOrPresent(message = "The employee cannot be appointed in the past")
    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public User setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
        return this;
    }

    @Column()
    @Min(0)
    public int getTotalDaysLeave() {
        return totalDaysLeave;
    }

    public User setTotalDaysLeave(int totalDaysLeave) {
        this.totalDaysLeave = totalDaysLeave;
        return this;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
@OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public List<Leave> getLeave() {
        return leave;
    }

    public User setLeave(List<Leave> leave) {
        this.leave = leave;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", jobPosition='" + jobPosition + '\'' +
                ", dateOfAppointment=" + dateOfAppointment +
                ", totalDaysLeave=" + totalDaysLeave +
                ", leave=" + leave +
                '}';
    }
}
