package com.example.shipmentservice.model;

import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@MappedSuperclass
public interface IEntity<T> extends Serializable {

    public T getId();
    public void setId( T id );
}
