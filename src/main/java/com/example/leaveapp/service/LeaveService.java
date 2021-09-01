package com.example.leaveapp.service;

import com.example.leaveapp.model.service.LeaveServiceModel;

import java.util.List;

public interface LeaveService {
    LeaveServiceModel requestForLeave(LeaveServiceModel leaveServiceModel);
    List<LeaveServiceModel> allApprovedLeave();

    List<LeaveServiceModel> allNotApprovedLeave();

    void changeStatus(Long id);

    void disapproved(Long id);


}
