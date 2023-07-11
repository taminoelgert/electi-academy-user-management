package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlagModel {
    private String name;
    private String color;
}
