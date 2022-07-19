package by.it.academy.repositories.type;

import by.it.academy.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findTypeByName(String name);

    List<Type> searchAllByNameContains(String search);

}
