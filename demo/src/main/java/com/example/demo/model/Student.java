package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencsv.bean.CsvBindByName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    @CsvBindByName(column = "studentId")
    private String studentId;

    @CsvBindByName(column = "firstName")
    private String firstName;

    @CsvBindByName(column = "lastName")
    private String lastName;

    @CsvBindByName(column = "year")
    private int year;

    private String encryptedYear;
    private String decryptedYear;

    public String getDecryptedYear() {
        return decryptedYear;
    }

    public void setDecryptedYear(String decryptedYear) {
        this.decryptedYear = decryptedYear;
    }

    public String getEncryptedYear() {
        return encryptedYear;
    }

    public void setEncryptedYear(String encryptedYear) {
        this.encryptedYear = encryptedYear;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYear() {
        return year;
    }
}
