package com.asset.user.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Container(containerName = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class User {
    @Id
    @JsonIgnore
    private String id;
    @PartitionKey
    private String key;
    private Date createdDate;
    private String email;
    private Date lastUpdatedDate;
    private int height;
    private double weight;
    private int stepHeight;
    private String heightUnit;
    private String weightUnit;
    private String stepHeightUnit;
    private String dateOfBirth;
    private String biologicalSex;
    private String ethnicityType;
    private String diabeticType;
    private int hypertension;
    private int bloodPressureMed;
    private int validatedTOS;
    private int smoker;
    private String currentBHAKey;
    private String firstName;
    private String lastName;
}
