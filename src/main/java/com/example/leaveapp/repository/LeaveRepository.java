package com.example.leaveapp.repository;

import com.example.leaveapp.model.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {
    @Query("SELECT l FROM Leave as l where l.approved=false")
    List<Leave> findAllNotApprovedLeave();
    @Query("SELECT l FROM Leave as l where l.approved=true")
    List<Leave> findAllApprovedLeave();
}
