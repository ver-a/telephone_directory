package org.example.cli.Get;

import org.example.cli.Command;
import org.example.entities.Subscriber;
import org.example.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.Scanner;

@RequiredArgsConstructor
public class AddSubscriber implements Command {
    private final SubscriberService subscriberService;
    private final Scanner scanner;

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private Long readLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                Long number = scanner.nextLong();
                scanner.nextLine(); // важно!
                return number;
            } catch (Exception e) {
                System.out.println("Invalid number! Please try again.");
                scanner.nextLine();
            }
        }
    }
    @Override
    public void execute() {
        System.out.println("\n--- ADD NEW SUBSCRIBER ---");

        String firstName = readString( "Enter first name: ");
        String lastName = readString("Enter last name: ");

        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName(firstName);
        subscriber.setLastName(lastName);
        subscriber.setPhoneNumbers(new ArrayList<>());

        // Добавление телефонных номеров (до 3-х)
        System.out.println("Add phone numbers (max 3, leave empty to finish):");
        for (int i = 0; i < 3; i++) {
            String phone = readString("Phone " + (i + 1) + ": ");
            if (phone.isEmpty()) break;
            subscriber.addPhoneNumber(phone);
        }

        try {
            Subscriber saved = subscriberService.create(subscriber);
            System.out.println("Subscriber added successfully with ID: " + saved.getId());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getTitle() {
        return "Add new subscriber";
    }
}