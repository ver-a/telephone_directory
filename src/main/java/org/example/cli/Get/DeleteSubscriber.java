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
                System.out.println("неправильный номер. поробуйте еще раз");
                scanner.nextLine();
            }
        }
    }

    @Override
    public void execute() {
        System.out.println("\n--- Удалить абонента ---");
        Long id = readLong("Введите ID абонента для удаления: ");

        Optional<?> subscriber = subscriberService.findById(id);
        if (subscriber.isEmpty()) {
            System.out.println("Абонент с ID " + id + " не найден.");
            return;
        }

        System.out.print("Вы уверены что хотите удалить абонента ? (y/n): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y") || confirmation.equals("yes")) {
            subscriberService.delete(id);
            System.out.println("Абонент успешно удален.");
        } else {
            System.out.println("Отмена удаления.");
        }
    }

    @Override
    public String getTitle() {
        return "Удалить абонента";
    }
}