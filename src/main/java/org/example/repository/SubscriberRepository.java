package org.example.repository;

import org.example.entities.Subscriber;
import java.util.List;
import java.util.Optional;

public interface SubscriberRepository extends Repository<Subscriber, Long> {
    List<Subscriber> findByLastName(String lastName);
    Optional<Subscriber> findByPhoneNumber(String phoneNumber);
    List<Subscriber> findAllSorted();
}
