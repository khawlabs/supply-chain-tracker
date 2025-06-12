package com.example.shipmentservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class Auditable<U> implements IEntity<U> {

    @CreatedBy
    protected U createdBy;

    @CreatedDate
    @JsonDeserialize( using = LocalDateTimeDeserializer.class )
    @JsonSerialize( using = LocalDateTimeSerializer.class )
    protected LocalDateTime createdAt;

    @LastModifiedBy
    protected U lastModifiedBy;

    @LastModifiedDate
    @JsonDeserialize( using = LocalDateTimeDeserializer.class )
    @JsonSerialize( using = LocalDateTimeSerializer.class )
    protected LocalDateTime lastModifiedAt;
}
