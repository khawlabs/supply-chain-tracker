package com.example.shipmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public abstract class AbstractDto<T> implements IDto<T> {}
