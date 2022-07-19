package by.it.academy.services.type;

import by.it.academy.contants.Order;

import java.util.List;
import java.util.Optional;

public interface TypeService<T> {

    Optional<T> getTypeById(int id);

    Optional<T> saveType(T t);

    void deleteType(T t);

    Optional<T> getTypeByName(String typeName);

    List<T> getAllTypes(Order order);

    Optional<T> getTypeByValuableFields(T t);
}
