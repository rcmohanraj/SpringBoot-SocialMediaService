package it.mohanrc.socialmedia.model;

import com.fasterxml.jackson.annotation.JsonFilter;

import java.time.LocalDate;

@JsonFilter("EmployeeFilter")
public class Employee {

    private Integer id;

    private String name;

    private LocalDate dob;

    public Employee(Integer id, String name, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
