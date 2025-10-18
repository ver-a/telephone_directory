package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Subscriber {
    private Long id;
    private String firstName;
    private String lastName;
    private List<String> phoneNumbers = new ArrayList<>();

    // Конструкторы
    public Subscriber() {}

    public Subscriber(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public List<String> getPhoneNumbers() { return phoneNumbers; }
    public void setPhoneNumbers(List<String> phoneNumbers) { this.phoneNumbers = phoneNumbers; }

    public void addPhoneNumber(String number) {
        if (phoneNumbers.size() < 3) {
            phoneNumbers.add(number);
        } else {
            throw new IllegalStateException("Cannot have more than 3 phone numbers");
        }
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }
}