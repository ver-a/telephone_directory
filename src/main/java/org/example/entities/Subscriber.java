package org.example.entities;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Subscriber {
    private Long id;
    private String firstName;
    private String lastName;
    private List<String> phoneNumbers = new ArrayList<>();

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
/*
//пример сущности
public class Subscriber {
    private int id;
    private String name;
    public Subscriber() {
    }
    public Subscriber(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\n Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "} \n";
    }
}
*/