package com.example.leaveapp.model.service;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntityServiceModel {

    private Long id;

    public BaseEntityServiceModel() {
    }
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public BaseEntityServiceModel setId(Long id) {
        this.id = id;
        return this;
    }
}
