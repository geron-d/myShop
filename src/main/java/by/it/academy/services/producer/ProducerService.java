package by.it.academy.services.producer;

import by.it.academy.contants.Order;

import java.util.List;
import java.util.Optional;

/**
 * Interface for generic operations on a service for a specific type of producer of product.
 *
 * @param <T> the type of the entity to handle
 * @author Maxim Zhevnov
 */
public interface ProducerService<T> {

    /**
     * Retrieves a producer by its id.
     *
     * @param id must not be less than 1.
     * @return the producer with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getProducerById(int id);

    /**
     * Saves a given producer. Use the returned instance for further operations as the save operation might have
     * changed the producer instance completely.
     *
     * @param t must not be {@literal null}.
     * @return the saved producer; will never be {@literal null}.
     */
    Optional<T> saveProducer(T t);

    /**
     * Deletes a given producer.
     *
     * @param t must not be {@literal null}.
     */
    void deleteProducer(T t);

    /**
     * Retrieves a producer by its name.
     *
     * @param producerName must not be {@literal null}.
     * @return the producer with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getProducerByName(String producerName);

    /**
     * Returns all instances of the producer in straight or reverse order.
     *
     * @param order must be ASC or DESC.
     * @return all producers
     */
    List<T> getAllProducers(Order order);

    /**
     * Retrieves a producer by its valuable fields.
     *
     * @param t must not be {@literal null}.
     * @return the producer with the given valuable fields or {@literal Optional#empty()} if none found.
     */
    Optional<T> getProducerByValuableFields(T t);
}
