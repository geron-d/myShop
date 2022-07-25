package by.it.academy.services.producer;

import by.it.academy.dtos.requests.producer.ProducerDTO;
import by.it.academy.entities.Producer;

import java.util.List;

/**
 * Interface for generic operations on a service for a producer of product.
 *
 * @author Maxim Zhevnov
 */
public interface ProducerService {

    /**
     * Return a producer by its id.
     *
     * @return the producer with the given id or throw NoSUchElementException if producer not found.
     */
    Producer findProducer(Long id);

    /**
     * Save a producer by given CategoryDTO.
     *
     * @return the id of saved producer. Throw SQLIntegrityConstraintViolationException when producer
     * with such name exists.
     */
    Long createProducer(ProducerDTO request);

    /**
     * Update a producer by given id and CategoryDTO.
     *
     * @return the id of updated producer. Throw NoSUchElementException if producer not found.
     * Throw SQLIntegrityConstraintViolationException when producer with such name exists.
     */
    Long updateProducer(Long id, ProducerDTO dto);

    /**
     * Delete a producer by given id.
     *
     * Throw NoSUchElementException if producer none found.
     */
    void deleteProducer(Long id);

    /**
     * Find producer by its name.
     *
     * @return the producer with the given name. Throw NoSUchElementException if producer none found.
     */
    Producer findProducer(String name);

    /**
     * Find all producers.
     *
     * @return all producers.
     */
    List<Producer> findProducers();

    /**
     * Search producers by given search word in their names.
     *
     * @return producers by given search word.
     */
    List<Producer> searchProducers(String search);

    /**
     * Find producers by given list of their names.
     *
     * @return producers by given list of their names.
     */
    List<Producer> findProducers(List<String> producerNames);

}
