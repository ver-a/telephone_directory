package org.example.cli.Get;

import org.example.cli.Command;
import org.example.entities.Subscriber;
import org.example.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class FindByLastName implements Command {
    private final SubscriberService subscriberService;
    private final Scanner scanner;

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    @Override
    public void execute() {
        System.out.println("\n--- FIND BY LAST NAME ---");
        String lastName = readString("Enter last name: ");

        List<Subscriber> subscribers = subscriberService.findByLastName(lastName);

        if (subscribers.isEmpty()) {
            System.out.println("No subscribers with last name '" + lastName + "' found.");
            return;
        }

        System.out.println("Found " + subscribers.size() + " subscriber(s):");
        for (Subscriber subscriber : subscribers) {
            System.out.printf("- %s %s - Phones: %s%n",
                    subscriber.getFirstName(),
                    subscriber.getLastName(),
                    String.join(", ", subscriber.getPhoneNumbers()));
        }
    }

    @Override
    public String getTitle() {
        return "Find by last name";
    }
}