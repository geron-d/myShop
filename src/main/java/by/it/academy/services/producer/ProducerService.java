package by.it.academy.services.producer;

import by.it.academy.dtos.requests.producer.ProducerDTO;

import java.util.List;

public interface ProducerService<T> {

    T findProducer(Long id);

    Long createProducer(ProducerDTO request);

    Long updateProducer(Long id, ProducerDTO dto);

    void deleteProducer(Long id);

    T findProducer(String name);

    List<T> findProducers();

    List<T> searchProducers(String search);

    List<T> findProducers(List<String> producerNames);

}
