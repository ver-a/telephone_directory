package org.example.cli.Get;


import org.example.cli.Command;
import org.example.entities.Subscriber;
import org.example.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import java.util.Scanner;

@RequiredArgsConstructor
public class GetSubscriberById implements Command {
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
        System.out.println("\n--- FIND SUBSCRIBER BY ID ---");
        Long id = readLong("Enter subscriber ID: ");

        Optional<Subscriber> subscriber = subscriberService.findById(id);

        if (subscriber.isPresent()) {
            Subscriber s = subscriber.get();
            System.out.printf("Found: %s %s - Phones: %s%n",
                    s.getFirstName(),
                    s.getLastName(),
                    String.join(", ", s.getPhoneNumbers()));
        } else {
            System.out.println("Subscriber with ID " + id + " not found.");
        }
    }

    @Override
    public String getTitle() {
        return "Find subscriber by ID";
    }
}

/*
import org.example.cli.Command;

import java.util.Scanner;

public class GetSubcriberById implements Command {
    private Scanner scn = new Scanner(System.in);
    // поле с сервисом

    public GetSubcriberById() {
        //объявление сервиса
    }

    @Override
    public void execute() {
        //вызов метода из сервиса
    }

    @Override
    public String getCommandName() {
        return "Get department by id";
    }
}
*/