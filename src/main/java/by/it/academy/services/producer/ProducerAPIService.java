package by.it.academy.services.producer;

import by.it.academy.contants.Order;
import by.it.academy.entities.Producer;
import by.it.academy.repositories.hiber.producer.ProducerRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class ProducerAPIService implements ProducerService<Producer> {
    private final Session session;
    private final ProducerRepository<Producer> producerRepository;

    public ProducerAPIService(Session session, ProducerRepository<Producer> producerRepository) {
        this.session = session;
        this.producerRepository = producerRepository;
    }

    @Override
    public Optional<Producer> getProducerById(int id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Producer> producer = producerRepository.getProducerById(id);

        transaction.commit();

        return producer;
    }

    @Override
    public Optional<Producer> saveProducer(Producer producer) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Producer> optionalProducer = producerRepository.saveProducer(producer);

        transaction.commit();

        return optionalProducer;
    }

    @Override
    public void deleteProducer(Producer producer) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        producerRepository.deleteProducer(producer);

        transaction.commit();
    }

    @Override
    public Optional<Producer> getProducerByName(String producerName) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Producer> optionalProducer = producerRepository.getProducerByName(producerName);

        transaction.commit();

        return optionalProducer;
    }

    @Override
    public List<Producer> getAllProducers(Order order) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<Producer> producers = producerRepository.getAllProducers(order);

        transaction.commit();

        return producers;
    }

    @Override
    public Optional<Producer> getProducerByValuableFields(Producer producer) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Producer> optionalProducer = producerRepository.getProducerByValuableFields(producer);

        transaction.commit();

        return optionalProducer;
    }
}
