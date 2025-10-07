package org.example;

// Импорты команд из папки Get
import org.example.cli.Get.*;

import org.example.cli.Command;
import org.example.cli.Menu;
import org.example.repository.SubscriberRepository;
import org.example.repository.SubscriberRepositoryInMemImpl;
import org.example.service.SubscriberService;
import org.example.service.SubscriberServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Инициализация зависимостей
        SubscriberRepository repository = new SubscriberRepositoryInMemImpl();
        SubscriberService service = new SubscriberServiceImpl(repository);
        Scanner scanner = new Scanner(System.in);

        // Создание команд меню
        List<Command> commands = Arrays.asList(
                new AddSubscriber(service, scanner),
                new GetAllSubscriber(service),
                new GetSubscriberById(service, scanner),
                new FindByLastName(service, scanner),
                new FindByPhone(service, scanner),
                new DeleteSubscriber(service, scanner)
        );

        // Запуск меню
        Menu menu = new Menu(commands, scanner);
        menu.show();

        scanner.close();
    }
}