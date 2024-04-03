package com.example.demo.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubjectResponse {

    private Long id;
    @JsonProperty("name")
    private String firstName;

    private String lastName;

    private int numberOfAccounts;
}
