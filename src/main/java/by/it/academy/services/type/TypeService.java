package by.it.academy.services.type;

import by.it.academy.dtos.requests.type.TypeDTO;

import java.util.List;

public interface TypeService<T> {

    T findType(Long id);

    Long createType(TypeDTO request);

    Long updateType(Long id, TypeDTO dto);

    void deleteType(Long id);

    T findType(String name);

    List<T> findTypes();

    List<T> searchTypes(String search);

    List<T> findTypes(List<String> typeNames);

}
