package org.example.repository;

import org.example.entities.Subscriber;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


public class SubscriberRepositoryInMemImpl implements SubscriberRepository {
    private final Map<Long, Subscriber> storage = new HashMap<>();
    private final AtomicLong currentId = new AtomicLong(1);

    @Override
    public Subscriber save(Subscriber subscriber) {
        if (subscriber.getId() == null) {
            subscriber.setId(currentId.getAndIncrement());
        }
        storage.put(subscriber.getId(), subscriber);
        return subscriber;
    }

    @Override
    public void delete(Long id) {
        storage.remove(id);
    }

    @Override
    public Optional<Subscriber> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Subscriber> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Subscriber> findByLastName(String lastName) {
        return storage.values().stream()
                .filter(s -> s.getLastName().equalsIgnoreCase(lastName))
                .toList();
    }

    @Override
    public Optional<Subscriber> findByPhoneNumber(String phoneNumber) {
        return storage.values().stream()
                .filter(s -> s.getPhoneNumbers().contains(phoneNumber))
                .findFirst();
    }

    @Override
    public List<Subscriber> findAllSorted() {
        return storage.values().stream()
                .sorted((s1, s2) -> {
                    int nameCompare = s1.getFullName().compareTo(s2.getFullName());
                    if (nameCompare != 0) return nameCompare;
                    return s1.getPhoneNumbers().toString()
                            .compareTo(s2.getPhoneNumbers().toString());
                })
                .toList();
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
