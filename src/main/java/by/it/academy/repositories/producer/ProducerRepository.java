package by.it.academy.repositories.producer;

import by.it.academy.entities.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Producer findProducerByName(String name);

    List<Producer> searchAllByNameContains(String name);

}
