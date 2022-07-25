package by.it.academy.repositories.producer;

import by.it.academy.entities.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for generic operations on a repository for a producer of product.
 *
 * @author Maxim Zhevnov
 */
public interface ProducerRepository extends JpaRepository<Producer, Long> {

    /**
     * Return a producer by its name.
     *
     * @return the producer with the given name or throw NoSUchElementException if producer not found.
     */
    Optional<Producer> findProducerByName(String name);

    /**
     * Search producers by given search word in their names.
     *
     * @return producers by given search word.
     */
    List<Producer> searchAllByNameContains(String name);

}
