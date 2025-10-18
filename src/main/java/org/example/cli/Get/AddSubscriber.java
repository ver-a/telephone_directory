package org.example.cli.Get;

import org.example.cli.Command;
import org.example.entities.Subscriber;
import org.example.service.SubscriberService;
import java.util.ArrayList;
import java.util.Scanner;


public class AddSubscriber implements Command {
    private final SubscriberService subscriberService;
    private final Scanner scanner;

    public AddSubscriber(SubscriberService subscriberService, Scanner scanner) {
        this.subscriberService = subscriberService;
        this.scanner = scanner;
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    @Override
    public void execute() {
        System.out.println("\n--- Добавить нового абонента ---");

        String firstName = readString( "Введите имя: ");
        String lastName = readString("Введите фамилию: ");

        Subscriber subscriber = new Subscriber();
        subscriber.setFirstName(firstName);
        subscriber.setLastName(lastName);
        subscriber.setPhoneNumbers(new ArrayList<>());

        // Добавление телефонных номеров (до 3-х)
        System.out.println("Добавте номер телефона (max 3, оставьте пустым если нет):");
        for (int i = 0; i < 3; i++) {
            String phone = readString("Телефон " + (i + 1) + ": ");
            if (phone.isEmpty()) break;
            subscriber.addPhoneNumber(phone);
        }

        try {
            Subscriber saved = subscriberService.create(subscriber);
            System.out.println("Абонент успешно добавлен с номером: " + saved.getId());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public String getTitle() {
        return "Добавить нового абонента";
    }
}