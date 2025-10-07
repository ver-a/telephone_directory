package org.example.service;

import org.example.entities.Subscriber;
import java.util.List;
import org.example.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {
    private final SubscriberRepository repository;

    @Override
    public Subscriber create(Subscriber subscriber) {
        validateSubscriber(subscriber);
        return repository.save(subscriber);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Optional<Subscriber> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Subscriber> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Subscriber> findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    @Override
    public Optional<Subscriber> findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Subscriber> findAllSorted() {
        return repository.findAllSorted();
    }

    @Override
    public Subscriber addPhoneNumber(Long subscriberId, String phoneNumber) {
        Subscriber subscriber = repository.findById(subscriberId)
                .orElseThrow(() -> new IllegalArgumentException("Subscriber not found"));
        subscriber.addPhoneNumber(phoneNumber);
        return repository.save(subscriber);
    }

    @Override
    public Subscriber removePhoneNumber(Long subscriberId, String phoneNumber) {
        Subscriber subscriber = repository.findById(subscriberId)
                .orElseThrow(() -> new IllegalArgumentException("Subscriber not found"));
        subscriber.getPhoneNumbers().remove(phoneNumber);
        return repository.save(subscriber);
    }

    private void validateSubscriber(Subscriber subscriber) {
        if (subscriber.getFirstName() == null || subscriber.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (subscriber.getLastName() == null || subscriber.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (subscriber.getPhoneNumbers().size() > 3) {
            throw new IllegalArgumentException("Cannot have more than 3 phone numbers");
        }
    }
}

/*
public class SubscriberServiceImpl implements SubscriberService {

    //
    private static SubscriberService obj;

    //поле с используемыми репозитроями

    private SubscriberServiceImpl() {
        //объявление репозиториев
    }

    //для синглтон-паттерна
    public static SubscriberService getInstance() {
        if (obj == null) {
            obj = new SubscriberServiceImpl();
        }
        return obj;
    }

    private Subscriber setInfoForNewDepartment(String[] parameters) {
        //метод обработки входных данных и создания экземпляра сущности
        return null;
    }

    @Override
    public Subscriber getById(int id) {
        //обращение к репозиторию
        return null;
    }

    @Override
    public List<Subscriber> getAll() {
        //обращение к репозиторию
        return null;
    }
}
*/