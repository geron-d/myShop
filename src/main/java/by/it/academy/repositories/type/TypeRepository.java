package by.it.academy.repositories.type;

import by.it.academy.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {

    Optional<Type> findTypeByName(String name);

    List<Type> searchAllByNameContains(String search);

}
