package by.it.academy.repositories.producer;

import by.it.academy.entities.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Optional<Producer> findProducerByName(String name);

    List<Producer> searchAllByNameContains(String name);

}
