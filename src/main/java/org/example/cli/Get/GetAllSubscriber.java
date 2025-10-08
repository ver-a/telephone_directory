package org.example.cli.Get;


import org.example.cli.Command;
import org.example.entities.Subscriber;
import org.example.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class GetAllSubscriber implements Command {
    private final SubscriberService subscriberService;

    @Override
    public void execute() {
        System.out.println("\n--- Все абоненты ---");
        List<Subscriber> subscribers = subscriberService.findAllSorted();

        if (subscribers.isEmpty()) {
            System.out.println("Абонентов нет");
            return;
        }

        for (int i = 0; i < subscribers.size(); i++) {
            Subscriber subscriber = subscribers.get(i);
            System.out.printf("%d. %s %s - Телефон: %s%n",
                    i + 1,
                    subscriber.getFirstName(),
                    subscriber.getLastName(),
                    String.join(", ", subscriber.getPhoneNumbers()));
        }
    }

    @Override
    public String getTitle() {
        return "Показать всех абонентов";
    }
}
