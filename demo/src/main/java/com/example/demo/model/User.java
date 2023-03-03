package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private long id;
    private String fistName;
    private String lastName;

    private Map<String, List<String>> hobby;

    public long getId() {
        return id;
    }

    public String getFistName() {
        return fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public Map<String, List<String>> getHobby() {
        return hobby;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHobby(Map<String, List<String>> hobby) {
        this.hobby = hobby;
    }
}
