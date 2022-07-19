package by.it.academy.repositories.hiber.type;

import by.it.academy.contants.Order;

import java.util.List;
import java.util.Optional;

public interface TypeRepository<T> {

    Optional<T> getTypeById(int id);

    Optional<T> saveType(T t);

    void deleteType(T t);

    Optional<T> getTypeByName(String typeName);

    List<T> getAllTypes(Order order);

    Optional<T> getTypeByValuableFields(T t);
}
