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
