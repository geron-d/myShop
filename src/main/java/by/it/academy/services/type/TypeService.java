package by.it.academy.services.type;

import by.it.academy.dtos.requests.type.TypeDTO;
import by.it.academy.entities.Type;

import java.util.List;

/**
 * Interface for generic operations on a service for a type of product.
 *
 * @author Maxim Zhevnov
 */
public interface TypeService {

    /**
     * Return a type by its id.
     *
     * @return the type with the given id or throw NoSUchElementException if type not found.
     */
    Type findType(Long id);

    /**
     * Save a type by given TypeDTO.
     *
     * @return the id of saved type. Throw SQLIntegrityConstraintViolationException when type
     * with such name exists.
     */
    Long createType(TypeDTO dto);

    /**
     * Update a type by given id and TypeDTO.
     *
     * @return the id of updated type. Throw NoSUchElementException if type not found.
     * Throw SQLIntegrityConstraintViolationException when type with such name exists.
     */
    Long updateType(Long id, TypeDTO dto);

    /**
     * Delete a type by given id.
     *
     * Throw NoSUchElementException if type none found.
     */
    void deleteType(Long id);

    /**
     * Find type by its name.
     *
     * @return the type with the given name. Throw NoSUchElementException if type none found.
     */
    Type findType(String name);

    /**
     * Find all types.
     *
     * @return all types.
     */
    List<Type> findTypes();

    /**
     * Search types by given search word in their names.
     *
     * @return types by given search word.
     */
    List<Type> searchTypes(String search);

    /**
     * Find types by given list of their names.
     *
     * @return types by given list of their names.
     */
    List<Type> findTypes(List<String> typeNames);

}
