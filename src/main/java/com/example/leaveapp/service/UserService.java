package com.example.leaveapp.service;

import com.example.leaveapp.model.service.LeaveServiceModel;
import com.example.leaveapp.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    UserServiceModel findById(Long id);

    List<LeaveServiceModel> findAllMyLeave(Long id);

    List<UserServiceModel> showAllEmployees();

    void deleteUser(Long id);

    void changeRole(Long id);
}
