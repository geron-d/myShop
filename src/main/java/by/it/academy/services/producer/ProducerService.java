package by.it.academy.services.producer;

import by.it.academy.contants.Order;

import java.util.List;
import java.util.Optional;

public interface ProducerService<T> {

    Optional<T> getProducerById(int id);

    Optional<T> saveProducer(T t);

    void deleteProducer(T t);

    Optional<T> getProducerByName(String producerName);

    List<T> getAllProducers(Order order);

    Optional<T> getProducerByValuableFields(T t);
}
