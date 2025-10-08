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
        System.out.println("\n--- Найти по фамилии ---");
        String lastName = readString("Введите фамилию: ");

        List<Subscriber> subscribers = subscriberService.findByLastName(lastName);

        if (subscribers.isEmpty()) {
            System.out.println("Нет абонента с такой фамилией '" + lastName);
            return;
        }

        System.out.println("Найден " + subscribers.size() + " абонент(ы):");
        for (Subscriber subscriber : subscribers) {
            System.out.printf("- %s %s - Телефон: %s%n",
                    subscriber.getFirstName(),
                    subscriber.getLastName(),
                    String.join(", ", subscriber.getPhoneNumbers()));
        }
    }

    @Override
    public String getTitle() {
        return "Найти по фамилии";
    }
}