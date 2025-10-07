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
        System.out.println("\n--- ALL SUBSCRIBERS (SORTED) ---");
        List<Subscriber> subscribers = subscriberService.findAllSorted();

        if (subscribers.isEmpty()) {
            System.out.println("No subscribers found.");
            return;
        }

        for (int i = 0; i < subscribers.size(); i++) {
            Subscriber subscriber = subscribers.get(i);
            System.out.printf("%d. %s %s - Phones: %s%n",
                    i + 1,
                    subscriber.getFirstName(),
                    subscriber.getLastName(),
                    String.join(", ", subscriber.getPhoneNumbers()));
        }
    }

    @Override
    public String getTitle() {
        return "Show all subscribers";
    }
}

/*import org.example.cli.Command;

public class GetAllSubscriber implements Command {
    // поле с сервисом

    public GetAllSubscriber() {
        //объявление сервиса
    }

    @Override
    public void execute() {
        //вызов метода из сервиса
    }

    @Override
    public String getCommandName() {
        return "Get all departments";
    }
}
*/