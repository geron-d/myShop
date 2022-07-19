package by.it.academy.repositories.hiber.type;

import by.it.academy.contants.Order;

import java.util.List;
import java.util.Optional;

/**
 * Interface for generic operations on a repository for a specific type of type of product.
 *
 * @param <T> the type of the entity to handle
 * @author Maxim Zhevnov
 */
public interface TypeRepository<T> {

    /**
     * Retrieves a type by its id.
     *
     * @param id must not be less than 1.
     * @return the type with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getTypeById(int id);

    /**
     * Saves a given type. Use the returned instance for further operations as the save operation might have
     * changed the type instance completely.
     *
     * @param t must not be {@literal null}.
     * @return the saved type; will never be {@literal null}.
     */
    Optional<T> saveType(T t);

    /**
     * Deletes a given type.
     *
     * @param t must not be {@literal null}.
     */
    void deleteType(T t);

    /**
     * Retrieves a type by its name.
     *
     * @param typeName must not be {@literal null}.
     * @return the type with the given id or {@literal Optional#empty()} if none found.
     */
    Optional<T> getTypeByName(String typeName);

    /**
     * Returns all instances of the type in straight or reverse order.
     *
     * @param order must be ASC or DESC.
     * @return all types
     */
    List<T> getAllTypes(Order order);

    /**
     * Retrieves a type by its valuable fields.
     *
     * @param t must not be {@literal null}.
     * @return the type with the given valuable fields or {@literal Optional#empty()} if none found.
     */
    Optional<T> getTypeByValuableFields(T t);
}
