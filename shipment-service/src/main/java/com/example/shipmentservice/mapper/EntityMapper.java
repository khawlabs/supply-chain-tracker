package com.example.shipmentservice.mapper;

import com.example.shipmentservice.dto.IDto;
import com.example.shipmentservice.model.IEntity;

import java.util.List;
import java.util.Set;

public interface EntityMapper<T extends IEntity, D extends IDto> {

    public T dtoToEntity( D object );

    public D entityToDto( T object );

    public List<T> listDtoToEntity(List<D> list );

    public Set<T> listDtoToEntity( Set<D> list );

    public List<D> listEntityToDto( List<T> list );

    public Set<D> listEntityToDto( Set<T> list );
}

