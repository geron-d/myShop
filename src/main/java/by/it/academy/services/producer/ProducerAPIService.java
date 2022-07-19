package by.it.academy.services.producer;

import by.it.academy.contants.Order;
import by.it.academy.entities.Producer;
import by.it.academy.repositories.hiber.producer.ProducerAPIRepository;
import by.it.academy.repositories.hiber.producer.ProducerRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the by.it.academy.sessions.ProducerService interface.
 *
 * @author Maxim Zhevnov
 */
public class ProducerAPIService implements ProducerService<Producer> {
    private final Session session;
    private final ProducerRepository<Producer> producerRepository;

    /**
     * Creates a new {@link ProducerAPIRepository} to manage objects of the given {@link Session}
     * and {@link ProducerRepository}.
     *
     * @param session            must not be {@literal null}.
     * @param producerRepository must not be {@literal null}.
     */
    public ProducerAPIService(Session session, ProducerRepository<Producer> producerRepository) {
        this.session = session;
        this.producerRepository = producerRepository;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.sessions.ProducerService#getProducerById
     */
    @Override
    public Optional<Producer> getProducerById(int id) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Producer> producer = producerRepository.getProducerById(id);

        transaction.commit();

        return producer;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.sessions.ProducerService#saveProducer
     */
    @Override
    public Optional<Producer> saveProducer(Producer producer) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Producer> optionalProducer = producerRepository.saveProducer(producer);

        transaction.commit();

        return optionalProducer;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.sessions.ProducerService#deleteProducer
     */
    @Override
    public void deleteProducer(Producer producer) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        producerRepository.deleteProducer(producer);

        transaction.commit();
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.sessions.ProducerService#getProducerByName
     */
    @Override
    public Optional<Producer> getProducerByName(String producerName) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Producer> optionalProducer = producerRepository.getProducerByName(producerName);

        transaction.commit();

        return optionalProducer;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.sessions.ProducerService#getAllProducers
     */
    @Override
    public List<Producer> getAllProducers(Order order) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        List<Producer> producers = producerRepository.getAllProducers(order);

        transaction.commit();

        return producers;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.sessions.ProducerService#getProducerByValuableFields
     */
    @Override
    public Optional<Producer> getProducerByValuableFields(Producer producer) {
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Optional<Producer> optionalProducer = producerRepository.getProducerByValuableFields(producer);

        transaction.commit();

        return optionalProducer;
    }
}
