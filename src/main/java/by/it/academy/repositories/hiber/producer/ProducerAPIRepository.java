package by.it.academy.repositories.hiber.producer;

import by.it.academy.contants.HQL;
import by.it.academy.contants.Order;
import by.it.academy.entities.Producer;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the by.it.academy.repositories.hiber.ProducerRepository interface.
 *
 * @author Maxim Zhevnov
 */
public class ProducerAPIRepository implements ProducerRepository<Producer> {
    Logger log = Logger.getLogger(ProducerAPIRepository.class);
    private final Session session;

    /**
     * Creates a new {@link ProducerAPIRepository} to manage objects of the given session.
     *
     * @param session must not be {@literal null}.
     */
    public ProducerAPIRepository(Session session) {
        this.session = session;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.ProducerRepository#getProducerById
     */
    @Override
    public Optional<Producer> getProducerById(int id) {
        Optional<Producer> producer = Optional.empty();
        try {
            producer = Optional.of(session.get(Producer.class, id));
            log.info("ProducerAPIRepository - method: getProducerById: " + producer);
        } catch (NullPointerException e) {
            log.info("ProducerAPIRepository - method: getProducerById: " + e);
            return producer;
        }
        return producer;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.ProducerRepository#saveProducer
     */
    @Override
    public Optional<Producer> saveProducer(Producer producer) {
        Optional<Producer> optionalProducer = Optional.ofNullable(producer);

        if (optionalProducer.isPresent()) {
            if ((Objects.isNull(optionalProducer.get().getId())
                    || !getProducerById(optionalProducer.get().getId()).isPresent())
                    && !getProducerByValuableFields(optionalProducer.get()).isPresent()) {
                session.persist(optionalProducer.get());
                log.info("ProducerAPIRepository - method: saveProducer: persist: " + optionalProducer);
                return getProducerByValuableFields(optionalProducer.get());
            } else if (!getProducerByValuableFields(optionalProducer.get()).isPresent()) {
                session.merge(optionalProducer.get());
                log.info("ProducerAPIRepository - method: saveProducer: merge: " + optionalProducer);
                return getProducerByValuableFields(optionalProducer.get());
            } else {
                log.info("ProducerAPIRepository - method: saveProducer: producer already exist");
                return getProducerByValuableFields(optionalProducer.get());
            }
        }

        return optionalProducer;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.ProducerRepository#deleteProducer
     */
    @Override
    public void deleteProducer(Producer producer) {
        Transaction transaction = session.getTransaction();

        Optional<Producer> optionalProducer = Optional.ofNullable(producer);

        if (optionalProducer.isPresent() && Objects.nonNull(optionalProducer.get().getId())) {
            session.delete(optionalProducer.get());
            log.info("ProducerAPIRepository - method: deleteProducer: remove: " + producer);
        } else {
            log.info("ProducerAPIRepository - method: deleteProducer: producer doesn't exist");
        }
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.ProducerRepository#getProducerByName
     */
    @Override
    public Optional<Producer> getProducerByName(String producerName) {
        Optional<Producer> producer = Optional.empty();
        try {
            TypedQuery<Producer> query = session.createQuery(HQL.GET_PRODUCER_BY_NAME_HQL, Producer.class);
            query.setParameter("producer", producerName);
            producer = Optional.of(query.getSingleResult());
            log.info("ProducerAPIRepository - method: getProducerByProducer: " + producer);
        } catch (NoResultException e) {
            log.info("ProducerAPIRepository - method: getProducerByProducer: " + e);
            return producer;
        }
        return producer;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.ProducerRepository#getAllProducers
     */
    @Override
    public List<Producer> getAllProducers(Order order) {
        List<Producer> producers;

        TypedQuery<Producer> query = session.createQuery(HQL.GET_ALL_PRODUCERS_HQL, Producer.class);
        TypedQuery<Producer> queryDesc = session.createQuery(HQL.GET_ALL_PRODUCERS_DESC_HQL, Producer.class);
        producers = order.equals(Order.ASC)
                ? query.getResultList()
                : queryDesc.getResultList();
        log.info("ProducerAPIRepository - method: getAllProducers: " + producers);

        return producers;
    }

    /*
     * (non-Javadoc)
     * @see by.it.academy.repositories.hiber.ProducerRepository#getProducerByValuableFields
     */
    @Override
    public Optional<Producer> getProducerByValuableFields(Producer producer) {
        Optional<Producer> optionalProducer = Optional.ofNullable(producer);
        if (optionalProducer.isPresent()) {
            return getProducerByName(producer.getProducer());
        } else {
            return optionalProducer;
        }
    }

}
