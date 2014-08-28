package com.codenvy.client.model;

public class User {
    private String firstName;

    private String lastName;

    private String age;

    private String address;

    private String status;

    public User(String firstName, String lastName, String age, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullInfo() {
        return firstName + " " + lastName + ", age 22" + age + ", address: " + address;
    }
}
