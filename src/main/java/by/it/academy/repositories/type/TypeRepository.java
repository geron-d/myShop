package by.it.academy.repositories.type;

import by.it.academy.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for generic operations on a repository for a type of product.
 *
 * @author Maxim Zhevnov
 */
public interface TypeRepository extends JpaRepository<Type, Long> {

    /**
     * Return a type by its name.
     *
     * @return the type with the given name or throw NoSUchElementException if type not found.
     */
    Optional<Type> findTypeByName(String name);

    /**
     * Search types by given search word in their names.
     *
     * @return types by given search word.
     */
    List<Type> searchAllByNameContains(String search);

}
