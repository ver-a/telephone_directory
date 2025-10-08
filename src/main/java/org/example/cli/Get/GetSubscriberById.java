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


    private Long readLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                Long number = scanner.nextLong();
                scanner.nextLine(); // важно!
                return number;
            } catch (Exception e) {
                System.out.println("НЕверный номер.Повторите попытку");
                scanner.nextLine();
            }
        }
    }

    @Override
    public void execute() {
        System.out.println("\n--- Найти абоненты по ID ---");
        Long id = readLong("Введите ID абонента: ");

        Optional<Subscriber> subscriber = subscriberService.findById(id);

        if (subscriber.isPresent()) {
            Subscriber s = subscriber.get();
            System.out.printf("Найден: %s %s - телефон: %s%n",
                    s.getFirstName(),
                    s.getLastName(),
                    String.join(", ", s.getPhoneNumbers()));
        } else {
            System.out.println("Абонент с ID " + id + " не найден");
        }
    }

    @Override
    public String getTitle() {
        return "Найти абонента по ID";
    }
}
