package org.example.service;

import org.example.entities.Subscriber;
import org.example.repository.SubscriberRepository;
import java.util.List;
import java.util.Optional;

public class SubscriberServiceImpl implements SubscriberService {
    private final SubscriberRepository repository;


    public SubscriberServiceImpl(SubscriberRepository repository) {
        this.repository = repository;
    }

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
                .orElseThrow(() -> new IllegalArgumentException("Абонент не найден"));
        subscriber.addPhoneNumber(phoneNumber);
        return repository.save(subscriber);
    }

    @Override
    public Subscriber removePhoneNumber(Long subscriberId, String phoneNumber) {
        Subscriber subscriber = repository.findById(subscriberId)
                .orElseThrow(() -> new IllegalArgumentException("Абонент не найден"));
        subscriber.getPhoneNumbers().remove(phoneNumber);
        return repository.save(subscriber);
    }
    //ПРОВЕРЯЕТ бизнес-правила:
    private void validateSubscriber(Subscriber subscriber) {
        if (subscriber.getFirstName() == null || subscriber.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("Имя обязательно для заполнения");
        }
        if (subscriber.getLastName() == null || subscriber.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Фамилия обязательна для заполнения");
        }
        if (subscriber.getPhoneNumbers().size() > 3) {
            throw new IllegalArgumentException("Не может быть больше 3 телефонных номеров");
        }
    }
}