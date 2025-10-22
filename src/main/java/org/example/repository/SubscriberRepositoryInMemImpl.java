package org.example.repository;

import org.example.entities.Subscriber;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


public class SubscriberRepositoryInMemImpl implements SubscriberRepository {
    private final Map<Long, Subscriber> subscribers = new HashMap<>();
    private long nextId = 1;

    @Override
    public Subscriber save(Subscriber subscriber) {
        if (subscriber.getId() == null) {
            subscriber.setId(nextId);
            nextId++;
        }
        subscribers.put(subscriber.getId(), subscriber);
        return subscriber;
    }

    @Override
    public void delete(Long id) {
        subscribers.remove(id);
    }

    @Override
    public Optional<Subscriber> findById(Long id) {
        Subscriber subscriber = subscribers.get(id);
        return Optional.ofNullable(subscriber);
    }

    @Override
    public List<Subscriber> findAll() {
        return new ArrayList<>(subscribers.values());
    }

    @Override
    public List<Subscriber> findByLastName(String lastName) {
        List<Subscriber> result = new ArrayList<>();
        for (Subscriber subscriber : subscribers.values()) {
            if (subscriber.getLastName().equalsIgnoreCase(lastName)) {
                result.add(subscriber);
            }
        }
        return result;
    }

    @Override
    public Optional<Subscriber> findByPhoneNumber(String phoneNumber) {
        for (Subscriber subscriber : subscribers.values()) {
            if (subscriber.getPhoneNumbers().contains(phoneNumber)) {
                return Optional.of(subscriber);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Subscriber> findAllSorted() {
        List<Subscriber> sorted = new ArrayList<>(subscribers.values());

        Collections.sort(sorted, new Comparator<Subscriber>() {
            @Override
            public int compare(Subscriber s1, Subscriber s2) {
                // Сравниваем по полному имени
                int nameCompare = s1.getFullName().compareTo(s2.getFullName());
                if (nameCompare != 0) return nameCompare;

                // При одинаковых именах сравниваем по телефонам
                List<String> phones1 = s1.getPhoneNumbers();
                List<String> phones2 = s2.getPhoneNumbers();
                return phones1.toString().compareTo(phones2.toString());
            }
        });

        return sorted;
    }
}


/*
import java.util.List;
public class SubscriberRepositoryInMemImpl implements SubscriberRepository {
    //для синглтон-паттерна
    private static SubscriberRepositoryInMemImpl obj;

    // место для хранения ваших сущностей (список, сет, мапа)

    private SubscriberRepositoryInMemImpl() {
        
    }

    //для синглтон-паттерна
    public static SubscriberRepository getInstance() {
        if (obj == null) {
            obj = new SubscriberRepositoryInMemImpl();
        }
        return obj;
    }

    @Override
    public Subscriber getDepartmentByName(String name) {
        //логика обращения к полю, хранящему ваши объекты
        return null;
    }

    @Override
    public void add(Subscriber object) {
        //логика обращения к полю, хранящему ваши объекты
    }

    @Override
    public void removeAll() {
        //логика обращения к полю, хранящему ваши объекты
    }
    
    @Override
    public void update(int id, Subscriber newObject) {
        //логика обращения к полю, хранящему ваши объекты
    }

    @Override
    public Subscriber getById(int id) {
        //логика обращения к полю, хранящему ваши объекты
        return null;
    }


    @Override
    public List<Subscriber> getAll() {
        //логика обращения к полю, хранящему ваши объекты
        return null;
    }
}
*/
