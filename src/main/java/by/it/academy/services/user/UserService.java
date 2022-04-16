package by.it.academy.services.user;

import java.util.List;
import java.util.Optional;

public interface UserService<T> {
    boolean create(T t);

    Optional<T> get(T t);

    boolean update(T t, T newT);

    boolean delete(T t);

    List<T> readAll();
}
