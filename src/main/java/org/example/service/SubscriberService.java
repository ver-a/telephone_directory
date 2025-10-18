package org.example.service;

import org.example.entities.Subscriber;
import java.util.List;
import java.util.Optional;

public interface SubscriberService {
    Subscriber create(Subscriber subscriber);
    void delete(Long id);
    Optional<Subscriber> findById(Long id);
    List<Subscriber> findAll();
    List<Subscriber> findByLastName(String lastName);
    Optional<Subscriber> findByPhoneNumber(String phoneNumber);
    List<Subscriber> findAllSorted();
    Subscriber addPhoneNumber(Long subscriberId, String phoneNumber);
    Subscriber removePhoneNumber(Long subscriberId, String phoneNumber);
}