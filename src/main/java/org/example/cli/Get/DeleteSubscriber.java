package org.example.cli.Get;

import org.example.cli.Command;
import org.example.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import java.util.Scanner;

@RequiredArgsConstructor
public class DeleteSubscriber implements Command {
    private final SubscriberService subscriberService;
    private final Scanner scanner;

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
        System.out.println("\n--- DELETE SUBSCRIBER ---");
        Long id = readLong("Enter subscriber ID to delete: ");

        Optional<?> subscriber = subscriberService.findById(id);
        if (subscriber.isEmpty()) {
            System.out.println("Subscriber with ID " + id + " not found.");
            return;
        }

        System.out.print("Are you sure you want to delete this subscriber? (y/n): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y") || confirmation.equals("yes")) {
            subscriberService.delete(id);
            System.out.println("Subscriber deleted successfully.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    @Override
    public String getTitle() {
        return "Delete subscriber";
    }
}