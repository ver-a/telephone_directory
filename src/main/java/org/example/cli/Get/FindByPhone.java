package org.example.cli.Get;

import org.example.cli.Command;
import org.example.entities.Subscriber;
import org.example.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import java.util.Scanner;

@RequiredArgsConstructor
public class FindByPhone implements Command {
    private final SubscriberService subscriberService;
    private final Scanner scanner;

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    @Override
    public void execute() {
        System.out.println("\n--- FIND BY PHONE NUMBER ---");
        String phone = readString("Enter phone number: ");

        Optional<Subscriber> subscriber = subscriberService.findByPhoneNumber(phone);

        if (subscriber.isPresent()) {
            Subscriber s = subscriber.get();
            System.out.printf("Found: %s %s - Phones: %s%n",
                    s.getFirstName(),
                    s.getLastName(),
                    String.join(", ", s.getPhoneNumbers()));
        } else {
            System.out.println("No subscriber with phone number '" + phone + "' found.");
        }
    }

    @Override
    public String getTitle() {
        return "Find by phone number";
    }
}