package by.it.academy.repositories.hiber.producer;

import by.it.academy.contants.Order;

import java.util.List;
import java.util.Optional;

public interface ProducerRepository<T> {

    Optional<T> getProducerById(int id);

    Optional<T> saveProducer(T t);

    void deleteProducer(T t);

    Optional<T> getProducerByName(String producerName);

    List<T> getAllProducers(Order order);

    Optional<T> getProducerByValuableFields(T t);
}
