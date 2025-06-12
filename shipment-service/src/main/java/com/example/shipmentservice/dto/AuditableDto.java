package com.example.shipmentservice.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class AuditableDto<T> extends AbstractDto<T> {

    protected Long createdBy;

    @JsonDeserialize( using = LocalDateTimeDeserializer.class )
    @JsonSerialize( using = LocalDateTimeSerializer.class )
    protected LocalDateTime createdAt;

    protected Long lastModifiedBy;

    @JsonDeserialize( using = LocalDateTimeDeserializer.class )
    @JsonSerialize( using = LocalDateTimeSerializer.class )
    protected LocalDateTime lastModifiedAt;

}
