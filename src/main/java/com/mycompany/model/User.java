package com.mycompany.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Component
public class User {

    @NotEmpty(message = "Surname can not be empty")
    @Pattern(message = "Surname should have one first capital letter and consist only of letters",
            regexp = "[A-Z][a-z]+")
    private String surname;

    @NotEmpty(message = "Name can not be empty")
    @Pattern(message = "Name should have one first capital letter and consist only of letters",
            regexp = "[A-Z][a-z]+")
    private String name;

    private String patronymic;

    @Min(value = 10, message = "User should be older then 10")
    private int age;

    @Min(value = 0, message = "Salary should be positive")
    private double salary = 100.00;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(message = "Job should consist only of letters",
            regexp = "[A-Za-z]+")
    private String job = "unemployed";


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getEmail() {
        return email;
    }

    public String getJob() {
        return job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "User: " +
                "surname= " + name +
                ", name= " + surname +
                ", patronymic= " + patronymic +
                ", age= " + age +
                ", salary= " + salary +
                ", email= " + email +
                ", job= " + job;
    }
}
